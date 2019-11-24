package android.eko.todo.presentation.ui.presenter;

import android.eko.core.domain.usecase.UseCaseObserver;
import android.eko.todo.domain.model.Todo;
import android.eko.todo.domain.usecase.GetTodoListUseCase;
import android.eko.todo.presentation.ui.contract.GetRemoteTodoListContract;

import java.util.List;

import javax.inject.Inject;

public class GetRemoteTodoListPresenter implements GetRemoteTodoListContract.Presenter {

    private GetRemoteTodoListContract.View view;
    private GetTodoListUseCase getTodoListUseCase;

    @Inject
    public GetRemoteTodoListPresenter(GetTodoListUseCase getTodoListUseCase) {
        this.getTodoListUseCase = getTodoListUseCase;
    }

    @Override
    public void initialize(GetRemoteTodoListContract.View view) {
        this.view = view;
    }

    @Override
    public void getRemoteTodoList() {
        try {
            getTodoListUseCase.execute(new UseCaseObserver<List<Todo>>() {
                @Override
                public void onNext(List<Todo> list) {
                    super.onNext(list);
                    view.onGetRemoteTodoList(list);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetRemoteTodoListFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetRemoteTodoListFailure("Exception: " + e.getMessage());
        }
    }
}
