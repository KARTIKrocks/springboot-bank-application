package io.tntra.bankapplication2.Services;


import io.tntra.bankapplication2.CustomExceptionHandling.InsufficientBalanceException;
import io.tntra.bankapplication2.CustomExceptionHandling.InvalidAmountException;
import io.tntra.bankapplication2.CustomExceptionHandling.InvalidOwnerNameException;
import io.tntra.bankapplication2.CustomExceptionHandling.MinBalanceException;
import io.tntra.bankapplication2.Entities.Account;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class AccountServiceImpl implements AccountServices {

    private HashMap<String,Account> accountMap = new HashMap<>();
    @Override
    public void createAccount(Account account) throws MinBalanceException {
        accountMap.put(account.getOwnerName(), account);
    }

    @Override
    public HashMap<String, Account> getAllAccount() {
        return accountMap;
    }
    public Account getAccount(String ownerName) throws InvalidOwnerNameException {
        if (isValidOwnerName(ownerName)) {
            return accountMap.get(ownerName);
        }
        else{
            throw new InvalidOwnerNameException("please enter valid owner name !");
        }
    }
    @Override
    public boolean isValidOwnerName(String ownerName) {
        return accountMap.containsKey(ownerName);
    }
    @Override
    public float getBalanceByName(String ownerName) throws InvalidOwnerNameException {
       if (isValidOwnerName(ownerName)) {
           return accountMap.get(ownerName).getBalance();
       }else{
           throw new InvalidOwnerNameException("please enter valid owner name !");
       }
    }
    @Override
    public float depositAmount(String ownerName, float amount) throws InvalidOwnerNameException, InvalidAmountException {
        if (isValidOwnerName(ownerName)  && isValidAmount(amount)){
            float currentBalance = getBalanceByName(ownerName);
            float newBalance = currentBalance + amount;
            accountMap.get(ownerName).setBalance(newBalance);
            return newBalance;
        }else {
            if (!isValidOwnerName(ownerName)) {
                throw new InvalidOwnerNameException("please enter valid owner name.");
            }else {
                throw new InvalidAmountException("please enter positive amount");
            }
        }
    }
    @Override
    public boolean isValidAmount(float amount) {
        if (amount>0){
            return true;
        }
        return false;
    }
    @Override
    public float withdrawAmount(String ownerName, float amount) throws InvalidOwnerNameException, InvalidAmountException, InsufficientBalanceException {
        if (isValidAmount(amount) && isValidOwnerName(ownerName)){
            Account acc = getAccount(ownerName);
            if (checkBalance(acc,amount)){
                float currentBalance = getBalanceByName(ownerName);
                float newBalance = currentBalance-amount;
                acc.setBalance(newBalance);
                return newBalance;
            }
            else {
                throw new InsufficientBalanceException("Insufficient Balance !");
            }
        }
        else{
            if (!isValidOwnerName(ownerName)) {
                throw new InvalidOwnerNameException("please enter valid owner name.");
            }else {
                throw new InvalidAmountException("please enter positive amount");
            }
        }
    }
    @Override
    public boolean checkBalance(@NotNull Account account, float amount){
        float deductibleAmount = account.getBalance() + (account.getOverdraft() * account.getBalance()/100);
        return Float.compare(deductibleAmount,amount)>=0;
    }

}
