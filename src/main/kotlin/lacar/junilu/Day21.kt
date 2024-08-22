package lacar.junilu

typealias ShopItemList = List<ShopItem>

/**
 * Day 21: RPG Simulator 20XX
 *
 * https://adventofcode.com/2015/day/21
 */
class Day21() {

    private fun ShopItemList.totalCost() = sumOf { it.cost }
    private fun ShopItemList.totalDamage() = sumOf { it.damage }
    private fun ShopItemList.totalArmor() = sumOf { it.armor }

    // puzzle input
    private val theBoss = Player(points = 100, damage = 8, armor = 2)

    fun leastAmountOfGoldSpentToWin() = itemsBoughtThatCanBeat(theBoss).minOf { it.totalCost() }

    private fun itemsBoughtThatCanBeat(boss: Player) =
        RpgShop.allCombinationsOfItemsBought().filter { mixOfItems ->
            val player = Player(points = 100, damage = mixOfItems.totalDamage(), armor = mixOfItems.totalArmor())
            RolePlayingGame(player, boss).playerWins()
        }

    fun mostAmountOfGoldSpentJustToLose() = itemsBoughtThatLoseAgainst(theBoss).maxOf { it.totalCost() }

    private fun itemsBoughtThatLoseAgainst(boss: Player) =
        RpgShop.allCombinationsOfItemsBought().filter { mixOfItems ->
            val player = Player(points = 100, damage = mixOfItems.totalDamage(), armor = mixOfItems.totalArmor())
            RolePlayingGame(player, boss).playerLoses()
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

data class ShopItem(val cost: Int, val damage: Int = 0, val armor: Int = 0)

object RpgShop {

    private fun weapon(cost: Int, damage: Int) =
        ShopItem(cost = cost, damage = damage)

    private val allWeapons = listOf(
        weapon(8, 4),  // Dagger
        weapon(10, 5), // Shortsword
        weapon(25, 6), // Warhammer
        weapon(40, 7), // Longsword
        weapon(74, 8), // Greataxe
    )

    private fun armor(cost: Int, armor: Int) =
        ShopItem(cost = cost, armor = armor)

    private val allArmor = listOf(
        armor(13, 1),   // Leather
        armor(31, 2),   // Chainmail
        armor(53, 3),   // Splintmail
        armor(75, 4),   // Bandedmail
        armor(102, 5),  // Platemail
    )

    private fun ring(cost: Int, damage: Int = 0, armor: Int = 0) =
        ShopItem(cost = cost, damage = damage, armor = armor)

    private val allRings = listOf(
        ring(25, damage = 1),  // Damage +1
        ring(50, damage = 2),  // Damage +2
        ring(100, damage = 3), // Damage +3
        ring(20, armor = 1),   // Defense +1
        ring(40, armor = 2),   // Defense +2
        ring(80, armor = 3),   // Defense +3
    )

    private val allRingPairs = allRings.combinations(2)

    fun allCombinationsOfItemsBought(): Sequence<ShopItemList> = sequence {
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