package android.eko.todo.data.repository.mapper;

import android.eko.core.data.repository.Mapper;
import android.eko.todo.data.entity.TodoEntity;
import android.eko.todo.domain.model.Todo;

import javax.inject.Inject;

public class TodoEntityToDomainMapper extends Mapper<TodoEntity, Todo> {

    @Inject
    public TodoEntityToDomainMapper() {
    }

    @Override
    public Todo map(TodoEntity value) {
        Todo model = new Todo();
        model.todoId = value.id;
        model.todoTitle = value.title;
        model.todoCompleted = value.completed;
        model.userId = value.userId;
        return model;
    }

    @Override
    public TodoEntity reverseMap(Todo value) {
        TodoEntity entity = new TodoEntity();
        entity.id = value.todoId;
        entity.title = value.todoTitle;
        entity.completed = value.todoCompleted;
        entity.userId = value.userId;
        return entity;
    }
}
