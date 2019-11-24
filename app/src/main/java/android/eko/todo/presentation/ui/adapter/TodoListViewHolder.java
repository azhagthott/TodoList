package android.eko.todo.presentation.ui.adapter;

import android.eko.todo.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoListViewHolder extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvStatus;

    TodoListViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_todo_title);
        tvStatus = itemView.findViewById(R.id.tv_status);
    }

    void setTitle(String title) {
        if (title != null) {
            tvTitle.setText(title);
        }
    }

    void setStatus(boolean status) {
        if (status) {
            tvStatus.setText(R.string.todo_completed);
        } else {
            tvStatus.setText(R.string.todo_uncompleted);
        }
    }
}
