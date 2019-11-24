package android.eko.todo.domain.usecase;

import android.eko.core.domain.usecase.UseCase;
import android.eko.todo.data.repository.TodoLocalRepository;
import android.eko.todo.data.repository.mapper.TodoLocalToDomainMapper;
import android.eko.todo.domain.model.Todo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SaveTodoListUseCase extends UseCase<Boolean> {

    private TodoLocalToDomainMapper mapper;
    private TodoLocalRepository repository;
    private List<Todo> todoList;

    @Inject
    public SaveTodoListUseCase(TodoLocalToDomainMapper mapper, TodoLocalRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public SaveTodoListUseCase setData(List<Todo> todoList) {
        this.todoList = todoList;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.saveAll(mapper.reverseMap(todoList)).map(response -> {
            if (response == null) {
                throw new Exception("Error saving in local db");
            }
            return response;
        });
    }
}
