import java.io.File

var x = 1; var y = 1
val code = File("in.txt").readLines().map { line ->
    line.forEach { char ->
        when(char) {
            'U' -> --y
            'D' -> ++y
            'L' -> --x
            'R' -> ++x
        }
        x = x.coerceIn(0, 2)
        y = y.coerceIn(0, 2)
    }
    y * 3 + x + 1
}
println(code.joinToString(separator = ""))