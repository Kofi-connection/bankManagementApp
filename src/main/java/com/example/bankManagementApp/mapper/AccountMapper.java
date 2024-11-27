package com.example.bankManagementApp.mapper;

import com.example.bankManagementApp.dto.AccountDTO;
import com.example.bankManagementApp.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDTO accountDTO){

        Account account = new Account(
                accountDTO.getId(),
                accountDTO.getAccountHolderName(),
                accountDTO.getBalance(),
                accountDTO.getAvailableToWithdraw()
        );

        return  account;
    }
    public static AccountDTO mapToAccountDTO(Account account){
        AccountDTO accountDTO = new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getAvailableToWithdraw()
        );
        return accountDTO;
    }
}
