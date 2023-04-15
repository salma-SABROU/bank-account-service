package org.sdia.bankaccountservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sdia.bankaccountservice.dto.BankAccountRequestDTO;
import org.sdia.bankaccountservice.dto.BankAccountResponseDTO;
import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.entities.Customer;
import org.sdia.bankaccountservice.reposetories.BankAccountRepository;
import org.sdia.bankaccountservice.reposetories.CustomerRepository;
import org.sdia.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccountGraphQL {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(bankAccount,id);
    }

    @MutationMapping
    public void deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
    }
    @QueryMapping
    List<Customer> customers(){
        return  customerRepository.findAll();
    }
}

