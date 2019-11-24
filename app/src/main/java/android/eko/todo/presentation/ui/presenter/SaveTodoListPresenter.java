package android.eko.todo.presentation.ui.presenter;

import android.eko.core.domain.usecase.UseCaseObserver;
import android.eko.todo.domain.model.Todo;
import android.eko.todo.domain.usecase.SaveTodoListUseCase;
import android.eko.todo.presentation.ui.contract.SaveTodoListContract;

import java.util.List;

import javax.inject.Inject;

public class SaveTodoListPresenter implements SaveTodoListContract.Presenter {

    private SaveTodoListContract.View view;
    private SaveTodoListUseCase saveTodoListUseCase;

    @Inject
    public SaveTodoListPresenter(SaveTodoListUseCase saveTodoListUseCase) {
        this.saveTodoListUseCase = saveTodoListUseCase;
    }

    @Override
    public void initialize(SaveTodoListContract.View view) {
        this.view = view;
    }

    @Override
    public void saveTodoList(List<Todo> todoList) {
        try {
            saveTodoListUseCase.setData(todoList).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onTodoListSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onTodoListSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onTodoListSavedFailure("Exception" + e.getMessage());
        }
    }
}
