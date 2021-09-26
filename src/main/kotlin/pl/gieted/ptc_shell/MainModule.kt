package pl.gieted.ptc_shell

import dagger.Binds
import dagger.Module
import pl.gieted.ptc_shell.console.Console
import pl.gieted.ptc_shell.console.SystemConsole

@Module
abstract class MainModule {
    
    @Binds
    abstract fun console(to: SystemConsole): Console
}
