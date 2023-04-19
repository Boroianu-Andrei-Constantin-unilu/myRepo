print("Welcome to the Adventure Game!")

// Define the world
class Room {
    var name: String
    var description: String
    var exits: [String: Room]
    
    init(name: String, description: String) {
        self.name = name
        self.description = description
        self.exits = [:]
    }
    
    func addExit(direction: String, room: Room) {
        exits[direction] = room
    }
}

let startRoom = Room(name: "Start Room", description: "You are in a dark room. You can see a faint light to the east.")
let endRoom = Room(name: "End Room", description: "You are in a bright room. There is a door to the west.")

startRoom.addExit(direction: "east", room: endRoom)
endRoom.addExit(direction: "west", room: startRoom)

// Define the player
class Player {
    var name: String
    var currentRoom: Room
    
    init(name: String, currentRoom: Room) {
        self.name = name
        self.currentRoom = currentRoom
    }
    
    func move(direction: String) {
        if let nextRoom = currentRoom.exits[direction] {
            currentRoom = nextRoom
            print("You move to the \(currentRoom.name).")
            print(currentRoom.description)
        } else {
            print("You can't go that way.")
        }
    }
}

// Start the game
print("What is your name?")
let playerName = readLine() ?? "Player"
let player = Player(name: playerName, currentRoom: startRoom)

print("Hello, \(player.name)!")
print(player.currentRoom.description)

// Game loop
while true {
    print("What do you want to do?")
    let input = readLine()?.lowercased() ?? ""
    let commands = input.split(separator: " ")
    
    if commands.count > 0 {
        let command = String(commands[0])
        
        switch command {
        case "go":
            if commands.count > 1 {
                let direction = String(commands[1])
                player.move(direction: direction)
            } else {
                print("Go where?")
            }
        case "quit":
            print("Thanks for playing!")
            System.exit(0)
        default:
            print("I don't understand.")
        }
    }
}