import java.net.URL

fun getTextFromUrl(url: String): String =
    URL(url).readText()

fun splitStringOnLineBreak(string: String): List<String> =
    string.split("\r\n")