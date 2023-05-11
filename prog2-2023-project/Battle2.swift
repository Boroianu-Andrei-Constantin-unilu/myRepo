import Foundation

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