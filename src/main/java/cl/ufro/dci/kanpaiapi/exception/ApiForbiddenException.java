package cl.ufro.dci.kanpaiapi.exception;

public class ApiForbiddenException extends ApiRequestException{
    public ApiForbiddenException(String message){
        super(message);
    }
}
