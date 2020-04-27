package com.bank.customeraccount;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.customeraccount.entity.Account;
import com.bank.customeraccount.entity.Customer;
import com.bank.customeraccount.entity.Transfer;
import com.bank.customeraccount.repository.AccountRepository;
import com.bank.customeraccount.repository.CustomerRepository;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RequirementTest {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Test
	@Order(1)
	void test_createCustomerWithAccount() {
		Customer customer1 = new Customer();
		customer1.setFirstName("Mohan");
		customer1.setLastName("Krishna");
		customer1.setEmail("mohan@gmail.com");
		Customer customer2 = new Customer();
		customer2.setFirstName("Nikhil");
		customer2.setLastName("Vikash");
		customer2.setEmail("nikhil@gmail.com");
		Account account1 = new Account();
		account1.setAccountType("Saving");
		account1.setBalance(500000);
		Account account2 = new Account();
		account2.setAccountType("Current");
		account2.setBalance(600000);
		Set<Account> accounts = new HashSet<>();
		accounts.add(account1);
		accounts.add(account2);
		customer1.setAccounts(accounts);
		customer2.setAccounts(accounts);
		Set<Customer> customers = new HashSet<>();
		customers.add(customer1);
		customers.add(customer2);
		account1.setCustomers(customers);
		account2.setCustomers(customers);
		customerRepository.save(customer1);
		customerRepository.save(customer2);
	}
	@Test
	@Order(2)
	@Transactional
	void test_getAllCustomersAccounts() {
		Iterable<Customer> customers = customerRepository.findAll();
		Iterator<Customer> customer = customers.iterator();
		while (customer.hasNext()) {
			System.out.println("Customers details");
			Customer temp = customer.next();
			System.out.println(temp);
			Set<Account> accounts = temp.getAccounts();
			for (Account tempAcc : accounts) {
				System.out.println("Account Details");
				System.out.println(tempAcc);
			}
		}
	}
	@Test
	@Order(3)
	@Transactional
	void test_getSingleCustomerAccount() {
		Optional<Customer> customers = customerRepository.findById(1);
		Customer customer = customers.get();
		System.out.println("Customer detail " + customer);
		System.out.println(customer.getAccounts());
	}
	@Test
	@Order(4)
	@Transactional
	void test_transferFund() {
		Transfer transfer = new Transfer();
		transfer.setFromAccount(1);
		transfer.setToAccount(2);
		transfer.setAmount(100000);
		Optional<Account> accounts = accountRepository.findById(transfer.getFromAccount());
		Account account1 = accounts.get();
		Optional<Account> accountsNew = accountRepository.findById(transfer.getToAccount());
		Account account2 = accountsNew.get();
		account1.setBalance(account1.getBalance() - transfer.getAmount());
		account2.setBalance(account2.getBalance() + transfer.getAmount());
		System.out.println("Balance after fund transfer: ");
		System.out.println(account1);
		System.out.println(account2);
	}
}
