package watcher.observer

import watcher.memento.DataMemento

abstract class Observer<T, M> {
    abstract fun handle(oldValue: T, newValue: T)

    abstract fun restore(memento: M)
    abstract fun save(): M
}