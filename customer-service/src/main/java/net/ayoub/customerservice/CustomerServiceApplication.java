package net.ayoub.customerservice;

import net.ayoub.customerservice.config.GlobalConfig;
import net.ayoub.customerservice.entities.Customer;
import net.ayoub.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            List<Customer> customerList = List.of(
                    Customer.builder()
                            .firstName("Ayoub")
                            .lastName("Anejdam")
                            .email("anejdam@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("Nora")
                            .lastName("Naji")
                            .email("Nora@gmail.com")
                            .build()
            );
            customerRepository.saveAll(customerList);
        };
    }
}
