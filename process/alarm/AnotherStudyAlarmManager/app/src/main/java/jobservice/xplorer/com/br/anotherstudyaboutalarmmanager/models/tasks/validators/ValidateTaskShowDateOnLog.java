package jobservice.xplorer.com.br.anotherstudyaboutalarmmanager.models.tasks.validators;


import br.com.rybyalarmmanager.entities.task.ValidateTask;

/**
 *
 * */

public class ValidateTaskShowDateOnLog extends ValidateTask<String, String> {

    @Override
    protected boolean test(String data) {
        return data.matches("d{2}\\/d{2}\\/d{4}\\/\\sd{2}\\:d{2}\\:d{2}");
    }

    @Override
    protected void onSuccess(String resultOnSuccess) { }

    @Override
    protected void onFailure(String resultOnFailure) { }

    @Override
    protected String buildResponseOnFailure() {
        return "Mensagem de erro qualquer";
    }
}
