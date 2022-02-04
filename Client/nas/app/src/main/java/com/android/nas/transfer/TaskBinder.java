package com.android.nas.transfer;

import android.os.Binder;

public class TaskBinder extends Binder {
    private TaskService mTaskService = null;
    public void TaskBinder(TaskService taskService){ mTaskService = taskService; }
    public TaskService getService() { return mTaskService; }

    public void begin(int fileNu){
        //启动一个Task
    }
}