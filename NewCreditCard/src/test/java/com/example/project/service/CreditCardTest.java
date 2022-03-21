package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.project.model.CreditCardHistory;
import com.example.project.model.CreditCardModel;
import com.example.project.model.CreditCardRegistration;
import com.example.project.repo.CreditCardHistoryRepo;
import com.example.project.repo.CreditCardRepo;
import com.example.project.repo.UserRepo;


@RunWith(MockitoJUnitRunner.class)
public class CreditCardTest {
	
	@InjectMocks
	CreditCardService creditCardService;
	
	@Mock
	CreditCardHistoryRepo creditCardHistoryRepo;
	
	@Mock
	CreditCardRepo creditCardRepo;
	
	@Mock
	UserRepo userRepo;
	
	@Mock
	CreditCardHistory creditCardHistory;
	
	
	
	@Test
	public void testCreateCreditCard() {
		CreditCardRegistration registrationModel = new CreditCardRegistration();
		registrationModel.setAcNo(4578);
		registrationModel.setCname("Krishnendu");
		registrationModel.setAge(18);
		registrationModel.setPhoneno(76256);
		registrationModel.setSalary(30000);

		CreditCardModel creditCard1 = new CreditCardModel();
		creditCard1.setCreditCardNumber(4588);
		creditCard1.setCreditLimit(50000);
		creditCard1.setPassword("krishna");
		creditCard1.setStatus("unblock");
		creditCard1.setCname("Krishnendu");

		CreditCardModel creditCard2 = new CreditCardModel();
		creditCard2.setCreditCardNumber(4588);
		creditCard2.setCreditLimit(50000);
		creditCard2.setPassword("123");
		creditCard2.setStatus("unblock");
		creditCard2.setCname("Krishnendu");

		when(userRepo.save(creditCard2)).thenReturn(creditCard1);
		assertEquals(creditCard1, creditCardService.createCard(registrationModel));

	}

	@Test
	public void testUnLockCard() {
		CreditCardRegistration registrationModel = new CreditCardRegistration();
		CreditCardModel creditCard = new CreditCardModel();
		creditCard.setStatus("unLock");
		creditCard.setCreditCardNumber(458);

		Optional<CreditCardModel> creditCardModel = Optional.ofNullable(creditCard);

		when(userRepo.findById(Mockito.anyInt())).thenReturn(creditCardModel);
		when(userRepo.save(Mockito.any())).thenReturn(creditCard);
		assertEquals(creditCard, creditCardService.unLockCard(458));

	}

	@Test
	public void testBlockCard() {
		CreditCardRegistration registrationModel = new CreditCardRegistration();
		CreditCardModel creditCard = new CreditCardModel();
		creditCard.setStatus("block");
		creditCard.setCreditCardNumber(458);

		Optional<CreditCardModel> creditCardModel = Optional.ofNullable(creditCard);

		when(userRepo.findById(Mockito.anyInt())).thenReturn(creditCardModel);
		when(userRepo.save(Mockito.any())).thenReturn(creditCard);
		assertEquals(creditCard, creditCardService.block(458));

	}
	
	
	

}
