class Chapter(var title: String) {
    val translations = ArrayList<Translation>()

    fun destroy() {}

    fun addTranslation(translation: Translation) {}

    fun removeTranslation(translation: Translation) {}
}