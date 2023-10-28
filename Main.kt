package expensetracker

import java.io.IOException
import java.util.Scanner
fun main() {
    val tracker = ExpenseTracker()
    val scanner = Scanner(System.`in`)

    var exit = false

    while (!exit) {
        println("---------------------------------------Expense Tracker Menu---------------------------------------")
        println("1. Add Expense")
        println("2. View Expenses")
        println("3. View Expenses by Category")
        println("4. Calculate Total Expenses")
        println("5. Save Expenses to File")
        println("6. Load Expenses from File")
        println("7. Remove an Expense")
        println("8. Sort Expenses by Category")
        println("9. Sort Expenses by Amount")
        println("10. Search for an Expense")
        println("11. Exit")
        print("Enter your choice: ")

        when (val choice = scanner.nextInt()) {
            1 -> {
                print("Enter category: ")
                val category = scanner.next()
                print("Enter amount: ")
                val amount = scanner.nextDouble()
                tracker.addExpense(category, amount)
            }
            2 -> tracker.viewExpenses()
            3 -> {
                print("Enter category to view: ")
                val category = scanner.next()
                tracker.viewExpensesByCategory(category)
            }
            4 -> println("\t\t\t\t\t\t\t\t\t\tTotal Expenses: $${tracker.calculateTotalExpenses()}")
            5 -> {
                print("Enter file name to save expenses: ")
                val saveFileName = scanner.next()
                tracker.saveExpensesToFile(saveFileName)
            }
            6 -> {
                print("Enter file name to load expenses from: ")
                val loadFileName = scanner.next()
                tracker.loadExpensesFromFile(loadFileName)
            }
            7 -> {
                print("Enter category to remove: ")
                val category = scanner.next()
                tracker.removeExpense(category)
            }
            8 -> tracker.sortExpensesByCategory()
            9 -> tracker.sortExpensesByAmount()
            10 -> {
                print("Enter keyword to search: ")
                val keyword = scanner.next()
                tracker.searchExpense(keyword)
            }
            11 -> exit = true
            else -> println("\t\t\t\t\t\t\t\t\t\tInvalid choice. Please try again.")
        }
    }
}

