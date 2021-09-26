package pl.gieted.ptc_shell.commands

import pl.gieted.ptc_shell.console.Console
import pl.gieted.ptc_shell.utils.toBinary
import javax.inject.Inject

class PlekserCommand @Inject constructor(private val console: Console) : Command {
    override val id: String = "plekser"

    private val pairFlag = Flag("-p", "--pair")

    override fun execute(arguments: List<String>, flags: Map<Flag, String?>) {
        if (arguments.isEmpty()) {
            console.println("Podaj funkcję")
        } else {
            val function = arguments.first().filter { it != ' ' }
            try {
                val numbers = function.split(",").map { it.toInt() }.sorted()
                var powerOfTwo = 1
                while (powerOfTwo < numbers.last()) {
                    powerOfTwo *= 2
                }
                val bitWidth = powerOfTwo.toString(radix = 2).length - 1
                val values = (0 until powerOfTwo).map { it in numbers }
                val pairedValues = mutableListOf<String>()
                for (i in 0 until powerOfTwo) {
                    console.print("${i.toBinary(bitWidth)} ($i): ${if (values[i]) "1" else "0"}")
                    if (pairFlag in flags && i % 2 != 0) {
                        console.println(
                            " ${
                                when {
                                    values[i - 1] && values[i] -> "1"
                                    !values[i -1] && !values[i] -> "0"
                                    !values[i -1] && values[i] -> "d"
                                    else -> "!d"
                                }.also { pairedValues.add(it) }
                            }"
                        )
                    }
                    console.println("")
                }

                if (pairFlag in flags) {
                    for (i in 0 until powerOfTwo / 2) {
                        console.println("${i.toBinary(bitWidth - 1)} ($i): ${pairedValues[i]}")
                    } 
                }
            } catch (exception: Exception) {
                console.println("Wprowadzono nieprawidłową funkcję")
            }
        }
    }

    override val parameterNames: List<String> = listOf("funkcja")

    override val flags: List<Flag> = listOf(pairFlag)
}
