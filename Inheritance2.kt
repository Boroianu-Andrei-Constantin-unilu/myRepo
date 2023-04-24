open class Person(age: Int, name: String) {
    // some code
}

class Footballer(age: Int, name: String, club: String): Person(age, name) {
    init {
        println("Football player $name of age $age and plays for $club.")
    }

    fun playFootball() {
        println("I am playing football.")
    }
}

fun main(args: Array<String>) {
    val f1 = Footballer(29, "Cristiano", "LA Galaxy")
}