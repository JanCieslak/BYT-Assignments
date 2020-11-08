package group_two

abstract class SortingAlgorithm<T: Comparable<T>>(protected val data: ArrayList<T>) {
    fun sort(): ArrayList<T> {
        var i = 0;
        while (i < data.size - 1) {
            var j = 0
            while (j < data.size - 1) {
                if (compare(j, j + 1)) {
                    swap(j, j + 1)
                }
                j++
            }
            i++
        }

        return data
    }

    private fun swap(index1: Int, index2: Int) {
        val temp = data[index1]
        data[index1] = data[index2]
        data[index2] = temp
    }

    abstract fun compare(index1: Int, index2: Int): Boolean
}

class DescendingSort<T: Comparable<T>>(data: ArrayList<T>): SortingAlgorithm<T>(data) {
    override fun compare(index1: Int, index2: Int): Boolean {
        return data[index1] < data[index2]
    }
}

class AscendingSort<T: Comparable<T>>(data: ArrayList<T>): SortingAlgorithm<T>(data) {
    override fun compare(index1: Int, index2: Int): Boolean {
        return data[index1] > data[index2]
    }
}

fun main() {
    val strings = arrayListOf("D", "A", "C", "B")
    val nums = arrayListOf(3, 2, 1, 5, 4)

    println("Asc: ${AscendingSort(strings).sort()}")
    println("Desc: ${DescendingSort(strings).sort()}")

    println()

    println("Asc: ${AscendingSort(nums).sort()}")
    println("Desc: ${DescendingSort(nums).sort()}")
}