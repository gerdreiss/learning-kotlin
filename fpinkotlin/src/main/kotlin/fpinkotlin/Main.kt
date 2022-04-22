package fpinkotlin

import arrow.core.Either
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    Either
        .catch { Files.list(Paths.get(".")) }
        .fold(
            { println("Error: $it") },
            { paths ->
                val files =
                    paths.map { it.toString() }
                        .reduce { t, u -> "$t\n$u" }
                        .orElse(".")
                println(files)
            }
        )
}
