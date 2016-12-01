package q1

import java.io.File
import java.util.*

data class Point(var x: Int = 0, var y: Int = 0)

var o = 0; val curr = Point()
val previous = HashSet<Point>()
File("in.txt").readText().split(", ").first {
    o = ((o + if(it[0] == 'L') -90 else +90) % 360 + 360) % 360
    val dist = it.drop(1).toInt()
    repeat(dist) {
        when(o) {
            0 -> curr.y += 1
            90 -> curr.x += 1
            180 -> curr.y -= 1
            270 -> curr.x -= 1
        }
        if(curr in previous) return@first true
        else {
            previous += curr.copy()
        }
    }
    false
}

println("$curr dist:${Math.abs(curr.x) + Math.abs(curr.y)}")

