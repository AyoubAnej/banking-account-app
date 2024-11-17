package net.ayoub.accountservice;

import net.ayoub.accountservice.clients.CustomerRestClient;
import net.ayoub.accountservice.entities.BankAccount;
import net.ayoub.accountservice.enums.AccountType;
import net.ayoub.accountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.allCustomers().forEach(c ->{
                BankAccount bankAccount1 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .balance(Math.random()* 80000)
                        .currency("MAD")
                        .type(AccountType.CURRENT_ACCOUNT)
                        .createdAt(LocalDate.now())
                        .customerId(c.getId())
                        .build();
                BankAccount bankAccount2 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .balance(Math.random()*45000)
                        .currency("MAD")
                        .type(AccountType.SAVING_ACCOUNT)
                        .createdAt(LocalDate.now())
                        .customerId(c.getId())
                        .build();

                bankAccountRepository.save(bankAccount1);
                bankAccountRepository.save(bankAccount2);
            });


        };
    }
}
