package android.eko.todo;

import android.eko.todo.data.repository.TodoRepository;
import android.eko.todo.domain.model.Todo;
import android.eko.todo.domain.usecase.GetTodoListUseCase;
import android.eko.todo.presentation.ui.contract.GetRemoteTodoListContract;
import android.eko.todo.presentation.ui.presenter.GetRemoteTodoListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoListPresenterTest {

    @Mock
    public Todo todo;
    @Mock
    public GetRemoteTodoListContract.View view;
    @Mock
    public GetTodoListUseCase useCase;
    @Mock
    public TodoRepository repository;
    private GetRemoteTodoListPresenter presenter;
    private List<Todo> todoList = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetTodoListUseCase(repository);
        presenter = new GetRemoteTodoListPresenter(useCase);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void testOnGetTodoList() {
        when(repository.getTodoList()).thenReturn(Observable.create(emitter -> {
            emitter.onNext(todoList);
            emitter.onComplete();
        }));

        presenter.initialize(view);
        presenter.getRemoteTodoList();

        verify(view, times(1)).onGetRemoteTodoList(ArgumentMatchers.anyList());
    }

    @Test
    public void testOnGetTodoListFailure() {
        presenter.initialize(view);
        presenter.getRemoteTodoList();
        verify(view, times(1)).onGetRemoteTodoListFailure("Exception: null");
    }
}
