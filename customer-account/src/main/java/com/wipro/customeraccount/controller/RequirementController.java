package com.wipro.customeraccount.controller;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.wipro.customeraccount.entity.Account;
import com.wipro.customeraccount.entity.Customer;
import com.wipro.customeraccount.entity.Transfer;
import com.wipro.customeraccount.service.CustomerService;
import com.wipro.customeraccount.service.RequirementService;
@RestController
public class RequirementController {
	@Autowired
	private RequirementService requirementService;
	@GetMapping("/customers/accounts")
	public List<Customer> getCustomersAccounts() {
		return requirementService.getCustomersAccounts();
	}
	@PostMapping("/customers/{id}/accounts")
	public Customer createNewCustomerAccount(@PathVariable int id, @RequestBody Set<Account> accounts) {
		return requirementService.createNewCustomerAccount(id, accounts);
	}
	@GetMapping("/customers/{id}/accounts")
	public Customer getCustomerAccount(@PathVariable int id) {
		return requirementService.getCustomerAccount(id);
	}
	@PutMapping("/accounts/transferFund")
	public void fundTransfer(@RequestBody Transfer transfer) {
		requirementService.fundTransfer(transfer);
	}
}
