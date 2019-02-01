package br.com.rybyalarmmanager.entities.task;

/**
 * Validacao do resultado de um processo e um callback para exibvir
 * um sucesso ou falha
 * */

public abstract class ValidateTask<Result, ResultOnFailure> {

    /**
     * template para validacao
     * */
    final void validate(Result result) {
        if (test(result)) {
            onSuccess(result);
        }
        else {
            onFailure(buildResponseOnFailure());
        }
    }

    protected abstract boolean test(Result data);

    // callback on success
    protected abstract void onSuccess(Result result);

    // callback on failure
    protected abstract void onFailure(ResultOnFailure resultOnFailure);

    // construtor de resposta no caso de falhar
    protected abstract ResultOnFailure buildResponseOnFailure();
}
