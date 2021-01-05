package watcher.memento

import watcher.Data
import java.io.File
import java.util.*

class DataMemento(data: List<Data>) : Memento<List<Data>>(data) {
    override fun persist() {
        val file = File("resources/memento/Memento.txt")
        val content = data.map { data -> "${data.url} ${data.lastModified.time}"}.joinToString("\n")
        file.writeText(content)
    }
}