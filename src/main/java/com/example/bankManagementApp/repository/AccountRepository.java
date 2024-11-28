package com.example.bankManagementApp.repository;

import com.example.bankManagementApp.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Modifying
    @Transactional
    @Query ("UPDATE Account e SET e.availableToWithdraw = :value ")
    void updateWithdrawalLimit(@Param("value")Double value);

}
