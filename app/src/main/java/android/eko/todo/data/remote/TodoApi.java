package android.eko.todo.data.remote;

import android.eko.todo.data.entity.TodoEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TodoApi {

    @GET("todos")
    Observable<List<TodoEntity>> getTodoList();
}
