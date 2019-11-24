package android.eko.todo.presentation.ui.presenter;

import android.eko.core.domain.usecase.UseCaseObserver;
import android.eko.todo.domain.model.Todo;
import android.eko.todo.domain.usecase.GetLocalTodoListUseCase;
import android.eko.todo.presentation.ui.contract.GetLocalTodoListContract;

import java.util.List;

import javax.inject.Inject;

public class GetLocalTodoListPresenter implements GetLocalTodoListContract.Presenter {

    private GetLocalTodoListContract.View view;
    private GetLocalTodoListUseCase getLocalTodoListUseCase;

    @Inject
    public GetLocalTodoListPresenter(GetLocalTodoListUseCase getLocalTodoListUseCase) {
        this.getLocalTodoListUseCase = getLocalTodoListUseCase;
    }

    @Override
    public void initialize(GetLocalTodoListContract.View view) {
        this.view = view;
    }

    @Override
    public void getLocalTodoList() {
        try {
            getLocalTodoListUseCase.execute(new UseCaseObserver<List<Todo>>() {
                @Override
                public void onNext(List<Todo> value) {
                    super.onNext(value);
                    view.onGetLocalTodoList(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetLocalTodoListFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetLocalTodoListFailure("Exception" + e.getMessage());
        }
    }
}
