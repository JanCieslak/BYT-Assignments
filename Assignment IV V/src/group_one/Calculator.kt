package group_one

import java.lang.IllegalStateException

abstract class ExpressionHandler {
    private var next: ExpressionHandler? = null

    fun then(next: ExpressionHandler): ExpressionHandler {
        this.next = next
        return next
    }

    abstract fun handle(expression: String): Boolean

    protected fun handleNext(expression: String): Boolean {
        if (this.next == null) {
            return true
        }
        return next!!.handle(expression)
    }
}

abstract class BaseExpressionHandler : ExpressionHandler() {
    protected abstract val sign: String

    private fun handleExpression(expression: String): String {
        val splitExpression = expression.split(" ")
        val newExpression = StringBuilder()

        if (splitExpression.size <= 2) {
            throw IllegalStateException("Wrong expressions")
        }

        var i = 0
        while (i < splitExpression.size) {
            if (i + 1 < splitExpression.size && splitExpression[i + 1] == sign) {
                val result = calculate(splitExpression[i].toInt(), splitExpression[i + 2].toInt())
                newExpression.append("$result ")
                i += 2
            }
            else {
                newExpression.append("${splitExpression[i]} ")
            }

            i++
        }

        return newExpression.toString().trim()
    }

    override fun handle(expression: String): Boolean  {
        var newExpression = handleExpression(expression)

        while (sign in newExpression) {
            newExpression = handleExpression(newExpression)
        }

        return handleNext(newExpression)
    }

    protected abstract fun calculate(left: Int, right: Int): Int
}

class MultiplyExpressionHandler : BaseExpressionHandler() {
    override val sign: String
        get() = "*"

    override fun calculate(left: Int, right: Int): Int {
        return left * right
    }
}

class DivideExpressionHandler : BaseExpressionHandler() {
    override val sign: String
        get() = "/"

    override fun calculate(left: Int, right: Int): Int {
        return left / right
    }
}

class AddExpressionHandler : BaseExpressionHandler() {
    override val sign: String
        get() = "+"

    override fun calculate(left: Int, right: Int): Int {
        return left + right
    }
}

class MinusExpressionHandler: BaseExpressionHandler() {
    override val sign: String
        get() = "-"

    override fun calculate(left: Int, right: Int): Int {
        return left - right
    }
}

class PrintExpression: ExpressionHandler() {
    override fun handle(expression: String): Boolean {
        println(expression)
        return handleNext(expression)
    }
}

fun main() {
    // Input needs spaces between every sign / number
    val expression = "5 * 5 + 2 - 1 + 23 / 5 * 2 + 3"
    val calculator = PrintExpression()

    println("Normal calculator:")

    calculator
        .then(MultiplyExpressionHandler()) // multiply
        .then(PrintExpression())
        .then(DivideExpressionHandler()) // divide
        .then(PrintExpression())
        .then(AddExpressionHandler()) // add
        .then(PrintExpression())
        .then(MinusExpressionHandler()) // subtract
        .then(PrintExpression())

    calculator.handle(expression)

    println("Strange calculator:")

    // a calculator that add -> divide -> subtract -> multiply - because i can
    val strangeCalculator = PrintExpression()
    strangeCalculator
        .then(AddExpressionHandler()) // add
        .then(PrintExpression())
        .then(DivideExpressionHandler()) // divide
        .then(PrintExpression())
        .then(MinusExpressionHandler()) // subtract
        .then(PrintExpression())
        .then(MultiplyExpressionHandler()) // multiply
        .then(PrintExpression())

    strangeCalculator.handle(expression)

    // OUTPUT:
//    Normal calculator:
//    5 * 5 + 2 - 1 + 23 / 5 * 2 + 3
//    25 + 2 - 1 + 23 / 10 + 3
//    25 + 2 - 1 + 2 + 3
//    27 - 6
//    21
//    Strange calculator:
//    5 * 5 + 2 - 1 + 23 / 5 * 2 + 3
//    5 * 7 - 24 / 5 * 5
//    5 * 7 - 4 * 5
//    5 * 3 * 5
//    75
}