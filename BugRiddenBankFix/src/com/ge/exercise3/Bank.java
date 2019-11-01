package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Bank {

	private static final Logger logger = LogManager.getLogger(Bank.class);
	private Map<String, Account> accountMap;

	public Bank() {
		accountMap = new HashMap<>();
	}

	public Account getAccount(String accountNumber) {
		return accountMap.get(accountNumber);
	}

	public void addAccount(Account account) {
		accountMap.put(account.getAccountNumber(), account);
	}

	public void depositToAccount(String accountNumber, float amount) {
		getAccount(accountNumber).deposit(amount);
	}

	public void withdrawFromAccount(String accountNumber, float amount) {
		try {
			getAccount(accountNumber).withdraw(amount);
		} catch (OverWithdrawlException e) {			
			e.printStackTrace();
			logger.error("Please enter the amount less than $100 to withdraw.");
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
			logger.error("Please enter the lesser amount to withdraw as the balance is going negative.");
		}
	}

	public int numAccounts() {
		return accountMap.size();
	}
	
	/*This method returns whether the bank produces profit/loss in the next month 
	  based on fees collected on each account v/s interest paid out*/
	public String getNextMonthPrediction() {
		float sumofFeesCollected = 0;
		float sumofInterestPaid=0;
		float nextMonthIncome=0;
		for (Map.Entry<String, Account> entry : accountMap.entrySet())  {
			sumofFeesCollected +=entry.getValue().getMonthlyFee();
			sumofInterestPaid +=entry.getValue().interestToBePaidToAccount();
		}
		nextMonthIncome=sumofFeesCollected-sumofInterestPaid;
		if(nextMonthIncome >0 ) {
			logger.info("Bank will produce Profit in the next month. nextMonthIncome: "+nextMonthIncome);
			return "Profit";
		}
		else if(nextMonthIncome <0) {
			logger.info("Bank will produce Loss in the next month. nextMonthIncome: "+nextMonthIncome);
			return "Loss";
		}
		else {
			logger.info("Bank will neither produce profit/loss in the next month. nextMonthIncome: "+nextMonthIncome);
			return null;
		}
	}
	
	/*This method returns the SumOfCurrentHoldings of the Bank at present*/
	public float getSumOfCurrentHoldings() {
		float sumofFeesCollected = 0;
		float sumofInterestToBePaid=0;
		float sumofCurrHoldings=0;
		float sumofAccountsBalance=0;
		for (Map.Entry<String, Account> entry : accountMap.entrySet())  {
			sumofFeesCollected +=entry.getValue().getMonthlyFee();
			sumofInterestToBePaid +=entry.getValue().interestToBePaidToAccount();
			sumofAccountsBalance +=entry.getValue().getBalance();
		}
		sumofCurrHoldings=(sumofAccountsBalance + sumofFeesCollected)-sumofInterestToBePaid;
		return sumofCurrHoldings;
	}
}
