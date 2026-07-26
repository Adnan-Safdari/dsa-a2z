#!/usr/bin/env python3
"""
Scan PROBLEM.md files and refresh progress stats in README.md.

Usage (from repo root):
    python3 scripts/update_readme_stats.py
"""

from __future__ import annotations

import re
from collections import Counter, defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
README = ROOT / "README.md"

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

A2Z_TOTAL = sum(t[2] for t in A2Z_TOPICS)
START = "<!-- STATS:START -->"
END = "<!-- STATS:END -->"


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


def collect_problems() -> list[dict[str, str]]:
    problems: list[dict[str, str]] = []
    for path in ROOT.rglob("PROBLEM.md"):
        if "templates" in path.parts:
            continue
        meta = parse_frontmatter(path.read_text(encoding="utf-8"))
        if not meta:
            continue
        meta["_path"] = str(path.relative_to(ROOT))
        problems.append(meta)
    return problems


def progress_bar(done: int, total: int, width: int = 20) -> str:
    if total <= 0:
        return "`" + ("░" * width) + "` 0%"
    ratio = min(done / total, 1.0)
    if done <= 0:
        filled = 0
    elif done >= total:
        filled = width
    else:
        filled = max(1, int(round(ratio * width)))
        filled = min(filled, width - 1)
    bar = "█" * filled + "░" * (width - filled)
    return f"`{bar}` {ratio * 100:.0f}%"


def build_stats(problems: list[dict[str, str]]) -> str:
    solved = [p for p in problems if p.get("status", "").lower() == "solved"]
    in_progress = [p for p in problems if p.get("status", "").lower() == "in-progress"]

    by_diff = Counter(p.get("difficulty", "Unknown") for p in solved)
    by_sheet = Counter(p.get("sheet", "unknown") for p in solved)
    by_lang: Counter[str] = Counter()
    for p in solved:
        for lang in [x.strip() for x in p.get("languages", "").split(",") if x.strip()]:
            by_lang[lang.lower()] += 1

    a2z_solved_by_topic: dict[str, int] = defaultdict(int)
    for p in solved:
        if p.get("sheet") != "striver-a2z":
            continue
        topic = p.get("topic", "")
        # normalize to folder key like 01-basics
        for key, _, _ in A2Z_TOPICS:
            if topic == key or topic.startswith(key) or key in topic:
                a2z_solved_by_topic[key] += 1
                break

    a2z_solved = by_sheet.get("striver-a2z", 0)
    extra_solved = by_sheet.get("leetcode-extra", 0)
    total_solved = len(solved)

    lines: list[str] = []
    lines.append("## Progress")
    lines.append("")
    lines.append("| Metric | Count |")
    lines.append("|---|---:|")
    lines.append(f"| **Total Solved** | **{total_solved}** |")
    lines.append(f"| In Progress | {len(in_progress)} |")
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
        "> Stats are generated from `PROBLEM.md` files. "
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
    problems = collect_problems()
    stats = build_stats(problems)
    if not README.exists():
        raise SystemExit("README.md not found. Create it first.")
    update_readme(stats)
    solved = sum(1 for p in problems if p.get("status", "").lower() == "solved")
    print(f"Updated README.md — {solved} solved / {len(problems)} tracked problems")


if __name__ == "__main__":
    main()
