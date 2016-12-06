import java.security.MessageDigest

val alg = MessageDigest.getInstance("MD5")

var i = 0
val password = CharArray(8)
generateSequence {
    if(i % 1000000 == 0) println("Progress: $i")
    alg.update("ffykfhsq${i++}".toByteArray())
    alg.digest().joinToString("") { "%02X".format(it) }
}
.filter { it.startsWith("00000") }
.map { Integer.parseInt(it[5].toString(), 16) to it[6] }
.filter { it.first < 8 }
.takeWhile {
    if(password[it.first].toInt() == 0) {
        password[it.first] = it.second
    }
    println(password)
    password.any { it.toInt() == 0 }
}.toList()

println(password.joinToString(""))

