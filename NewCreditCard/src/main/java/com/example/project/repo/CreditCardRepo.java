package com.example.project.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



import com.example.project.model.CreditCardRegistration;



public interface CreditCardRepo extends CrudRepository<CreditCardRegistration, Integer> {

	 

}
