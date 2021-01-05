package watcher.observer

class Observable<T>(initValue: T) {
    private val observables = ArrayList<Observer<T, Any>>()
    var value: T = initValue
        set(value) {
            if (value != field) {
                for (observable in observables) {
                    observable.handle(field, value)
                }

                field = value
            }
        }
}