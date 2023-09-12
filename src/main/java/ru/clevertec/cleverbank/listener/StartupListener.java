package ru.clevertec.cleverbank.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static ru.clevertec.cleverbank.configs.PropertyConstant.PERCENTS;

@WebListener
public class StartupListener implements ServletContextListener {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Runnable task = () -> {
            accountService.findAll().forEach(account -> CompletableFuture.supplyAsync(() -> {
                LocalDateTime lastUpdateDate = account.getInterestDate();
                LocalDateTime currentDate = LocalDateTime.now();
                return (int) ChronoUnit.MONTHS.between(lastUpdateDate, currentDate);
            }).thenAccept(countMonth -> {
                if (countMonth >= 1) {
                    BigDecimal am = account.getAmount().multiply(PERCENTS.pow(countMonth));
                    System.out.println(am);
                    account.setAmount(am);
                    account.setInterestDate(LocalDateTime.now());
                    accountService.update(account);
                }
            }));
        };

        executor.scheduleAtFixedRate(task, 90, 90, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        executor.shutdown();
    }
}
