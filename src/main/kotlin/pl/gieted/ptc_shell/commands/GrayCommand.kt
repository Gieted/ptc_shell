package pl.gieted.ptc_shell.commands

import pl.gieted.ptc_shell.console.Console
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

class GrayCommand @Inject constructor(private val console: Console) : Command {
    override val id = "gray"

    override fun execute(arguments: List<String>, flags: Map<Flag, String?>) {
        if (arguments.isEmpty()) {
            console.println("Podaj długość kodu")
            return
        }
        try {
            val length = arguments.first().toInt()
            var powerOfTwo = 1
            while (2.0.pow(powerOfTwo) < length) {
                powerOfTwo++
            }
            grayCoded(powerOfTwo).slice(0 until length).forEach { console.println(it) }
        } catch (e: Exception) {
            console.println("Wprowadzono niepoprawne argumenty")
        }
    }

    override val parameterNames = listOf("length")

    private fun grayCoded(length: Int): List<String> = if (length == 1) listOf("0", "1") else {
        val previous = grayCoded(length - 1)
        (previous + previous.reversed()).mapIndexed { index, s -> if (index < previous.size) "0$s" else "1$s" }
    }
}
