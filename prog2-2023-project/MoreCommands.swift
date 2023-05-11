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

class OpenCommand: Command {
    init() {}

    func run(game: Game, arguments: [String]) {
            print("You have opened the door.")
        }
    }

class ForceOpenCommand: Command {
    init() {}

    func run(game: Game, arguments: [String]) {
            print("You have force-opened the door.")
        }
    }

class LookCommand: Command {
    init() {}

    func run(game: Game, arguments: [String]) {
            let input: String? = readLine()
            switch input {
                case "room":
                print("Room with various items")
                case "treasure":
                print("Rich, but useless")
                case "chest":
                print("Map for future level")
                case "statue":
                print("Loose arm, might unlock exit")
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