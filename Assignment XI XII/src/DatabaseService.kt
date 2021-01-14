class DatabaseService {
    fun login(username: String, password: String): Boolean = false
    fun register(username: String, password: String): Boolean = false
    fun userExists(username: String): Boolean = false

    fun addLearningMaterial(material: LearningMaterial) {}
    fun getLearningMaterial(title: String): LearningMaterial = LearningMaterial("")

    fun addChapter(material: LearningMaterial, chapter: Chapter) {}
    fun getChapter(material: LearningMaterial, title: String): Chapter = Chapter("")

    fun addTranslation(chapter: Chapter, translation: Translation) {}
    fun getTranslations(chapter: Chapter): List<Translation> = listOf()
}