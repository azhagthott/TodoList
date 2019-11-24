package android.eko.todo.presentation.ui.adapter;

import android.eko.todo.R;
import android.eko.todo.domain.model.Todo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListViewHolder> {

    private List<Todo> todoList;

    public TodoListAdapter(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_item, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.setTitle(todo.todoTitle);
        holder.setStatus(todo.todoCompleted);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
