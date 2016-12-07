package q7

import java.io.File

val count = File("in.txt").readLines().count { line ->

    val hyperNet = Regex("(?<=\\[)[a-z]+(?=\\])").findAll(line).map { it.value }
    val superNet = line.replace(Regex("\\[[a-z]+\\]"), ",").split(",")

    fun hasAbba(s : String): Boolean {
        for(i in 3 .. s.length - 1) {
            if(s[i]     == s[i - 3] &&
               s[i - 1] == s[i - 2] &&
               s[i]     != s[i - 1])
            return true
        }
        return false
    }

    hyperNet.none(::hasAbba) && superNet.any(::hasAbba)
}
println(count)