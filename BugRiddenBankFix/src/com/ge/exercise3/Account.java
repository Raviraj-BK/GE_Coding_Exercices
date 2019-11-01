package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account {

    private static final Logger logger = LogManager.getLogger(Account.class);
    //code fixed- static keyword removed in the below line
    private float monthlyInterestRate = 1.01f;
    private float monthlyFee = 0.0f;
    private float balanceNextMonth;
    private String accountNumber;
    private String accountType;
    private float balance;

    public Account(String accountNumber, String accountType, float balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        if (accountType == "Checking") {
            monthlyInterestRate = 1.0f;
        }
    }

    public Account(String accountNumber, String accountType) {
    	//code fixed
    	this.accountNumber = accountNumber;
    	this.accountType = accountType;
    	this.balance = 0.0f;
        new Account(accountNumber, accountType, balance);
    }

    public Account(String accountNumber) {
    	//code fixed to assign accountNumber by adding below line
    	this.accountNumber = accountNumber;
    	this.accountType = "Savings";
    	this.balance = 0.0f;
        new Account(accountNumber,accountType ,balance);     
    }

    public float valueNextMonth() {
    	balanceNextMonth=(balance * monthlyInterestRate) - monthlyFee;
        return balanceNextMonth;
    }
    
    //This Method is used to find interest to be paid for particular account
    public float interestToBePaidToAccount() {
    	 valueNextMonth() ;
    	 return balanceNextMonth -balance;
    }
    
    @Override
    public String toString() {
        if (accountType == "Checking") {
            if (monthlyFee == 0.0f) {
                return "No fee checking account #" + accountNumber;
            } else {
                return "Checking account #" + accountNumber;
            }
        } else {
            if (monthlyInterestRate > 1.01) {
                if (monthlyFee == 0.0f) {
                    return "High interest no fee savings account #" + accountNumber;
                } else {
                    return "High interest savings account #" + accountNumber;
                }
            } else {
                if (monthlyFee == 0.0f) {
                    return "No fee savings account #" + accountNumber;
                } else {
                    return "Savings account #" + accountNumber;
                }
            }
        }
    }

    public void deposit(float amount) {
        balance += amount;
    }

    public void withdraw(float amount) throws OverWithdrawlException, NegativeBalanceException {
    	 if (accountType == "Checking" && amount>100) {
    		 logger.error("Amount to withdraw exceeds the limit $100.Amount tried to withdraw :"+amount);
             throw new OverWithdrawlException("Amount to withdraw exceeds the limit $100.Amount tried to withdraw :"+amount);
         }
    	float balanceOnWithdraw= balance-amount;
    	if(accountType == "Savings" && balanceOnWithdraw<0) {
    		logger.error("Balance Amount in Savings account is going negative.Current balance is: "+balance);
    		throw new NegativeBalanceException("Balance Amount in Savings account is going negative.Current balance is: "+balance);
    	}
        balance = balanceOnWithdraw;
    }

    public float getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(float monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public float getBalance() {
        return balance;
    }

    void setBalance(float balance) {
        this.balance = balance;
    }
}
