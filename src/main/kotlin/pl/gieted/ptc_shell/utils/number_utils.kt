package pl.gieted.ptc_shell.utils

fun Int.toBinary(base: Int): String {
    val str = toString(radix = 2)

    return if (base > str.length) "0".repeat(base - str.length) + str else str
}
