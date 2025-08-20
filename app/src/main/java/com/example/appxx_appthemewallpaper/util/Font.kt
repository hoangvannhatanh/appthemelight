package com.example.appxx_appthemewallpaper.util

//DẠNG Unicode surrogate pairs

//Fraktur/Gothic
fun toFraktur(input: String): String {
    val frakturUpper = mapOf(
        'A' to "\uD835\uDD04", 'B' to "\uD835\uDD05", 'C' to "\uD835\uDD06",
        'D' to "\uD835\uDD07", 'E' to "\uD835\uDD08", 'F' to "\uD835\uDD09",
        'G' to "\uD835\uDD0A", 'H' to "\uD835\uDD0B", 'I' to "\uD835\uDD0C",
        'J' to "\uD835\uDD0D", 'K' to "\uD835\uDD0E", 'L' to "\uD835\uDD0F",
        'M' to "\uD835\uDD10", 'N' to "\uD835\uDD11", 'O' to "\uD835\uDD12",
        'P' to "\uD835\uDD13", 'Q' to "\uD835\uDD14", 'R' to "\uD835\uDD7D",
        'S' to "\uD835\uDD16", 'T' to "\uD835\uDD17", 'U' to "\uD835\uDD18",
        'V' to "\uD835\uDD19", 'W' to "\uD835\uDD1A", 'X' to "\uD835\uDD1B",
        'Y' to "\uD835\uDD1C", 'Z' to "\uD835\uDD1D"
    )

    val frakturLower = mapOf(
        'a' to "\uD835\uDD1E", 'b' to "\uD835\uDD1F", 'c' to "\uD835\uDD20",
        'd' to "\uD835\uDD21", 'e' to "\uD835\uDD22", 'f' to "\uD835\uDD23",
        'g' to "\uD835\uDD24", 'h' to "\uD835\uDD25", 'i' to "\uD835\uDD26",
        'j' to "\uD835\uDD27", 'k' to "\uD835\uDD28", 'l' to "\uD835\uDD29",
        'm' to "\uD835\uDD2A", 'n' to "\uD835\uDD2B", 'o' to "\uD835\uDD2C",
        'p' to "\uD835\uDD2D", 'q' to "\uD835\uDD2E", 'r' to "\uD835\uDD63",
        's' to "\uD835\uDD30", 't' to "\uD835\uDD31", 'u' to "\uD835\uDD32",
        'v' to "\uD835\uDD33", 'w' to "\uD835\uDD34", 'x' to "\uD835\uDD35",
        'y' to "\uD835\uDD36", 'z' to "\uD835\uDD37"
    )

    val builder = StringBuilder()
    for (c in input) {
        builder.append(
            frakturUpper[c] ?: frakturLower[c] ?: c // nếu không có trong map thì giữ nguyên
        )
    }
    return builder.toString()
}

//Kanit Font Style
fun toKanit(input: String): String {
    val kanitUpper = mapOf(
        'A' to "\uD835\uDD38", 'B' to "\uD835\uDD39", 'C' to "\uD835\uDD3A",
        'D' to "\uD835\uDD3B", 'E' to "\uD835\uDD3C", 'F' to "\uD835\uDD3D",
        'G' to "\uD835\uDD3E", 'H' to "\uD835\uDD3F", 'I' to "\uD835\uDD40",
        'J' to "\uD835\uDD41", 'K' to "\uD835\uDD42", 'L' to "\uD835\uDD43",
        'M' to "\uD835\uDD44", 'N' to "\uD835\uDD45", 'O' to "\uD835\uDD46",
        'P' to "\uD835\uDD47", 'Q' to "\uD835\uDD48", 'R' to "\uD835\uDD49",
        'S' to "\uD835\uDD4A", 'T' to "\uD835\uDD4B", 'U' to "\uD835\uDD4C",
        'V' to "\uD835\uDD4D", 'W' to "\uD835\uDD4E", 'X' to "\uD835\uDD4F",
        'Y' to "\uD835\uDD50", 'Z' to "\uD835\uDD51"
    )

    val kanitLower = mapOf(
        'a' to "\uD835\uDD52", 'b' to "\uD835\uDD53", 'c' to "\uD835\uDD54",
        'd' to "\uD835\uDD55", 'e' to "\uD835\uDD56", 'f' to "\uD835\uDD57",
        'g' to "\uD835\uDD58", 'h' to "\uD835\uDD59", 'i' to "\uD835\uDD5A",
        'j' to "\uD835\uDD5B", 'k' to "\uD835\uDD5C", 'l' to "\uD835\uDD5D",
        'm' to "\uD835\uDD5E", 'n' to "\uD835\uDD5F", 'o' to "\uD835\uDD60",
        'p' to "\uD835\uDD61", 'q' to "\uD835\uDD62", 'r' to "\uD835\uDD63",
        's' to "\uD835\uDD64", 't' to "\uD835\uDD65", 'u' to "\uD835\uDD66",
        'v' to "\uD835\uDD67", 'w' to "\uD835\uDD68", 'x' to "\uD835\uDD69",
        'y' to "\uD835\uDD6A", 'z' to "\uD835\uDD6B"
    )

    val builder = StringBuilder()
    for (c in input) {
        builder.append(
            kanitUpper[c] ?: kanitLower[c] ?: c // nếu không có trong map thì giữ nguyên
        )
    }
    return builder.toString()
}