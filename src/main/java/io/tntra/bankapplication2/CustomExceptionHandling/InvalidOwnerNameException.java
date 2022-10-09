package io.tntra.bankapplication2.CustomExceptionHandling;

public class InvalidOwnerNameException extends Exception{
    public InvalidOwnerNameException(String str){
        super(str);
    }
}
