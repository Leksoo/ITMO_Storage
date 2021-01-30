package lab4.dao

import lab4.model.TodoItem
import lab4.model.TodoItemDTO
import lab4.model.TodoList

interface TodoListDao {
    fun addList()
    fun getLists(): List<TodoList>
    fun addItemToList(listId: Int, item: TodoItemDTO)
    fun getList(listId: Int): TodoList
    fun updateItem(listId: Int, itemId: Int)
    fun removeItem(listId: Int, itemId: Int)
    fun removeList(listId: Int)
}