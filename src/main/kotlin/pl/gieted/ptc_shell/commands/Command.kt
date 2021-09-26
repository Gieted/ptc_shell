package pl.gieted.ptc_shell.commands

interface Command {
    val id: String
    
    fun execute(arguments: List<String>, flags: Map<Flag, String?>)
    
    val flags: List<Flag>
      get() = emptyList()
    
    val parameterNames: List<String>
      get() = emptyList()
    
    val description: String
      get() = ""
}
