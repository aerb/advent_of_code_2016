import java.io.File
import java.util.*


val transpose = ArrayList<MutableList<Int>>()
File("in.txt").readLines()
    .map { it.trim().split(Regex("\\s+")).map(String::toInt) }
    .forEachIndexed { i, row ->
        if(i % 3 == 0) {
            repeat(3) { transpose.add(ArrayList<Int>()) }
        }
        row.forEachIndexed { i, side ->
            transpose[transpose.size - 3 + i].add(side)
        }
    }

val possible = transpose.count { sides ->
    sides[0] + sides[1] > sides[2] &&
    sides[0] + sides[2] > sides[1] &&
    sides[1] + sides[2] > sides[0]
}
println(possible)