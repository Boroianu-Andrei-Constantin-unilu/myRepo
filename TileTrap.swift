import Foundation

struct Player {
    var position: Int
    var health: Int
}

func main() {
    let correctPath = [1, 0, 1, 0, 0, 1, 1, 1, 0, 0]
    let exitPosition = correctPath.count
    var player = Player(position: 0, health: 10)
    
    while player.position < exitPosition && player.health > 0 {
        print("Your current position: \(player.position)")
        print("Your health: \(player.health)")
        print("Enter 1 to step on the left tile, 0 to step on the right tile:")
        
        if let input = readLine(), let step = Int(input), (step == 0 || step == 1) {
            if step == correctPath[player.position] {
                player.position += 1
            } else {
                player.health -= 1
            }
        } else {
            print("Invalid input. Please enter 0 or 1.")
        }
    }
    
    if player.position == exitPosition {
        print("Congratulations! You've reached the exit safely.")
    } else {
        print("Game over! You've run out of health.")
    }
}

main()
