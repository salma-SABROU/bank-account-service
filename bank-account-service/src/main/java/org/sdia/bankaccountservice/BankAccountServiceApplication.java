package org.sdia.bankaccountservice;

import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.entities.Customer;
import org.sdia.bankaccountservice.enums.AccountType;
import org.sdia.bankaccountservice.reposetories.BankAccountRepository;
import org.sdia.bankaccountservice.reposetories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
		return args -> {
			Stream.of("Mohammed","Yassine","Salma","Hanaa","Ali").forEach(c->{
				Customer customer=Customer.builder()
						.nom(c)
						.build();
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(customer -> {
				for (int i = 0; i < 10; i++) {
					BankAccount bankAccount=BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
							.balance(Math.random()*900000)
							.creatAt(new Date())
							.currency("MAD")
							.customer(customer)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});

		};
	}
}
