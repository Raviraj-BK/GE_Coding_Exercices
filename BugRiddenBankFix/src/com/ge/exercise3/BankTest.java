package com.ge.exercise3;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankTest {

    Bank bank;
    
    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void addAccountTest() {
        Account account = new Account("001");
        bank.addAccount(account);
        
       //NegativeBalanceExceptionTest
        bank.depositToAccount("001", 100.0f);         
        //bank.withdrawFromAccount("001", 101.0f);
        assertEquals(1, bank.numAccounts());
    }

    @Test
    public void getAccountTest() {
        Account account = new Account("002", "Checking", 100.0f);
        bank.addAccount(account);
        assertEquals(account, bank.getAccount("002"));
    }

    @Test
    public void depositToAccountTest() {
        Account account = new Account("003", "Checking", 100.0f);
        bank.addAccount(account);
        bank.depositToAccount("003", 100.0f);
        assertEquals(200.0f, account.getBalance(), 0.01);
    }

    @Test
    public void withdrawFromAccountTest() {
        Account account = new Account("004", "Checking", 100.0f);
        bank.addAccount(account);
        bank.withdrawFromAccount("004", 100.0f);
        
        //OverwithdrawlExceptionTest
        //bank.withdrawFromAccount("004", 101.0f);
        assertEquals(0.0f, account.getBalance(), 0.01);
    } 
    
    @Test
    public void testE() {
    	addAccountTest();
    	getAccountTest();
    	depositToAccountTest();
    	withdrawFromAccountTest();
        assertEquals("Loss",  bank.getNextMonthPrediction());
        //System.out.println(bank.getNextMonthPrediction());
        System.out.println(bank.getSumOfCurrentHoldings());
    }
}