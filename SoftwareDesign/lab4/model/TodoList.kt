package lab4.model

data class TodoList(
    val id: Int,
    val items: MutableList<TodoItem> = ArrayList()
)
