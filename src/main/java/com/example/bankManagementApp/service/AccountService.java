package com.example.bankManagementApp.service;

import com.example.bankManagementApp.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);

    AccountDTO deposit(Long id, double amountToBeAdded);

    AccountDTO withdraw(Long id, double amountToBeWithdrawn);

    List<AccountDTO> getAllAccounts();


    void deleteAccount(Long id);
}
