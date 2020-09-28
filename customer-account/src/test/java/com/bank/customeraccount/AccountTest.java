package com.bank.customeraccount;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.customeraccount.entity.Account;
import com.bank.customeraccount.repository.AccountRepository;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AccountTest {
	@Autowired
	AccountRepository accountRepository;
	@Test
	@Order(1)
	void test_createAccount() {
		Account account1 = new Account();
		account1.setAccountType("Saving");
		account1.setBalance(200000);
		Account account2 = new Account();
		account2.setAccountType("Current");
		account2.setBalance(300000);
		accountRepository.save(account1);
		accountRepository.save(account2);
	}
	@Test
	@Order(2)
	void test_getAccounts() {
		List<Account> accounts=(List<Account>) accountRepository.findAll();
		for(Account temp:accounts)
		{
			System.out.println(temp);
		}
		
	}
	@Test
	@Order(3)
	void test_getAccount() {
		Optional<Account> accounts = accountRepository.findById(2);
		Account account = accounts.get();
		System.out.println(account);
	}
	@Test
	@Order(4)
	void test_updateAccount() {
		Optional<Account> accounts = accountRepository.findById(1);
		Account account = accounts.get();
		account.setAccountType("Saving");
		account.setBalance(400000);
		accountRepository.save(account);
	}
	@Test
	@Order(5)
	void test_deleteCustomer() {
		Optional<Account> accounts = accountRepository.findById(1);
		Account account = accounts.get();
		accountRepository.delete(account);
		System.out.println("Deleted account is " + account);
	}
}
