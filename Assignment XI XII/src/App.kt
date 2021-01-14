class App {
    private val databaseService = DatabaseService()
    private val learningMaterials = ArrayList<LearningMaterial>()

    fun createLearningMaterial(title: String) {}
    fun chooseLearningMaterial(leaningMaterial: LearningMaterial) {}

    fun login(username: String, password: String): Boolean = false
    fun register(username: String, password: String): Boolean = false

    fun generateTest(testStrategy: TestStrategy) {}

    fun quit() {}
}

