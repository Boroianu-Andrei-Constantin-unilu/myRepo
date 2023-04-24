data class User(val name: String, val age: Int, val gender: String)

fun main(args: Array<String>) {
    val u1 = User("John", 29, "Male")

    val (name, age, gender) = u1
    println("name = $name")
    println("age = $age")
    println("gender = $gender")

    println("Components: ")
    println(u1.component1())     // John
    println(u1.component2())     // 29  
    println(u1.component3())     // "Male"
}