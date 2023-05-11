import Foundation

protocol Command {
    func execute()
}

class GoCommand: Command {
    let direction: String
    let game: TrapRoomGame
    
    init(direction: String, game: TrapRoomGame) {
        self.direction = direction
        self.game = game
    }
    
    func execute() {
        game.move(direction: direction)
    }
}

class LookCommand: Command {
    let game: TrapRoomGame
    
    init(game: TrapRoomGame) {
        self.game = game
    }
    
    func execute() {
        game.describeRoom()
    }
}

class HelpCommand: Command {
    func execute() {
        print("Commands:\n  go <north|east|south|west> - Move in the specified direction.\n  look - Get a description of the current room.\n  help - Show this help message.")
    }
}

class Controller {
    func parseInput(input: String, game: TrapRoomGame) -> Command? {
        let inputParts = input.lowercased().split(separator: " ")
        
        if inputParts.count == 0 {
            return nil
        }
        
        let command = String(inputParts[0])
        
        switch command {
        case "go":
            if inputParts.count > 1 {
                let direction = String(inputParts[1])
                return GoCommand(direction: direction, game: game)
            }
        case "look":
            return LookCommand(game: game)
        case "help":
            return HelpCommand()
        default:
            return nil
        }
        
        return nil
    }
}

class DiceRollingGame {
    private var playerWins = 0
    private var computerWins = 0
    
    func play() {
        print("Welcome to the Dice Rolling game!")
        for i in 1...9 {
            print("Roll \(i) of 9:")
            let playerRoll = Int.random(in: 1...6)
            let computerRoll = Int.random(in: 1...6)
            print("You rolled: \(playerRoll)")
            print("Computer rolled: \(computerRoll)")
            
            if playerRoll > computerRoll {
                playerWins += 1
                print("You win this round!")
            } else {
                computerWins += 1
                print("Computer wins this round!")
            }
            
            if computerWins >= 5 {
                print("Computer has won 5 times! Game over.")
                exit(0)
            }
        }
        
        if playerWins >= 5 {
            print("Congratulations, you won the Dice Rolling game! Proceed to the next room.")
        } else {
            print("You lost the Dice Rolling game. Better luck next time!")
            exit(0)
        }
    }
}

class TrapRoomGame {
    private var playerPosition: (Int, Int) = (0, 0)
    private var exitPosition: (Int, Int) = (2, 2)
    private var health: Int = 10
    
    func play() {
        let controller = Controller()
        
        print("Welcome to the Trap Room game!")
        print("Type 'help' for available commands.")
        
        while health > 0 {
            print("Health: \(health)")
            print("Enter a command:")
            
            if let input = readLine(),
               let command = controller.parseInput(input: input, game: self) {
                command.execute()
                
                if playerPosition == exitPosition {
                    print("Congratulations, you've reached the exit! You won the Trap Room game!")
                    exit(0)
                }
            } else {
                print("Invalid command. Type 'help' for available commands.")
            }
        }
        
        print("You've run out of health. Game over.")
exit(0)
}

func move(direction: String) {
    var newPosition = playerPosition
    
    switch direction {
    case "north":
        newPosition.1 += 1
    case "east":
        newPosition.0 += 1
    case "south":
        newPosition.1 -= 1
    case "west":
        newPosition.0 -= 1
    default:
        print("Invalid direction. Use north, east, south, or west.")
        return
    }
    
    if newPosition.0 < 0 || newPosition.1 < 0 || newPosition.0 > 2 || newPosition.1 > 2 {
        print("You can't move in that direction.")
        return
    }
    
    playerPosition = newPosition
    print("Moved \(direction).")
    
    if playerPosition != exitPosition {
        health -= 1
    }
}

func describeRoom() {
    print("You are in a locked room with a 3x3 grid. There's an exit on the opposite corner. Beware of traps!")
}

}

func drawMap() {
print("Map:")
print("+---+---+")
print("| D |   |")
print("+---+---+")
print("|   | T |")
print("+---+---+")
print("|   |   |")
print("+---+---+")
print("D - Dice Rolling Room")
print("T - Trap Room")
}

func main() {
drawMap()
let diceRollingGame = DiceRollingGame()
diceRollingGame.play()

let trapRoomGame = TrapRoomGame()
trapRoomGame.play()

}

main()