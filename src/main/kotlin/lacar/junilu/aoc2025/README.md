# Advent of Code 2025 - In Kotlin

Starting this year, the event will only go for twelve days instead of the usual twenty-five as in the past.

## Solutions

Here are my notes on each of the puzzles and their solutions:

* &#11088;&#11088; [Day 1](day01/.) 
* &#11088;&#11088; [Day 2](day02/.) 
* &#11088;&#11088; [Day 3](day03/.) 
* &#11088;&#11088; [Day 4](day04/.) 
* &#11088;&#11088; [Day 5](day05/.) 
* &#11088;&#11088; [Day 6](day06/.) 
* &#11088;&#11088; [Day 7](day07/.) 
* &#11035;&#11035; Day 8 - day08/. 
* &#11035;&#11035; Day 9 - day09/. 
* &#11035;&#11035; Day 10 - day10/.
* &#11035;&#11035; Day 11 - day11/.
* &#11035;&#11035; Day 12 - day12/.

## Reflections

I'm rethinking my approach to organizing the input parsing logic this year. In the past, I have used a companion object in the solution class to encapsulate the parsing logic. This year, I'm going to try putting the logic to parse the input in the test class instead.

My reasoning is that the parsing logic is a separate concern from calculating the solution. The shape of the input data can change independently from the solution. If we consider the Ports and Adapters (Hexagonal) architecture, the parsing logic will reside in an Adapter class that knows how to convert the raw input data to the shape expected by the solution code.











