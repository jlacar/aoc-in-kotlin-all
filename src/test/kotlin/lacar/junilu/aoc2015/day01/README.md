# AoC 2015 Day 1 - Tests

I wrote unit tests using JUnit and a couple of test styles in Kotest.

# JUnit 
Test: [Day01Test.kt](./Day01Test.kt)

# Kotest 

## FunSpec style 
Test: [Day01FunSpec.kt](./Day01FunSpec.)

Includes [data-driven tests](https://kotest.io/docs/framework/datatesting/data-driven-testing.html) for examples.

I added the `scenario()` helper function to make the tests read better.

## DescribeSpec style
Test: [Day01DescribeSpec.kt](./Day01DescribeSpec.kt)
