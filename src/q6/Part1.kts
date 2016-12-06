import java.io.File

val lines = File("in.txt").readLines()
var message = ""
for(i in 0 .. lines.first().length - 1) {
    message += lines.groupBy { it[i] }.maxBy { it.value.size }!!.key
}
println(message)