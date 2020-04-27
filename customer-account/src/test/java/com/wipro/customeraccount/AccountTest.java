package com.wipro.customeraccount;
import java.util.Iterator;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.wipro.customeraccount.entity.Account;
import com.wipro.customeraccount.repository.AccountRepository;
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
		Iterable<Account> accounts = accountRepository.findAll();
		Iterator<Account> account = accounts.iterator();
		while (account.hasNext()) {
			System.out.println(account.next());
		}
	}
	@Test
	@Order(3)
	void test_getAccount() {
		Optional<Account> accounts = accountRepository.findById(1);
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
