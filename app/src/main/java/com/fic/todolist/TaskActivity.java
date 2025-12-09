package com.fic.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import com.fic.todolist.controller.TaskController;
import com.fic.todolist.model.Task;
import com.fic.todolist.view.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskActivity extends AppCompatActivity {
    private TaskAdapter taskAdapter;
    private TaskController taskController;
    private Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);

        RecyclerView recyclerViewTasks = findViewById(R.id.rvTasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        recyclerViewTasks.setAdapter(taskAdapter);
        taskController = new TaskController(this);
        List<Task> tasks = taskController.getAllTask();
        taskAdapter.setData(tasks);

        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);

        fabAddTask.setOnClickListener(view -> {
            showAddTaskActivity();
        });

        taskAdapter.setOnTaskClickListener(new TaskAdapter.OnTaskClickListener() {
            @Override
            public void onCompleteClick(Task task) {
                task.is_completed = "Completado"; // o boolean según tu modelo
                // aquí actualizas en la BD
                taskController.updateTask(task.id, task.task_title, task.task_description,task.created_at,task.is_completed);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDeleteClick(Task task) {
                taskController.deleteTask(task); // elimina de la BD
            }
        });


    }

    private void showAddTaskActivity(){
        Intent intent = new Intent(TaskActivity.this,AddTaskActivity.class);
        startActivity(intent);
    }
}
