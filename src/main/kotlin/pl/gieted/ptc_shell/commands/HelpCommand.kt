package pl.gieted.ptc_shell.commands

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import pl.gieted.ptc_shell.commands.parsing.UnknownCommandException
import pl.gieted.ptc_shell.console.Console

class HelpCommand @AssistedInject constructor(
    @Assisted private val commandRegistry: CommandRegistry,
    private val console: Console
) : Command {

    @AssistedFactory
    interface Factory {
        fun create(commandRegistry: CommandRegistry): HelpCommand
    }

    override val id = "help"

    override fun execute(arguments: List<String>, flags: Map<Flag, String?>) {
        if (arguments.isEmpty()) {
            console.println("Komendy:")
            commandRegistry.sortedBy { it.id }.forEach {
                console.println(if (it.description.isNotEmpty()) "${it.id} - ${it.description}" else it.id)
            }
        } else {
            val commandId = arguments.first()
            val command = commandRegistry[commandId] ?: throw UnknownCommandException(commandId)
            console.println(
                """
                $commandId ${
                    (command.parameterNames + command.flags.map {
                        if (it.short != null) "${it.short} ${it.verbose}" else it.verbose
                    }).joinToString(" ") { "[$it]" }
                }
                
                ${command.description}
                """.trimIndent()
            )
        }
    }

    override val description: String = "wyświetla informacje dotyczące komend"

    override val parameterNames: List<String> = listOf("komenda")
}
