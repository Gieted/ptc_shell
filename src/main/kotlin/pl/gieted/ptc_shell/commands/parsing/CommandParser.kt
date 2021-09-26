package pl.gieted.ptc_shell.commands.parsing

import pl.gieted.ptc_shell.commands.Command
import pl.gieted.ptc_shell.commands.CommandRegistry
import pl.gieted.ptc_shell.commands.Flag
import javax.inject.Inject

class CommandParser @Inject constructor(private val commandRegistry: CommandRegistry) {
    data class Output(val command: Command, val arguments: List<String>, val flags: Map<Flag, String?>)
    
    private fun getSymbols(input: String): List<String> {
        fun <T> MutableList<T>.modifyLast(transform: (T) -> T) {
            this[size - 1] = transform(last())
        }

        var symbolStart = true
        val symbols = mutableListOf("")
        var inQuotes = false

        fun newSymbol() {
            symbolStart = true
            symbols.add("")
        }

        for (char in input) {
            if (char == ' ' && !inQuotes) {
                newSymbol()
            } else {
                when {
                    char == '"' && (symbolStart || inQuotes) -> inQuotes = !inQuotes
                    else -> symbols.modifyLast { it + char }
                }
                symbolStart = false
            }

        }

        return symbols.filter { it.isNotEmpty() }
    }
    
    

    fun parse(input: String): Output {
        fun handleFlag(symbol: String, flags: MutableMap<Flag, String?>, command: Command, selectIdentifier: (Flag) -> String?) {
            val flagName = symbol.split("=")[0]
            val value = if ("=" in symbol) symbol.split("=")[1] else null
            val flag = command.flags.find { selectIdentifier(it) == flagName } ?: throw UnknownFlagException(flagName)
            flags[flag] = value
        }
        
        
        val symbols = getSymbols(input)
        val commandId = symbols.first()
        val command = commandRegistry[commandId] ?: throw UnknownCommandException(commandId)
        val arguments = mutableListOf<String>()
        val flags = mutableMapOf<Flag, String?>()

        symbols.drop(1).forEach { symbol ->
            when {
                symbol.startsWith("--") -> {
                    handleFlag(symbol, flags, command) { it.verbose }
                }
                symbol.startsWith("-") -> {
                    handleFlag(symbol, flags, command) { it.short }
                }
                else -> arguments.add(symbol)
            }
        }

        return Output(command, arguments, flags)
    }
}
