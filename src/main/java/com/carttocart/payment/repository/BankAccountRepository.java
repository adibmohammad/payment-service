package com.carttocart.payment.repository;

import com.carttocart.payment.model.entity.BankAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query(" select b from BankAccount b join b.user c where c.username = :username and b.activeFlag = true and b.disableDate is null order by b.creationDate desc ")
    List<BankAccount> getCardList(String username, Pageable pageable);
}
