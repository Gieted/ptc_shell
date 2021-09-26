package pl.gieted.ptc_shell.commands

import pl.gieted.ptc_shell.console.Console
import pl.gieted.ptc_shell.tesseract.Tesseract
import pl.gieted.ptc_shell.utils.allPermutations
import javax.inject.Inject

class TesseractCommand @Inject constructor(private val console: Console) : Command {

    override val id: String = "tess"


    override fun execute(arguments: List<String>, flags: Map<Flag, String?>) {
        if (arguments.size != 2) {
            console.println("Wprowadzono złe argumenty")
            return
        }

        try {
            val states = arguments.first().filter { it != ' ' }.split(",").map { it.toInt() }
            val transitions = arguments[1].filter { it != ' ' }.split(",").map {
                val (first, second) = it.split(">")
                first.toInt() to second.toInt()
            }

            val bestPermutation = allPermutations(states.toSet()).minByOrNull { permutation ->
                val tesseract = Tesseract(permutation)
                transitions.count { !tesseract.isSideTransition(it) }
            }!!

            console.println(bestPermutation.joinToString(", "))
        } catch (exception: Exception) {
            console.println("Wprowadzono złe argumenty")
        }
    }

    override val parameterNames = listOf("stany", "przejścia")
}
