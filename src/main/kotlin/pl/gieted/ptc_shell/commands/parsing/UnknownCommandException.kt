package pl.gieted.ptc_shell.commands.parsing

class UnknownCommandException(val commandId: String) : Exception()
