package com.bank.customeraccount.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.customeraccount.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
