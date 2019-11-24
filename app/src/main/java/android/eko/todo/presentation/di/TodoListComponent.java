package android.eko.todo.presentation.di;

import android.eko.core.di.ActivityComponent;
import android.eko.todo.di.TodoModule;
import android.eko.todo.presentation.ui.activity.TodoListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        TodoModule.class,
        TodoListModule.class
})
public interface TodoListComponent extends ActivityComponent<TodoListActivity> {
}
