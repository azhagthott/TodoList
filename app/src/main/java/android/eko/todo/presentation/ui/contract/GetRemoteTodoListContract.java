package android.eko.todo.presentation.ui.contract;

import android.eko.core.presentation.contract.BaseViewPresenter;
import android.eko.core.presentation.contract.UIProgressView;
import android.eko.todo.domain.model.Todo;

import java.util.List;

public interface GetRemoteTodoListContract {

    interface View extends UIProgressView {
        void onGetRemoteTodoList(List<Todo> todoList);

        void onGetRemoteTodoListFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getRemoteTodoList();
    }
}
