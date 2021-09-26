package pl.gieted.ptc_shell.tesseract

class Tesseract(states: List<Int>) {
    private val states = states.toMutableList()

    private val sideTransitions = listOf(
        0 to 1,
        1 to 3,
        3 to 2,
        0 to 4,
        1 to 5,
        2 to 6,
        3 to 7,
        4 to 5,
        5 to 7,
        2 to 0,
        7 to 6,
        6 to 4
    )

    fun isSideTransition(transition: Pair<Int, Int>): Boolean {
        val mappedTransition = states.indexOf(transition.first) to states.indexOf(transition.second)

        return sideTransitions.any {
            it.first == mappedTransition.first && it.second == mappedTransition.second
                    || it.first == mappedTransition.second && it.second == mappedTransition.first
        }
    }
}
