#!/usr/bin/env python3
"""
Scan PROBLEM.md / Solution files and refresh progress stats in README.md.

Also applies scripts/progress_baseline.json for sheet items completed outside
this repo (e.g. Things to Know, STL) that have no problem folders here.

Usage (from repo root):
    python3 scripts/update_readme_stats.py
"""

from __future__ import annotations

import json
import re
from collections import Counter, defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
README = ROOT / "README.md"
BASELINE_PATH = Path(__file__).resolve().parent / "progress_baseline.json"
STRIVER_ROOT = ROOT / "striver-a2z"
EXTRA_ROOT = ROOT / "leetcode-extra"

# Striver A2Z sheet totals (https://takeuforward.org/dsa/strivers-a2z-sheet-learn-dsa-a-to-z)
A2Z_TOPICS = [
    ("01-basics", "Learn the Basics", 54),
    ("02-sorting", "Sorting Techniques", 7),
    ("03-arrays", "Arrays", 40),
    ("04-binary-search", "Binary Search", 32),
    ("05-strings", "Strings (Basic & Medium)", 15),
    ("06-linked-list", "Linked List", 31),
    ("07-recursion", "Recursion", 25),
    ("08-bit-manipulation", "Bit Manipulation", 18),
    ("09-stack-queues", "Stack & Queues", 30),
    ("10-sliding-window-two-pointer", "Sliding Window & Two Pointer", 12),
    ("11-heaps", "Heaps", 17),
    ("12-greedy", "Greedy Algorithms", 15),
    ("13-binary-trees", "Binary Trees", 38),
    ("14-bst", "Binary Search Trees", 16),
    ("15-graphs", "Graphs", 53),
    ("16-dp", "Dynamic Programming", 55),
    ("17-tries", "Tries", 7),
    ("18-strings-hard", "Strings (Hard)", 9),
]

A2Z_KEYS = {key for key, _, _ in A2Z_TOPICS}
A2Z_TOTAL = sum(t[2] for t in A2Z_TOPICS)
START = "<!-- STATS:START -->"
END = "<!-- STATS:END -->"

SOLUTION_RE = re.compile(
    r"^(Solution|solution|P\d+_.+)\.(java|py|c|cpp|cc|js|ts|go|rs|kt)$",
    re.IGNORECASE,
)
LANG_FROM_EXT = {
    "java": "java",
    "py": "python",
    "c": "c",
    "cpp": "cpp",
    "cc": "cpp",
    "js": "javascript",
    "ts": "typescript",
    "go": "go",
    "rs": "rust",
    "kt": "kotlin",
}
SKIP_DIR_NAMES = {".git", ".venv", "venv", "node_modules", "templates", "playground"}


def parse_frontmatter(text: str) -> dict[str, str]:
    if not text.startswith("---"):
        return {}
    end = text.find("\n---", 3)
    if end == -1:
        return {}
    meta: dict[str, str] = {}
    for line in text[3:end].splitlines():
        line = line.strip()
        if not line or ":" not in line:
            continue
        key, value = line.split(":", 1)
        meta[key.strip()] = value.strip().strip("[]").replace('"', "").replace("'", "")
    return meta


def load_baseline() -> dict[str, int]:
    """Return topic_key → external solved count (sum of subtopic credits)."""
    if not BASELINE_PATH.exists():
        return {}
    data = json.loads(BASELINE_PATH.read_text(encoding="utf-8"))
    out: dict[str, int] = {}
    for topic_key, topic_data in data.get("topics", {}).items():
        external = topic_data.get("external", {})
        if isinstance(external, dict):
            out[topic_key] = sum(int(v) for v in external.values())
        else:
            out[topic_key] = int(external)
    return out


def normalize_topic(raw: str, path: Path | None = None) -> str:
    raw = (raw or "").strip()
    if raw in A2Z_KEYS:
        return raw
    for key in A2Z_KEYS:
        if raw == key or raw.startswith(key) or key in raw:
            return key
    if path is not None:
        parts = path.parts
        for part in parts:
            if part in A2Z_KEYS:
                return part
    return raw


def problem_count(meta: dict[str, str]) -> int:
    raw = meta.get("count", "1").strip()
    try:
        n = int(raw)
    except ValueError:
        n = 1
    return max(1, n)


def languages_from_meta(meta: dict[str, str]) -> list[str]:
    return [x.strip().lower() for x in meta.get("languages", "").split(",") if x.strip()]


def discover_solution_langs(folder: Path) -> list[str]:
    langs: list[str] = []
    for f in folder.iterdir():
        if not f.is_file():
            continue
        m = SOLUTION_RE.match(f.name)
        if not m:
            continue
        langs.append(LANG_FROM_EXT.get(m.group(2).lower(), m.group(2).lower()))
    return langs


def infer_sheet(path: Path) -> str:
    parts = path.parts
    if "striver-a2z" in parts:
        return "striver-a2z"
    if "leetcode-extra" in parts:
        return "leetcode-extra"
    return "unknown"


def infer_subtopic(path: Path, topic: str) -> str:
    parts = list(path.parts)
    try:
        idx = parts.index(topic)
    except ValueError:
        return ""
    # topic / subtopic / problem-folder
    if idx + 1 < len(parts) - 1:
        return parts[idx + 1]
    return ""


def collect_from_problem_md() -> dict[str, dict[str, str]]:
    """Map problem-folder path → meta from PROBLEM.md."""
    found: dict[str, dict[str, str]] = {}
    for path in ROOT.rglob("PROBLEM.md"):
        if any(part in SKIP_DIR_NAMES for part in path.parts):
            continue
        meta = parse_frontmatter(path.read_text(encoding="utf-8"))
        if not meta:
            continue
        folder = path.parent
        rel = str(folder.relative_to(ROOT))
        meta["_path"] = rel
        meta["_source"] = "problem.md"
        if "sheet" not in meta:
            meta["sheet"] = infer_sheet(path)
        meta["topic"] = normalize_topic(meta.get("topic", ""), path)
        if "subtopic" not in meta:
            meta["subtopic"] = infer_subtopic(folder, meta["topic"])
        if "languages" not in meta or not meta["languages"].strip():
            langs = discover_solution_langs(folder)
            if langs:
                meta["languages"] = ", ".join(langs)
        if "status" not in meta:
            meta["status"] = "todo"
        if "count" not in meta:
            meta["count"] = "1"
        if "difficulty" not in meta:
            meta["difficulty"] = "Easy"
        found[rel] = meta
    return found


def collect_from_solutions(existing: dict[str, dict[str, str]]) -> list[dict[str, str]]:
    """
    Treat folders that contain Solution.* (and no PROBLEM.md) as solved.
    Skips folders already covered by PROBLEM.md.
    """
    problems: list[dict[str, str]] = list(existing.values())
    search_roots = [p for p in (STRIVER_ROOT, EXTRA_ROOT) if p.exists()]

    for root in search_roots:
        for path in root.rglob("*"):
            if not path.is_file() or not SOLUTION_RE.match(path.name):
                continue
            if any(part in SKIP_DIR_NAMES for part in path.parts):
                continue
            folder = path.parent
            rel = str(folder.relative_to(ROOT))
            if rel in existing:
                continue
            # Avoid counting topic/subtopic roots that hold a single multi-file entry
            # only when a sibling PROBLEM.md exists at the same level (handled above).
            langs = discover_solution_langs(folder)
            if not langs:
                continue
            topic = normalize_topic("", folder)
            if not topic and "leetcode-extra" in folder.parts:
                topic = ""
            meta = {
                "title": folder.name,
                "difficulty": "Easy",
                "platform": "TakeUForward",
                "sheet": infer_sheet(folder),
                "topic": topic,
                "subtopic": infer_subtopic(folder, topic) if topic else "",
                "status": "solved",
                "languages": ", ".join(sorted(set(langs))),
                "count": "1",
                "_path": rel,
                "_source": "solution",
            }
            existing[rel] = meta
            problems.append(meta)
    return problems


def collect_problems() -> list[dict[str, str]]:
    from_md = collect_from_problem_md()
    return collect_from_solutions(from_md)


def progress_bar(done: int, total: int, width: int = 20) -> str:
    if total <= 0:
        return "`" + ("░" * width) + "` 0%"
    done = max(0, min(done, total))
    ratio = done / total
    if done <= 0:
        filled = 0
    elif done >= total:
        filled = width
    else:
        filled = max(1, int(round(ratio * width)))
        filled = min(filled, width - 1)
    bar = "█" * filled + "░" * (width - filled)
    return f"`{bar}` {ratio * 100:.0f}%"


def build_stats(problems: list[dict[str, str]], baseline: dict[str, int]) -> str:
    solved = [p for p in problems if p.get("status", "").lower() == "solved"]
    in_progress = [p for p in problems if p.get("status", "").lower() == "in-progress"]

    by_diff: Counter[str] = Counter()
    by_lang: Counter[str] = Counter()
    a2z_solved_by_topic: dict[str, int] = defaultdict(int)
    extra_solved = 0

    for p in solved:
        n = problem_count(p)
        by_diff[p.get("difficulty", "Unknown")] += n
        for lang in languages_from_meta(p):
            by_lang[lang] += n

        sheet = p.get("sheet", "")
        if sheet == "striver-a2z":
            topic = normalize_topic(p.get("topic", ""), Path(p.get("_path", "")))
            if topic in A2Z_KEYS:
                a2z_solved_by_topic[topic] += n
        elif sheet == "leetcode-extra":
            extra_solved += n

    # Apply external baseline credits (sections not represented in the repo).
    # Off-repo basics credit is treated as Easy for difficulty totals.
    for topic_key, credit in baseline.items():
        if topic_key in A2Z_KEYS and credit > 0:
            a2z_solved_by_topic[topic_key] += credit
            by_diff["Easy"] += credit

    # Cap each topic at its sheet total.
    topic_totals = {key: total for key, _, total in A2Z_TOPICS}
    for key, total in topic_totals.items():
        if a2z_solved_by_topic[key] > total:
            a2z_solved_by_topic[key] = total

    a2z_solved = sum(a2z_solved_by_topic[key] for key, _, _ in A2Z_TOPICS)
    a2z_solved = min(a2z_solved, A2Z_TOTAL)
    total_solved = a2z_solved + extra_solved

    # In-progress entries still count as tracked items (not sheet units).
    in_progress_count = sum(problem_count(p) for p in in_progress)

    lines: list[str] = []
    lines.append("## Progress")
    lines.append("")
    lines.append("| Metric | Count |")
    lines.append("|---|---:|")
    lines.append(f"| **Total Solved** | **{total_solved}** |")
    lines.append(f"| In Progress | {in_progress_count} |")
    lines.append(f"| Striver A2Z | {a2z_solved} / {A2Z_TOTAL} |")
    lines.append(f"| LeetCode Extra | {extra_solved} |")
    lines.append("")
    lines.append("### By Difficulty")
    lines.append("")
    lines.append("| Difficulty | Solved |")
    lines.append("|---|---:|")
    for diff in ("Easy", "Medium", "Hard"):
        lines.append(f"| {diff} | {by_diff.get(diff, 0)} |")
    other = sum(v for k, v in by_diff.items() if k not in {"Easy", "Medium", "Hard"})
    if other:
        lines.append(f"| Other | {other} |")
    lines.append("")
    lines.append("### By Language")
    lines.append("")
    lines.append("| Language | Solved |")
    lines.append("|---|---:|")
    if by_lang:
        for lang, count in sorted(by_lang.items()):
            lines.append(f"| {lang} | {count} |")
    else:
        lines.append("| — | 0 |")
    lines.append("")
    lines.append("### Striver A2Z Sheet")
    lines.append("")
    lines.append(f"Overall: {progress_bar(a2z_solved, A2Z_TOTAL)} ({a2z_solved}/{A2Z_TOTAL})")
    lines.append("")
    lines.append("| # | Topic | Progress | Solved |")
    lines.append("|---:|---|---|---:|")
    for i, (key, name, total) in enumerate(A2Z_TOPICS, start=1):
        done = a2z_solved_by_topic.get(key, 0)
        lines.append(f"| {i} | {name} | {progress_bar(done, total, 12)} | {done}/{total} |")
    lines.append("")
    lines.append(
        "> Stats combine `PROBLEM.md` / `Solution.*` folders with "
        "`scripts/progress_baseline.json` (sheet items done outside this repo). "
        "Run `python3 scripts/update_readme_stats.py` after adding/solving a problem."
    )
    lines.append("")
    return "\n".join(lines)


def update_readme(stats: str) -> None:
    content = README.read_text(encoding="utf-8")
    block = f"{START}\n{stats}{END}"
    if START in content and END in content:
        content = re.sub(
            re.escape(START) + r".*?" + re.escape(END),
            block,
            content,
            flags=re.DOTALL,
        )
    else:
        content = content.rstrip() + "\n\n" + block + "\n"
    README.write_text(content, encoding="utf-8")


def main() -> None:
    baseline = load_baseline()
    problems = collect_problems()
    stats = build_stats(problems, baseline)
    if not README.exists():
        raise SystemExit("README.md not found. Create it first.")
    update_readme(stats)

    solved_units = 0
    for p in problems:
        if p.get("status", "").lower() == "solved":
            solved_units += problem_count(p)
    external = sum(baseline.values())
    print(
        f"Updated README.md — {solved_units} repo solved + {external} off-repo "
        f"credit / {len(problems)} tracked problem folders"
    )


if __name__ == "__main__":
    main()
