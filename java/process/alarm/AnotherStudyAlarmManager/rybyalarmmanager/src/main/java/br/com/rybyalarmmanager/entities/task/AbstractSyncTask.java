package br.com.rybyalarmmanager.entities.task;


/**
 * Modelo de uma tarefa agendada
 * */

public abstract class AbstractSyncTask<OnSuccess, OnFailure> extends AbstractTask<OnSuccess, OnFailure> {

    public AbstractSyncTask(ValidateTask<OnSuccess, OnFailure> validateTask
            , int typeOfTask, long startAt, long interval) {
        super(validateTask, typeOfTask, startAt, interval);
    }

    public BehaviorTask<OnSuccess, OnFailure> startSyncTask() {
        return beforeRun().runSyncTask().runValidationResult().afterRun();
    }




}
