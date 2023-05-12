import Foundation

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

let game = AdvGame()
game.play()