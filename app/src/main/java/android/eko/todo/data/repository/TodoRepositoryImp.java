package android.eko.todo.data.repository;

import android.eko.todo.data.remote.TodoApi;
import android.eko.todo.data.repository.mapper.TodoEntityToDomainMapper;
import android.eko.todo.domain.model.Todo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TodoRepositoryImp implements TodoRepository {

    private TodoApi api;
    private TodoEntityToDomainMapper mapper;

    @Inject
    public TodoRepositoryImp(TodoApi api, TodoEntityToDomainMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<Todo>> getTodoList() {
        return api.getTodoList().map(response -> {
            if (response == null) {
                throw new Exception("Error while receiving response from server");
            }
            return mapper.map(response);
        });
    }
}
