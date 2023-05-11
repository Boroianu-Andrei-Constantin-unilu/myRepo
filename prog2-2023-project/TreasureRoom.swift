import Foundation

class TreasureRoom {

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