package io.tntra.bankapplication2.Services;

import io.tntra.bankapplication2.CustomExceptionHandling.MinBalanceException;
import io.tntra.bankapplication2.Entities.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class HDFC_service extends AccountServiceImpl implements AccountServices{


    public void createAccount(@NotNull Account account) throws MinBalanceException {
        account.setMinBalance(1000);
        if (Float.compare(account.getBalance(), account.getMinBalance())>=0){
            super.createAccount(account);
        }else{
            throw new MinBalanceException("Minimum balance of "+account.getMinBalance()+" is required");
        }
    }

}
