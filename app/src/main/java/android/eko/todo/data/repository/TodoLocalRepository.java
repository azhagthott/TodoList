package android.eko.todo.data.repository;

import android.eko.core.data.local.DBRepository;
import android.eko.todo.data.local.TodoLocal;

import javax.inject.Inject;

public class TodoLocalRepository extends DBRepository<TodoLocal> {

    @Inject
    public TodoLocalRepository() {
    }

    @Override
    protected Class<TodoLocal> getItemClass() {
        return TodoLocal.class;
    }
}
