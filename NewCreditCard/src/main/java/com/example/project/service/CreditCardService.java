package com.example.project.service;

import java.time.LocalDate;
import java.util.Optional;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.project.model.CreditCardHistory;
import com.example.project.model.CreditCardModel;
import com.example.project.model.CreditCardRegistration;
import com.example.project.repo.CreditCardHistoryRepo;
import com.example.project.repo.CreditCardRepo;
import com.example.project.repo.UserRepo;



@Component
@Service
public class CreditCardService {
	
	PasswordEncoder password;	
	
	@Autowired
	UserRepo urepo;
	@Autowired
	CreditCardRepo ccrepo;
	@Autowired
	CreditCardModel creditCardModel;
	@Autowired
	CreditCardHistory creditCardHistory;
	@Autowired
	CreditCardHistoryRepo creditCardHistoryRepo;
	@Autowired
	CreditCardRegistration creditCardRegistration;
	
	Logger logger=LoggerFactory.getLogger(CreditCardService.class);
	
	
	/**
	 * Method to createCreditCard
	 * 
	 * @param registrationModel
	 * @return creditCardModel
	 */
	
	
	public CreditCardModel createCard(CreditCardRegistration userRegister) 
	{
		logger.debug("createCreditCard  started");
		PasswordEncoder p = new BCryptPasswordEncoder();
		
		
		creditCardModel.setCname(userRegister.getCname());
		creditCardModel.setCreditCardNumber(userRegister.getAcNo()+10);
		
		creditCardModel.setPassword(p.encode(userRegister.getCname().substring(0,5).concat("mukil")));
		creditCardModel.setPassword("mukil");
		creditCardModel.setStatus("unblock");
		creditCardModel.setCreditLimit(30000);
		urepo.save(creditCardModel);
		logger.debug("createCreditCard ended");
		return creditCardModel;
	}
	
	
	/**
	 * Method to unLockCard
	 * 
	 * @param creditCardNumber
	 * @return creditCardModel
	 */
	
	
	public String unLockCard(int creditCardNumber) {
		
		logger.info("unLockCard method started  creditCardNumber{} ");
		
		String creditCardStatus="null";
		if(!ObjectUtils.isEmpty(urepo.findById(creditCardNumber))){
			Optional<CreditCardModel> card=urepo.findById(creditCardNumber);
			CreditCardModel creditCardModel=card.get();
			creditCardModel.setStatus("unblock");
			urepo.save(creditCardModel); 

			creditCardStatus="unblock";
			}
			else
			{
				creditCardStatus="fruad user ";
			}
		logger.debug("unLockCard method end with argument creditCardNumber{} ");
			return creditCardStatus;
			}
		
		
	
	
	

	/**
	 * Method to blockCard
	 * 
	 * @param creditCardNumber
	 * @return creditCardModel
	 */
	
	public String block(int creditCardNumber) {
		logger.info("blockCard method started creditCardNumber{} ");
		
		String creditCardStatus="null";
		if(!ObjectUtils.isEmpty(urepo.findById(creditCardNumber))){
			Optional<CreditCardModel> card=urepo.findById(creditCardNumber);
			CreditCardModel creditCardModel=card.get();
			creditCardModel.setStatus("block");
			urepo.save(creditCardModel); 

			creditCardStatus="block";
			}
			else
			{
				creditCardStatus="fruad user ";
			}
		logger.debug("blockCard method end with argument creditCardNumber{} ");
			return creditCardStatus;
			}
		
		
		
	
	
	
	/**
	 * Method to updateCardLimit
	 * 
	 * @param creditCardNumber
	 * @return creditCardModel
	 */
	
	public CreditCardModel cardLimitUpdate(int creditCardNumber)

	{
		logger.info("cardLimitUpdate method started  creditCardNumber{} ");
		CreditCardModel cm=new CreditCardModel();
		urepo.findById(creditCardNumber);
		creditCardModel.setCreditLimit(creditCardModel.getCreditLimit() + 5000);
		urepo.save(creditCardModel);
		logger.debug("cardLimitUpdate method ended creditCardNumber{} ");
		return creditCardModel;
	}
	
	/**
	 * Method for acceptingDues
	 * 
	 * @param creditCardNumber
	 * @param amount
	 * @return creditCardModel
	 */
	
	public CreditCardModel acceptingDue(int creditCardNumber, Integer amount) {
		logger.info("acceptingDue method started creditCardNumber{} and amount{} ");
		CreditCardModel cm=new CreditCardModel();
		urepo.findById(creditCardNumber);
		creditCardModel.setCreditLimit(creditCardModel.getCreditLimit() + amount);
		urepo.save(creditCardModel);
		logger.debug("acceptingDue method ended creditCardNumber{} and amount{} ");
		return creditCardModel;
	}
	
	/**
	 * Method for cancelCreditCard
	 * 
	 * @param creditCardNumber
	 */
	
	public void cancelCreditCard(int creditCardNumber) {
		logger.info("cancelCreditCard method started  creditCardNumber{} ");
		LocalDate date = LocalDate.now();
		Optional<CreditCardModel> customer = urepo.findById(creditCardNumber);
		creditCardHistory.setUsername(customer.get().getCname());
		creditCardHistory.setCreditCardNumber(creditCardNumber);
		creditCardHistory.setCancelDate(date);
		creditCardHistory.setStatus("cancel");
		creditCardHistoryRepo.save(creditCardHistory);
		urepo.deleteById(creditCardNumber);
		logger.debug("cancelCreditCard method ended creditCardNumber{} ");
	}
	
	

}
