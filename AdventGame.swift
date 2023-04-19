import Foundation

// Define the game's rooms
let rooms = [
    "start": "You are in a dark room. There is a door to the north.",
    "north": "You are in a bright room. There is a key on the table.",
    "east": "You are in a small room. There is a window to the east.",
    "west": "You are in a large room. There is a chest in the corner.",
    "south": "You are in a dusty room. There is a book on the shelf."
]

// Define the game's commands
let commands = [
    "north": "start",
    "east": "north",
    "west": "north",
    "south": "start",
    "look": "You see nothing of interest.",
    "take key": "You take the key.",
    "open chest": "You open the chest and find a treasure."
]

// Define the game loop
var currentRoom = "start"
while true {
    // Print the current room description
    print(rooms[currentRoom]!)
    
    // Get user input
    let input = readLine()!
    
    // Parse the user input
    let words = input.components(separatedBy: " ")
    let command = words
    let argument = words.count > 1 ? words[1] : ""
    
    // Execute the command
    if let newRoom = commands["\(command) \(argument)"] {
        currentRoom = newRoom
    } else if let newRoom = commands[command] {
        currentRoom = newRoom
    } else {
        print("I don't understand that command.")
    }
}