package com.ally.hhnCalamba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ally.hhnCalamba.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
