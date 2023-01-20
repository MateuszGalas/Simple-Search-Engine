package search

fun main() {
    println("Enter the number of people:")
    val people = mutableListOf<String>()
    repeat(readln().toInt().also { println("Enter all people:") }) {
        people.add(readln())
    }
    println("Enter the number of search queries:")
    repeat(readln().toInt()){
        println("Enter data to search people:")
        val valueToSearch = readln().lowercase()
        val list = people.filter { it.lowercase().matches(""".*${valueToSearch}.*""".toRegex()) }
        if (list.isEmpty()) println("No matching people found.\n") else println(list.joinToString("\n") + "\n")
    }
}
