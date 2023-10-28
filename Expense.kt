package expensetracker

data class Expense(val category: String, val amount: Double) {
    override fun toString(): String = "$category,$amount"
}
