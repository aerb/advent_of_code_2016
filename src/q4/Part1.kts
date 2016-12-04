import java.io.File
import java.util.*

val sum = File("in.txt").readLines().sumBy { line ->
    val parts = Regex("([a-z|-]+)-([0-9]+)\\[([a-z]+)\\]").matchEntire(line)!!.groupValues
    val name = parts[1].replace("-", "")
    val id = parts[2].toInt()
    val hash = parts[3]

    val calculated = name
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

    if(calculated == hash) id
    else 0
}

println(sum)