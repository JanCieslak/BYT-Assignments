import kotlin.test.assertEquals

class Test {
    private val someText = "someText"

    @Test
    fun `App works`() {
        val app = App()
    }

    @Test
    fun `Learning Material works`() {
        assertEquals(someText, LearningMaterial(someText).title)
    }

    @Test
    fun `Chapter works`() {
        assertEquals(someText, Chapter(someText).title)
    }

    @Test
    fun `Translation works`() {
        val originalWord = "palec"
        val translatedWord = "finger"
        val translation = Translation(originalWord, translatedWord)

        assertEquals(originalWord, translation.originalWord)
        assertEquals(translatedWord, translation.translatedWord)
    }

    @Test
    fun `DbService works`() {
        val dbService = DatabaseService()
    }
}