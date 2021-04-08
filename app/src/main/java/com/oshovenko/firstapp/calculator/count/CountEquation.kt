package com.oshovenko.firstapp.calculator.count

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.pow

class CountEquation(private var formula: String) {
    private lateinit var equationResult: String

    fun count(): String {
        equationResult = searchUnaryMinus(formula)
        addSpace()
        val reversNotation = ReversNotation(equationResult)
        var postfixFormula: String = reversNotation.parsing()
        return  calculate(postfixFormula)
    }


    /**
     * Converts a unary negative expression to a valid entry by adding parentheses.
     *
     * @param expression initial expression record
     * @return valid expression with unary minus
     */
    private fun searchUnaryMinus(expression: String): String {
        var expression: String = expression
        expression = replacementUnaryMinus(expression, "\\--([A-Za-z0-9]+|\\([A-Za-z0-9]+)", "-(0-$1)")
        expression = replacementUnaryMinus(expression, "\\+-([A-Za-z0-9]+|\\([A-Za-z0-9]+)", "+(0-$1)")
        expression = replacementUnaryMinus(expression, "\\*-([A-Za-z0-9]+|\\(.+)", "*(0-$1)")
        expression = replacementUnaryMinus(expression, "\\/-([A-Za-z0-9]+|\\(.+)", "/(0-$1)")
        expression = replacementUnaryMinus(expression, "\\^-([A-Za-z0-9]+|\\(.+)", "^(0-$1)")
        expression = replacementUnaryMinus(expression, "^-([A-Za-z0-9]+|\\(.+)", "0-$1")
        expression = replacementUnaryMinus(expression, "\\(-([A-Za-z0-9])", "(0-$1")
        return expression
    }


    /**
     * Brings the unary minus formula to the correct entry.
     *
     * @param expression  incoming formula
     * @param mask        search pattern
     * @param replacement template for replacing the found unary minus
     * @return properly written formula
     */
    private fun replacementUnaryMinus(expression: String, mask: String, replacement: String): String {
        var expression: String = expression
        val pattern: Pattern = Pattern.compile(mask)
        val interpreter: Matcher = pattern.matcher(expression)
        expression = interpreter.replaceAll(replacement)
        return expression
    }

    /**
     * Adds spaces to the expression
     */
    private fun addSpace() {
        val pattern = Pattern.compile("([\\+\\-\\*\\/\\^\\(\\)])")
        val interpreter = pattern.matcher(equationResult)
        equationResult = interpreter.replaceAll(" $1 ")
    }

    /**
     * The method counts an expression written in reverse Polish notation
     *
     * @param postfix   formula in the form of reverse Polish notation
     */
    private fun calculate(postfix: String?): String {
        val formula = StringTokenizer(postfix)
        val stack: Stack<Double> = Stack()
        var a: Double
        var currentItem: String
            while (formula.hasMoreTokens()) {
                currentItem = formula.nextToken()

                // if the current element is a operatorL
                if (currentItem.length == 1 && isOperator(currentItem[0])) {
                    if (stack.size < 2) {
                        throw Exception("Ops! Invalid formula")
                    }
                    a = getOperationValue(currentItem[0], stack.pop(), stack.pop())

                    //return the result of the operation to the stack
                    stack.push(a)
                } else {
                    try {
                        a = currentItem.toDouble()
                        stack.push(a)
                    } catch (e: Exception){
                        throw Exception("Ops! Invalid formula")
                    }
                }
            }
            //There must be one number left on the stack. If not, then the formula is wrong
            if (stack.size !== 1) {
                throw Exception("Ops! Invalid formula")
            }
            return stack.pop().toString()
    }

    /**
     * The method checks if the current character is an operator
     *
     * @param operator character to check
     * @return true if character is operator, else - false
     */
    fun isOperator(operator: Char): Boolean {
        when (operator) {
            '-', '+', '*', '/', '^' -> return true
        }
        return false
    }

    /**
     * Performs mathematical operations on two operands
     *
     * @param operator mathematical operator
     * @param b        second operand
     * @param a        first operand
     * @return arithmetic result
     */
    @Throws(Exception::class)
    private fun getOperationValue(operator: Char, b: Double, a: Double): Double {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            '/' -> {
                if (b != 0.0) {
                    return a / b
                }
                throw Exception("Ops! Division by zero!")
            }
            '*' -> a * b
            '^' -> {
                if (!(a == 0.0 && b < 0)) {
                    return a.pow(b)
                }
                throw Exception("Ops! 0 cannot be raised to a negative power!")
            }
            else -> throw Exception("Ops! Invalid operation!")
        }
    }
}