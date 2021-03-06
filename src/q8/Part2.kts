package q8

import java.io.File
import java.util.*

data class Pixel(val x: Int, val y: Int)

val set = File("in.txt").readLines().fold(setOf<Pixel>()) { set, line ->
    val parts = line.split(" ")
    when(parts[0]) {
        "rect" -> {
            val (w, h) = parts[1].split("x").map(String::toInt)
            set + Array(w * h) { i -> Pixel(i % w, i / w) }
         }
        "rotate" -> {
            val index = parts[2].split("=").last().toInt()
            val by = parts[4].toInt()
            when(parts[1]) {
                "column" -> {
                    set.map {
                        if(it.x == index) it.copy(y = (it.y + by) % 6)
                        else it
                    }.toSet()
                }
                "row" -> {
                    set.map {
                        if(it.y == index) it.copy(x = (it.x + by) % 50)
                        else it
                    }.toSet()
                }
                else -> throw IllegalStateException(parts[1])
            }
        }
        else -> throw IllegalStateException(parts[0])
    }
}

var lastRow = 0
for(i in 0 .. 50 * 6 - 1) {
    val p = Pixel(i % 50, i / 50)

    if(p.y != lastRow) {
        lastRow = p.y
        println()
    }
    if(p in set) print("X")
    else print(" ")
}