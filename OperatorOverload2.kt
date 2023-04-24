fun main(args: Array<String>) {
    var point = Point(3, -8)
    --point

    println("point = (${point.x}, ${point.y})")
}

class Point(var x: Int = 0, var y: Int = 10) {
    operator fun dec() = Point(--x, --y)
}