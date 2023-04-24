fun callMe() {
    println("Printing from callMe() function.")
    println("This is cool (still printing from inside).")
}

fun main(args: Array<String>) {
    callMe()
    println("Printing outside from callMe() function.")
}