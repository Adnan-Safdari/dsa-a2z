---
id: 7
title: Reverse Integer
difficulty: Easy
platform: LeetCode
sheet: striver-a2z
topic: 01-basics
subtopic: basic-maths
status: solved
languages: [java]
link: https://leetcode.com/problems/reverse-integer/
---

# Reverse Integer

Given a signed 32-bit integer `x`, return `x` with its digits reversed.
If reversing `x` causes the value to go outside the signed 32-bit range `[-2^31, 2^31 - 1]`, return `0`.

## Approach
- Pop digits with `% 10` / `/ 10` and rebuild the reversed number
- Check overflow **before** multiplying by 10 (against `Integer.MAX_VALUE` / `MIN_VALUE`)
- Time: O(log₁₀|x|) · Space: O(1)
