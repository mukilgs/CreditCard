package com.example.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.CreditCardHistory;

public interface CreditCardHistoryRepo extends JpaRepository<CreditCardHistory, Integer> {
	

}
