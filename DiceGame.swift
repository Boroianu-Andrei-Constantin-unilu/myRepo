import Foundation

// Function to simulate rolling a dice
func rollDice() -> Int {
    return Int.random(in: 1...6)
}

var playerWins: Int = 0
var computerWins: Int = 0
let numberOfTries: Int = 9
let winningThreshold: Int = 5

for _ in 1...numberOfTries {
    let playerRoll = rollDice()
    let computerRoll = rollDice()
    print("Player rolls: \(playerRoll) | Computer rolls: \(computerRoll)")

    if playerRoll > computerRoll {
        playerWins += 1
        print("Player wins this round!")
    } else if computerRoll > playerRoll {
        computerWins += 1
        print("Computer wins this round!")
    } else {
        print("It's a tie!")
    }

    print("Player wins: \(playerWins) | Computer wins: \(computerWins)\n")

    if computerWins == winningThreshold {
        print("Computer has won 5 rounds! The player loses the game.")
        break
    }

    if playerWins == winningThreshold {
        print("Player has won 5 rounds! The player wins the game.")
        break
    }
}

if computerWins < winningThreshold && playerWins < winningThreshold {
    print("After 9 tries, nobody has reached the winning threshold.")
}
