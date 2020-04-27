package com.wipro.customeraccount;
import java.util.Iterator;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.wipro.customeraccount.entity.Customer;
import com.wipro.customeraccount.repository.CustomerRepository;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerTest {
	@Autowired
	CustomerRepository customerRepository;
	@Test
	@Order(1)
	void test_createCustomers() {
		System.out.println("Creating all customers");
		Customer customer1 = new Customer();
		customer1.setFirstName("Akash");
		customer1.setLastName("Raj");
		customer1.setEmail("akash@wipro.com");
		Customer customer2 = new Customer();
		customer2.setFirstName("Pemi");
		customer2.setLastName("Kalyan");
		customer2.setEmail("pemi@wipro.com");
		customerRepository.save(customer1);
		customerRepository.save(customer2);
	}
	@Test
	@Order(2)
	void test_getCustomers() {
		System.out.println("Printing all customers");
		Iterable<Customer> customers = customerRepository.findAll();
		Iterator<Customer> customer = customers.iterator();
		while (customer.hasNext()) {
			Customer temp = customer.next();
			System.out.println(temp);
		}
	}
	@Test
	@Order(3)
	void test_getCustomer() {
		System.out.println("Printing a single customer");
		System.out.println(customerRepository.findById(1));
	}
	@Test
	@Order(4)
	void test_updateCustomer() {
		Optional<Customer> customers = customerRepository.findById(1);
		Customer customer = customers.get();
		customer.setFirstName("Deepika");
		customer.setLastName("Puhan");
		customer.setEmail("deepika@wipro.com");
		customerRepository.save(customer);
		System.out.println("Updated customer is " + customer);
	}
	@Test
	@Order(5)
	void test_deleteCustomer() {
		Optional<Customer> customers = customerRepository.findById(2);
		Customer customer = customers.get();
		customerRepository.delete(customer);
		System.out.println("Deleted customer is " + customer);
	}
}
