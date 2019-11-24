package android.eko.todo;

import android.eko.todo.data.local.TodoLocal;
import android.eko.todo.data.repository.TodoLocalRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TodoLocalRepositoryTest {

    @Mock
    public TodoLocalRepository repository = new TodoLocalRepository();
    @Mock
    private List<TodoLocal> todoList = new ArrayList<>();
    @Mock
    private TodoLocal local = new TodoLocal();

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

        local = new TodoLocal();
        local.todoId = 1;
        local.todoTitle = "This is the title";
        local.userId = 100;
        local.todoCompleted = false;
        todoList.add(local);
    }

    @Test
    public void clearAllTest() {
        doReturn(Observable.create(emitter -> {
            emitter.onNext(true);
            emitter.onComplete();
        })).when(repository).clear();

        repository.clear();
        verify(repository, times(1)).clear();
    }

    @Test
    public void saveTest() {
        doReturn(Observable.create(emitter -> {
            emitter.onNext(true);
            emitter.onComplete();
        })).when(repository).save(local);

        repository.save(local);
        verify(repository, times(1)).save(local);
    }

    @Test
    public void getTodoItemByIdTest() {
        when(repository.get(1)).thenReturn(Observable.create(emitter -> {
            emitter.onNext(local);
            emitter.onComplete();
        }));

        repository.get(1);
        verify(repository, times(1)).get(1);

    }

    @Test
    public void getAllTest() {
        when(repository.getAll()).thenReturn(Observable.create(emitter -> {
            emitter.onNext(todoList);
            emitter.onComplete();
        }));

        repository.getAll();
        verify(repository, times(1)).getAll();
    }
}
