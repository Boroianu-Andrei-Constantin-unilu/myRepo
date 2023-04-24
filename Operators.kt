fun main(args: Array<String>) {

    var number = 12

    number *= 5   // number = number*5
    println("number = $number")
    
    val a = 1
    val b = true
    var c = 1

    var result: Int
    var booleanResult: Boolean

    result = -a
    println("-a = $result")

    booleanResult = !b
    println("!b = $booleanResult")

    --c
    println("--c = $c")
}