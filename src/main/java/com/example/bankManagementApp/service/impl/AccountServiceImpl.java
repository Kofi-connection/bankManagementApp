package com.example.bankManagementApp.service.impl;

import com.example.bankManagementApp.dto.AccountDTO;
import com.example.bankManagementApp.entity.Account;
import com.example.bankManagementApp.mapper.AccountMapper;
import com.example.bankManagementApp.repository.AccountRepository;
import com.example.bankManagementApp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }




    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account =accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDTO(account);

    }

    @Override
    public AccountDTO deposit(Long id, double amountToBeAdded) {
        AccountDTO accountDTO = getAccountById(id);
        double newAmount = accountDTO.getBalance()+amountToBeAdded;
        accountDTO.setBalance(newAmount);
        Account savedAccount = accountRepository.save(AccountMapper.mapToAccount(accountDTO));
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdraw(Long id, double amountToBeWithdrawn) {
        Account account =accountRepository.
                findById(id)
                        .orElseThrow(() -> new RuntimeException("Account does not exist"));
        if (amountToBeWithdrawn > account.getBalance()){
            throw new RuntimeException("Insufficient amount");
        }
        if (amountToBeWithdrawn>account.getAvailableToWithdraw()){
            throw new RuntimeException("Amount to be withdrawn is over the limit");
        }
        double newAmount = account.getBalance()-amountToBeWithdrawn;
        account.setBalance(newAmount);
        double updatedLimit = account.getAvailableToWithdraw() - amountToBeWithdrawn;
        account.setAvailableToWithdraw(updatedLimit);
        return AccountMapper.mapToAccountDTO(accountRepository.save(account));
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> acc = accountRepository.findAll();
        return acc.stream().map(account -> AccountMapper.mapToAccountDTO(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);
    }
    public void addDefaultLimit(){
        accountRepository.findAll().forEach(entity ->{
            entity.setAvailableToWithdraw(1000.0);
            accountRepository.save(entity);
        });
    }

}
