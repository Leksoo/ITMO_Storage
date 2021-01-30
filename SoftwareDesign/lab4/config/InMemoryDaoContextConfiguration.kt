package lab4.config

import lab4.dao.TodoListDao
import lab4.dao.TodoListInMemoryDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
open class InMemoryDaoContextConfiguration {

    @Bean
    open fun todoListDao(): TodoListDao {
        return TodoListInMemoryDao()
    }
}