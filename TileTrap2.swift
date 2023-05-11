import Foundation

struct Player {
    var position: (row: Int, col: Int)
    var health: Int
}

func isValidMove(_ room: [[Int]], _ position: (row: Int, col: Int)) -> Bool {
    return position.row >= 0 && position.row < room.count && position.col >= 0 && position.col < room[0].count
}

func main() {
    let room: [[Int]] = [
        [1, 0, 0, 0],
        [1, 1, 0, 0],
        [0, 1, 1, 1],
        [0, 0, 0, 1]
    ]
    let exitPosition = (row: room.count - 1, col: room[0].count - 1)
    var player = Player(position: (0, 0), health: 10)
    let moves: [String: (row: Int, col: Int)] = [
        "N": (-1, 0),
        "E": (0, 1),
        "S": (1, 0),
        "W": (0, -1)
    ]

    while player.position != exitPosition && player.health > 0 {
        print("Your current position: \(player.position)")
        print("Your health: \(player.health)")
        print("Enter N for North, E for East, S for South, or W for West:")

        if let input = readLine(), let move = moves[input.uppercased()] {
            let newPosition = (row: player.position.row + move.row, col: player.position.col + move.col)

            if isValidMove(room, newPosition) {
                if room[newPosition.row][newPosition.col] == 1 {
                    player.position = newPosition
                } else {
                    player.health -= 1
                }
            } else {
                print("Invalid move. You cannot move outside the room.")
            }
        } else {
            print("Invalid input. Please enter N, E, S, or W.")
        }
    }

    if player.position == exitPosition {
        print("Congratulations! You've reached the exit safely.")
    } else {
        print("Game over! You've run out of health.")
    }
}

main()
