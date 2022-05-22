package exceptions;

public class CustomerOutOfFundException extends RuntimeException{
    public CustomerOutOfFundException (String message){
        super(message);
    }
}
