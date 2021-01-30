package lab4.model

import java.util.*


data class TodoItem(
    val id: Int,
    val name: String,
    val description: String,
    val time: Date,
    var isCompleted: Boolean = false
)

data class TodoItemDTO(
    var name: String = "",
    var description: String = ""
)