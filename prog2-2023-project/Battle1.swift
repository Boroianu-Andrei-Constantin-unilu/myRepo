import Foundation

class Combatant {
    var health: Int

    init (health: Int) {
        self.health = health
    }

    func alive() -> Bool {
        return health >= 0
    }
}

enum Move {
    case attack
    case block
}

class Battle1 {

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