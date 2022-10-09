package io.tntra.bankapplication2.Entities;

import io.tntra.bankapplication2.Enumerators.AccountType;

public class Account {
public String ownerName;
    public float balance;
    public AccountType AccType;
    public float overdraft;
    public float minBalance;
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public AccountType getAccType() {
        return AccType;
    }
    public void setAccType(AccountType accType) {
        AccType = accType;
    }
    public float getOverdraft() {
        return overdraft;
    }
    public void setOverdraft(float overdraft) {
        this.overdraft = overdraft;
    }
    public float getMinBalance() {
        return minBalance;
    }
    public void setMinBalance(float minBalance) {
        this.minBalance = minBalance;
    }
    @Override
    public String toString() {
        return "Account{" +
                "ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", AccType=" + AccType +
                ", overdraft=" + overdraft +
                ", minBalance=" + minBalance +
                '}';
    }
}
