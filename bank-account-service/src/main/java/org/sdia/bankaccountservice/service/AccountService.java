package org.sdia.bankaccountservice.service;

import org.sdia.bankaccountservice.dto.BankAccountRequestDTO;
import org.sdia.bankaccountservice.dto.BankAccountResponseDTO;
import org.sdia.bankaccountservice.entities.BankAccount;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountDTO, String id);
}
