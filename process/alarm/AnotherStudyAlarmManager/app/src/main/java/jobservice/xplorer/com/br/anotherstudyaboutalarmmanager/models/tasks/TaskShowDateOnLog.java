package jobservice.xplorer.com.br.anotherstudyaboutalarmmanager.models.tasks;

import br.com.rybyalarmmanager.entities.task.AbstractSyncTask;
import br.com.rybyalarmmanager.entities.task.BehaviorTask;
import br.com.rybyalarmmanager.entities.task.ValidateTask;

public class TaskShowDateOnLog extends AbstractSyncTask<String, String> {

    public TaskShowDateOnLog(ValidateTask<String, String> validateTask, int typeOfTask, long startAt, long interval) {
        super(validateTask, typeOfTask, startAt, interval);
    }

    @Override
    public BehaviorTask<String, String> runSyncTask() {
        return null;
    }

    @Override
    public BehaviorTask<String, String> runAsyncTask() {
        return null;
    }

    @Override
    public BehaviorTask<String, String> beforeRun() {
        return null;
    }

    @Override
    public BehaviorTask<String, String> afterRun() {
        return null;
    }
}
