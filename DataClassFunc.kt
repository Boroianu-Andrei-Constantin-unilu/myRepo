data class User(val name: String, val age: Int)

fun main(args: Array<String>) {
    val u = User("Mike", 24)
    println(u.toString())

    val u1 = User("John", 29)
    val u2 = u1.copy()
    val u3 = u1.copy(name = "Amanda")

    println("u1 hashcode = ${u1.hashCode()}")
    println("u2 hashcode = ${u2.hashCode()}")
    println("u3 hashcode = ${u3.hashCode()}")

    if (u1.equals(u2) == true)
        println("u1 is equal to u2.")
    else
        println("u1 is not equal to u2.")

    if (u1.equals(u3) == true)
        println("u1 is equal to u3.")
    else
        println("u1 is not equal to u3.")
}