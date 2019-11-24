package android.eko.todo.domain.usecase;

import android.eko.core.domain.usecase.UseCase;
import android.eko.todo.data.repository.TodoLocalRepository;
import android.eko.todo.data.repository.mapper.TodoLocalToDomainMapper;
import android.eko.todo.domain.model.Todo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetLocalTodoListUseCase extends UseCase<List<Todo>> {

    private TodoLocalRepository repository;
    private TodoLocalToDomainMapper mapper;

    @Inject
    public GetLocalTodoListUseCase(TodoLocalRepository repository, TodoLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected Observable<List<Todo>> createObservableUseCase() {
        return repository.getAll().map(response -> {
            if (response == null) {
                throw new Exception("Error getting list from db");
            }
            return mapper.map(response);
        });
    }
}
