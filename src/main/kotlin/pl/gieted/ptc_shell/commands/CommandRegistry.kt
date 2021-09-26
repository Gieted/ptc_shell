package pl.gieted.ptc_shell.commands

import javax.inject.Inject

class CommandRegistry @Inject constructor(
    exitCommand: ExitCommand,
    helpCommandFactory: HelpCommand.Factory,
    plekserCommand: PlekserCommand,
    grayCommand: GrayCommand,
    combinationCommand: CombinationCommand,
    tesseractCommand: TesseractCommand,
    jkCommand: JKCommand
) : Collection<Command> {
    private val commands = listOf(
        exitCommand,
        helpCommandFactory.create(this),
        plekserCommand,
        grayCommand,
        combinationCommand,
        tesseractCommand,
        jkCommand
    )

    operator fun get(id: String) = commands.find { it.id == id }

    override fun iterator() = commands.iterator()

    override val size
        get() = commands.size

    override fun contains(element: Command) = commands.contains(element)

    override fun containsAll(elements: Collection<Command>) = commands.containsAll(elements)

    override fun isEmpty() = commands.isEmpty()
}
