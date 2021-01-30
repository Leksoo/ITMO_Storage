package lab4.dao

import lab4.model.TodoItem
import lab4.model.TodoItemDTO
import lab4.model.TodoList
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger


class TodoListInMemoryDao : TodoListDao {
    private val lastListId = AtomicInteger(0)
    private val listToLastItemId = ConcurrentHashMap<Int, Int>()
    private val todos = ConcurrentHashMap<Int, TodoList>()

    override fun addList() {
        val listId = lastListId.getAndIncrement()
        todos[listId] = TodoList(listId)
        listToLastItemId[listId] = 0
    }

    override fun getLists(): List<TodoList> {
        return todos.values.toList()
    }

    override fun addItemToList(listId: Int, item: TodoItemDTO) {
        todos[listId]?.let {
            val id = listToLastItemId[listId]?.also {
                listToLastItemId[listId] = it + 1
            } ?: return
            it.items.add(
                TodoItem(id, item.name, item.description, Calendar.getInstance().time, false)
            )
        }
    }

    override fun getList(listId: Int): TodoList {
        return todos[listId]!!
    }

    override fun updateItem(listId: Int, itemId: Int) {
        todos[listId]?.let { list ->
            list.items.find { it.id == itemId }?.apply {
                isCompleted = !isCompleted
            }
        }
    }

    override fun removeItem(listId: Int, itemId: Int) {
        todos[listId]?.let { list ->
            list.items.removeIf { it.id == itemId }
        }
    }

    override fun removeList(listId: Int) {
        todos.remove(listId)
        listToLastItemId.remove(listId)
    }
}