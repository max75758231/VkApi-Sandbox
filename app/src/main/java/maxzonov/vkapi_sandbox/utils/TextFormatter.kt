package maxzonov.vkapi_sandbox.utils

object TextFormatter {

    fun highlightHashtagsInText(text: String): String {
        var resultText: String = text
        var hashtags: ArrayList<String> = getHashtagsArrayFromText(resultText)
        for (hashtag in hashtags) {
            resultText = resultText.replace(hashtag, "<font color='#1242B6'>$hashtag</font>")
        }

        return resultText
    }

    fun getHashtagsArrayFromText(text: String): ArrayList<String> {
        val textWithoutNewlines = text.replace("\n\n", " ").replace("\n", " ")
        val splittedText = textWithoutNewlines.split(" ")
        val hashtags: ArrayList<String> = ArrayList()
        for (item in splittedText) {
            if (item.startsWith('#'))
                hashtags.add(item)
        }
        return hashtags
    }
}