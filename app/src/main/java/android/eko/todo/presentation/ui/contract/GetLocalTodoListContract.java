package android.eko.todo.presentation.ui.contract;

import android.eko.core.presentation.contract.BaseViewPresenter;
import android.eko.core.presentation.contract.UIProgressView;
import android.eko.todo.domain.model.Todo;

import java.util.List;

public interface GetLocalTodoListContract {

    interface View extends UIProgressView {
        void onGetLocalTodoList(List<Todo> todoList);

        void onGetLocalTodoListFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getLocalTodoList();
    }
}
