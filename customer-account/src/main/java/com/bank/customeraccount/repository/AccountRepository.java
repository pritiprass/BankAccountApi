package com.bank.customeraccount.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.customeraccount.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
