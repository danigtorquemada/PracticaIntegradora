package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByAuditory_EntryDateBetweenAndTotalSpentMoneyBetweenAndContact_LastNameContainsAndClientType_Type_AbbreviationIn(LocalDate entryDateStart, LocalDate entryDateEnd, BigDecimal totalSpentMoneyStart, BigDecimal totalSpentMoneyEnd, String lastName, Collection<String> abbreviations);
    Client findByUser(User user);
}