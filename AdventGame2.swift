import Foundation

class Player {
    var power: Int
    var intelligence: Int
    var luck: Int

    init(power: Int, intelligence: Int, luck: Int) {
        self.power = power
        self.intelligence = intelligence
        self.luck = luck
    }
}

func battle(player: Player, enemyPower: Int) -> Bool {
    let outcome = player.power * Int.random(in: 1...player.luck) - enemyPower
    return outcome >= 0
}

func miniGame(player: Player, requiredIntelligence: Int) -> Bool {
    return player.intelligence >= requiredIntelligence
}

func roomTransition(player: Player) {
    print("Moving to the next room...")
    sleep(1)
}

func endGame(player: Player) {
    if player.power > 5 && player.intelligence > 5 && player.luck > 5 {
        print("Congratulations! You have successfully completed the game!")
    } else {
        print("You have reached the final room, but your stats are too weak to proceed. Better luck next time!")
    }
    exit(0)
}

func game() {
    let player = Player(power: 5, intelligence: 5, luck: 5)

    // Room 1 - First Battle
    print("Entering Room 1 - First Battle")
    if battle(player: player, enemyPower: 10) {
        print("You won the battle! Proceeding to the next room.")
        roomTransition(player: player)
    } else {
        print("You lost the battle. Game over.")
        exit(0)
    }

    // Room 2 - First Mini Game
    print("Entering Room 2 - First Mini Game")
    if miniGame(player: player, requiredIntelligence: 4) {
        print("You passed the mini game! Proceeding to the next room.")
        roomTransition(player: player)
    } else {
        print("You failed the mini game. Game over.")
        exit(0)
    }

    // Room 3
    print("Entering Room 3")
    roomTransition(player: player)

    // Room 4 - Second Mini Game
    print("Entering Room 4 - Second Mini Game")
    if miniGame(player: player, requiredIntelligence: 6) {
        print("You passed the mini game! Proceeding to the next room.")
        roomTransition(player: player)
    } else {
        print("You failed the mini game. Game over.")
        exit(0)
    }

    // Room 5
    print("Entering Room 5")
    roomTransition(player: player)

    // Room 6 - Second Battle
    print("Entering Room 6 - Second Battle")
    if battle(player: player, enemyPower: 15) {
        print("You won the battle! Proceeding to the final room.")
        roomTransition(player: player)
    } else {
        print("You lost the battle. Game over.")
        exit(0)
    }

    // Final Room
    print("Entering Final Room")
    endGame(player: player)
}

game()