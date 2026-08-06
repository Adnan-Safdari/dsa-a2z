# DSA — Striver A2Z + LeetCode

Personal DSA practice repo following **[Striver's A2Z Sheet](https://takeuforward.org/dsa/strivers-a2z-sheet-learn-dsa-a-to-z)**, plus extra LeetCode problems that are not on the sheet.

<!-- STATS:START -->
## Progress

| Metric | Count |
|---|---:|
| **Total Solved** | **48** |
| In Progress | 1 |
| Striver A2Z | 48 / 474 |
| LeetCode Extra | 0 |

### By Difficulty

| Difficulty | Solved |
|---|---:|
| Easy | 48 |
| Medium | 0 |
| Hard | 0 |

### By Language

| Language | Solved |
|---|---:|
| java | 34 |

### Striver A2Z Sheet

Overall: `██░░░░░░░░░░░░░░░░░░` 10% (48/474)

| # | Topic | Progress | Solved |
|---:|---|---|---:|
| 1 | Learn the Basics | `███████████░` 89% | 48/54 |
| 2 | Sorting Techniques | `░░░░░░░░░░░░` 0% | 0/7 |
| 3 | Arrays | `░░░░░░░░░░░░` 0% | 0/40 |
| 4 | Binary Search | `░░░░░░░░░░░░` 0% | 0/32 |
| 5 | Strings (Basic & Medium) | `░░░░░░░░░░░░` 0% | 0/15 |
| 6 | Linked List | `░░░░░░░░░░░░` 0% | 0/31 |
| 7 | Recursion | `░░░░░░░░░░░░` 0% | 0/25 |
| 8 | Bit Manipulation | `░░░░░░░░░░░░` 0% | 0/18 |
| 9 | Stack & Queues | `░░░░░░░░░░░░` 0% | 0/30 |
| 10 | Sliding Window & Two Pointer | `░░░░░░░░░░░░` 0% | 0/12 |
| 11 | Heaps | `░░░░░░░░░░░░` 0% | 0/17 |
| 12 | Greedy Algorithms | `░░░░░░░░░░░░` 0% | 0/15 |
| 13 | Binary Trees | `░░░░░░░░░░░░` 0% | 0/38 |
| 14 | Binary Search Trees | `░░░░░░░░░░░░` 0% | 0/16 |
| 15 | Graphs | `░░░░░░░░░░░░` 0% | 0/53 |
| 16 | Dynamic Programming | `░░░░░░░░░░░░` 0% | 0/55 |
| 17 | Tries | `░░░░░░░░░░░░` 0% | 0/7 |
| 18 | Strings (Hard) | `░░░░░░░░░░░░` 0% | 0/9 |

> Stats combine `PROBLEM.md` / `Solution.*` folders with `scripts/progress_baseline.json` (sheet items done outside this repo). Run `python3 scripts/update_readme_stats.py` after adding/solving a problem.
<!-- STATS:END -->

## Repository Layout

```text
.
├── striver-a2z/          # Primary path — mirrors A2Z sheet topics
│   ├── 01-basics/
│   ├── 02-sorting/
│   ├── 03-arrays/
│   ├── ...
│   └── 18-strings-hard/
├── leetcode-extra/       # Problems outside the A2Z sheet
│   ├── easy/
│   ├── medium/
│   └── hard/
├── templates/            # Starter files for a new problem
├── playground/           # Quick local runners / scratch
└── scripts/              # README progress generator
```

## Conventions

### Folder naming

| Kind                 | Pattern                     | Example                   |
| -------------------- | --------------------------- | ------------------------- |
| LeetCode problem     | `LXXXX_Problem_Name`        | `L0009_Palindrome_Number` |
| Sheet-only / concept | descriptive kebab or Pascal | `patterns/`               |

LeetCode IDs are **zero-padded to 4 digits**.

### Each problem folder

```text
L0009_Palindrome_Number/
├── PROBLEM.md      # optional but preferred — YAML frontmatter drives stats
├── Solution.java   # or solution.py / solution.c / ...
└── NOTES.md        # optional
```

Folders with a `Solution.*` file count as **solved** even without `PROBLEM.md`.

### `PROBLEM.md` frontmatter

```yaml
---
id: 9
title: Palindrome Number
difficulty: Easy          # Easy | Medium | Hard
platform: LeetCode
sheet: striver-a2z        # striver-a2z | leetcode-extra
topic: 01-basics          # A2Z folder key, e.g. 03-arrays
subtopic: basic-maths     # optional
status: solved            # solved | in-progress | todo
count: 1                  # optional — use >1 for multi-item entries (e.g. patterns)
languages: [java]
link: https://leetcode.com/problems/palindrome-number/
---
```

### Off-repo progress

Early sheet sections you completed on TakeUForward but did not mirror here (Things to Know, Logical Thinking, STL, etc.) live in `scripts/progress_baseline.json` and are added into topic totals.

## How to add a problem

1. Pick the right place:
   - On the A2Z sheet → `striver-a2z/<topic>/...`
   - Not on the sheet → `leetcode-extra/<difficulty>/`
2. Copy files from `templates/` (or just add `Solution.java`)
3. Fill in `PROBLEM.md` frontmatter when you want difficulty / link metadata
4. Refresh README stats:

```bash
python3 scripts/update_readme_stats.py
```

## Languages

Solutions may be in **Java**, **C**, **Python**, or others — whatever fits the problem. Prefer one clear `Solution.*` (or language-idiomatic filename) per approach.

## Roadmap

- [ ] Complete Striver A2Z sheet (474 problems)
- [ ] Keep `leetcode-extra` for interview / contest extras
- [ ] Revisit marked `in-progress` / hard problems periodically
