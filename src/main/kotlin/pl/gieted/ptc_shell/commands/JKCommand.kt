package pl.gieted.ptc_shell.commands

import pl.gieted.ptc_shell.console.Console
import javax.inject.Inject

class JKCommand @Inject constructor(private val console: Console) : Command {
    override val id = "jk"

    override fun execute(arguments: List<String>, flags: Map<Flag, String?>) {
        console.println(
            """
            0 -> 0 = 0 -
            0 -> 1 = 1 -
            1 -> 0 = - 1
            1 -> 1 = - 0
            """.trimIndent()
        )
    }
}
