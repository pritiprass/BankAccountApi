package com.wipro.customeraccount.repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.customeraccount.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
