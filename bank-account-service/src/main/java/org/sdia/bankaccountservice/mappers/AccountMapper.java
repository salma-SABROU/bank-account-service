package org.sdia.bankaccountservice.mappers;

import org.sdia.bankaccountservice.dto.BankAccountRequestDTO;
import org.sdia.bankaccountservice.dto.BankAccountResponseDTO;
import org.sdia.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccountToResponseDTO(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO=new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    public BankAccount fromRequestDTOToBankAccount(BankAccountRequestDTO bankAccountDTO){
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .type(bankAccountDTO.getType())
                .balance(bankAccountDTO.getBalance())
                .creatAt(new Date())
                .currency(bankAccountDTO.getCurrency())
                .build();
        return bankAccount;
    }
}
