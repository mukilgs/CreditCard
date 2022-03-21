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
public class CreditCardRegistration {
	
	private String cname;
	
	
	private int phoneno;
	@Id
	private int acNo;
	private int age;
	private int salary;
	
	
	
}
