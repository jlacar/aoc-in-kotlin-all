package lacar.junilu.common

// display numbers with units

private fun plural(value: String, singular: String, plural: String) =
    when {
        plural == "*" -> "$value $singular"
        plural.startsWith("+") -> "$value ${singular}${plural.drop(1)}"
        plural.startsWith("-") -> "$value ${singular.dropLast(1)}${plural.drop(1)}"
        else -> "$value $plural"
    }

fun Int.units(singular: String, plural: String = "+s") =
    when (this) {
        1 -> "$this $singular"
        else -> plural("$this", singular, plural)
    }

fun Long.units(singular: String, plural: String = "") =
    when (this) {
        1L -> "$this $singular"
        else -> plural("$this", singular, plural)
    }
