fun main(args: Array<String>) {
    println(getName("John", "Doe"))
}

fun getName(firstName: String, lastName: String): String = "$firstName $lastName"