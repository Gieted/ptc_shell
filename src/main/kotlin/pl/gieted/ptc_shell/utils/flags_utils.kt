package pl.gieted.ptc_shell.utils

import pl.gieted.ptc_shell.commands.Flag

operator fun Map<Flag, String?>.contains(flag: Flag) = keys.any { it.short == flag.short || it.verbose == flag.verbose }
