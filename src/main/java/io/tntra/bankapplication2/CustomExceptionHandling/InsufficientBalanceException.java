package io.tntra.bankapplication2.CustomExceptionHandling;

public class InsufficientBalanceException extends Exception{
    public  InsufficientBalanceException(String str){
        super(str);
    }
}
