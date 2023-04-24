// Empty primary constructor
open class Person() {
    open var age: Int = 0
        get() = field

        set(value) {
            field = value
        }
}

class Girl: Person() {

    override var age: Int = 0
        get() = field

        set(value) {
            field = value - 5
        }
}

fun main(args: Array<String>) {

    val girl = Girl()
    girl.age = 31
    println("My fake age is ${girl.age}.")
}