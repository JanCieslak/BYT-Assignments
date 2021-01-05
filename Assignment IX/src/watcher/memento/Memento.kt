package watcher.memento

abstract class Memento<T>(initData: T) {
    val data: T = initData

    abstract fun persist()
}