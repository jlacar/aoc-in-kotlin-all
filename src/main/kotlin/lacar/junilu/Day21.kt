package lacar.junilu

typealias ShopItemList = List<ShopItem>

/**
 * Day 21: RPG Simulator 20XX
 *
 * https://adventofcode.com/2015/day/21
 */
class Day21() {
    // puzzle input
    private val theBoss = Player(100, damage = 8, armor = 2)

    fun leastAmountOfGoldSpentToWin() = itemsBoughtThatCanBeat(theBoss).minOf { it.totalCost() }

    private fun ShopItemList.totalCost() = sumOf { it.cost }

    private fun itemsBoughtThatCanBeat(boss: Player) =
        RpgShop.itemCombinations().filter { itemsBought ->
            RolePlayingGame(
                Player(
                    points = 100,
                    damage = itemsBought.sumOf { it.damage },
                    armor = itemsBought.sumOf { it.armor }
                ),
                boss
            ).playerWins()
        }

    fun mostAmountOfGoldSpentJustToLose() = itemsBoughtThatLoseAgainst(theBoss).maxOf { it.totalCost() }

    private fun itemsBoughtThatLoseAgainst(boss: Player) =
        RpgShop.itemCombinations().filter { itemsBought ->
            RolePlayingGame(
                Player(
                    points = 100,
                    damage = itemsBought.sumOf { it.damage },
                    armor = itemsBought.sumOf { it.armor }
                ),
                boss
            ).playerLoses()
        }
}

data class Player(var points: Int, val damage: Int = 0, val armor: Int = 0) {
    fun dealDamageTo(enemy: Player) {
        enemy.receive(damage)
    }

    fun receive(damageDealtByAttacker: Int) {
        points -= if (armor < damageDealtByAttacker) damageDealtByAttacker - armor else 1
    }

    fun isStillAlive() = points > 0
}

enum class ShopItemType() { WEAPON, ARMOR, RING }

data class ShopItem(val type: ShopItemType,
                    val cost: Int, val damage: Int = 0, val armor: Int = 0)

object RpgShop {
    private val items = listOf(
        weapon(8, 4),  // Dagger
        weapon(10, 5), // Shortsword
        weapon(25, 6), // Warhammer
        weapon(40, 7), // Longsword
        weapon(74, 8), // Greataxe

        armor(13, 1),   // Leather
        armor(31, 2),   // Chainmail
        armor(53, 3),   // Splintmail
        armor(75, 4),   // Bandedmail
        armor(102, 5),    // Platemail

        ring(25, damage = 1),  // Damage +1
        ring(50, damage = 2),  // Damage +2
        ring(100, damage = 3), // Damage +3
        ring(20, armor = 1),   // Defense +1
        ring(40, armor = 2),   // Defense +2
        ring(80, armor = 3),   // Defense +3
    )

    private fun weapon(cost: Int, damage: Int) =
        ShopItem(ShopItemType.WEAPON, cost = cost, damage = damage)

    private fun armor(cost: Int, armor: Int) =
        ShopItem(ShopItemType.ARMOR, cost = cost, armor = armor)

    private fun ring(cost: Int, damage: Int = 0, armor: Int = 0) =
        ShopItem(ShopItemType.RING, cost = cost, damage = damage, armor = armor)

    fun itemCombinations(): Sequence<ShopItemList> = sequence {
        val allWeapons = items.filter { it.type == ShopItemType.WEAPON }
        val allArmor = items.filter { it.type == ShopItemType.ARMOR }
        val allRings = items.filter { it.type == ShopItemType.RING }
        val allRingPairs = allRings.combinations(2)

        allWeapons.forEach { weapon ->
            yield(listOf(weapon))
            allArmor.forEach { armor ->
                yield(listOf(weapon, armor))
                allRings.forEach { ring ->
                    yield(listOf(weapon, ring))
                    yield(listOf(weapon, armor, ring))
                }
                allRingPairs.forEach { rings ->
                    yield(listOf(weapon) + rings)
                    yield(listOf(weapon, armor) + rings)
                }
            }
        }
    }
}

class RolePlayingGame(val player: Player, val boss: Player) {
    fun playerWins(): Boolean {
        val thePlayer = player.copy()
        val theBoss = boss.copy()
        var (attacker, defender) = (thePlayer to theBoss)
        do {
            attacker.dealDamageTo(defender)
            if (defender.isStillAlive()) {
                attacker = defender.also { defender = attacker } // swap roles
            }
        } while (defender.isStillAlive())
        return attacker === thePlayer
    }

    fun playerLoses() = !playerWins()
}
