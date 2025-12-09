package com.fic.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.fic.todolist.controller.TaskController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etTask;
    private EditText etBody;
    private EditText etDate;
    private Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaActual = sdf.format(new Date());
        btnSave = findViewById(R.id.btnSave);
        initViews();

        btnSave.setOnClickListener(view -> {
            String task = etTask.getText().toString();
            String body = etBody.getText().toString();
            saveTask(task, body, fechaActual);
        });



    }

    private void initViews(){

        etTask = findViewById(R.id.etTask);
        etBody = findViewById(R.id.etBody);

    }

    private void saveTask(String task, String body, String date){
        TaskController taskController = new TaskController(this);
        boolean result = taskController.addTask(task, body, date);
        if (result){
            Toast.makeText(this, "La tarea se agrego con exito", Toast.LENGTH_SHORT).show();
            clearForm();
            showTaskActivity();
        }else {
            Toast.makeText(this, "Error al agregar la tarea", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm(){
        etTask.setText("");
        etBody.setText("");
    }

    private void showTaskActivity(){
        Intent intent = new Intent(AddTaskActivity.this, TaskActivity.class);
        startActivity(intent);
    }
}
