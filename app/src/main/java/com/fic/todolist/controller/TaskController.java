package com.fic.todolist.controller;

import android.content.Context;
import android.util.Log;

import com.fic.todolist.model.Task;
import com.fic.todolist.model.TaskDao;
import com.fic.todolist.model.TaskDatabase;

import java.util.List;

public class TaskController {
    private final TaskDao taskDao;

    public TaskController(Context context){
        TaskDatabase database = TaskDatabase.getInstance(context);
        taskDao = database.taskDao();
    }

    //Crear una nueva tarea
    public boolean addTask(String task_title, String task_description, String created_at){
        try {
            Task task = new Task();
            task.task_title = task_title;
            task.task_description = task_description;
            task.created_at = created_at;
            task.is_completed = "Pendiente";
            taskDao.insert(task);
            Log.i("BOOK_SAVE", "La tarea fue agregada con exito");
            return true;
        }catch (Exception e){
            Log.e("TASK_ERROR", e.getMessage());
            return false;
        }
    }

    public List<Task> getAllTask() { return taskDao.getAllBooks();}

    public void updateTask(int id, String task_title, String task_description, String created_at, String is_completed){
        try {
            Task task = taskDao.getTaskById(id); //
            task.task_title = task_title;
            task.task_description = task_description;
            task.created_at = created_at;
            task.is_completed = is_completed;
            taskDao.update(task);
            Log.i("TASK_UPDATE","La tarea se actualizo con exito");
        }catch (Exception e){
            Log.e("TASK_ERROR", e.getMessage());
        }
    }

    public void deleteTask(Task task) {
        taskDao.delete(task);
    }


}
