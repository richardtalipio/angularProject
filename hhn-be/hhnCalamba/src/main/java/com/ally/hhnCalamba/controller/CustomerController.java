package com.ally.hhnCalamba.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ally.hhnCalamba.model.Customer;
import com.ally.hhnCalamba.model.Item;
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
	public ResponseEntity<JSONObject> loadCustomerTableData() {
		JSONObject jsonResponse = customerService.findAllPending();
		return new ResponseEntity<JSONObject>(jsonResponse, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/loadCustomersWithParam")
	public ResponseEntity<JSONObject> loadCustomersWithParam(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "customerName") String sort, @RequestParam(defaultValue = "asc") String order,
			@RequestParam(defaultValue = "5") Integer pageSize,  @RequestParam(defaultValue = "") String filter) {
		JSONObject response = customerService.getCustomersWithParam(page, sort, order, pageSize, filter);
		return new ResponseEntity<JSONObject>(response, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<JSONObject> addCustomer(@RequestBody Customer customer) {
		JSONObject response = customerService.save(customer);
		return new ResponseEntity<JSONObject>(response, new HttpHeaders(), HttpStatus.OK);
		
	}
}
