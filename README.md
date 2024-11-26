# Advent of Code in Kotlin - All events from 2015

[Advent of Code](https://adventofcode.com) is an annual programming competition with a Christmas-y theme. It runs during Advent, from December 1st to the 25th.

I'm backtracking to the first AoC set of problems, having already started solving some of the more recent years' puzzles. My goal is to get all fifty stars for each year and solve the puzzles using Kotlin. This repository is a record of this multi-year exercise.

## Events

* [AoC 2015](src/main/kotlin/lacar/junilu/aoc2015/README.md) - 46/50 &#11088;
* [AoC 2016](src/main/kotlin/lacar/junilu/aoc2016/README.md) - 0/50 &#11088;
* [AoC 2017](src/main/kotlin/lacar/junilu/aoc2017/README.md) - 0/50 &#11088;
* [AoC 2018](src/main/kotlin/lacar/junilu/aoc2018/README.md) - 0/50 &#11088;
* [AoC 2019](src/main/kotlin/lacar/junilu/aoc2019/README.md) - 0/50 &#11088;
* [AoC 2020](src/main/kotlin/lacar/junilu/aoc2020/README.md) - 0/50 &#11088;
* [AoC 2021](src/main/kotlin/lacar/junilu/aoc2021/README.md) - 0/50 &#11088;
* [AoC 2022](src/main/kotlin/lacar/junilu/aoc2022/README.md) - 0/50 &#11088;
* [AoC 2023](src/main/kotlin/lacar/junilu/aoc2023/README.md) - 0/50 &#11088;
* [AoC 2024](src/main/kotlin/lacar/junilu/aoc2024/README.md) - 0/50 &#11088;

## Developer Notes

### Some over-engineering expected

I'm not much of a competitive programmer. I am, however, big on doing TDD and refactoring for simplicity, clarity, and ease of maintenance. I'm not playing [code golf](https://code.golf) with any of these solutions but I am striving for clean code where "clean" means it's easy to understand and work with. That includes having at least one automated test for each solution.

Having said that, you might note that I sometimes go overboard and end up with something that looks over-engineered, which it probably is. Feel free to add a comment or two to call out anything you might question as good practice. 

A note on my general approach to these problems. I'm not generally going for brevity but rather clarity. Sometimes I end up with clear code that also brief. Oftentimes, I'll also refactor for clarity and end up with more lines than I started with. 

### Semantic Commit Messages

I'm using a modified form of [sematic commit messages](https://joshbuchea/semantic-commit-messages.md) that's greatly influenced by [Arlo Belshee's Commit Notation](https:github.com/arlobelshee/ArlosCommitNotation).

| Prefix  | Name          | Intention                                   |
|:-------:|:--------------|:--------------------------------------------|
| `f` `F` | Feature       | New feature or functionality                |
| `b` `B` | Bugfix        | Bug fix                                     |
| `r` `R` | Refactoring   | Refactoring code, both prod and test        |
| `d` `D` | Documentation | Adding/changing documentation               |
| `t` `T` | Test          | Adding missing tests, refactoring tests     |
| `s` `S` | Style         | Formatting, project structure, organization |
| `c` `C` | Chore         | Updating grunt tasks, etc.; no code changes |

Arlo uses lowercase vs. Uppercase prefix to signal the risk level: lowercase indicates less risk involved than uppercase prefix.

Arlo also adds special characters to the prefix as "qualifiers". I'm going to use these qualifiers:

| Qualifier Mark | Intention                                          |
|:--------------:|:---------------------------------------------------|
|      `!!`      | Changes include tests for new behavior             |
|      `::`      | Changes include broken tests                       |
|      `**`      | No automated tests, or unfinished implementation   |

## Sources of inspiration

I've learned quite a few lessons since I started learning Kotlin by solving AoC puzzles. I couldn't have done it without help and inspiration from others though so I want to take a moment to acknowledge the folks who are much smarter and better than me in Kotlin and in programming in general.

* [Todd Ginsberg](https://github.com/tginsberg) - I saw some posts from Todd on Reddit and the Kotlin programs he writes are generally very clear and elegant.
* Andrew Pickett - I know Andrew from work. He's a beast. He does his AoC in Python as far as I can tell but his solutions are still good examples to learn from.
* Stephan van Hulst - A fellow moderator at [CodeRanch](https://coderanch.com), Stephan is a great programmer and always has great takes on problems and how they might be solved in Kotlin or whatever language he's using at the moment.
* _(more to follow)_

## Acknowledgements

I want to acknowledge and thank [Eric Wastl](https://was.tl), creator and maintainer of Advent of Code. I encourage you to donate and support Eric so he can continue the awesome work of bringing joy to countless developers every December. May his project continue to grow and prosper.
