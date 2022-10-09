package io.tntra.bankapplication2.CustomExceptionHandling;

public class MinBalanceException extends Exception{
    public MinBalanceException(String str){
        super(str);
    }
}
