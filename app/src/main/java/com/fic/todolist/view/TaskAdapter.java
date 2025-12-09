package com.fic.todolist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.todolist.R;
import com.fic.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final List<Task> taskList = new ArrayList<>();

    public void setData(List<Task> tasks){
        taskList.clear();
        if(tasks != null){
            taskList.addAll(tasks);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.txtTask.setText(task.task_title);
        holder.txtBody.setText(task.task_description);
        holder.txtDate.setText(task.created_at);
        holder.txtComplete.setText(task.is_completed);

        // BOTÓN COMPLETAR
        holder.btnComplete.setOnClickListener(v -> {
            if(listener != null) {
                listener.onCompleteClick(task);
            }
        });

        // BOTÓN ELIMINAR
        holder.btnDelete.setOnClickListener(v -> {
            if(listener != null) {
                listener.onDeleteClick(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView txtTask, txtBody, txtDate, txtComplete;
        Button btnComplete,btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTask = itemView.findViewById(R.id.tvTask);
            txtBody = itemView.findViewById(R.id.tvBody);
            txtDate = itemView.findViewById(R.id.tvDate);
            txtComplete = itemView.findViewById(R.id.tvStatus);
            btnComplete = itemView.findViewById(R.id.btnComplete);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface OnTaskClickListener {
        void onCompleteClick(Task task);
        void onDeleteClick(Task task);
    }

    private OnTaskClickListener listener;

    public void setOnTaskClickListener(OnTaskClickListener listener) {
        this.listener = listener;
    }
}
