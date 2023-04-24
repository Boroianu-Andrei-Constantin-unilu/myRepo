fun main(args: Array<String>) {

    val a = 11
    val n = "11"

    when (n) {
        "cat" -> println("Cat? Really?")
        12.toString() -> println("Close but not close enough.")
        a.toString() -> println("Bingo! It's eleven.")
    }
}