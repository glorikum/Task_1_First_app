package com.oshovenko.firstapp.calculator.count

import java.util.*

class ReversNotation(private var formula: String) {

    /**
     * Method converts infix to postfix
     */
    @Throws(Exception::class)
     fun parsing(): String{
        val result = StringBuilder()
        var currentElement: String
        var previousElement: String
        var firstSymbol: Char
        val stackOperations: Stack<String> = Stack()
        val rnp = StringTokenizer(formula)
        while (rnp.hasMoreTokens()) {
            currentElement = rnp.nextToken()
            firstSymbol = currentElement[0]

            // if the current element is an operator or function
            if (isOperator(firstSymbol)) {
                while (!stackOperations.empty()) {
                    previousElement = stackOperations.peek()
                    if (isOperator(previousElement[0]) && priority(firstSymbol) <=
                            priority(previousElement[0]) && firstSymbol != '^') {
                        result.append(" ").append(previousElement)
                        stackOperations.pop()
                    } else {
                        break
                    }
                }
                result.append(" ")
                stackOperations.push(currentElement)

                // if the current element is a bracket
            } else if ('(' == firstSymbol) {
                stackOperations.push(currentElement)
            } else if (')' == firstSymbol) {

                // for cases without other operators of type "2)"
                if (stackOperations.empty()) {
                    throw Exception("Ops! Invalid formula")
                }
                // handle everything between brackets
                handleProcessing(result, stackOperations)


            } else {
                result.append(currentElement)
            }
        }

        // extract the remaining operators or functions
        while (!stackOperations.empty()) {
            if (stackOperations.peek().get(0) === '(') {
                throw Exception("Ops! Invalid formula")
            }
            result.append(" ").append(stackOperations.pop())
        }
        return result.toString()
    }

    /**
     * Extract all statements between brackets
     *
     * @param result          The string to write the result
     * @param stackOperations Stack with operators
     */
    private fun handleProcessing(result: StringBuilder, stackOperations: Stack<String>) {
        var previouselement: String
        previouselement = stackOperations.pop()
        while ('(' != previouselement[0]) {
            if (stackOperations.empty()) {
                throw Exception("Ops! Invalid formula")
            }
            result.append(" ").append(previouselement)
            previouselement = stackOperations.pop()
        }
    }

    /**
     * The method checks if the current character is an operator
     *
     * @param operator character to check
     * @return true if character is operator, else - false
     */
    private fun isOperator(operator: Char): Boolean {
        when (operator) {
            '-', '+', '*', '/', '^' -> return true
        }
        return false
    }

    /**
     * The method will check the priority of the operation.
     *
     * @param operation Operation mark
     * @return Operation priority number
     */
    private fun priority(operation: Char): Int {
        when (operation) {
            '^' -> return 3
            '*', '/' -> return 2
            '+', '-' -> return 1
        }
        // Priority for functions
        return 4
    }
}