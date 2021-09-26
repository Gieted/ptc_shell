package pl.gieted.ptc_shell

import pl.gieted.ptc_shell.commands.parsing.CommandParser
import pl.gieted.ptc_shell.commands.parsing.UnknownCommandException
import pl.gieted.ptc_shell.commands.parsing.UnknownFlagException
import pl.gieted.ptc_shell.console.Console
import javax.inject.Inject

class Shell @Inject constructor(
    private val console: Console,
    private val commandParser: CommandParser
) {
    fun start() {
        console.clear()
        printHeader()
        while (true) {
            console.print("\n> ")
            val input = console.readLine()
            if (input.isBlank()) {
                continue
            }
            onInput(input)
        }
    }

    private fun printHeader() {
        console.println("PTC Shell v1.0.0")
    }

    private fun onInput(input: String) {
        try {
            val parserOutput = commandParser.parse(input)
            parserOutput.command.execute(parserOutput.arguments, parserOutput.flags)
        } catch (exception: UnknownCommandException) {
            console.println("Nieznana komenda: ${exception.commandId}")
        } catch (exception: UnknownFlagException) {
            console.println("Nieznany parametr: ${exception.flag}")
        }
    }
}
