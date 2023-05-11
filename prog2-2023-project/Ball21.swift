import Foundation

class Ball21 {

    var ballsNr = 21

    var roundsPlayed = 0

    init(ballsNr: Int, roundsPlayed: Int) {
        self.ballsNr = ballsNr
        self.roundsPlayed = roundsPlayed
    }

    func play() {
        roundsPlayed += 1

        if roundsPlayed == 1 {
            print("As you enter the next room, you spot another enemy!")
            print("Hello, stranger! Welcome to your new obstacle... a 21-ball game!")
            print("We have to take turns and remove up to 3 balls per turn.")
            print("Whoever removes the last ball is the winner.")
            print("If you win, you may pass. If not, I must kill you.")
            print("Let the match start!") 
        } else {
            print("In this case, let the winner take it all!")
        }
        while ballsNr > 0 {
            playerTurn()
            if ballsNr == 0 {
                if roundsPlayed == 1 {
                    print("My word, are you not a skilled one!")
                    print("Very well, I will keep my promise and let you pass...")
                    return
                } else {
                    print("My word, my luck seems to have run out!")
                    print("I guess this is it, then... You may take the Holy Grail, dear knight...")
                    print("You triumphantly take the grail from the pedestal.")
                    print("Now you can return to the kingdom and cure the King!")
                    return
                }
                
            }
            enemyTurn()
            if ballsNr == 0 {
                if roundsPlayed == 1 {
                    print("'Twas nice to meet you, stranger...")
                    print("You have met your demise at the hands of your adversary! If only you had a different strategy...")
                    exit(0)
                } else {
                    print("Tsk, tsk, tsk... I genuinely thought you were better than that!")
                    print("You have just been beheaded by your nemesis as a punishment for your loss...")
                    print("Peace will now be a long lost dream in a kingless kingdom!")
                    exit(0)
                }
                
            }
        }
    }

    func playerTurn() {
        while true {
            print("How many balls do you desire to remove (1 to 3)?")
            let input = readLine() ?? ""
            if let ballsRemoved = Int(input), ballsRemoved >= 1, ballsRemoved <= 3 {
                if ballsRemoved <= ballsNr {
                    ballsNr -= ballsRemoved
                    print("Balls remaining: \(ballsNr)")
                    break
                } else {
                    print("You have taken \(ballsRemoved). There are only \(ballsNr) left to remove.")
                }
            } else {
                print("I am sorry, only 1, 2 or 3 balls can be removed.")
            }
        }
    }

    func enemyTurn() {
        let ballsRemoved = min(ballsNr, Int.random(in: 1...3))
        ballsNr -= ballsRemoved
        print("I have taken \(ballsRemoved) balls. \(ballsNr) remain.")
    }
}