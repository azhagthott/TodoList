package android.eko.todo.presentation.di;

import android.eko.todo.domain.usecase.GetLocalTodoListUseCase;
import android.eko.todo.domain.usecase.GetTodoListUseCase;
import android.eko.todo.domain.usecase.SaveTodoListUseCase;
import android.eko.todo.presentation.ui.contract.GetLocalTodoListContract;
import android.eko.todo.presentation.ui.contract.GetRemoteTodoListContract;
import android.eko.todo.presentation.ui.contract.SaveTodoListContract;
import android.eko.todo.presentation.ui.presenter.GetLocalTodoListPresenter;
import android.eko.todo.presentation.ui.presenter.GetRemoteTodoListPresenter;
import android.eko.todo.presentation.ui.presenter.SaveTodoListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class TodoListModule {

    @Provides
    GetRemoteTodoListContract.Presenter provideGetTodoListPresenter(GetTodoListUseCase getTodoListUseCase) {
        return new GetRemoteTodoListPresenter(getTodoListUseCase);
    }

    @Provides
    SaveTodoListContract.Presenter provideSaveTodoListPresenter(SaveTodoListUseCase saveTodoListUseCase) {
        return new SaveTodoListPresenter(saveTodoListUseCase);
    }

    @Provides
    GetLocalTodoListContract.Presenter provideGetLocalTodoListPresenter(GetLocalTodoListUseCase getLocalTodoListUseCase) {
        return new GetLocalTodoListPresenter(getLocalTodoListUseCase);
    }
}
