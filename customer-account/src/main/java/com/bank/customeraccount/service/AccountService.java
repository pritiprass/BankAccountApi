package com.bank.customeraccount.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.customeraccount.entity.Account;
import com.bank.customeraccount.repository.AccountRepository;
@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	public List<Account> getAccounts() {
		List<Account> accounts = (List<Account>) accountRepository.findAll();
		return accounts;
	}
	public Account getAccount(int id) {
		Optional<Account> accounts = accountRepository.findById(id);
		Account tempAccount = null;
		if (accounts.isPresent()) {
			tempAccount = accounts.get();
		}
		else {
			throw new RuntimeException("Account with id " + id + " does not exist");
		}
		return tempAccount;
	}
	public Account addAccount(Account account) {
		return accountRepository.save(account);
	}
	public Account updateAccount(int id, Account accountNew) {
		Optional<Account> accounts = accountRepository.findById(id);
		Account tempAccount = null;
		if (accounts.isPresent()) {
			tempAccount = accounts.get();
		}
		else {
			throw new RuntimeException("Account with id " + id + " does not exist");
		}
		tempAccount.setAccountType(accountNew.getAccountType());
		tempAccount.setBalance(accountNew.getBalance());
		return accountRepository.save(tempAccount);
	}
	public Account deleteAccount(int id) {
		Optional<Account> accounts = accountRepository.findById(id);
		Account tempAccount = null;
		if (accounts.isPresent()) {
			tempAccount = accounts.get();
		} else {
			throw new RuntimeException("Account with id " + id + " does not exist");
		}
		accountRepository.delete(tempAccount);
		return tempAccount;
	}
}
