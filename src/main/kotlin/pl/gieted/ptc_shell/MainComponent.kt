package pl.gieted.ptc_shell

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun shell(): Shell
}
