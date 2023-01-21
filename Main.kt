package search

import java.io.File
import kotlin.system.exitProcess

class SearchEngine(file: String) {
    private val people: List<String>
    private val file = File(file)
    private val data = mutableMapOf<String, MutableList<Int>>()

    //Sorting data in map with indexes(inverted indexes)
    init {
        if (!this.file.exists()) println("File doesn't exist").also { exitProcess(0) }
        people = this.file.readLines()

        people.forEachIndexed { index, string ->
            string.split(" ").forEach {
                val key = it.lowercase()
                if (data[key] == null) {
                    data[key] = mutableListOf(index)
                } else {
                    val value = data[key]
                    value!!.add(index)
                    data[key] = value
                }
            }
        }
        menu()
    }

    //Searching for any, all or none words from query in file lines
    private fun searchForPerson(mode: String) {
        println("Enter a name or email to search all suitable people.")
        val value = readln().lowercase().split(" ")
        val list = when (mode) {
            "ANY" -> people.filter { line -> line.split(" ").any { it.lowercase() in value }}
            "ALL" -> people.filter { line -> line.split(" ").all { it.lowercase() in value }}
            "NONE" -> people.filter { line -> line.split(" ").none { it.lowercase() in value }}
            else -> listOf()
        }
        if (list.isEmpty()) println("No matching people found.\n") else println(list.joinToString("\n"))
    }

    //List of all file lines
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
                "1" -> {
                    println("Select a matching strategy: ALL, ANY, NONE")
                    searchForPerson(readln())
                }

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
