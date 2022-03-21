package com.example.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.project.model.CreditCardModel;

public interface UserRepo extends JpaRepository<CreditCardModel, Integer>  {

}
