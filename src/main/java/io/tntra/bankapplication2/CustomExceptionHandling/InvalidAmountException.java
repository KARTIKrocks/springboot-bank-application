package io.tntra.bankapplication2.CustomExceptionHandling;

public class InvalidAmountException extends Exception{
    public InvalidAmountException(String str){
        super(str);
    }
}
