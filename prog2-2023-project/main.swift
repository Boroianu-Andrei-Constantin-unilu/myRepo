import Foundation
import Dispatch
#if os(macOS)
import Darwin
#else
import Glibc
#endif

/// DO NOT MODIFY!
/**
 The `Command` protocol is the basis for the interpretation of textual commands entered by the user.  The user can enter a command using the `Parser`, in the format `keyword arguments` where `keyword` is associated to a specific type of `Command` (see the `Controller` class), and arguments are optional strings that allow a command to execute a parametrized job.
 */
protocol Command {
    
    /**
     Defines the job to be executed by a command.
     
     - Parameters:
        - game: Reference to the game instance in which the command is run
        - arguments: Array of (`String`) arguments
     */
    func run(game:Game, arguments: [String])
}

/// Error type used in `Controller`
enum CommandError:Error {
    
    /// No command registered for the indicated `keyword`
    case commandNotFound(keyword:String)
}

/**
    The `Controller` holds a registry of keywords and associated commands.
 */
class Controller {
    
    /// A dictionary mapping keywords to associated commands
    private(set) var commands = [String:Command]()

    /// A reference to the game instance, need for running commands
    let game:Game

    /// Initializer
    init(game:Game) {
        self.game = game
    }
    
    /**
     Adds a mapping between the provided keyword and command.
     
     - Parameters:
        - keyword: The keyword which a user needs to enter to execute the command
        - command: The command to associate with
     */
    func register(keyword:String, command:Command) {
        self.commands[keyword] = command
    }
    
    /**
     Removes the command for the given keyword (if any).
     
     - Parameters:
        - keyword: The keyword whose associated command shall be removed
     */
    func deregister(keyword:String) {
        self.commands.removeValue(forKey: keyword)
    }
    
    /**
     Executes a command for the provided keyword, with the provided arguments.
     
     - Parameters:
        - keyword: The keyword entered by the user
        - arguments: The array of (`String`) arguments entered by the user
     
     - Throws: `CommandError.commandNotFound` if no command is registered for the provided keyword
     */
    func execute(command keyword:String, arguments: [String]) throws {
        if let command = self.commands[keyword] {
            command.run(game: self.game, arguments: arguments)
        } else {
            throw CommandError.commandNotFound(keyword: keyword)
        }
    }
    
}

// MARK: Concrete command implementations

/// This command prints all keywords known to a game's controller.
class HelpCommand:Command {

    /// Initializer
    init() {}
    
    func run(game: Game, arguments: [String]) {
        print("Available commands: \(game.controller.commands.keys.joined(separator: " "))")
    }
}

/// This command stops a game, based on its `finished` property (cf. `Game` protocol)
class StopCommand:Command {

    /// Initializer
    init() {}

    func run(game: Game, arguments: [String]) {
        game.finished = true
    }
}

/// This command allows to change the `currentRoom` of a game instance, based on the currently available exits. As a single argument, the direction is expected (cf. `Direction` enum).
class GoCommand:Command {

    /// Initializer
    init() {}

    func run(game: Game, arguments: [String]) {
        // only allow a single argument
        guard arguments.count < 2 else {
            print("Could you please decide where to go?")
            return
        }
        
        // require an argument
        guard let directionString = arguments.first else {
            print("Please indicate a direction!")
            return
        }
        
        // retrieve the enum value for the given string
        guard let direction = Direction.init(rawValue: directionString) else {
            print("Unknown direction!")
            return
        }

        (game as! AdvGame).playerDirection = direction
        
        // retrieve the room in this direction (if any)
        guard let nextRoom = game.currentRoom.nextRoom(direction: direction) else {
            print("You cannot go \(direction) from here.")
            return
        }
     
        // condition required to unlock the door in the first room
        if game.currentRoom.name == "Room A" && direction == .East && !(game as! AdvGame).armPulled {
            print("The door is locked! What could be done to possibly open it?")
            game.currentRoom.name = "Room A"
            return
        }
        
        // set the game's current room
        game.currentRoom = nextRoom
    }
}

/// DO NOT MODIFY!
/// The `Game` protocol shall be adopted by a concrete game *class*.
protocol Game:AnyObject {
    
    /// A reference to a controller, comprising the recognized keywords and commands
    var controller:Controller { get }
    
    /// A flag symbolizing whether the game is still running or not
    var finished:Bool { get set }
    
    /// The room in which the player is currently located in
    var currentRoom:Room { get set }
    
}

/// DO NOT MODIFY!
/// Error type used in the controller
public enum ParserError:Error {
    
    /// Only white-space characters entered
    case noInputGiven
}

/// The `Parser` is responsible for retrieving user input and passing it over to the `Controller` to run commands.
class Parser {
    
    /// A symbol printed to show that user input is expected
    private let promptSymbol = ">"

    /// A reference to a game's controller, necessary to run commands
    private let controller:Controller
    
    /// Initializer
    public init(controller:Controller) {
        self.controller = controller
    }
    
    /**
     Retrieves user input from the command line (`stdin`) and runs the requested commands through the `Controller`.
     
     - Throws:
        - `ParserError.noInputGiven` if no input at all or only spaces were entered. At least one token (word) is expected from the user, arguments are optional but may be required by specific commands.
        - `CommandError.commandNotFound` if no command is registered for the first provided token (rethrown from `Controller.execute`)
     */
    func readCommand() throws {
        // print prompt symbol to invite user for input
        print("\(self.promptSymbol) ", terminator: "")
        
        // read a line (if any)
        guard let input = readLine() else {
            throw ParserError.noInputGiven
        }
        
        // split line into tokens
        var tokens = input.split(separator: " ").map { String($0) }
        
        // require at least 1 token
        guard tokens.count > 0 else {
            throw ParserError.noInputGiven
        }
        
        let commandKeyword = tokens.removeFirst()
        let arguments = tokens
        
        // try to execute a command for the given keyword with its arguments
        try self.controller.execute(command: commandKeyword, arguments: arguments)
    }
    
}

/// DO NOT MODIFY!
/// Compass points used for setting room exits
enum Direction:String {
    /// 4 main cardinal points
    case North, East, South, West
}

/// A rudimentary implementation of a room (location) within the game map, based on a name and exits to other rooms. The exits are labeled with a `Direction`.
class Room {
    
    /// The name of the room (does not need to be an identifier)
    var name:String
    
    /// The exit map (initially empty)
    var exits = [Direction:Room]()

    var objects: [String : GameObject] = [String:GameObject]()
    
    /// Initializer
    init(name:String) {
        self.name = name
    }
    
    /**
     This function allows to retrieve a neighboring room based on its exit direction from inside the current room.
     
     - Parameters:
        - direction: A `Direction`
     - Returns: The room through the exit in the next direction (if any)
     */
    func nextRoom(direction:Direction) -> Room? {
        return exits[direction]
    }
    
}

/// Extension for the `CustomStringConvertible` protocol.
extension Room:CustomStringConvertible {

    /// The description includes the name of the room as well as all possible exit directions
    var description: String {
        return "You are in \(self.name)\n\nExits:\n\(self.exits.keys.map { "- \($0.rawValue)" }.joined(separator: "\n"))"
    }
    
}

/**
Beginning of main class
*/

/**
Map of game                    
        Room A ←-----→  Room B          Room E  ←-----→ Room F
                           |              |               |
                           |              |               |
                           |              |               |
                        Room C ←-----→  Room D      Room G (Exit)
*/

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

class GameObject {
    var name: String
    var description: String
    var isCollectible: Bool

    init(name: String, description: String, isCollectible: Bool = false) {
        self.name = name
        self.description = description
        self.isCollectible = isCollectible
    }
}

class TreasureRoom {

    /* The DispatchQueue, TimeInterval, and Thread features are responsible for creating a 30-second timer. */
    private var inputQueue: DispatchQueue

    init() {
        inputQueue = DispatchQueue(label: "inputQueue")
    }

    func play() {
        print("Welcome to the pyramid, my dear knight! Your main task is to collect the Holy Grail, in order to cure the king and save the kingdom from total anarchy.")
        print("You find yourself in a treasure room, with a chest and a statue inside.")

        let timer: TimeInterval = 30
        let timesup = Date(timeIntervalSinceNow: timer)

        let parser = Parser(controller: game.controller)

        var finished = false

        inputQueue.async {
            while !finished {
            do {
                try parser.readCommand()
            } catch {
                print("Error: \(error)")
            }

            if (game as! AdvGame).armPulled {
            print("As soon as you pull the arm of the statue, you hear a sound...")
            print("The door has opened! You can advance to the next level!")
            finished = true
            }
        }
        }

        while !finished && Date() < timesup {
            let timeleft = timesup.timeIntervalSinceNow
            Thread.sleep(forTimeInterval: 1)
        }

        if !((game as! AdvGame).armPulled) {
            print("Alas, you did not manage to unlock the door in time, and were intoxicated as a result...")
            print("If only you searched the statue more clearly...")
            exit(0)
        }
    }
}

class DiceRoll {
    var knightWinsNr: Int
    var enemyWinsNr: Int

    /* The gamesPlayed variable helps to create different messages, based on whether the player plays the mini game against a regular enemy or the final boss.*/

    var gamesPlayed = 0

    init (knightWinsNr: Int, enemyWinsNr: Int, gamesPlayed: Int) {
        self.knightWinsNr = knightWinsNr
        self.enemyWinsNr = enemyWinsNr
        self.gamesPlayed = gamesPlayed
    }

    func play() {
        gamesPlayed += 1

        if gamesPlayed == 1 {
            print("I have been waiting for you, my friend.")
            print("In order to get past the next room, you will have to beat me at a simple dice-rolling game.")
            print("Out of nine throws, whoever gets a larger number five times, wins. ")
            print("If you win, I will let you pass. If not, demise awaits you.")
            print("Let us proceed!")
        } else {
            print("Then may the best of us win!")
        }

        for roll: Int in 1...9 {
            print("Round \(roll) out of 9:")
            let knightRoll: Int = Int.random(in: 1...6)
            let enemyRoll: Int = Int.random(in: 1...6)
            print("The knight rolled a \(knightRoll), against the enemy roll \(enemyRoll).")

            if knightRoll < enemyRoll {
                print("You seem to have knocked on Death's door. Will he answer?")
                enemyWinsNr += 1
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
            }
            if knightRoll > enemyRoll {
                print("You seem to be lucky this time. Will you make it to the end?")
                knightWinsNr += 1
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
            }
            if knightRoll == enemyRoll {
                print("None of us are going to progress this way. Let us roll again!")
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
            }

            if gamesPlayed == 1 {
                if enemyWinsNr == 5 || (roll >= 9 && (enemyWinsNr > knightWinsNr)){
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis a sad thing that your quest must end here...")
                print("Alas, you have lost the match and your soul will be taken by the Grim Reaper as a result.")
                print("If only you had been slightly luckier...")
                exit(0)
                }
                if knightWinsNr == 5 || (roll >= 9 && (enemyWinsNr < knightWinsNr)) || knightWinsNr == enemyWinsNr {
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis cannot be! You truly ARE worthy of your name, my friend...")
                print("I am a creature of my word. You are free to continue your journey.")
                break
                }
            } else {
                if enemyWinsNr == 5 || (roll >= 9 && (enemyWinsNr > knightWinsNr)){
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis a sad thing that your quest must end here...")
                print("Alas, you have run out of luck and were slain to death by your nemesis!")
                print("Better luck next time!")
                exit(0)
                }
                if knightWinsNr == 5 || (roll >= 9 && (enemyWinsNr < knightWinsNr)) || knightWinsNr == enemyWinsNr {
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis cannot be! You truly ARE worthy of your name, my friend!")
                print("Very well, then. I will die by your sword, as it is honourable to do so...")
                print("You triumphantly take the grail from the pedestal.")
                print("Now you can return to the kingdom and cure the King!")
                break
                }
            }
            
        }
    }
}

class Combatant {
    var health: Int

    init (health: Int) {
        self.health = health
    }

    func alive() -> Bool {
        return health > 0
    }
}

enum Move {
    case attack
    case block
}

class Battle1 {

    /* The fightCount variable helps to create different messages, based on whether the player plays the mini game against a regular enemy or the final boss.*/

    var fightCount = 0

    init (fightCount: Int) {
        self.fightCount = fightCount
    }

    func fight() {
        fightCount += 1

        if fightCount == 1 {
            print("Out of nowhere, a mummy shows up in front of you!")
            print("Stop right there, traveler! You will never get the holy Grail!")
            print("It looks like time has come to fight... The battle beggins!")
        } else {
            print("Well, then... EN GARDE!")
        }

        let knight: Combatant = Combatant(health: 10)
        let mummy: Combatant = Combatant(health: 10)

        while knight.alive() && mummy.alive() {
            print("Knight HP: \(knight.health), Mummy HP: \(mummy.health)")
            print("The enemy is ready to strike. 1 (attack) or 2 (block)?")

            var knightMove: Move
            if let choice = readLine(), let action = Int(choice) {
                knightMove = action == 1 ? .attack : .block
            } else {
                knightMove = .attack
            }

            let mummyMove = arc4random_uniform(2) == 0 ? Move.attack : Move.block

            switch (knightMove, mummyMove) {
                case (.attack, .block):
                let posib = arc4random_uniform(2)
                if posib == 0 {
                    print("Yikes! The enemy managed to block your attack...")
                } else if posib == 1 {
                    print("Success! You managed to strike the enemy!")
                    mummy.health -= 1
                }
                case (.block, .attack):
                let option = arc4random_uniform(2)
                if option == 0 {
                    print("Whew... That was close!")
                } else if option == 1 {
                    print("Ouch! The enemy managed to break your attack and strike you...")
                    knight.health -= 1
                }
                case (.attack, .attack):
                let possibility = arc4random_uniform(3)
                if possibility == 0 {
                    print("Argh! That hurt! (X2) Both of you lost health...")
                    knight.health -= 1
                    mummy.health -= 1
                } else if possibility == 1 {
                    print("Argh! That hurt! The enemy managed to attack you...")
                    knight.health -= 1
                } else if possibility == 2 {
                    print("Whew! You managed to strike the enemy...")
                    mummy.health -= 1
                }
                case (.block, .block):
                print("Whew...")
            }
        }

        if fightCount == 1 {
            if knight.alive() {
            print("Aaargh... I have failed my master... (Dies)")
            print("You managed to defeat your first enemy! Go get the Grail!")
            } else {
            print("Despite having fought like a hero, you were butchered like the fresh meat you now resemble.")
            print("Better luck next fight!")
            exit(0)
            }
        } else {
            if knight.alive() {
            print("Aaargh... You may have won this time, but we WILL meet again, I guarantee you!")
            print("You triumphantly take the grail from the pedestal.")
            print("Now you can return to the kingdom and cure the King!")
            } else {
            print("Alas, you fought valiantly against your nemesis, and died by his final blow...")
            print("The whole kingdom mourns your loss, but all hope for order is now gone!")
            exit(0)
            }
        }
    }
}

class TrapRoom {
    var x: Int
    var y: Int
    var currentPos: (Int, Int)
    var exitPos: (Int, Int)
    var health: Int

    /* The InputSemaphore, DispatchSemaphore, DispatchTime and Thread features are responsible for creating a 45-second timer. */
    /* The readNonBlock() and readInput() functions prevent an infinite loop of the map.*/

    let inputSemaphore = DispatchSemaphore(value: 0)

    private var theEnd = false
    private var nextStep: String? = nil

    func readNonBlock() -> String? {
        var input: String? = nil

        var fileDescriptorSet = fd_set()
        fileDescriptorSet.fds_bits.0 = 1 << FileHandle.standardInput.fileDescriptor

        var timeout = timeval(tv_sec: 0, tv_usec: 100000)

        let result = select(FileHandle.standardInput.fileDescriptor + 1, &fileDescriptorSet, nil, nil, &timeout)
        
        if result > 0 {
            let inputData = FileHandle.standardInput.availableData
            if !inputData.isEmpty {
                input = String(data: inputData, encoding: .utf8)?.trimmingCharacters(in: .whitespacesAndNewlines)
            }
        }
        return input
    }

    func readInput() {
        while !theEnd {
            if let typed = readNonBlock(), ["North", "East", "South", "West"].contains(typed) {
                nextStep = typed
                inputSemaphore.signal()
                }
            }
        }

    init(x: Int, y: Int, currentPos: (Int, Int), exitPos: (Int, Int), health: Int) {
        self.x = x
        self.y = y
        self.currentPos = (0, 0)
        self.exitPos = (x-1, y-1)
        self.health = health
    }

    var roomGrid: [[Bool]] = []

    func createSafePath() {
        roomGrid = Array(repeating: (Array(repeating: false, count: x)), count: y)
        var safePath: [(Int, Int)] = [currentPos]
        while let lastPos = safePath.last, lastPos != exitPos {
            let listOfMoves: [(Int, Int)] = [(lastPos.0-1, lastPos.1), (lastPos.0+1, lastPos.1), (lastPos.0, lastPos.1-1), (lastPos.0, lastPos.1+1)].filter { pos in
                pos.0 >= 0 && pos.1 >= 0 && pos.0 < x && pos.1 < y && !roomGrid[pos.1][pos.0]
            }

            if let nextStep = listOfMoves.randomElement() {
                safePath.append(nextStep)
                roomGrid[nextStep.1][nextStep.0] = true
            } else {
                safePath.removeLast()
            }
        }
    }

    func drawRoom() {
        for b in (0..<y).reversed() {
            for a in 0..<x {
                if currentPos == (a, b) {
                    print("Me", terminator: " ")
                }
                if exitPos == (a, b) {
                    print("Exit", terminator: " ")
                }
                if roomGrid[b][a] == true {
                    print("." , terminator: " ")
                } else {
                    print("#", terminator: " ")
                }
            }
            print()
        }
    }

    func play() {
        print("Suddenly, you hear a voice, which says: ")
        print("Welcome to your worst nightmare yet, traveler! Make sure you step on the right tiles in order to reach the exit...")
        print("...lest you discover your worst possible fate!")
        print("Length: \(x)")
        print("Height: \(y)")
        createSafePath()

        Thread {
            self.readInput()
        }.start()

        let begin = DispatchTime.now()

        while health > 0 {
            print("Health: \(health)")
            print("Where do you want to go?")
            drawRoom()
            print("Current position is: \(currentPos)")
            print("Exit position is: \(exitPos)")

            let passedTime = Double(DispatchTime.now().uptimeNanoseconds - begin.uptimeNanoseconds) / 1_000_000_000
            let remainingTime = Double(45) - passedTime

            if remainingTime <= 0 || inputSemaphore.wait(timeout: .now() + .seconds(Int(remainingTime))) == .timedOut {
                print("Suddenly, you feel the ground crumbling under you!")
                print("It is an unforgiving fall to the sharp spikes below...")
                print("Act faster next time!")
                exit(0)
            }
            let input = nextStep
            nextStep = nil
            var newPos = currentPos
            
            switch input {
                case "North":
                newPos.1 += 1
                case "South":
                newPos.1 -= 1
                case "East":
                newPos.0 += 1
                case "West":
                newPos.0 -= 1
                case "stop":
                exit(0)
                default:
                print("Wrong direction: try typing 'North', 'South', 'East', 'West' instead!")
                continue
            }

            if (newPos.0 < 0 || newPos.1 < 0 || newPos.0 >= x || newPos.1 >= y) {
                print("Alas, there is a wall in front of you... Try going somewhere else!")
                continue
            }

            if currentPos != exitPos && health > 0 {
            if !roomGrid[newPos.1][newPos.0] {
                print("Oops! You have activated a trap! Be more careful...")
                health -= 1
            }
            }

            currentPos = newPos

            if currentPos == exitPos {
                print("Current position is: \(currentPos)")
                print("Exit position is: \(exitPos)")
                print("It looks like you managed to pass the trap successfully...")
                print("There is no time for admiration: you must conquer the Grail!")
                theEnd = true
                break
            }
        }

        theEnd = true

        if health == 0 {
            print("You have suffered too many injuries and cannot continue your adventure.")
            print("If only you were a little more careful...")
            exit(0)
        }
    }
}

class Ball21 {

    var ballsNr = 21

    /* The roundsPlayed variable helps to create different messages, based on whether the player plays the mini game against a regular enemy or the final boss.*/

    var roundsPlayed = 0

    init(ballsNr: Int, roundsPlayed: Int) {
        self.ballsNr = ballsNr
        self.roundsPlayed = roundsPlayed
    }

    func play() {
        roundsPlayed += 1

        if roundsPlayed == 1 {
            print("As you enter the next room, you spot another enemy!")
            print("Hello, stranger! Welcome to your new obstacle... a 21-ball game!")
            print("We have to take turns and remove up to 3 balls per turn.")
            print("Whoever removes the last ball is the winner.")
            print("If you win, you may pass. If not, I must kill you.")
            print("Let the match start!") 
        } else {
            print("In this case, let the winner take it all!")
        }
        while ballsNr > 0 {
            playerTurn()
            if ballsNr == 0 {
                if roundsPlayed == 1 {
                    print("My word, are you not a skilled one!")
                    print("Very well, I will keep my promise and let you pass...")
                    return
                } else {
                    print("My word, my luck seems to have run out!")
                    print("I guess this is it, then... You may take the Holy Grail, dear knight...")
                    print("You triumphantly take the grail from the pedestal.")
                    print("Now you can return to the kingdom and cure the King!")
                    return
                }
                
            }
            enemyTurn()
            if ballsNr == 0 {
                if roundsPlayed == 1 {
                    print("'Twas nice to meet you, stranger...")
                    print("You have met your demise at the hands of your adversary! If only you had a different strategy...")
                    exit(0)
                } else {
                    print("Tsk, tsk, tsk... I genuinely thought you were better than that!")
                    print("You have just been beheaded by your nemesis as a punishment for your loss...")
                    print("Peace will now be a long lost dream in a kingless kingdom!")
                    exit(0)
                }
                
            }
        }
    }

    func playerTurn() {
        while true {
            print("How many balls do you desire to remove (1 to 3)?")
            let input = readLine() ?? ""
            if let ballsRemoved = Int(input), ballsRemoved >= 1, ballsRemoved <= 3 {
                if ballsRemoved <= ballsNr {
                    ballsNr -= ballsRemoved
                    print("Balls remaining: \(ballsNr)")
                    break
                } else {
                    print("You have taken \(ballsRemoved). There are only \(ballsNr) left to remove.")
                }
            } else {
                print("I am sorry, only 1, 2 or 3 balls can be removed.")
            }
        }
    }

    func enemyTurn() {
        let ballsRemoved = min(ballsNr, Int.random(in: 1...3))
        ballsNr -= ballsRemoved
        print("I have taken \(ballsRemoved) balls. \(ballsNr) remain.")
    }
}

class Battle2 {
    var player: Combatant = Combatant(health: 10)
    var master: Combatant = Combatant(health: 10)

    init (player: Combatant, master: Combatant) {
        self.player = player
        self.master = master
    }

    func play() {
        print("Hahaha... So you have managed to go this far, eh?")
            print("You look much smarter than you are, my dear knight...")
            print("Yet my joints are not that weak, either! ;)")
            print("Therefore, I will give you a choice: you either fight me, or play one of the previous minigames against me.")
            print("The last man standing lives! So, what is your choice, knight?")

            let diceRoll: DiceRoll = DiceRoll(knightWinsNr: 0, enemyWinsNr: 0, gamesPlayed: 1)
            let trapRoom: TrapRoom = TrapRoom(x: Int.random(in: 3...7), y: Int.random(in: 3...7), currentPos: (0, 0), exitPos: (Int.random(in: 3...7), Int.random(in: 3...7)), health: 10)
            let ball21: Ball21 = Ball21(ballsNr: 21, roundsPlayed: 1)
            let battle1: Battle1 = Battle1(fightCount: 1)

            let input: String? = readLine()
            switch input {
                case "fight":
                battle1.fight()
                case "game":
                let choice = arc4random_uniform(2)
                if choice == 0 {
                    ball21.play()
                } else {
                    diceRoll.play()
                }
                default:
                print("Please choose between a classic battle or a game, my knight...")
            }
        }
    }

class AdvGame: Game {
    lazy var controller: Controller = Controller(game: self)
    lazy var parser: Parser = Parser(controller: self.controller)
    var finished: Bool = false
    var currentRoom: Room
    let rooms: [Room]
    var inventory: [GameObject] = []
    var player = Player()
    var playerDirection: Direction = .North

    /* The seven variables below prevent a mini game from being played a second time after a misspelled direction command.*/
    var armPulled = false
    var doorOpened = false
    var dicePlayed = false
    var battleFought = false
    var trapRoomPassed = false
    var played21 = false
    var finalPlayed = false

    init() {
        self.rooms = AdvGame.createMap()
        self.currentRoom = self.rooms.first!
        self.addCommands()
    }

    struct Item {
    var name: String
    }

    func exitGame() {
        exit(0)
    }

    struct Player {
        var power: Int = Int.random(in: 1...10)
        var knowledge: Int = Int.random(in: 1...10)
        var luck: Int = Int.random(in: 1...10)
    }

    func setStats() {
        print("Welcome to our adventure game. In order to proceed, please enter your character's stats as follows: ")

        print("Power: ")
        if let powerInput = readLine(), let power = Int(powerInput), power >= 5 && power <= 10 {
            player.power = power
        } else {
            print("Invalid input. Setting standard value: 5")
            player.power = 5
        }
        print("Knowledge: ")
        if let knowInput = readLine(), let knowledge = Int(knowInput), knowledge >= 5 && knowledge <= 10 {
            player.knowledge = knowledge
        } else {
            print("Invalid input. Setting standard value: 5")
            player.knowledge = 5
        }
        print("Luck: ")
        if let luckInput = readLine(), let luck = Int(luckInput), luck >= 5 && luck <= 10 {
            player.luck = luck
        } else {
            print("Invalid input. Setting standard value: 5")
            player.luck = 5
        }
    }

    class func createMap() -> [Room] {
        let roomA: Room = Room(name: "Room A")
        let roomB: Room = Room(name: "Room B")
        let roomC: Room = Room(name: "Room C")
        let roomD: Room = Room(name: "Room D")
        let roomE: Room = Room(name: "Room E")
        let roomF: Room = Room(name: "Room F")
        let roomG: Room = Room(name: "Room G")

        roomA.exits = [.East: roomB]
        roomB.exits = [.South: roomC]
        roomC.exits = [.East: roomD]
        roomD.exits = [.North: roomE]
        roomE.exits = [.East: roomF]
        roomF.exits = [.South: roomG]

        return [roomA, roomB, roomC, roomD, roomE, roomF, roomG]

    }

    func addCommands() {
        self.controller.register(keyword: "help", command: HelpCommand())
        self.controller.register(keyword: "go", command: GoCommand())
        self.controller.register(keyword: "stop", command: StopCommand())
        self.controller.register(keyword: "look", command: LookCommand())
        self.controller.register(keyword: "pickup", command: PickUpCommand())
        self.controller.register(keyword: "pull", command: PullCommand())
        self.controller.register(keyword: "inventory", command: InventoryCommand())
        self.controller.register(keyword: "stats", command: StatsCommand())
    }

    func play() {
    
    setStats()

    while !self.finished {
        let treasureRoom: TreasureRoom = TreasureRoom()
        let diceRoll: DiceRoll = DiceRoll(knightWinsNr: 0, enemyWinsNr: 0, gamesPlayed: 0)
        let trapRoom: TrapRoom = TrapRoom(x: Int.random(in: 3...7), y: Int.random(in: 3...7), currentPos: (0, 0), exitPos: (Int.random(in: 3...7), Int.random(in: 3...7)), health: 10)
        let ball21: Ball21 = Ball21(ballsNr: 21, roundsPlayed: 0)
        let battle1: Battle1 = Battle1(fightCount: 0)
        let battle2: Battle2 = Battle2(player: Combatant(health: 10), master: Combatant(health: 10))
        if self.currentRoom.name == "Room A" && !doorOpened{
            treasureRoom.play()
            doorOpened = true
        }
        if self.currentRoom.name == "Room B" && !dicePlayed{
            diceRoll.play()
            dicePlayed = true
        }
        if self.currentRoom.name == "Room C" && !battleFought{
            battle1.fight()
            battleFought = true
        }
        if self.currentRoom.name == "Room D" && !trapRoomPassed{
            trapRoom.play()
            trapRoomPassed = true
        }
        if self.currentRoom.name == "Room E" && !played21{
            ball21.play()
            played21 = true
        }
        if self.currentRoom.name == "Room F" && !finalPlayed{
            battle2.play()
            finalPlayed = true
        }
        if self.currentRoom.name == "Room G" {
            print("You have exited the pyramid and arrived in time to cure the King with the Holy Grail.")
            print("The kingdom is now saved!")
            exit(0)
        }
        print(self.currentRoom)
        print("You are curently heading \(playerDirection.rawValue).")

        do {
            try parser.readCommand()
        } catch ParserError.noInputGiven{
            print("No input given")
        } catch CommandError.commandNotFound{
            print("Command not found")
        } catch {
            print("Error: \(error)")
        }
    }
    }
}

/*Instantiation of the game*/

let game = AdvGame()
game.play()
