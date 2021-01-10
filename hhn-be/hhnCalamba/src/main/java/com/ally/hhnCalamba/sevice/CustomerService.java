package com.ally.hhnCalamba.sevice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ally.hhnCalamba.model.Customer;
import com.ally.hhnCalamba.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers(){
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}
	
	public List<Customer> findAllPending(){
		List<Customer> customerList = customerRepository.findAll();
		List<Customer> customerWithNoPending = customerList.stream()
				  .filter(o -> o.hasNoPending())
				  .collect(Collectors.toList());
		List<Customer> customerWithPending = customerList.stream()
				  .filter(o -> o.containsPending())
				  .collect(Collectors.toList());
		
		customerList.clear();
		customerList.addAll(customerWithPending);
		customerList.addAll(customerWithNoPending);
		return customerList;
	}
	
	
}
