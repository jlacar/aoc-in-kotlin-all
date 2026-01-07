# AoC 2015 Day 2 - Tests

## JUnit Test - [Day02Test.kt](./Day02Test.kt)

I used the `@Nested` annotation to group the tests by general focus: one that used the GitHub account input, another for the GMail account input, and another for the examples givein in the puzzle.

The `@DisplayName` annotation is used to give each nested test class a more display-friendly name. The tests have names that are display-friendly, too, by using backticks, which greatly improves the readability of the test output. In Java, I'd normally use the `@DisplayNameGenerator` annotation to customize the test display names.

Using the Kotest assertion library also makes the tests much more readable, in my opinion.

## Kotest BehaviorSpec - [Day02BehaviorSpec.kt](./Day02BehaviorSpec.kt)

This is an example of using the Kotest BehaviorSpec to test the same logic as the JUnit test. If you're familiar with BDD-like specifications, this will look very familiar. The one thing I noticed though with the test output in IntelliJ IDEA is that the `Given` part doesn't add a "Given: " prefix to the description provided, unlike the `Context`, `When`, and `Then` parts, which add "Context: ", "When: ", and "Then: " prefixes, respectively. I'm not sure if this is a bug in IntelliJ IDEA Kotest plugin or if it's just a quirk of the test output format.

The test output is a little too verbose for my taste though, so I'll probably stick with the JUnit test or use a different Kotest style.