// Program to compute the sum of natural numbers from 1 to 100.
fun main(args: Array<String>) {

    var sum = 0
    var i = 100

    while (i != 0) {
        sum += i     // sum = sum + i;
        --i
    }
    println("sum = $sum")
}