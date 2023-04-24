open class Person() {
    open fun displayAge(age: Int) {
        println("My actual age is $age.")
    }
}

class Girl: Person() {

    override fun displayAge(age: Int) {

        // calling function of base class
        super.displayAge(age)
        
        println("My fake age is ${age - 5}.")
    }
}

fun main(args: Array<String>) {
    val girl = Girl()
    girl.displayAge(31)
}