import Foundation
import Dispatch

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
        self.currentPos = currentPos
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
    
    // The reversed() function places the initial position on the bottom-left of the map.

    func drawRoom() {
        for b in 0..<y.reversed() {
            for a in 0..<x {
                if currentPos == (a, b) {
                    print("Me", terminator: " ")
                }
                if exitPos == (a, b) {
                    print("Exit", terminator: " ")
                }

                /* The "." are the safe tiles to walk on, and the "#" are the dangerous ones (traps). */

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
