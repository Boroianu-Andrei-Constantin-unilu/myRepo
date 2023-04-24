fun main(args: Array<String>) {

    val number = 0

    val result = if (number > 0)
        "positive number"
    else if (number < 0)
        "negative number"
    else 
        "zero"
    
    println("number is $result")
}