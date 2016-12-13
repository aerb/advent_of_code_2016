package q9

import java.io.File


interface State
class Read() : State
data class Marker(val text: String) : State
data class Copy(val upTo: Int, val times: Int, val text: String) : State

var uncompressed = ""
var state: State = Read()
File("in.txt").readText().foldIndexed<State>(Read()) { i, state, c ->
     when(state) {
        is Read -> {
            if(c == '(') {
                Marker("")
            } else {
                uncompressed += c
                Read()
            }
        }
        is Marker -> {
            if(c == ')') {
                val parts = state.text.split("x")
                Copy(upTo = i + parts[0].toInt(), times = parts[1].toInt(), text = "")
            } else {
                Marker(state.text + c)
            }
        }
        is Copy -> {
            if(i < state.upTo) {
                state.copy(text = state.text + c)
            } else {
                uncompressed += state.text.plus(c).repeat(state.times)
                Read()
            }
        }
        else -> throw IllegalStateException(state.toString())
     }
}

println(uncompressed.length)
