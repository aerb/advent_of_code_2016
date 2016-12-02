import sun.plugin.dom.exception.InvalidStateException
import java.io.File

val keys = listOf(
    listOf(null, null, "1", null, null),
    listOf(null,  "2", "3",  "4", null),
    listOf( "5",  "6", "7",  "8",  "9"),
    listOf(null,  "A", "B",  "C", null),
    listOf(null, null, "D", null, null)
)

data class Coord(val x: Int, val y: Int)
var pos = Coord(0, 2)
val code = File("in.txt").readLines().map { line ->
    line.forEach { char ->
        val next = when(char) {
            'U' -> pos.copy(y = pos.y - 1)
            'D' -> pos.copy(y = pos.y + 1)
            'L' -> pos.copy(x = pos.x - 1)
            'R' -> pos.copy(x = pos.x + 1)
            else -> throw InvalidStateException("$char")
        }
        val valid = keys.getOrNull(next.y)?.getOrNull(next.x) != null
        if(valid) {
            pos = next
        }
    }
    keys[pos.y][pos.x]
}
println(code.joinToString(separator = ""))