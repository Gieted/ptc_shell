package pl.gieted.ptc_shell

fun main() {
    val component: MainComponent = DaggerMainComponent.create()
    component.shell().start()
}
