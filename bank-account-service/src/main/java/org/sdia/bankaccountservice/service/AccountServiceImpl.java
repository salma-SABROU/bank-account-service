package org.sdia.bankaccountservice.service;

import org.sdia.bankaccountservice.dto.BankAccountRequestDTO;
import org.sdia.bankaccountservice.dto.BankAccountResponseDTO;
import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.mappers.AccountMapper;
import org.sdia.bankaccountservice.reposetories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service @Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        //mapping d'objet vers un autre objet (DTO vers ENTITIES)
        BankAccount bankAccount=accountMapper.fromRequestDTOToBankAccount(bankAccountDTO);
        BankAccount sevedAccount=bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO=accountMapper.fromBankAccountToResponseDTO(sevedAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountDTO, String id) {
        BankAccount bankAccount=BankAccount.builder()
                .id(id)
                .type(bankAccountDTO.getType())
                .balance(bankAccountDTO.getBalance())
                .creatAt(new Date())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount sevedAccount=bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO=accountMapper.fromBankAccountToResponseDTO(sevedAccount);
        return bankAccountResponseDTO;
    }

}
