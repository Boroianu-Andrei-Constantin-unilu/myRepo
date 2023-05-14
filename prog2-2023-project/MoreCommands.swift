class PickUpCommand: Command {
    init() {}
    func run(game: Game, arguments: [String]) {
            let input: String? = readLine()
            switch input {
                case "map":
                print("Great idea! You might need it later in your adventure...")
                case "":
                print("Which object are you planning to pick up?")
                default:
                print("This object is non-existent, my knight.")
            }
        }
    }

class LookCommand: Command {
    init() {}
    func run(game: Game, arguments: [String]) {
            let input: String? = readLine()
            switch input {
                case "room":
                print("The treasure room is filled with various items, such as a statue, a chest.")
                case "treasure":
                print("The treasure lying around you is abundent, but useless for your purposes.")
                case "chest":
                print("The chest contains an indeschiperable map of the pyramid. You decide to leave it where it is.")
                case "statue":
                print("The statue's left arm seems loose...")
                case "stop":
                exit(0)
                default:
                print("?")
            }
        }
    }
class PullCommand: Command {
    init() {}
    func run(game: Game, arguments: [String]) {
        let input: String? = readLine()
        switch input {
            case "arm":
            (game as! AdvGame).armPulled = true
            case "":
            print("Which item would you like to act upon?")
            default:
            print("?")
            }
            
        }
    }

class InventoryCommand: Command {
    init() {}
    func run(game: Game, arguments: [String]) {
            let inventory = (game as! AdvGame).inventory
            if inventory.isEmpty {
                print("Alas, you have carried nothing so far.")
            } else {
                print("You have the following items in possesion:")
                for item in inventory {
                    print(" -- \(item.name)")
                }
            }
        }
    }

class StatsCommand: Command {
    init() {}
    func run(game: Game, arguments: [String]) {
        let player = (game as! AdvGame).player
        print("Player stats: ")
        print("Power: \(player.power), Knowledge: \(player.knowledge), Luck: \(player.luck)")
    }
}

class WalkThroughCommand: Command {
    init() {}
    func run(game: Game, arguments: [String]) {
        print("Room A: Look at the statue and pull the loose arm. If 30 seconds pass without the door being open, you die.")
        print("Room B: Automated: If you get less wins than your opponent on a dice-rolling game, you die.")
        print("Room C: Automated or manual: You must fight the enemy until he loses health, otherwise you die.")
        print("Room D: Pass the trap room successfully (. not #) in less than 45 seconds, by stepping on the right tiles.")
        print("Room E: Automated or manual: If you pick the last ball, you win. If the enemy does, you die.")
        print("Room F: Automated or manual: You can beat the final boss by either playing one of the previous mini games, or by conventional fight.")
        print("Room G: This room triggers the end of the game, with a congratulatory message.")
    }
}