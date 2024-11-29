# Day 24 - It Hangs in the Balance

## Approach

### Assumptions (verified with tests)

- The total of all weights in the puzzle input is divisible by 3
- The puzzle input doesn't include the balanced weight. This means the minimum group size is 2.

### Algorithm

1. Calculate the balanced weight for each compartment. This is the sum of all the weights divided the number of compartments.
2. Calculate the smallest possible number of weights that would add up to at least the balanced weight. Since the weights are sorted, we can start at the end of the list and take however many needed
3. Starting with smallest possible number of weights, k, see if there are _any_ k-sized combinations that sum up to the balanced weight. The first k is where the answer lies.
4. Get all k-sized combinations where the sum equals the balanced weight, then get the minimum QE.

QE is calculated by multiplying all the numbers in the group.

### Design Notes

For Part 1, the number of compartments was hard-coded to 3.

For Part 2, the number of compartments was parameterized. Adding a parameter with a default value of 3 made this easy. However, the default of 3 doesn't make much sense and the associated change to part 1 was small (only the call in the test was affected) so I decided to make the parameter mandatory.

Had to change return type to Long because Int wasn't big enough to hold the product - negative QEs indicated overflow.

## Discoveries

### Know when to `fold`, know when to `reduce`

(AI) The `reduce` function uses the first element of the collection as the initial value for the accumulator. The `fold` function is passed the initial value.