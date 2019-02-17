package maxzonov.vkapi_sandbox.utils

object TextFormatter {

    fun highlightHashtagsInText(text: String): String {
        var resultText: String = text
        val textWithoutNewlines = resultText.replace("\n\n", " ").replace("\n", " ")
        val splittedText = textWithoutNewlines.split(" ")
        val hashtags: ArrayList<String> = ArrayList()
        for (item in splittedText) {
            if (item.startsWith('#'))
                hashtags.add(item)
        }
        for (hashtag in hashtags) {
            resultText = resultText.replace(hashtag, "<font color='#1242B6'>$hashtag</font>")
        }

        return resultText
    }
}