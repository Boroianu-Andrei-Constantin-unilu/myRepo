import Foundation

// Mini-game 1: Dice Rolling Simulator
func diceRollingGame() -> Bool {
    var playerWins = 0
    var computerWins = 0
    
    for _ in 1...9 {
        let playerRoll = Int.random(in: 1...6)
        let computerRoll = Int.random(in: 1...6)
        
        if playerRoll > computerRoll {
            playerWins += 1
        } else {
            computerWins += 1
        }
        
        if computerWins == 5 {
            print("The computer wins! Better luck next time.")
            return false
        }
    }
    
    if playerWins >= 5 {
        print("Congratulations, you won the dice rolling game!")
        return true
    }
    
    return false
}

// Mini-game 2: Trap Room Simulator
func trapRoomGame() {
    var health = 10
    let correctPath = ["North", "East", "East", "North", "West", "North"]
    var currentIndex = 0
    
    while health > 0 {
        print("Enter your direction (North, East, South, West): ")
        if let input = readLine()?.capitalized, ["North", "East", "South", "West"].contains(input) {
            if input == correctPath[currentIndex] {
                currentIndex += 1
                if currentIndex == correctPath.count {
                    print("Congratulations, you've successfully navigated the trap room!")
                    return
                }
            } else {
                health -= 1
                print("Wrong step! You've triggered a trap. Health: \(health)")
            }
        } else {
            print("Invalid input, please enter a valid direction.")
        }
    }
    
    print("Game over. You've run out of health.")
}

// Main program
if diceRollingGame() {
    print("Do you want to exit or proceed to the trap room game? (Enter 'Exit' to exit or 'Proceed' to continue)")
    if let decision = readLine()?.capitalized, decision == "Proceed" {
        trapRoomGame()
    } else {
        print("Thank you for playing!")
    }
}
