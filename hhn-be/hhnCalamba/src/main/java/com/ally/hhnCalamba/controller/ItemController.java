package com.ally.hhnCalamba.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.JSONObject;
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

	@GetMapping("/loadItemswithParam")
	public ResponseEntity<JSONObject> loadItemswithParam(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "itemName") String sort, @RequestParam(defaultValue = "asc") String order,
			@RequestParam(defaultValue = "5") Integer pageSize,  @RequestParam(defaultValue = "") String filter) {
		JSONObject response = itemService.getItemsWithParam(page, sort, order, pageSize, filter);
		return new ResponseEntity<JSONObject>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/loadItems")
	public ResponseEntity<List<Item>> loadItems() {
		List<Item> itemList = itemService.getAllItems();
		return new ResponseEntity<List<Item>>(itemList, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/addItem")
	public ResponseEntity<JSONObject> addItem(@RequestBody Item item) {
		itemService.save(item);
		return loadItemswithParam(0, "itemName", "asc", 5, "");
	}

	@PutMapping("/updateItem/{id}")
	public ResponseEntity<JSONObject> update(@RequestBody Item item, @PathVariable Integer id) {
		try {
			item.setItemId(id);
			itemService.save(item);
			return loadItemswithParam(0, "itemName", "asc", 5, "");
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteItem/{id}")
	public ResponseEntity<JSONObject> delete(@PathVariable Integer id) {
		try {
			itemService.delete(id);
			return loadItemswithParam(0, "itemName", "asc", 5, "");
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
