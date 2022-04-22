object Raindrops {
    fun convert(input: Int): String = buildString {
        if (input % 3 == 0) append("Pling")
        if (input % 5 == 0) append("Plang")
        if (input % 7 == 0) append("Plong")
        if (isEmpty()) append(input)
    }
    //mapOf(
    //    3 to "Pling",
    //    5 to "Plang",
    //    7 to "Plong"
    //).map { pair ->
    //    if (input % pair.key == 0) pair.value else ""
    //}.fold(input.toString()) { total, next ->
    //    total + next
    //}.partition { ch ->
    //    ch.isLetter()
    //}.toList().first { it.isNotEmpty() }
}
