package android.eko.todo.data.repository.mapper;

import android.eko.core.data.repository.Mapper;
import android.eko.todo.data.local.TodoLocal;
import android.eko.todo.domain.model.Todo;

import javax.inject.Inject;

public class TodoLocalToDomainMapper extends Mapper<TodoLocal, Todo> {

    @Inject
    public TodoLocalToDomainMapper() {
    }

    @Override
    public Todo map(TodoLocal value) {
        Todo model = new Todo();
        model.todoId = value.todoId;
        model.todoTitle = value.todoTitle;
        model.todoCompleted = value.todoCompleted;
        model.userId = value.userId;
        return model;
    }

    @Override
    public TodoLocal reverseMap(Todo value) {
        TodoLocal local = new TodoLocal();
        local.todoId = value.todoId;
        local.todoTitle = value.todoTitle;
        local.todoCompleted = value.todoCompleted;
        local.userId = value.userId;
        return local;
    }
}
