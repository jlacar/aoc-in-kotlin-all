# AoC 2024 - Day 13: Claw Contraption

## Notes

## Design

### Parsing

1. Chunk lines by 4 using `chunk(4)`
2. Then ignore the empty line with `map { it.take(3) }` since each claw machine entry has 3 lines to it
3. Parse each line individually to create a Triple(a, b, prize) all with type Pair(x, y)

### Geometry

Line geometry.  

### Can win prize in this machine?

Slope of Prize needs to be between the slopes of A and B for any combination of pressing A+B to get to P

Tries at evaluating whether prize can be won:
1. Check slopes. If A < P < B or B < P < A.
2. Check combos or rise/run using integer % *
3. Solve for two unknowns (#A pushed, #B pushed) from two equations - got an assist from ChatGPT on the equations.

Last approach solved Part 1.

## 