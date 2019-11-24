package android.eko.todo.domain.usecase;

import android.eko.core.domain.usecase.UseCase;
import android.eko.todo.data.repository.TodoRepository;
import android.eko.todo.domain.model.Todo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTodoListUseCase extends UseCase<List<Todo>> {

    private TodoRepository repository;

    @Inject
    public GetTodoListUseCase(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<List<Todo>> createObservableUseCase() {
        return repository.getTodoList().map(response -> {
            if (response == null) {
                throw new Exception("Error while receiving object from usecase");
            }
            return response;
        });
    }
}
