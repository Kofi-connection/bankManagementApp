package com.example.bankManagementApp.controller;


import com.example.bankManagementApp.dto.AccountDTO;
import com.example.bankManagementApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;
    public AccountController (AccountService accountService){
        this.accountService = accountService;
    }

//    Add Account Rest API
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);

    }
//    Get account REST API
    @GetMapping("{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
        AccountDTO accountDTO = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDTO);
    }
//    Deposit to account
    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        AccountDTO accountDTO = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDTO);
    }
    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        AccountDTO accountDTO = accountService.withdraw(id, request.get("amount"));
        if (request.get("amount")>accountDTO.getBalance()){
            return new ResponseEntity<>(HttpStatus.valueOf("Insufficient Amount"));
        }
        return ResponseEntity.ok(accountDTO);
    }
//    Get All accounts

    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accountDTOList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDTOList);
    }
//    Delete account
    @DeleteMapping("deleteAccount")
    public ResponseEntity<String> deleteAccount(Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
