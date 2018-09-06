package jobservice.xplorer.com.br.anotherstudyaboutalarmmanager.models;


public interface Task<Response, Params> {
    void execute();
    void execute(Params ... params);
    void onComplete();
    void onFailure(FailureMessageTask failureMessageTask);
    void onSuccess(Response response);
}
