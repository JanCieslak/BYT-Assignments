import org.junit.Before
import org.junit.Test
import watcher.UrlWatcher
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ObserverTest {
    private lateinit var watcher: UrlWatcher

    @Before
    fun before() {
        watcher = UrlWatcher()
    }

    @Test
    fun registerTest() {
        watcher.register("http://pja.edu.pl/")
        watcher.update()
        val memento = watcher.save()

        assertEquals(1, memento.data.size)
        assertEquals("http://pja.edu.pl/", memento.data[0].url.toString())
    }

    @Test
    fun restoreTest() {
        watcher.register("http://pja.edu.pl/")
        watcher.update()
        val memento = watcher.save()

        assertEquals(1, memento.data.size)
        assertEquals("http://pja.edu.pl/", memento.data[0].url.toString())
        val time = memento.data[0].lastModified.time

        watcher.update()

        val memento2 = watcher.save()

        assertEquals(1, memento2.data.size)
        assertNotEquals(time, memento2.data[0].lastModified.time)
    }

    @Test
    fun saveTest() {
        watcher.register("http://pja.edu.pl/")
        watcher.update()
        val memento = watcher.save()

        assertEquals(1, memento.data.size)
        assertEquals("http://pja.edu.pl/", memento.data[0].url.toString())
    }
}