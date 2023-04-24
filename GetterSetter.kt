fun main(args: Array<String>) {

    val p = Person()
    p.name = "jack"
    println("${p.name}")
}

class Person {
    var name: String = "defaultValue"

    get() = field

    set(value) {
        field = value
    }
}