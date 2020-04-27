package com.bank.customeraccount.controller;
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

import com.bank.customeraccount.entity.Customer;
import com.bank.customeraccount.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer tempCustomer = customerService.getCustomer(id);
		return tempCustomer;
	}
	@PostMapping("/customers")
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerService.addCustomer(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		Customer tempCustomer = customerService.updateCustomer(id, customer);
		return tempCustomer;
	}
	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable int id) {
		Customer tempCustomer = customerService.deleteCustomer(id);
	}
}
