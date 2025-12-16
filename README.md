# Advent of Code in Kotlin - All Events

[Advent of Code](https://adventofcode.com) is an annual programming competition with a Christmas-y theme. It originally ran from December 1st to the 25th. In 2025, it was shortened to twelve days, running only to December 12th. I suppose the creators saw that most people only made it halfway to December 25th and decided to shorten the event so as not to waste any effort creating twice as many puzzles as most people would ever complete.

I'm slowly working my way through all the AoC puzzles starting from the first year it ran in 2015. My goal is to get all fifty stars for each year using Kotlin. This repository is a record of this multi-year exercise.

## Events

* [AoC 2025](src/main/kotlin/lacar/junilu/aoc2025/README.md) - 18/24 &#11088;
* [AoC 2024](src/main/kotlin/lacar/junilu/aoc2024/README.md) - 8/50 &#11088;
* [AoC 2023](src/main/kotlin/lacar/junilu/aoc2023/README.md) - 18/50 &#11088;
* [AoC 2022](src/main/kotlin/lacar/junilu/aoc2022/README.md) - 22/50 &#11088;
* [AoC 2021](src/main/kotlin/lacar/junilu/aoc2021/README.md) - 16/50 &#11088;
* [AoC 2020](src/main/kotlin/lacar/junilu/aoc2020/README.md) - 0/50 &#11088;
* [AoC 2019](src/main/kotlin/lacar/junilu/aoc2019/README.md) - 0/50 &#11088;
* [AoC 2018](src/main/kotlin/lacar/junilu/aoc2018/README.md) - 0/50 &#11088;
* [AoC 2017](src/main/kotlin/lacar/junilu/aoc2017/README.md) - 0/50 &#11088;
* [AoC 2016](src/main/kotlin/lacar/junilu/aoc2016/README.md) - 2/50 &#11088;
* [AoC 2015](src/main/kotlin/lacar/junilu/aoc2015/README.md) - 50/50 &#11088;

## Developer Notes

### Some over-engineering expected

I'm not much of a competitive programmer so I don't expect to solve these puzzles faster than the average. 

I am, however, big on doing TDD and refactoring for simplicity, clarity, and ease of maintenance. I strive to keep the code clean, where "clean" means easy to understand and work with. Keeping the code as clean as it can be as I go helps me keep from getting confused and helps me solve the problem faster in the long run.

That said, I still manage to go overboard at times and end up with code that looks over-engineered, which it probably is.

My general approach is to prefer clarity over brevity. Sometimes I refactor for clarity and get less code. Other times, I'll end up with more code. I never intentionally play [code golf](https://code.golf) with any of these solutions so if you see something that's brief but not clear or looks too clever but cryptic, please drop a comment and let me know.

Feel free to comment and point out anything that might be a questionable or problematic practice in Kotlin. 

Suggestions for alternative approaches are always welcome and greatly appreciated.

### Semantic Commit Messages

I'm using a modified form of [semantic commit messages](https://joshbuchea/semantic-commit-messages.md) that's based on [Arlo Belshee's Commit Notation](https://github.com/arlobelshee/ArlosCommitNotation).

| Prefix  | Name            | Intention                                   |
|:-------:|:----------------|:--------------------------------------------|
| `f` `F` | Feature         | New feature or functionality                |
| `g` `G` | Feature (Gen)   | New feature or functionality using GenAI    |
| `b` `B` | Bugfix          | Bug fix                                     |
| `r` `R` | Refactoring     | Refactoring code, both prod and test        |
| `d` `D` | Documentation   | Adding/changing documentation               |
| `t` `T` | Test            | Adding missing tests, refactoring tests     |
| `s` `S` | Structure/Style | Formatting, project structure, organization |
| `c` `C` | Chore           | Updating grunt tasks, etc.; no code changes |

Arlo uses lowercase vs. Uppercase prefix to signal the risk level: lowercase indicates less risk involved than uppercase prefix.

Arlo also adds special characters to the prefix as "qualifiers". I'm going to use these qualifiers:

| Qualifier Mark | Intention                                        |
|:--------------:|:-------------------------------------------------|
|      `++`      | Changes with new passing tests                   |
|      `^^`      | Changes made with assistance from AI             |
|      `!!`      | Changes with existing tests passing              |
|      `!!`      | Changes with existing tests passing              |
|      `%%`      | Changes with broken tests                        |
|      `**`      | No automated tests, or unfinished implementation |

## Sources of inspiration

I've learned quite a few lessons since I started studying Kotlin by solving AoC puzzles. I couldn't have done it without help and inspiration from others though so I want to acknowledge the people who are much smarter and better than I am in Kotlin and in programming in general.

* [Todd Ginsberg](https://github.com/tginsberg) - I saw some posts from Todd on Reddit and the Kotlin programs he writes are generally very clear and elegant.
* [Andrew Pickett](https://github.com/andrewpickett) - I know Andrew from work at JPMorganChase. He's a beast. As far as I can tell, he has earned all the stars in every AoC event. He's is a beast. He does his solutions in Python but they are still good examples to learn from.
* Stephan van Hulst - Stephan is a fellow moderator at [CodeRanch](https://coderanch.com). He's an excellent programmer. He always has good takes on problems and how they might be solved in Kotlin or whatever language he's using at the moment.
* Other folks who hang out in the [Programming Diversions forum at CodeRanch.com](https://coderanch.com/f/71/Programming): Liutauras Vilda, Tim Cooke, Mike Simmons, Piet Souris, and others who have provided feedback, insights, and inspiration.
* _(more to follow)_

## Acknowledgements

[Eric Wastl](https://was.tl), creator and maintainer of Advent of Code, deserves the most thanks. I encourage you to donate and support Eric so he can continue the awesome work of bringing joy to countless developers every December. May his project continue to grow and prosper.

Happy Coding and Happy Holidays!