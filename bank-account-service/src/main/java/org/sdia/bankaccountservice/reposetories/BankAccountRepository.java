package org.sdia.bankaccountservice.reposetories;

import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Currency;
import java.util.List;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    @RestResource(path = "/byType")
    List<BankAccount> findBankAccountByType(@Param("t") AccountType type);

    List<BankAccount> findBankAccountByCurrency(Currency currency);
}
