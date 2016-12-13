package q10

import java.io.File
import java.util.*

data class Bot(val id: String, val higherTo: String, val lowerTo: String, val values: ArrayList<Int> = ArrayList())

val lines = File("in.txt").readLines().map { it.split(" ") }

val outputs = mutableListOf(0, 0 ,0)
val bots = lines
    .filter { it[0] == "bot" }
    .fold(listOf<Bot>()) { list, line ->
        list + Bot(
            id = line[0] + "-" + line[1],
            lowerTo = line[5] + "-" + line[6],
            higherTo = line[10] + "-" + line[11]
        )
    }.associateBy { it.id }

println(bots)

lines.filter { it[0] == "value" }
    .forEach {
        fun passValue(id: String, value: Int) {
            if(id.startsWith("bot")) {
                val bot = bots[id] ?: throw RuntimeException(id)
                bot.values += value
                if(bot.values.size >= 2) {
                    passValue(bot.lowerTo, bot.values.min()!!)
                    passValue(bot.higherTo, bot.values.max()!!)
                }
            } else {
                val index = id.split("-").last().toInt()
                if(index < 3) {
                    outputs[index] = value
                }
            }
        }

        val to = it[4] + "-" + it[5]
        val value = it[1].toInt()
        passValue(to, value)
    }

println(bots)

println(bots.values.first { 61 in it.values && 17 in it.values })
println(outputs.reduce { i0, i1 -> i0 * i1 })




