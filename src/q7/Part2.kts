package q7

import java.io.File
import java.util.*

val count = File("in.txt").readLines().count { line ->

    val hyperNet = Regex("(?<=\\[)[a-z]+(?=\\])").findAll(line).map { it.value }
    val superNet = line.replace(Regex("\\[[a-z]+\\]"), ",").split(",")

    fun getAbas(s : String): List<String> {
        val list = ArrayList<String>()
        for(i in 2 .. s.length - 1) {
            if(s[i] == s[i - 2] && s[i] != s[i - 1])
                list += s.substring(i - 2, i)
        }
        return list
    }

    val babs = superNet.flatMap(::getAbas).map { String(charArrayOf(it[1], it[0], it[1])) }
    hyperNet.any { net -> babs.any { it in net } }
}
println(count)