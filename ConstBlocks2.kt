fun main(args: Array<String>) {
    val person1 = Person("joe", 25)
}

class Person(fName: String, personAge: Int) {
    val firstName = fName.capitalize()
    var age = personAge

    // initializer block
    init {
        println("First Name = $firstName")
        println("Age = $age")
    }
}