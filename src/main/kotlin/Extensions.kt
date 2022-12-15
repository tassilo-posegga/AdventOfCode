import java.net.URL

fun String.getTextFromUrl(): String =
    URL(this).readText()

fun String.splitOnLineBreak(): List<String> =
    split("\r\n")