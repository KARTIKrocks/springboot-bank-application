package io.tntra.bankapplication2.Controller;

import io.tntra.bankapplication2.CustomExceptionHandling.InvalidAmountException;
import io.tntra.bankapplication2.CustomExceptionHandling.InvalidOwnerNameException;
import io.tntra.bankapplication2.CustomExceptionHandling.MinBalanceException;
import io.tntra.bankapplication2.Entities.Account;
import io.tntra.bankapplication2.Services.HDFC_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HDFC {
    @Autowired
    HDFC_service hdfcService;

    @PostMapping(value="/HDFC")
    public ResponseEntity<Object> createAccount(@RequestBody Account account){
        try {
            hdfcService.createAccount(account);
            return new ResponseEntity<>("HDFC account created successfully.", HttpStatus.CREATED);
        } catch (MinBalanceException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("minimum balance of HDFC bank is",HttpStatus.PAYMENT_REQUIRED);
        }

    }
    @GetMapping(value="/HDFC")
    public ResponseEntity<Object> getAllAccount(){
        return new ResponseEntity<>(hdfcService.getAllAccount(),HttpStatus.OK);
    }
    @GetMapping(value="/HDFC/{ownerName}")
    public ResponseEntity<Object> getAccount(@PathVariable("ownerName") String ownerName) {
        try {
            return new ResponseEntity<>(hdfcService.getAccount(ownerName),HttpStatus.OK);
        } catch (InvalidOwnerNameException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("this owner name does not exist !",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value="/HDFC/balance/{ownerName}")
    public ResponseEntity<Object> getBalance(@PathVariable("ownerName") String ownerName)  {
        try {
            float balance = hdfcService.getBalanceByName(ownerName);
            return new ResponseEntity<>("Your balance : "+ balance  , HttpStatus.OK);
        } catch (InvalidOwnerNameException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("this owner name does not exist !",HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value="/HDFC/deposit/{ownerName}/{amount}")
    public ResponseEntity<Object> depositAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount") float amount){
        try {
            hdfcService.depositAmount(ownerName, amount);
            return new ResponseEntity<>("amount deposited.", HttpStatus.OK);
        }
        catch (InvalidOwnerNameException | InvalidAmountException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("enter valid owner or amount",HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value="/HDFC/withdraw/{ownerName}/{amount}")
    public ResponseEntity<Object> withdrawAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount") float amount){
        try{
            hdfcService.withdrawAmount(ownerName,amount);
            return new ResponseEntity<>("amount widrawn successfully",HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
