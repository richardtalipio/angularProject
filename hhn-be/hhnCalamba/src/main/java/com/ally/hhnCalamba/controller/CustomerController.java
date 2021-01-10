package com.ally.hhnCalamba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ally.hhnCalamba.model.Customer;
import com.ally.hhnCalamba.sevice.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/loadCustomers")
	public ResponseEntity<List<Customer>> loadCustomers() {
		List<Customer> customerList = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customerList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/loadCustomerTableData")
	public ResponseEntity<List<Customer>> loadCustomerTableData() {
		List<Customer> customerList = customerService.findAllPending();
		return new ResponseEntity<List<Customer>>(customerList, new HttpHeaders(), HttpStatus.OK);
	}
}
