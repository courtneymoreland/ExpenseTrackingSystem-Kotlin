package expensetracker

import java.io.File
import java.io.IOException

class ExpenseTracker {
    private val expenses = mutableListOf<Expense>()
    private val FILE_NAME = "src/expenses.txt"

    init {
        loadExpensesFromFile(FILE_NAME)
    }

    fun addExpense(category: String, amount: Double) {
        val expense = Expense(category, amount)
        expenses.add(expense)
        println("\t\t\t\t\t\t\t\tExpense added: $category - $$amount")
    }

    fun viewExpenses() {
        println("---------------------------------------Expenses---------------------------------------------")
        expenses.forEach { expense ->
            println("${expense.category} - $${expense.amount}")
        }
    }

    fun calculateTotalExpenses(): Double = expenses.sumByDouble { it.amount }
    fun removeExpense(category: String) {
        expenses.removeIf { it.category == category }
    }
    fun viewExpensesByCategory(category: String) {
        val categoryExpenses = expenses.filter { it.category == category }
        if (categoryExpenses.isEmpty()) {
            println("No expenses found for category: $category")
        } else {
            println("---------------------------------------$category Expenses---------------------------------------")
            categoryExpenses.forEach { expense ->
                println("${expense.category} - $${expense.amount}")
            }
        }
    }
    fun sortExpensesByCategory() {
        expenses.sortBy { it.category }
    }

    fun sortExpensesByAmount() {
        expenses.sortBy { it.amount }
    }
    fun searchExpense(keyword: String) {
        val searchResults = expenses.filter { it.category.contains(keyword, ignoreCase = true) }
        if (searchResults.isEmpty()) {
            println("No expenses found for keyword: $keyword")
        } else {
            println("---------------------------------------Search Results for '$keyword'---------------------------------------")
            searchResults.forEach { expense ->
                println("${expense.category} - $${expense.amount}")
            }
        }
    }

    fun loadExpensesFromFile(fileName: String) {
        try {
            File(fileName).forEachLine { line ->
                val parts = line.split(",")
                if (parts.size == 2) {
                    val category = parts[0]
                    val amount = parts[1].toDouble()
                    expenses.add(Expense(category, amount))
                }
            }
            println("\t\t\t\t\t\t\t\tExpenses loaded from $fileName")
        } catch (e: IOException) {
            println("\t\t\t\t\t\t\t\tError loading expenses from file: ${e.message}")
        }
    }

    fun saveExpensesToFile(fileName: String) {
        try {
            File(fileName).printWriter().use { writer ->
                expenses.forEach { expense ->
                    writer.println("${expense.category},${expense.amount}")
                }
            }
            println("\t\t\t\t\t\t\t\tExpenses saved to $fileName")
        } catch (e: IOException) {
            println("\t\t\t\t\t\t\t\tError saving expenses to file: ${e.message}")
        }
    }
}
