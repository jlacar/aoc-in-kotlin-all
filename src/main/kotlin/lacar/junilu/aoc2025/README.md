# Advent of Code 2025 - In Kotlin

- Some intro notes here - 

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

This year, instead of using a companion object in the solution class to parse the text input as I have in the past years, I'm moving the parsing logic into the test class. My reasoning is that the parsing logic is a separate concern from the solution logic. The shape of the input data should be loosely coupled to the solution. The parsing logic will act as a layer of abstraction between the solution and the source input data, transforming it from the incoming shape to the desired shape. 











