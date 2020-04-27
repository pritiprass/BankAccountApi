package com.bank.customeraccount.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.customeraccount.entity.Account;
import com.bank.customeraccount.entity.Customer;
import com.bank.customeraccount.entity.Transfer;
import com.bank.customeraccount.exception.CustomerNotFound;
import com.bank.customeraccount.repository.AccountRepository;
import com.bank.customeraccount.repository.CustomerRepository;
@Service
public class RequirementService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	public List<Customer> getCustomersAccounts() {
		return (List<Customer>) customerRepository.findAll();
	}
	public Customer createNewCustomerAccount(int id, Set<Account> accounts) {
		Optional<Customer> customers = customerRepository.findById(id);
		Customer tempCustomer = null;
		if (customers.isPresent()) {
			tempCustomer = customers.get();
		} else {
			throw new CustomerNotFound("Customer does not exist");
		}
		tempCustomer.setAccounts(accounts);
		return customerRepository.save(tempCustomer);
	}
	public Customer getCustomerAccount(int id) {
		Optional<Customer> customers = customerRepository.findById(id);
		Customer tempCustomer = null;
		if (customers.isPresent()) {
			tempCustomer = customers.get();
		}
		else {
			throw new RuntimeException("Customer with id " + id + " does not exist");
		}
		return tempCustomer;
	}
	@Transactional
	public void fundTransfer(Transfer transfer) {
		int fromAccountNumber = transfer.getFromAccount();
		int toAccountNumber = transfer.getToAccount();
		double theAmount = transfer.getAmount();
		Optional<Account> fromAccounts = accountRepository.findById(fromAccountNumber);
		Account tempFromAccount = null;
		if (fromAccounts.isPresent()) {
			tempFromAccount = fromAccounts.get();
		}
		else {
			throw new RuntimeException("Account with id " + fromAccountNumber + " does not exist");
		}
		Optional<Account> toAccounts = accountRepository.findById(toAccountNumber);
		Account tempToAccount = null;
		if (toAccounts.isPresent()) {
			tempToAccount = toAccounts.get();
		} else {
			throw new RuntimeException("Account with id " + toAccountNumber + " does not exist");
		}
		if (tempFromAccount.getBalance() > theAmount) {
			tempFromAccount.setBalance(tempFromAccount.getBalance() - theAmount);
			tempToAccount.setBalance(tempToAccount.getBalance() + theAmount);
		}
		else {
			throw new RuntimeException("Insufficient fund");
		}
		accountRepository.save(tempFromAccount);
		accountRepository.save(tempToAccount);
	}
}
