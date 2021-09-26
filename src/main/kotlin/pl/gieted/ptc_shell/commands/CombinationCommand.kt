package pl.gieted.ptc_shell.commands

import pl.gieted.ptc_shell.console.Console
import pl.gieted.ptc_shell.utils.allPermutations
import javax.inject.Inject

class CombinationCommand @Inject constructor(private val console: Console): Command {
    override val id: String = "comb"

    override fun execute(arguments: List<String>, flags: Map<Flag, String?>) {
        if (arguments.isEmpty()) {
            console.println("Wprowadź elementy")
            return
        }

        if (countFlag in flags) {
            console.println(allPermutations(arguments.toSet()).size.toString())

            return
        }

        allPermutations(arguments.toSet()).map { it.joinToString(" ") }.forEach { console.println(it) }
    }

    override val parameterNames = listOf("elementy...")

    private val countFlag = Flag("-c", "--count")

    override val flags = listOf(countFlag)
}
