fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    println("Flattened list:  ${listOf(listOf(1), listOf(2), listOf(), listOf(3)).flatten().joinToString()}")
}
