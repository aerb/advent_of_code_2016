import java.io.File
import java.util.*

File("in.txt").readLines().forEach{  line ->
    val parts = Regex("([a-z|-]+)-([0-9]+)\\[([a-z]+)\\]").matchEntire(line)!!.groupValues
    val name = parts[1]
    val strippedName = name.replace("-", "")
    val id = parts[2].toInt()
    val hash = parts[3]

    val calculated = strippedName
        .groupBy { it }
        .map { it.key to it.value.size }
        .sortedWith(Comparator { c0, c1 ->
            val bySize = c1.second - c0.second
            if(bySize != 0) bySize
            else c0.first - c1.first
        })
        .take(5)
        .map { it.first }
        .joinToString(separator = "")

    if(calculated == hash) {
        val shift = name.map {
            if(it == '-') ' '
            else ((it.toInt() - 97 + id) % 26 + 97).toChar()
        }.joinToString("")

        println("$shift $id")
    }
}