fun main(args : Array<String>) {
    val range: Byte = 112
    println("$range")

    // The code below gives error. Why?
    // val range1: Byte = 200

    val temperature: Short = -11245
    println("$temperature")

    val score: Int =  100000
    println("$score")

    // score is of type Int
    val score2 = 10
    println("$score2")

    val highestScore: Long = 9999
    println("$highestScore")

    // distance is of type Double
    val distance = 999.5
    println("$distance")

    // distance is of type Float
    val distance2 = 19.5F
    println("$distance2")
}