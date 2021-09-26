package pl.gieted.ptc_shell.console

interface Console {
    fun println(message: String)
    
    fun print(message: String)
    
    fun readLine(): String
    
    fun clear()
}
