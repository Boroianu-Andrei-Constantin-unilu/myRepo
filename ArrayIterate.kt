fun main(args: Array<String>) {

    var language = arrayOf("Ruby", "Kotlin", "Python", "Java")

    for (item in language.indices) {

        // printing array elements having even index only
        if (item%2 == 0)
            println(language[item])
    }
}