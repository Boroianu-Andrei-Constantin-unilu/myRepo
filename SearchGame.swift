import Foundation

protocol Command {
    func execute() -> String
}

struct LookCommand: Command {
    var target: String
    func execute() -> String {
        switch target {
        case "room":
            return "You are in an ancient pyramid room. There's a chest, a table with various artifacts, and a statue with a loose hand."
        case "chest":
            return "The chest contains a map."
        case "table":
            return "The table has various artifacts."
        case "statue":
            return "The statue has a loose hand, which looks like a lever."
        default:
            return "I don't know what you're looking at."
        }
    }
}

struct PullCommand: Command {
    func execute() -> String {
        return "You pull the statue's hand, and a secret door opens!"
    }
}

struct CollectCommand: Command {
    var item: String
    var inventory: UnsafeMutablePointer<Set<String>>
    func execute() -> String {
        if item == "map" {
            inventory.pointee.insert(item)
            return "You collected the map from the chest."
        } else {
            return "You cannot collect this item."
        }
    }
}

struct InventoryCommand: Command {
    var inventory: Set<String>
    func execute() -> String {
        if inventory.isEmpty {
            return "You have no items in your inventory."
        } else {
            return "Inventory: \(Array(inventory).joined(separator: ", "))"
        }
    }
}

struct EnterCommand: Command {
    func execute() -> String {
        return "You entered the secret door. The mini game has ended."
        exit(0)
    }
}

var inventory = Set<String>()

func handleInput(_ input: String) -> String {
    let commandParts = input.lowercased().split(separator: " ")
    let command = commandParts[0]

    guard commandParts.count > 1 else {
        return "Missing arguments for the \(command) command."
    }
    let argument = String(commandParts[1])

    switch command {
    case "look":
        return LookCommand(target: argument).execute()
    case "pull":
        return PullCommand().execute()
    case "collect":
        return CollectCommand(item: argument, inventory: withUnsafeMutablePointer(to: &inventory) { $0 }).execute()
    case "inventory":
        return InventoryCommand(inventory: inventory).execute()
    case "enter":
        return EnterCommand().execute()
    default:
        return "Unknown command."
    }
}

print("Welcome to the Ancient Pyramid Mini Game!")
print("Enter your command or type 'quit' to exit the game.")

while true {
    print("> ", terminator: "")
    if let input = readLine(), input.lowercased() != "quit" {
        print(handleInput(input))
    } else {
        print("Thank you for playing! Goodbye!")
        break
    }
}