package com.wipro.customeraccount.repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.customeraccount.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
