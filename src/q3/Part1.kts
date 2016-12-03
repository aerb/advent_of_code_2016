import java.io.File

val possible = File("in.txt").readLines().count {
    val sides = it.trim().split(Regex("\\s+")).map(String::toInt)
    sides[0] + sides[1] > sides[2] &&
    sides[0] + sides[2] > sides[1] &&
    sides[1] + sides[2] > sides[0]
}
println(possible)