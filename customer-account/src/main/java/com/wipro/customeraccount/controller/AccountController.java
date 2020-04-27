package com.wipro.customeraccount.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wipro.customeraccount.entity.Account;
import com.wipro.customeraccount.repository.AccountRepository;
import com.wipro.customeraccount.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@GetMapping("/accounts/{id}")
	public Account getAccount(@PathVariable int id) {
		Account tempAccount = accountService.getAccount(id);
		return tempAccount;
	}

	@PostMapping("/accounts")
	public ResponseEntity<Void> addAccount(@RequestBody Account account) {
		Account savedAccount = accountService.addAccount(account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAccount.getAccountNumber()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/accounts/{id}")
	public Account updateAccount(@PathVariable int id, @RequestBody Account account) {
		Account tempAccount = accountService.updateAccount(id, account);
		return tempAccount;
	}

	@DeleteMapping("/accounts/{id}")
	public void deleteAccount(@PathVariable int id) {
		Account tempAccount = accountService.deleteAccount(id);
	}
}
