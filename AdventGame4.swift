import Foundation

struct Player {
    var power: Int
    var intelligence: Int
    var luck: Int
}

enum GameState {
    case ongoing
    case lost
    case won
}

class Game {
    var player: Player
    var roomNumber: Int
    var gameState: GameState

    init() {
        self.player = Player(power: Int.random(in: 1...10),
                             intelligence: Int.random(in: 1...10),
                             luck: Int.random(in: 1...10))
        self.roomNumber = 1
        self.gameState = .ongoing
    }

    func printStats() {
        print("Player Stats: ")
        print("Power: \(player.power)")
        print("Intelligence: \(player.intelligence)")
        print("Luck: \(player.luck)\n")
    }

    func battle() {
        let enemyPower = Int.random(in: 1...10)
        if player.power >= enemyPower {
            print("You won the battle!")
            player.power += 1
        } else {
            print("You lost the battle...")
            player.power -= 1
        }
        printStats()
    }

    func miniGame() {
        let correctAnswer = Int.random(in: 1...5)
        print("Guess the correct number between 1 and 5:")
        if let userInput = readLine(), let guessedNumber = Int(userInput), guessedNumber == correctAnswer {
            print("You won the mini game!")
            player.intelligence += 1
        } else {
            print("You lost the mini game...")
            player.intelligence -= 1
        }
        printStats()
    }

    func challenge() {
        let challengeOutcome = Int.random(in: 1...2)
        if challengeOutcome == 1 {
            print("You passed the challenge!")
            player.luck += 1
        } else {
            print("You failed the challenge...")
            player.luck -= 1
        }
        printStats()
    }

    func moveToNextRoom() {
        roomNumber += 1
        print("You entered room \(roomNumber).")
    }

    func play() {
        while gameState == .ongoing {
            switch roomNumber {
            case 1, 3:
                battle()
                moveToNextRoom()
            case 2, 5:
                miniGame()
                moveToNextRoom()
            case 4:
                challenge()
                moveToNextRoom()
            case 6:
                if player.power >= 5 && player.intelligence >= 5 && player.luck >= 5 {
                    print("Congratulations! You won the game!")
                    gameState = .won
                } else {
                    print("Sorry, your stats are too weak. Game over.")
                    gameState = .lost
                }
            default:
                break
            }
        }
    }
}

let game = Game()
game.play()