import watcher.memento.DataMemento
import watcher.UrlWatcher

fun main() {
    val watcher = UrlWatcher()

    watcher.register("http://pja.edu.pl/")
    watcher.register("http://google.com/")

    var i = 0
    var mem: DataMemento? = null
    while (true) {
        print("$i: ")
        watcher.update()

        if (i == 2) {
            mem = watcher.save()
            mem.persist()
        }

        if (i == 4)
            break

        Thread.sleep(2000)
        i++
    }

    val strings = mem!!.data.mapIndexed { index, data -> "$index - ${data.url} - ${data.lastModified}"}.joinToString("\n")
    println("saved: $strings")
}