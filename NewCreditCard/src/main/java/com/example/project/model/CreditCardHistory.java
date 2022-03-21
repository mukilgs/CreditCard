package com.example.project.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Component
@Getter
@Setter
@ToString
public class CreditCardHistory {
	
	private String username;
	@Id
	private int creditCardNumber;
	private LocalDate cancelDate;
	private String Status;

}
