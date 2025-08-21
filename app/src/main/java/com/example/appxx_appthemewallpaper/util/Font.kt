package com.example.appxx_appthemewallpaper.util

//DẠNG Unicode surrogate pairs

//Fraktur/Gothic
fun getListFont(): MutableList<String> {
    val listFont: MutableList<String> = arrayListOf()
    listFont.add("Roboto")
    listFont.add("Fraktur")
    listFont.add("Gothic")
    listFont.add("Kanit")
    listFont.add("Satoshi")
    listFont.add("Poppins")
    listFont.add("Product Sans")
    return listFont
}
fun toRoboto(input: String): String {
    val frakturUpper = mapOf(
        'A' to "\uD835\uDD6C", 'B' to "\uD835\uDD05", 'C' to "\uD835\uDCB8",
        'D' to "\uD835\uDD07", 'E' to "\uD835\uDD08", 'F' to "\uD835\uDD09",
        'G' to "\uD835\uDD0A", 'H' to "\uD835\uDD0B", 'I' to "\uD835\uDD0C",
        'J' to "\uD835\uDD0D", 'K' to "\uD835\uDD0E", 'L' to "\uD835\uDD77",
        'M' to "\uD835\uDD10", 'N' to "\uD835\uDD11", 'O' to "\uD835\uDD7A",
        'P' to "\uD835\uDD13", 'Q' to "\uD835\uDD14", 'R' to "\uD835\uDCC7",
        'S' to "\uD835\uDD16", 'T' to "\uD835\uDD17", 'U' to "\uD835\uDD18",
        'V' to "\uD835\uDD19", 'W' to "\uD835\uDD1A", 'X' to "\uD835\uDD1B",
        'Y' to "\uD835\uDD1C", 'Z' to "\uD835\uDD6B"
    )


    val frakturLower = mapOf(
        'a' to "\uD835\uDD1E", 'b' to "\uD835\uDD1F", 'c' to "\uD835\uDCB8",
        'd' to "\uD835\uDD21", 'e' to "\uD835\uDC52", 'f' to "\uD835\uDD23",
        'g' to "\uD835\uDD24", 'h' to "\uD835\uDCBD", 'i' to "\uD835\uDD26",
        'j' to "\uD835\uDD27", 'k' to "\uD835\uDD28", 'l' to "\uD835\uDD29",
        'm' to "\uD835\uDCC2", 'n' to "\uD835\uDD2B", 'o' to "\uD835\uDC5C",
        'p' to "\uD835\uDD2D", 'q' to "\uD835\uDD2E", 'r' to "\uD835\uDCC7",
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

fun toFraktur(input: String): String {
    val frakturUpper = mapOf(
        'A' to "\uD835\uDD6C", 'B' to "\uD835\uDD05", 'C' to "\uD835\uDCB8",
        'D' to "\uD835\uDD07", 'E' to "\uD835\uDD08", 'F' to "\uD835\uDD09",
        'G' to "\uD835\uDD0A", 'H' to "\uD835\uDD0B", 'I' to "\uD835\uDD0C",
        'J' to "\uD835\uDD0D", 'K' to "\uD835\uDD0E", 'L' to "\uD835\uDD77",
        'M' to "\uD835\uDD10", 'N' to "\uD835\uDD11", 'O' to "\uD835\uDD7A",
        'P' to "\uD835\uDD13", 'Q' to "\uD835\uDD14", 'R' to "\uD835\uDCC7",
        'S' to "\uD835\uDD16", 'T' to "\uD835\uDD17", 'U' to "\uD835\uDD18",
        'V' to "\uD835\uDD19", 'W' to "\uD835\uDD1A", 'X' to "\uD835\uDD1B",
        'Y' to "\uD835\uDD1C", 'Z' to "\uD835\uDD6B"
    )


    val frakturLower = mapOf(
        'a' to "\uD835\uDD1E", 'b' to "\uD835\uDD1F", 'c' to "\uD835\uDCB8",
        'd' to "\uD835\uDD21", 'e' to "\uD835\uDC52", 'f' to "\uD835\uDD23",
        'g' to "\uD835\uDD24", 'h' to "\uD835\uDCBD", 'i' to "\uD835\uDD26",
        'j' to "\uD835\uDD27", 'k' to "\uD835\uDD28", 'l' to "\uD835\uDD29",
        'm' to "\uD835\uDCC2", 'n' to "\uD835\uDD2B", 'o' to "\uD835\uDC5C",
        'p' to "\uD835\uDD2D", 'q' to "\uD835\uDD2E", 'r' to "\uD835\uDCC7",
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

//Satoshi Font Style
fun toSatoshi(input: String): String {
    val satoshiUpper = mapOf(
        'A' to "\uD835\uDD6C", 'B' to "\uD835\uDD6D", 'C' to "\uD835\uDD6E",
        'D' to "\uD835\uDD6F", 'E' to "\uD835\uDD70", 'F' to "\uD835\uDD71",
        'G' to "\uD835\uDD72", 'H' to "\uD835\uDD73", 'I' to "\uD835\uDD74",
        'J' to "\uD835\uDD75", 'K' to "\uD835\uDD76", 'L' to "\uD835\uDD77",
        'M' to "\uD835\uDD78", 'N' to "\uD835\uDD79", 'O' to "\uD835\uDD7A",
        'P' to "\uD835\uDD7B", 'Q' to "\uD835\uDD7C", 'R' to "\uD835\uDD7D",
        'S' to "\uD835\uDD7E", 'T' to "\uD835\uDD7F", 'U' to "\uD835\uDD80",
        'V' to "\uD835\uDD81", 'W' to "\uD835\uDD82", 'X' to "\uD835\uDD83",
        'Y' to "\uD835\uDD84", 'Z' to "\uD835\uDD85"
    )

    val satoshiLower = mapOf(
        'a' to "\uD835\uDD86", 'b' to "\uD835\uDD87", 'c' to "\uD835\uDD88",
        'd' to "\uD835\uDD89", 'e' to "\uD835\uDD8A", 'f' to "\uD835\uDD8B",
        'g' to "\uD835\uDD8C", 'h' to "\uD835\uDD8D", 'i' to "\uD835\uDD8E",
        'j' to "\uD835\uDD8F", 'k' to "\uD835\uDD90", 'l' to "\uD835\uDD91",
        'm' to "\uD835\uDD92", 'n' to "\uD835\uDD93", 'o' to "\uD835\uDD94",
        'p' to "\uD835\uDD95", 'q' to "\uD835\uDD96", 'r' to "\uD835\uDD97",
        's' to "\uD835\uDD98", 't' to "\uD835\uDD99", 'u' to "\uD835\uDD9A",
        'v' to "\uD835\uDD9B", 'w' to "\uD835\uDD9C", 'x' to "\uD835\uDD9D",
        'y' to "\uD835\uDD9E", 'z' to "\uD835\uDD9F"
    )

    val builder = StringBuilder()
    for (c in input) {
        builder.append(
            satoshiUpper[c] ?: satoshiLower[c] ?: c // nếu không có trong map thì giữ nguyên
        )
    }
    return builder.toString()
}

//Poppins Font Style
fun toPoppins(input: String): String {
    val poppinsUpper = mapOf(
        'A' to "\uD835\uDDA0", 'B' to "\uD835\uDDA1", 'C' to "\uD835\uDDA2",
        'D' to "\uD835\uDDA3", 'E' to "\uD835\uDDA4", 'F' to "\uD835\uDDA5",
        'G' to "\uD835\uDDA6", 'H' to "\uD835\uDDA7", 'I' to "\uD835\uDDA8",
        'J' to "\uD835\uDDA9", 'K' to "\uD835\uDDAA", 'L' to "\uD835\uDDAB",
        'M' to "\uD835\uDDAC", 'N' to "\uD835\uDDAD", 'O' to "\uD835\uDDAE",
        'P' to "\uD835\uDDAF", 'Q' to "\uD835\uDDB0", 'R' to "\uD835\uDDB1",
        'S' to "\uD835\uDDB2", 'T' to "\uD835\uDDB3", 'U' to "\uD835\uDDB4",
        'V' to "\uD835\uDDB5", 'W' to "\uD835\uDDB6", 'X' to "\uD835\uDDB7",
        'Y' to "\uD835\uDDB8", 'Z' to "\uD835\uDDB9"
    )

    val poppinsLower = mapOf(
        'a' to "\uD835\uDDBA", 'b' to "\uD835\uDDBB", 'c' to "\uD835\uDDBC",
        'd' to "\uD835\uDDBD", 'e' to "\uD835\uDDBE", 'f' to "\uD835\uDDBF",
        'g' to "\uD835\uDDC0", 'h' to "\uD835\uDDC1", 'i' to "\uD835\uDDC2",
        'j' to "\uD835\uDDC3", 'k' to "\uD835\uDDC4", 'l' to "\uD835\uDDC5",
        'm' to "\uD835\uDDC6", 'n' to "\uD835\uDDC7", 'o' to "\uD835\uDDC8",
        'p' to "\uD835\uDDC9", 'q' to "\uD835\uDDCA", 'r' to "\uD835\uDDCB",
        's' to "\uD835\uDDCC", 't' to "\uD835\uDDCD", 'u' to "\uD835\uDDCE",
        'v' to "\uD835\uDDCF", 'w' to "\uD835\uDDD0", 'x' to "\uD835\uDDD1",
        'y' to "\uD835\uDDD2", 'z' to "\uD835\uDDD3"
    )

    val builder = StringBuilder()
    for (c in input) {
        builder.append(
            poppinsUpper[c] ?: poppinsLower[c] ?: c // nếu không có trong map thì giữ nguyên
        )
    }
    return builder.toString()
}

// Product Sans (Bold Sans-Serif) Style
fun toProductSans(input: String): String {
    val productSansUpper = mapOf(
        'A' to "\uD835\uDDD4", 'B' to "\uD835\uDDD5", 'C' to "\uD835\uDDD6",
        'D' to "\uD835\uDDD7", 'E' to "\uD835\uDDD8", 'F' to "\uD835\uDDD9",
        'G' to "\uD835\uDDDA", 'H' to "\uD835\uDDDB", 'I' to "\uD835\uDDDC",
        'J' to "\uD835\uDDDD", 'K' to "\uD835\uDDDE", 'L' to "\uD835\uDDDF",
        'M' to "\uD835\uDDE0", 'N' to "\uD835\uDDE1", 'O' to "\uD835\uDDE2",
        'P' to "\uD835\uDDE3", 'Q' to "\uD835\uDDE4", 'R' to "\uD835\uDDE5",
        'S' to "\uD835\uDDE6", 'T' to "\uD835\uDDE7", 'U' to "\uD835\uDDE8",
        'V' to "\uD835\uDDE9", 'W' to "\uD835\uDDEA", 'X' to "\uD835\uDDEB",
        'Y' to "\uD835\uDDEC", 'Z' to "\uD835\uDDED"
    )

    val productSansLower = mapOf(
        'a' to "\uD835\uDDEE", 'b' to "\uD835\uDDEF", 'c' to "\uD835\uDDF0",
        'd' to "\uD835\uDDF1", 'e' to "\uD835\uDDF2", 'f' to "\uD835\uDDF3",
        'g' to "\uD835\uDDF4", 'h' to "\uD835\uDDF5", 'i' to "\uD835\uDDF6",
        'j' to "\uD835\uDDF7", 'k' to "\uD835\uDDF8", 'l' to "\uD835\uDDF9",
        'm' to "\uD835\uDDFA", 'n' to "\uD835\uDDFB", 'o' to "\uD835\uDDFC",
        'p' to "\uD835\uDDFD", 'q' to "\uD835\uDDFE", 'r' to "\uD835\uDDFF",
        's' to "\uD835\uDE00", 't' to "\uD835\uDE01", 'u' to "\uD835\uDE02",
        'v' to "\uD835\uDE03", 'w' to "\uD835\uDE04", 'x' to "\uD835\uDE05",
        'y' to "\uD835\uDE06", 'z' to "\uD835\uDE07"
    )

    val builder = StringBuilder()
    for (c in input) {
        builder.append(
            productSansUpper[c] ?: productSansLower[c] ?: c
        )
    }
    return builder.toString()
}