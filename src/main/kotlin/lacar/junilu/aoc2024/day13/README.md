# AoC 2024 - Day 13: Claw Contraption

## Notes

## Design

### Parsing

1. Chunk lines by 4 using `chunk(4)`
2. Then ignore the empty line with `map { it.take(3) }` since each claw machine entry has 3 lines to it
3. Parse each line individually to create a Triple(a, b, prize) all with type Pair(x, y)

### Geometry

Line geometry. 

### Part 1 - Can win prize with no more than 100 pushes?

Slope of Prize needs to be between the slopes of A and B for any combination of pressing A+B to get to P

Tries at evaluating whether prize can be won:

&#x274C; Check slopes. If A < P < B or B < P < A. 

&#x274C; Check combos or rise/run using integer % *
 
&#x2B50; Solve for two unknowns (#A pushed, #B pushed) from two equations - got an assist from ChatGPT on the equations.

This stipulation was important:
> ...each button would need to be pressed **no more than 100 times** to win a prize.

### Part 2 - Prize location adjusted by 10_000_000_000_000 on both the X and Y axis

&#x274C; Just adding correction parameter, removing the 1..100 constraint, and changing all to `Long`

## Discoveries

You can move a variable declaration into a when - IDE will suggest it.

    // refactor this 
    val determinant = ...
    when (determinant) {
        0 -> ...
        else -> ...
    }

    // to this
    when (val determinant = ...) {
        0 -> ...
        else -> ...
    }

(see calculations for tokens and canWinPrize)
