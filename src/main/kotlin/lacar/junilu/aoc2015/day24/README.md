# Day 24 - It Hangs in the Balance

## Approach

### Assumptions (verified with tests)

- The total of all weights in the puzzle input is divisible by 3
- The puzzle input doesn't include the balanced weight. This means the minimum group size is 2.
- When a first group is identified, the total weights remaining is even / divisible by 2.

### Algorithm

1. Find the sum of all the weights and divide it by three.

     balancedWeight = totalWeight / 3
     chunks: k = 2 to N weights = if sum of 


Each group needs to 

balancedWeightGroup( divisor (3..1)
)

### Initial design

Hard coded # of compartments

Part 2 made it necessary to parameterize # of compartments. Again, the default parameter value makes it easy in Kotlin.

## Discoveries

### Know when to `fold`, know when to `reduce`

(AI) The `reduce` function uses the first element of the collection as the initial value for the accumulator. The `fold` function is passed the initial value.

For the `productOfAll()` function, `reduce` is simpler since the first value in the list can be the initial value of the product.