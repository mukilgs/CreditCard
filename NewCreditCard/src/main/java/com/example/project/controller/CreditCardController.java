package com.example.project.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.model.CreditCardModel;
import com.example.project.model.CreditCardRegistration;
import com.example.project.repo.CreditCardHistoryRepo;
import com.example.project.repo.CreditCardRepo;
import com.example.project.repo.UserRepo;
import com.example.project.service.CreditCardService;




@Controller
public class CreditCardController {
	
	org.slf4j.Logger logger=LoggerFactory.getLogger(CreditCardController.class);
	
	@Autowired
	CreditCardRepo repo;
	
	@Autowired	
	//CreditCardRepo creditCardRepository;
	UserRepo user;
	
	@Autowired
	CreditCardService CreditCardService;
	
	@Autowired
	CreditCardHistoryRepo credhistory;
	
	
	@RequestMapping("/")
	public String home()
	{
		logger.debug("Registration method started");
		return "registration";
	}
	
	
	
	@RequestMapping("/regdetails")
	public String cardRegister(CreditCardRegistration creditCardRegistration)
	{
		logger.debug("registration info loaded");
		CreditCardModel card=CreditCardService.createCard(creditCardRegistration);
		
	repo.save(creditCardRegistration);
	logger.debug("registration info loaded ended");
	return "registration";
	}
	
	
	/**
	 * Method to update CreditCard
	 * 
	 * @param 
	 * @return 
	 */
	
	
@PutMapping(path="/credituser",consumes= {"application/json"})
	
	public CreditCardModel getUser(@RequestBody CreditCardModel creditcard)
	{
		
		user.save(creditcard);
		
		return creditcard;
	}


/**
 * Method to delete CreditCard
 * 
 * @param 
 * @return 
 */

	
@DeleteMapping("/credituser/{creditCardNumber}")

public CreditCardModel getUser1(@PathVariable("creditCardNumber") int creditCardNumber)
{

	CreditCardModel c=user.getOne(creditCardNumber);
	user.delete(c);
	
	
	return c;
}
	
	
	

/**
 * Method to unLock CreditCard
 * 
 * @param cardNo
 * @return card
 */


@PutMapping("/unLockCard/{creditCardNumber}")
public String unBlockCard(@PathVariable("creditCardNumber") int cardNo) {
	logger.info("unBlockCard method started with cardNo{}", cardNo);
	String card = CreditCardService.unLockCard(cardNo);
	logger.debug("unBlockCard method ended cardNo{}", cardNo);
	return card;
}

/**
 * Method to Block CreditCard
 * 
 * @param cardNo
 * @return card
 */

	
@PutMapping("/blockCard/{creditCardNumber}")
public String blockCard(@PathVariable("creditCardNumber") int cardNo) {
	logger.info("blockCard method started cardNo{}", cardNo);
	String card = CreditCardService.unLockCard(cardNo);
	logger.debug("blockCard method ended cardNo{}", cardNo);
	return card;
}

/**
 * Method to update cardLimit
 * 
 * @param cardNo
 * @return card
 */



@PutMapping("/cardLimitUpdate/{creditCardNumber}")
public CreditCardModel cardLimitUpdate(@PathVariable("creditCardNumber") int cardNo) {
	logger.info("cardLimitUpdate method started cardNo{}", cardNo);
	CreditCardModel card = CreditCardService.cardLimitUpdate(cardNo);
	logger.debug("cardLimitUpdate method ended cardNo{}", cardNo);
	return card;
}

/**
 * Method to accept credit card dues
 * 
 * @param cardNo
 * @param amount
 * @return card
 */




@PutMapping("/acceptingDue/{cardNo}/{amount}")
public CreditCardModel acceptDues(@PathVariable int cardNo,@PathVariable Integer amount )
{
	logger.info("acceptingDue method started  cardNo{} ,amount{} ", cardNo, amount);
CreditCardModel card=CreditCardService.acceptingDue(cardNo,amount);
logger.debug("acceptingDue method ended cardNo{} ,amount{} ", cardNo, amount);
return card;
}



/**
 * Method to cancel creditCard
 * 
 * @param cardNo
 */



@DeleteMapping("/cancelCreditCard/{creditCardNumber}")
public void cancelCreditCard(@PathVariable("creditCardNumber")int cardNo)
{
	logger.info("cancelCreditCard method started cardNo{}", cardNo);
CreditCardService.cancelCreditCard(cardNo);
logger.debug("cancelCreditCard method ended cardNo{}", cardNo);

}


	

}
