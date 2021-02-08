package com.ally.hhnCalamba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ally.hhnCalamba.model.Customer;
import com.ally.hhnCalamba.model.Item;

@Repository 
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("SELECT C FROM Customer C WHERE C.customerName LIKE %:customerName% ")
	Page<Item> findByItemName(@Param("customerName") String customerName, Pageable pageable);
	
}
