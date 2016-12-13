package q9
import java.io.File

val text = File("src/q9/in.txt").readText()

data class Marker(val endIndex: Int, val times: Int)

var i = 0
fun readMarker(): Marker {
    var s = ""
    while (true) {
        val c = text[++i]
        if(c == ')') {
            ++i
            return s.split("x").map(String::toInt).let { Marker(it[0] + i, it[1]) }
        } else s += c
    }
}

fun expandMarker(): Long {
    var size = 0L
    val marker = readMarker()
    while(i < marker.endIndex) {
        if(text[i] == '(') {
            size += expandMarker()
        } else {
            size++
            i++
        }
    }
    return size * marker.times
}

fun main(args: Array<String>) {
    var size = 0L
    while(i < text.length) {
        val c = text[i]
        if(c == '(') {
            size += expandMarker()
        } else {
            size++
            i++
        }
    }

    println(size)
}

