package maxzonov.vkapi_sandbox

import maxzonov.vkapi_sandbox.utils.TextFormatter
import org.junit.Test

import org.junit.Assert.*

class TextFormatTest {

    @Test
    fun testingHashtagsFromTextHighlighter() {
        val text = "Какой-то текст, #очень #крутой текст"
        assertEquals("Какой-то текст, <font color='#1242B6'>#очень</font> <font color='#1242B6'>#крутой</font> текст", TextFormatter.highlightHashtagsInText(text))
    }

    @Test
    fun testingHashtagsListFromText() {
        val text1 = "Какой-то текст, #очень #крутой текст"
        val text2 = "Самый длинный #пост заходил #вчера#, делать было # лень"
        val hashtags: ArrayList<String> = ArrayList()
        hashtags.add("#пост")
        hashtags.add("#вчера#,")
        hashtags.add("#")
        assertEquals(hashtags, TextFormatter.getHashtagsArrayFromText(text2))
    }
}