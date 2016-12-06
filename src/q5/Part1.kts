import java.security.MessageDigest

val alg = MessageDigest.getInstance("MD5")

var i = 0
val password = generateSequence {
    if(i % 1000000 == 0) println("Progress: $i")
    alg.update("ffykfhsq${i++}".toByteArray())
    alg.digest().joinToString("") { "%02X".format(it) }
}
.filter { it.startsWith("00000") }
.map { it[5] }
.take(8)
.joinToString("")

println(password)

