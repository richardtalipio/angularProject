package com.ally.hhnCalamba.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ally.hhnCalamba.model.Item;
import com.ally.hhnCalamba.sevice.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/loadItemsInPage")
	public ResponseEntity<List<Item>> loadItemsInPage(
			 @RequestParam(defaultValue = "0") Integer pageNumber, 
             @RequestParam(defaultValue = "10") Integer pageSize,
             @RequestParam(defaultValue = "asc") String order) {
		List<Item> itemList = itemService.getItemsWithParam(pageNumber, pageSize, order);
	    return new ResponseEntity<List<Item>>(itemList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/loadItems")
	public ResponseEntity<List<Item>> loadItems() {
		List<Item> itemList = itemService.getAllItems();
		return new ResponseEntity<List<Item>>(itemList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addItem")
	public void addItem(@RequestBody Item item) {
		itemService.save(item);
	}
	
	@PutMapping("/updateItem/{id}")
	public ResponseEntity<?> update(@RequestBody Item item, @PathVariable Integer id) {
	    try {
	        item.setItemId(id);  
	        itemService.save(item);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/deleteItem/{id}")
	public void delete(@PathVariable Integer id) {
		itemService.delete(id);
	}
	
}
