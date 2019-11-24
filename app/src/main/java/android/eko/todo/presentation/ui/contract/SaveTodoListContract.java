package android.eko.todo.presentation.ui.contract;

import android.eko.core.presentation.contract.BaseViewPresenter;
import android.eko.core.presentation.contract.UIProgressView;
import android.eko.todo.domain.model.Todo;

import java.util.List;

public interface SaveTodoListContract {

    interface View extends UIProgressView {
        void onTodoListSaved(boolean saved);

        void onTodoListSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveTodoList(List<Todo> todoList);
    }
}
