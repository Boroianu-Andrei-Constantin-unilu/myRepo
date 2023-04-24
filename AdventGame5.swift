import Foundation

struct Player {
    var power: Int
    var intelligence: Int
    var luck: Int
}

func randomStat() -> Int {
    return Int.random(in: 1...10)
}

func printStats(player: Player) {
    print("Power: \(player.power), Intelligence: \(player.intelligence), Luck: \(player.luck)")
}

func battle(player: inout Player) {
    print("You encountered an enemy!")
    let enemyPower = randomStat()
    if player.power > enemyPower {
        print("You defeated the enemy!")
        player.power += 1
    } else {
        print("You lost the battle...")
        player.power -= 1
    }
    printStats(player: player)
}

func miniGame(player: inout Player) {
    print("You found a mini game!")
    let gameResult = player.intelligence + player.luck
    if gameResult >= 10 {
        print("You won the mini game!")
        player.intelligence += 1
    } else {
        print("You lost the mini game...")
        player.luck -= 1
    }
    printStats(player: player)
}

func main() {
    var player = Player(power: randomStat(), intelligence: randomStat(), luck: randomStat())
    print("Welcome to the adventure game!")
    print("Your initial stats:")
    printStats(player: player)
    
    print("\nEntering room 1...")
    battle(player: &player)
    
    print("\nEntering room 2...")
    miniGame(player: &player)
    
    print("\nEntering room 3...")
    if player.luck > 5 {
        print("You found a shortcut! Moving to room 4...")
    } else {
        print("You couldn't find a shortcut... Moving to room 4 through a challenge.")
        player.intelligence -= 1
    }
    
    print("\nEntering room 4...")
    let finalBattleResult = player.power + player.intelligence
    if finalBattleResult >= 15 {
        print("You defeated the final boss and won the game!")
    } else {
        print("Your stats are too weak, and you lost the final battle. Game over.")
    }
}

main()