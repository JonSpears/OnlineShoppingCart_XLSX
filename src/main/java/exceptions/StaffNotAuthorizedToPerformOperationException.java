package exceptions;

public class StaffNotAuthorizedToPerformOperationException extends Exception{
    public StaffNotAuthorizedToPerformOperationException (String message){
        super(message);
    }
}
