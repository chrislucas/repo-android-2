package br.com.rybyalarmmanager.entities.task;

public abstract class AbstractTask<OnSuccess, OnFailure> implements BehaviorTask<OnSuccess, OnFailure> {

    protected OnSuccess result;

    protected boolean activated;

    private @TypeOfTask int typeOfTask;
    private final long startAt;
    private final long interval;


    protected static final String TAG = AbstractSyncTask.class.getSimpleName();


    protected ValidateTask<OnSuccess, OnFailure> validateTask;


    public AbstractTask(ValidateTask<OnSuccess, OnFailure> validateTask
            , @TypeOfTask int typeOfTask, long startAt, long interval) {
        this.validateTask   = validateTask;
        this.typeOfTask     = typeOfTask;
        this.startAt        = startAt;
        this.interval       = interval;
    }


    // Template Mathod
    @Override
    public BehaviorTask<OnSuccess, OnFailure> runValidationResult() {
        if (result == null)
            throw new IllegalArgumentException("Sua implementacao de runSyncTask" +
                    " não define o valor de resultado para ser testado");
        validateTask.validate(result);
        return this;
    }

    public OnSuccess getResult() {
        return result;
    }

    public void setResult(OnSuccess result) {
        this.result = result;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public int getTypeOfTask() {
        return typeOfTask;
    }

    public long getStartAt() {
        return startAt;
    }

    public long getInterval() {
        return interval;
    }
}
