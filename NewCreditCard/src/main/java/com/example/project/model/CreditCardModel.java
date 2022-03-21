package com.example.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Component
@Entity


@Getter
@Setter
@ToString

public class CreditCardModel{
	
	
	    
	    private String  cname;
		@Id
		private int creditCardNumber;
		private String password;
		private String status;
		private int creditLimit;
		
		
		
}
