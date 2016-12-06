import java.io.File

val lines = File("in.txt").readLines()
var message = ""
for(i in 0 .. lines.first().length - 1) {
    message += lines.groupBy { it[i] }.minBy { it.value.size }!!.key
}
println(message)