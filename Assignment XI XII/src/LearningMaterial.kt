class LearningMaterial(var title: String) {
    val chapters = ArrayList<Chapter>()

    fun destroy() {}

    fun createChapter(title: String) {}

    fun searchChapter(title: String): Chapter = Chapter("")

    fun chooseChapter(chapter: Chapter) {}
}