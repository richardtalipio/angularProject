package com.ally.hhnCalamba.sevice;

import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ally.hhnCalamba.model.Customer;
import com.ally.hhnCalamba.model.Item;
import com.ally.hhnCalamba.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers(){
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}
	
	public JSONObject findAllPending(){
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
		JSONObject json = new JSONObject();
		json.put("customerList", customerList);
		json.put("customerCount", customerList.size());
		return json;
		
	}
	
	public JSONObject getCustomersWithParam(Integer pageNumber, String sortColumn, String order, Integer pageSize, String filter) {

		Sort sort;
		if (order.equalsIgnoreCase("ASC")) {
			sort = Sort.by(sortColumn).ascending();
		} else {
			sort = Sort.by(sortColumn).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		List<Customer> customerList = customerRepository.findByCustomerName(filter, pageable).getContent();
		int customerCount;
		if(filter.isBlank() || filter.isEmpty()) {
			customerCount = getAllCustomers().size();
		}else {
			customerCount = customerRepository.findByCustomerName(filter).size();
		}
		JSONObject json = new JSONObject();
		json.put("customerList", customerList);
		json.put("customerCount", customerCount);
		return json;
	}
	
	public JSONObject save(Customer customer) {
		Integer customerId = customerRepository.save(customer).getCustomerId();
		JSONObject json = new JSONObject();
		json.put("customerId", customerId);
		return json;
	
	}
	
	
}
