package pl.gieted.ptc_shell.commands

import javax.inject.Inject
import kotlin.system.exitProcess

class ExitCommand @Inject constructor(): Command {
    override val id: String = "exit"

    override fun execute(arguments: List<String>, flags: Map<Flag, String?>) {
        exitProcess(0)
    }

    override val description: String = "kończy sesję"
}
