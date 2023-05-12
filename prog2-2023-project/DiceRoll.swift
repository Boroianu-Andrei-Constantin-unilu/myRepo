import Foundation

class DiceRoll {
    var knightWinsNr: Int
    var enemyWinsNr: Int

    /* The gamesPlayed variable helps to create different messages, based on whether the player plays the mini game against a regular enemy or the final boss.*/

    var gamesPlayed = 0

    init (knightWinsNr: Int, enemyWinsNr: Int, gamesPlayed: Int) {
        self.knightWinsNr = knightWinsNr
        self.enemyWinsNr = enemyWinsNr
        self.gamesPlayed = gamesPlayed
    }

    func play() {
        gamesPlayed += 1

        if gamesPlayed == 1 {
            print("I have been waiting for you, my friend.")
            print("In order to get past the next room, you will have to beat me at a simple dice-rolling game.")
            print("Out of nine throws, whoever gets a larger number five times, wins. ")
            print("If you win, I will let you pass. If not, demise awaits you.")
            print("Let us proceed!")
        } else {
            print("Then may the best of us win!")
        }

        for roll: Int in 1...9 {
            print("Round \(roll) out of 9:")
            let knightRoll: Int = Int.random(in: 1...6)
            let enemyRoll: Int = Int.random(in: 1...6)
            print("The knight rolled a \(knightRoll), against the enemy roll \(enemyRoll).")

            if knightRoll < enemyRoll {
                print("You seem to have knocked on Death's door. Will he answer?")
                enemyWinsNr += 1
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
            }
            if knightRoll > enemyRoll {
                print("You seem to be lucky this time. Will you make it to the end?")
                knightWinsNr += 1
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
            }
            if knightRoll == enemyRoll {
                print("None of us are going to progress this way. Let us roll again!")
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
            }

            if gamesPlayed == 1 {
                if enemyWinsNr == 5 || (roll >= 9 && (enemyWinsNr > knightWinsNr)){
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis a sad thing that your quest must end here...")
                print("Alas, you have lost the match and your soul will be taken by the Grim Reaper as a result.")
                print("If only you had been slightly luckier...")
                exit(0)
                }
                if knightWinsNr == 5 || (roll >= 9 && (enemyWinsNr < knightWinsNr)) || knightWinsNr == enemyWinsNr {
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis cannot be! You truly ARE worthy of your name, my friend...")
                print("I am a creature of my word. You are free to continue your journey.")
                break
                }
            } else {
                if enemyWinsNr == 5 || (roll >= 9 && (enemyWinsNr > knightWinsNr)){
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis a sad thing that your quest must end here...")
                print("Alas, you have run out of luck and were slain to death by your nemesis!")
                print("Better luck next time!")
                exit(0)
                }
                if knightWinsNr == 5 || (roll >= 9 && (enemyWinsNr < knightWinsNr)) || knightWinsNr == enemyWinsNr {
                print("The score is: Knight - \(knightWinsNr), Enemy - \(enemyWinsNr)")
                print("'Tis cannot be! You truly ARE worthy of your name, my friend!")
                print("Very well, then. I will die by your sword, as it is honourable to do so...")
                print("You triumphantly take the grail from the pedestal.")
                print("Now you can return to the kingdom and cure the King!")
                break
                }
            }
            
        }
    }
}