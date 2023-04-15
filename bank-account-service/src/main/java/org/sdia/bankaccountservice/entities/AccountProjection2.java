package org.sdia.bankaccountservice.entities;

import org.sdia.bankaccountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class , name = "p2")
public interface AccountProjection2 {
    public String getCurrency();
    public AccountType getType();
}
