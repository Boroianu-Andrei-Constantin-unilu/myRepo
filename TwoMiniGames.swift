import Foundation

// Game 1: Dice-rolling simulator
func playDiceRollingSimulator() -> Bool {
    var playerWins = 0
    var computerWins = 0
    
    for _ in 1...9 {
        let playerRoll = Int.random(in: 1...6)
        let computerRoll = Int.random(in: 1...6)
        
        if playerRoll > computerRoll {
            playerWins += 1
        } else if computerRoll > playerRoll {
            computerWins += 1
        }
    }
    
    if playerWins >= 5 {
        print("Congratulations! You won the dice-rolling simulator!")
        return true
    } else {
        print("Sorry, you lost the dice-rolling simulator.")
        return false
    }
}

// Game 2: Trap room simulator
func playTrapRoomSimulator() {
    let trapTile = Int.random(in: 1...4)
    var playerHealth = 10
    
    print("You are in a locked room with four tiles: North, East, South, and West.")
    print("One of the tiles triggers a trap and decreases your health by 1.")
    print("Your goal is to reach the exit without triggering the trap.")
    print("You have 10 health points. Good luck!")
    
    var direction = ""
    while true {
        print("Which direction do you want to go? (N, E, S, W)")
        direction = readLine() ?? ""
        
        if direction == "N" {
            if trapTile == 1 {
                playerHealth -= 1
                print("You stepped on the trap tile and lost 1 health point. You have \(playerHealth) health points left.")
            } else {
                print("You stepped on the safe tile and advance to the next room.")
                break
            }
        } else if direction == "E" {
            if trapTile == 2 {
                playerHealth -= 1
                print("You stepped on the trap tile and lost 1 health point. You have \(playerHealth) health points left.")
            } else {
                print("You stepped on the safe tile and advance to the next room.")
                break
            }
        } else if direction == "S" {
            if trapTile == 3 {
                playerHealth -= 1
                print("You stepped on the trap tile and lost 1 health point. You have \(playerHealth) health points left.")
            } else {
                print("You stepped on the safe tile and advance to the next room.")
                break
            }
        } else if direction == "W" {
            if trapTile == 4 {
                playerHealth -= 1
                print("You stepped on the trap tile and lost 1 health point. You have \(playerHealth) health points left.")
            } else {
                print("You stepped on the safe tile and advance to the next room.")
                break
            }
        } else {
            print("Invalid direction. Please choose N, E, S, or W.")
        }
        
        if playerHealth <= 0 {
            print("Sorry, you lost the trap room simulator.")
            return
        }
    }
    
    print("Congratulations! You won the trap room simulator!")
}

// Main program
print("Welcome to the mini-games simulator!")
print("You will first play the dice-rolling simulator.")
print("If you win, you can enter the trap room simulator.")
print("Good luck!")

if playDiceRollingSimulator() {
    playTrapRoomSimulator()
}

print("Thanks for playing!")
