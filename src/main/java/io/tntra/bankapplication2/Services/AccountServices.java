package io.tntra.bankapplication2.Services;
import io.tntra.bankapplication2.CustomExceptionHandling.InsufficientBalanceException;
import io.tntra.bankapplication2.CustomExceptionHandling.InvalidAmountException;
import io.tntra.bankapplication2.CustomExceptionHandling.InvalidOwnerNameException;
import io.tntra.bankapplication2.CustomExceptionHandling.MinBalanceException;
import io.tntra.bankapplication2.Entities.Account;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public interface AccountServices {
    void createAccount(Account account) throws MinBalanceException;

    HashMap<String, Account> getAllAccount();

    Account getAccount(String ownerName) throws InvalidOwnerNameException;
    boolean isValidOwnerName(String ownerName);

    float getBalanceByName(String ownerName) throws InvalidOwnerNameException;

    float depositAmount(String ownerName, float amount) throws InvalidOwnerNameException, InvalidAmountException;
    boolean isValidAmount(float amount);

    float withdrawAmount(String ownerName, float amount) throws InvalidOwnerNameException, InvalidAmountException, InsufficientBalanceException;

    boolean checkBalance(@NotNull Account account, float amount);
}
