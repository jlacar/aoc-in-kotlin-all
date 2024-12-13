# AoC 2024 - Day 13: Claw Contraption

## Notes

## Design

### Parsing

1. Chunk lines by 4 using `chunk(4)`
2. Then ignore the empty line with `map { it.take(3) }` since each claw machine entry has 3 lines to it
3. Parse each line individually to create a Triple(a, b, prize) all with type Pair(x, y)
## 