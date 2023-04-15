package org.sdia.bankaccountservice.reposetories;

import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
