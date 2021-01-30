package lab4.controller

import lab4.dao.TodoListDao
import lab4.model.TodoItemDTO
import lab4.model.TodoList
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class TodoListController(
    private val todoListDao: TodoListDao
) {

    @RequestMapping(value = ["/add-list"], method = [RequestMethod.POST])
    fun addList(): String {
        todoListDao.addList()
        return "redirect:/get-lists"
    }

    @RequestMapping(value = ["/remove-list"], method = [RequestMethod.POST])
    fun removeList(@RequestParam("list_id") listId: Int): String {
        todoListDao.removeList(listId)
        return "redirect:/get-lists"
    }

    @RequestMapping(value = ["/get-lists"], method = [RequestMethod.GET])
    fun getLists(map: ModelMap): String {
        prepareModelMap(map, todoListDao.getLists())
        return "index"
    }

    @RequestMapping(value = ["/add-todo"], method = [RequestMethod.POST])
    fun addTodoItem(
        @RequestParam("list_id") listId: Int,
        @ModelAttribute("list_item") item: TodoItemDTO
    ): String {
        todoListDao.addItemToList(listId, item)
        return "redirect:/get-lists"
    }

    @RequestMapping(value = ["/update-todo"], method = [RequestMethod.POST])
    fun updateTodoStatus(
        @RequestParam("list_id") listId: Int,
        @RequestParam("item_id") itemId: Int
        ): String {
        todoListDao.updateItem(listId, itemId)
        return "redirect:/get-lists"
    }

    @RequestMapping(value = ["/remove-todo"], method = [RequestMethod.POST])
    fun removeTodo(
        @RequestParam("list_id") listId: Int,
        @RequestParam("item_id") itemId: Int
    ): String {
        todoListDao.removeItem(listId, itemId)
        return "redirect:/get-lists"
    }

    private fun prepareModelMap(map: ModelMap, lists: List<TodoList>) {
        map.addAttribute("todo_lists", lists)
        map.addAttribute("list_item", TodoItemDTO())
    }

}
