package android.eko.todo.data.repository;

import android.eko.todo.domain.model.Todo;

import java.util.List;

import io.reactivex.Observable;

public interface TodoRepository {

    Observable<List<Todo>> getTodoList();
}
