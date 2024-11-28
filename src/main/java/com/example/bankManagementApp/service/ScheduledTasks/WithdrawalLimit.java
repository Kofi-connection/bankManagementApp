package com.example.bankManagementApp.service.ScheduledTasks;

import com.example.bankManagementApp.repository.AccountRepository;
import com.example.bankManagementApp.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component


public class WithdrawalLimit {
    @Autowired
    private AccountServiceImpl accountService;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateWithdrawalLimit(){
        accountService.addDefaultLimit();
        log.info("Default limit updated");
    }



}
