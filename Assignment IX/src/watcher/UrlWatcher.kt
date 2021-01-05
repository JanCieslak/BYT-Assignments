package watcher

import watcher.memento.DataMemento
import watcher.observer.Observable
import watcher.observer.Observer
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class UrlWatcher: Observer<Data, DataMemento>() {
    private val data = HashMap<URL, Observable<Date>>()

    fun update() {
        for ((url, date) in data) {
            val connection = url.openConnection()

            if (connection.lastModified > date.value.time) {
                val newDate = Date(connection.lastModified)

                val oldData = Data(url, date.value)
                val newData = Data(url, newDate)
                handle(oldData, newData)
                date.value = newDate
            }
        }
    }

    fun register(url: String) {
        val url = URL(url)
        val connection = url.openConnection()
        data[url] = Observable(Date(connection.lastModified))
    }

    override fun handle(oldValue: Data, newValue: Data) {
        println("${oldValue.url} update from ${oldValue.lastModified} to ${newValue.lastModified}")
    }

    override fun restore(memento: DataMemento) {
        data.clear()

        for (mem in memento.data) {
            data[mem.url] = Observable(mem.lastModified)
        }
    }

    override fun save(): DataMemento {
        val list = ArrayList<Data>()

        for ((url, date) in data) {
            list.add(Data(url, date.value))
        }

        return DataMemento(list)
    }
}