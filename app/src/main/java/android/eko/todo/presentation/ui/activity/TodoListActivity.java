package android.eko.todo.presentation.ui.activity;

import android.eko.core.presentation.activity.BaseActivity;
import android.eko.todo.R;
import android.eko.todo.databinding.ActivityTodoListBinding;
import android.eko.todo.domain.model.Todo;
import android.eko.todo.presentation.di.DaggerTodoListComponent;
import android.eko.todo.presentation.ui.adapter.TodoListAdapter;
import android.eko.todo.presentation.ui.contract.GetLocalTodoListContract;
import android.eko.todo.presentation.ui.contract.GetRemoteTodoListContract;
import android.eko.todo.presentation.ui.contract.SaveTodoListContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class TodoListActivity extends BaseActivity<ActivityTodoListBinding> implements
        GetRemoteTodoListContract.View,
        SaveTodoListContract.View,
        GetLocalTodoListContract.View {

    private static final String TAG = TodoListActivity.class.getCanonicalName();

    @Inject
    GetRemoteTodoListContract.Presenter getRemoteTodoListPresenter;
    @Inject
    SaveTodoListContract.Presenter saveTodoListPresenter;
    @Inject
    GetLocalTodoListContract.Presenter getLocalTodoListPresenter;

    @Override
    protected void injectDependencies() {
        DaggerTodoListComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_todo_list;
    }

    @Override
    protected void initView() {

        // Initialize presenters
        getRemoteTodoListPresenter.initialize(this);
        saveTodoListPresenter.initialize(this);
        getLocalTodoListPresenter.initialize(this);

        // Get remote TodoList
        getRemoteTodoListPresenter.getRemoteTodoList();

        // UI set current date
        binder.tvDate.setText(getDateCurrent());

        // Swipe to refresh event, on swipe refresh TodoList
        // and repeat all
        binder.strRefresh.setOnRefreshListener(() -> {
            getRemoteTodoListPresenter.getRemoteTodoList();
            showProgress(true);
        });
    }

    /**
     * @param todoList receive a list of TodoObjects from API
     */
    @Override
    public void onGetRemoteTodoList(List<Todo> todoList) {
        try {
            if (todoList != null) {
                // Save TodoList to local database
                saveTodoListPresenter.saveTodoList(todoList);
            }
        } catch (Exception e) {
            showMessage(" Exception: " + e.getMessage());
        }
    }

    /**
     * @param message In case of failing fetching the list of TodoObjects, will asks for the
     *                local list that means at least once the application require Internet connection
     */
    @Override
    public void onGetRemoteTodoListFailure(String message) {
        showMessage(" -onGetRemoteTodoListFailure: " + message);

        // fetch the list of local TodoLObjects
        getLocalTodoListPresenter.getLocalTodoList();

        // show messages to the user "Working offline"
        Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        showProgress(false);
    }

    /**
     * @param saved in case of save list success will receive saved = true
     *              The user always works with the todoList from local database
     */
    @Override
    public void onTodoListSaved(boolean saved) {
        Log.i(TAG, "onTodoListSaved: " + saved);

        // fetch the list of local TodoLObjects
        getLocalTodoListPresenter.getLocalTodoList();
    }

    @Override
    public void onTodoListSavedFailure(String message) {
        showMessage(" -onTodoListSavedFailure: " + message);
    }

    /**
     * @param todoList receive a list of TodoObjects and show in a RecyclerView
     */
    @Override
    public void onGetLocalTodoList(List<Todo> todoList) {
        try {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            TodoListAdapter adapter = new TodoListAdapter(todoList);
            binder.rvTodoList.setLayoutManager(layoutManager);
            binder.rvTodoList.setHasFixedSize(false);
            binder.rvTodoList.setAdapter(adapter);
            showProgress(false);
        } catch (Exception e) {
            showMessage(" Exception: " + e.getMessage());
        }
    }

    /**
     * @param message show message in console log in case of find error while trying to fetch
     *                todoList from local database
     */
    @Override
    public void onGetLocalTodoListFailure(String message) {
        showMessage(" -onGetLocalTodoListFailure: " + message);
        showProgress(false);
    }

    /**
     * @param show show or hide progress bar
     */
    @Override
    public void showProgress(boolean show) {
        binder.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        binder.strRefresh.setRefreshing(show);
    }

    /**
     * @param message message to show in console log as error
     */
    @Override
    public void showMessage(String message) {
        Log.e(TAG, "ERROR: " + message);
    }

    private String getDateCurrent() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMM d", Locale.getDefault());
        return df.format(date);
    }
}
