package org.sdia.bankaccountservice.web;

import org.sdia.bankaccountservice.dto.BankAccountRequestDTO;
import org.sdia.bankaccountservice.dto.BankAccountResponseDTO;
import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.mappers.AccountMapper;
import org.sdia.bankaccountservice.reposetories.BankAccountRepository;
import org.sdia.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;
    public AccountRestController(BankAccountRepository bankAccountRepository,AccountService accountService,AccountMapper accountMapper){
        this.bankAccountRepository=bankAccountRepository;
        this.accountService=accountService;
        this.accountMapper=accountMapper;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    /*@PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        if(bankAccount.getId() == null) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }*/

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @PutMapping ("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        //search account
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatAt()!=null) account.setCreatAt(new Date());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
