package q1

import java.io.File

var o = 0; var x = 0; var y = 0;
File("in.txt").readText().split(", ").forEach {
    o += if(it[0] == 'L') -90 else +90
    val dist = it.drop(1).toInt()
    when((o % 360 + 360) % 360) {
        0 -> y += dist
        90 -> x += dist
        180 -> y -= dist
        270 -> x -= dist
    }
}

println("x:$x y:$y dist:${Math.abs(x) + Math.abs(y)}")
