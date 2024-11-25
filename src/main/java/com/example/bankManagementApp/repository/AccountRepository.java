package com.example.bankManagementApp.repository;

import com.example.bankManagementApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}
