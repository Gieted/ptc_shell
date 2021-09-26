package pl.gieted.ptc_shell.console

import javax.inject.Inject

class SystemConsole @Inject constructor() : Console {
    override fun println(message: String) = kotlin.io.println(message)

    override fun print(message: String) = kotlin.io.print(message)
    
    override fun readLine() = kotlin.io.readLine()!!
    
    override fun clear() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }
}
