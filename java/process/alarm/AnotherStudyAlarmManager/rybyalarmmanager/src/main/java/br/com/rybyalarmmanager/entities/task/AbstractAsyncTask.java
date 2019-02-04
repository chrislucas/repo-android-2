package br.com.rybyalarmmanager.entities.task;

public abstract class AbstractAsyncTask<OnSuccess, OnFailure>
        extends AbstractTask<OnSuccess, OnFailure> implements Runnable {

    public AbstractAsyncTask(ValidateTask<OnSuccess, OnFailure> validateTask
            , int typeOfTask, long startAt, long interval) {
        super(validateTask, typeOfTask, startAt, interval);
    }

    @Override
    public void run() {

    }
}
