package br.com.rybyalarmmanager.entities.task;

import java.util.LinkedHashMap;

public class TaskManager {


    private LinkedHashMap<String, AbstractSyncTask> tasks;


    private TaskManager taskManager = null;


    private TaskManager() {
        tasks = new LinkedHashMap<>();
    }


    public void addTask(AbstractSyncTask task) {
        tasks.put(task.TAG, task);
    }


    public AbstractSyncTask getTask(String tag) {
        return  tasks.get(tag);
    }
}
