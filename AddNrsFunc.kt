fun addNumbers(n1: Double, n2: Double): Int {
    val sum = n1 + n2
    val sumInteger = sum.toInt()
    return sumInteger
}

fun main(args: Array<String>) {
    val number1 = 12.2
    val number2 = 3.4
    val result: Int

    result = addNumbers(number1, number2)
    println("result = $result")
}