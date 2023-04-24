class Outer {

    val a = "Outside Nested class."

    inner class Inner {
        fun callMe() = a
    }
}

fun main(args: Array<String>) {

    val outer = Outer()
    println("Using outer object: ${outer.Inner().callMe()}")

    val inner = Outer().Inner()
    println("Using inner object: ${inner.callMe()}")
}