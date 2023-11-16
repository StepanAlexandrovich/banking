package com.example.banking.repositories;

import com.example.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByUserId(Long userId);
    Optional<Account> findByName(String name);
    @Procedure("transfer")
    void transfer(Long fromId,Long toId,Double amountTransfer);

}
