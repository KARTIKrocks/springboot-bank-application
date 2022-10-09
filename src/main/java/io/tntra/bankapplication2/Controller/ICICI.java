package io.tntra.bankapplication2.Controller;

import io.tntra.bankapplication2.CustomExceptionHandling.InvalidAmountException;
import io.tntra.bankapplication2.CustomExceptionHandling.InvalidOwnerNameException;
import io.tntra.bankapplication2.CustomExceptionHandling.MinBalanceException;
import io.tntra.bankapplication2.Entities.Account;
import io.tntra.bankapplication2.Services.ICICI_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ICICI {

    @Autowired
    ICICI_service iciciService;
    @PostMapping(value="/ICICI")
    public ResponseEntity<Object> createAccount(@RequestBody Account account){
        try {
            iciciService.createAccount(account);
            return new ResponseEntity<>("ICICI account created successfully.", HttpStatus.CREATED);
        }
        catch (MinBalanceException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("minimum balance of HDFC bank is",HttpStatus.PAYMENT_REQUIRED);
        }

    }
    @GetMapping(value="/ICICI")
    public ResponseEntity<Object> getAllAccount(){
        return new ResponseEntity<>(iciciService.getAllAccount(),HttpStatus.OK);
    }
    @GetMapping(value="/ICICI/{ownerName}")
    public ResponseEntity<Object> getAccount(@PathVariable("ownerName") String ownerName) {
        try {
            return new ResponseEntity<>(iciciService.getAccount(ownerName),HttpStatus.OK);
        } catch (InvalidOwnerNameException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("this owner name does not exist !",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value="/ICICI/balance/{ownerName}")
    public ResponseEntity<Object> getBalance(@PathVariable("ownerName") String ownerName)  {
        try {
            float balance = iciciService.getBalanceByName(ownerName);
            return new ResponseEntity<>("Your balance : "+ balance  , HttpStatus.OK);
        } catch (InvalidOwnerNameException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("this owner name does not exist !",HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value="/ICICI/deposit/{ownerName}/{amount}")
    public ResponseEntity<Object> depositAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount") float amount){
        try {
            iciciService.depositAmount(ownerName, amount);
            return new ResponseEntity<>("amount deposited.", HttpStatus.OK);
        }
        catch (InvalidOwnerNameException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("enter valid owner",HttpStatus.BAD_REQUEST);
        }
        catch (InvalidAmountException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("enter valid amount",HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value="/ICICI/withdraw/{ownerName}/{amount}")
    public ResponseEntity<Object> withdrawAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount") float amount){
        try{
            iciciService.withdrawAmount(ownerName,amount);
            return new ResponseEntity<>("amount widrawn successfully",HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
