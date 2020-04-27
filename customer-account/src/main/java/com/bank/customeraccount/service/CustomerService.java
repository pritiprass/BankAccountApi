package com.bank.customeraccount.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.customeraccount.entity.Customer;
import com.bank.customeraccount.exception.CustomerNotFound;
import com.bank.customeraccount.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Service
@JsonIgnoreProperties(value = { "accounts" })
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	public List<Customer> getCustomers() {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		for (Customer tempCustomer : customers) {
			tempCustomer.setAccounts(null);
		}
		return customers;
	}
	public Customer getCustomer(int id) {
		Optional<Customer> customers = customerRepository.findById(id);
		Customer tempCustomer = null;
		if (customers.isPresent()) {
			tempCustomer = customers.get();
		}
		else {
			throw new CustomerNotFound("Customer with id " + id + " does not exist");
		}
		return tempCustomer;
	}
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	public Customer updateCustomer(int id, Customer customerNew) {
		Optional<Customer> customers = customerRepository.findById(id);
		Customer tempCustomer = null;
		if (customers.isPresent()) {
			tempCustomer = customers.get();
		}
		else {
			throw new CustomerNotFound("Customer with id " + id + " does not exist");
		}
		tempCustomer.setFirstName(customerNew.getFirstName());
		tempCustomer.setLastName(customerNew.getLastName());
		tempCustomer.setEmail(customerNew.getEmail());
		return customerRepository.save(tempCustomer);
	}
	public Customer deleteCustomer(int id) {
		Optional<Customer> customers = customerRepository.findById(id);
		Customer tempCustomer = null;
		if (customers.isPresent()) {
			tempCustomer = customers.get();
		}
		else {
			throw new CustomerNotFound("Customer with id " + id + " does not exist");
		}
		customerRepository.delete(tempCustomer);
		return tempCustomer;
	}
}
