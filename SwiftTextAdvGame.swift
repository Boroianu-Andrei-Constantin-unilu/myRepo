import Foundation

protocol Command {
    func execute()
}

class Map {
    func draw() {
        print("🚪 🎲")
        print("🚪 🔑")
    }
}

class DiceGame {
    private var playerWins = 0
    private var computerWins = 0

    func start() {
        while playerWins < 5 && computerWins < 5 {
            let playerRoll = Int.random(in: 1...6)
            let computerRoll = Int.random(in: 1...6)

            print("You rolled a \(playerRoll), computer rolled a \(computerRoll).")

            if playerRoll > computerRoll {
                playerWins += 1
                print("You won this round!")
            } else {
                computerWins += 1
                print("Computer won this round.")
            }

            print("Score: Player \(playerWins) - Computer \(computerWins)\n")
        }

        if playerWins >= 5 {
            print("Congratulations, you won the dice game! You may proceed to the trap room game.")
            let trapRoomGame = TrapRoomGame()
            trapRoomGame.start()
        } else {
            print("Sorry, you lost the dice game. Better luck next time.")
            exit(0)
        }
    }
}

class TrapRoomGame {
    private let correctPath: [String] = ["N", "E", "N", "W", "N", "E", "N", "E", "S", "E"]
    private var currentPosition = 0
    private var health = 10

    func start() {
        print("You are now in the trap room. Type 'help' for instructions.")
        while health > 0 && currentPosition < correctPath.count {
            let input = readLine()?.uppercased() ?? ""
            let command: Command

            switch input {
            case "LOOK":
                command = LookCommand()
            case "HELP":
                command = HelpCommand()
            case "N", "E", "S", "W":
                command = GoCommand(direction: input, trapRoomGame: self)
            default:
                print("Invalid command. Type 'help' for instructions.")
                continue
            }

            command.execute()
        }

        if health <= 0 {
            print("You ran out of health. Game over.")
        } else {
            print("Congratulations! You have successfully navigated the trap room.")
        }

        exit(0)
    }

    func step(direction: String) {
        if direction == correctPath[currentPosition] {
            currentPosition += 1
        } else {
            health -= 1
            print("Wrong step. Health: \(health)")
        }
    }
}

class LookCommand: Command {
    func execute() {
        print("You are in a trap room. Choose a direction (N, E, S, W) to navigate through the room without stepping on traps. Health: 10")
    }
}

class HelpCommand: Command {
    func execute() {
        print("Commands:\n- go [direction]: Move in the specified direction (N, E, S, W).\n- look: Get a description of the room.\n- help: Show this help message.")
    }
}

class GoCommand: Command {
    let direction: String
    let trapRoomGame: TrapRoomGame

    init(direction: String, trapRoomGame: TrapRoomGame) {
        self.direction = direction
        self.trapRoomGame = trapRoomGame
    }

    func execute() {
        trapRoomGame.step(direction: direction)
    }
}

let map = Map()
map.draw()

let diceGame = DiceGame()
diceGame.start()
