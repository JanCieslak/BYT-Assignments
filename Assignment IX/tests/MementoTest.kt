import org.junit.Test
import watcher.UrlWatcher
import java.io.File
import kotlin.test.assertEquals

class MementoTest {
    @Test
    fun createsBackupFiles() {
        val watcher = UrlWatcher();

        val file = File("resources/memento/Memento.txt")
        if (file.exists())
            file.delete()

        watcher.register("http://pja.edu.pl/")
        watcher.update()
        val memento = watcher.save()
        memento.persist()

        val file2 = File("resources/memento/Memento.txt")
        if (file2.exists()) {
            val data = file2.readLines()
            val lines = memento.data

            assertEquals(data.size, lines.size)

            for (i in data.indices) {
                val (url, time) = data[i].split(" ")
                assertEquals(url, lines[i].url.toString())
                assertEquals(time.toLong(), lines[i].lastModified.time)
            }
        } else {
            error("File should exist")
        }
    }
}