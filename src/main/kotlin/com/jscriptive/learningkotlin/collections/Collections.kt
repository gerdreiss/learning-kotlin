package com.jscriptive.learningkotlin.collections

fun main(args: Array<String>) {
    val seasons = listOf("spring", "summer", "autumn", "winter")
    val colors = listOf("black", "white", "red")

    println(seasons.last())
    println(seasons.reversed())
    println(seasons.getOrElse(5) { "None" })
    println(colors.max())
    println(seasons.zip(colors))

    val merged = listOf(*seasons.toTypedArray(), *colors.toTypedArray())
    println(merged)
    println(seasons + colors)

    println(colors.union(colors))
    println((colors + colors).distinct())
}