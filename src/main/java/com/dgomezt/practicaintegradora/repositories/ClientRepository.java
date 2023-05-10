package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByAuditory_EntryDateBetweenAndTotalSpentMoneyBetweenAndContact_LastNameContainsAndClientType_Type_Abbreviation(LocalDate entryDateStart, LocalDate entryDateEnd,
                                                                                                                                    BigDecimal totalSpentMoneyStart, BigDecimal totalSpentMoneyEnd,
                                                                                                                                    String lastName, String abbreviation);
}