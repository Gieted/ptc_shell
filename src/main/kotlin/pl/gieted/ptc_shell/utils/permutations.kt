package pl.gieted.ptc_shell.utils

fun <T> allPermutations(set: Set<T>): Set<List<T>> {
    if (set.isEmpty()) return emptySet()

    fun <T> allPermutations2(list: List<T>): Set<List<T>> {
        if (list.isEmpty()) return setOf(emptyList())

        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in list.indices) {
            allPermutations2(list - list[i]).forEach{
                    item -> result.add(item + list[i])
            }
        }
        return result
    }

    return allPermutations2(set.toList())
}