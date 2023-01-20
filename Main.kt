package search

import java.io.File
import kotlin.system.exitProcess

class SearchEngine(file: String) {
    private val people = mutableListOf<String>()
    private val file = File(file)

    init {
        if (!this.file.exists()) println("File doesn't exist").also { exitProcess(0) }
        people.addAll(this.file.readLines())
        menu()
    }

    private fun searchForPerson() {
        println("Enter a name or email to search all suitable people.")
        val valueToSearch = readln().lowercase()
        val list = people.filter { it.lowercase().matches(""".*${valueToSearch}.*""".toRegex()) }
        if (list.isEmpty()) println("No matching people found.\n") else println(list.joinToString("\n") + "\n")
    }

    private fun status() {
        println("=== List of people ===")
        println(people.joinToString("\n"))
    }

    private fun menu() {
        while (true) {
            println(
                "=== Menu ===\n" +
                        "1. Find a person\n" +
                        "2. Print all people\n" +
                        "0. Exit"
            )
            when (readln()) {
                "1" -> searchForPerson()
                "2" -> status()
                "0" -> break
                else -> println("Incorrect option! Try again.")
            }
        }
        println("Bye!")
    }
}

fun main(args: Array<String>) {
    SearchEngine(args[args.indexOf("--data") + 1])
}
