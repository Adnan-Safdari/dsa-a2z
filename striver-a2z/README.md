# Striver's A2Z DSA Sheet

Primary learning path: [Striver's A2Z Sheet](https://takeuforward.org/dsa/strivers-a2z-sheet-learn-dsa-a-to-z)

## Structure

| Folder | Topic | Problems on sheet |
|---|---|---:|
| `01-basics/` | Learn the Basics | 54 |
| `02-sorting/` | Sorting Techniques | 7 |
| `03-arrays/` | Arrays | 40 |
| `04-binary-search/` | Binary Search | 32 |
| `05-strings/` | Strings (Basic & Medium) | 15 |
| `06-linked-list/` | Linked List | 31 |
| `07-recursion/` | Recursion | 25 |
| `08-bit-manipulation/` | Bit Manipulation | 18 |
| `09-stack-queues/` | Stack & Queues | 30 |
| `10-sliding-window-two-pointer/` | Sliding Window & Two Pointer | 12 |
| `11-heaps/` | Heaps | 17 |
| `12-greedy/` | Greedy Algorithms | 15 |
| `13-binary-trees/` | Binary Trees | 38 |
| `14-bst/` | Binary Search Trees | 16 |
| `15-graphs/` | Graphs | 53 |
| `16-dp/` | Dynamic Programming | 55 |
| `17-tries/` | Tries | 7 |
| `18-strings-hard/` | Strings (Hard) | 9 |

## Adding a problem

1. Create a folder under the matching topic, e.g. `03-arrays/easy/L0075_Sort_Colors/`
2. Copy `templates/PROBLEM.md` and a language template into it
3. Fill the YAML frontmatter (`sheet: striver-a2z`, correct `topic`, `status`, etc.)
4. Run `python3 scripts/update_readme_stats.py`
