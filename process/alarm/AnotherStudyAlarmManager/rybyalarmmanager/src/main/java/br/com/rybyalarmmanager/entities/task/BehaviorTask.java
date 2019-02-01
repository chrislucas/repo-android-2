package br.com.rybyalarmmanager.entities.task;

/**
 * Modelo de comportamento que se espera numa tarefa agendada
 * */

public interface BehaviorTask<Result, ResultOnFailure> {

    public BehaviorTask<Result, ResultOnFailure> startSyncTask();

    public BehaviorTask<Result, ResultOnFailure> startAsyncTask();

    // uma tarefa sabe como validar o resultado da sua execucao
    BehaviorTask<Result, ResultOnFailure> runValidationResult();

    // execucao de algo (tarefa principal) sem esperar por retorno
    BehaviorTask<Result, ResultOnFailure> runSyncTask();

    // execacao de algo numa thread separaa de fora assincrona
    BehaviorTask<Result, ResultOnFailure> runAsyncTask(ValidateTask<Result, ResultOnFailure> validateTask);


    // execucao de algo apos a tarefa principal
    BehaviorTask<Result, ResultOnFailure> beforeRun();
    // possivel configuracao antes de executar um algoritmo
    BehaviorTask<Result, ResultOnFailure> afterRun();

}
