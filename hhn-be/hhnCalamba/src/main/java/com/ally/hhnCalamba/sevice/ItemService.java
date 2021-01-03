package com.ally.hhnCalamba.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ally.hhnCalamba.model.Item;
import com.ally.hhnCalamba.repository.ItemRepository;
import org.json.simple.JSONObject;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public List<Item> getAllItems() {
		List<Item> itemList = (List<Item>) itemRepository.findAll();
		return itemList;
	}

	public JSONObject getItemsWithParam(Integer pageNumber, String sortColumn, String order, Integer pageSize, String filter) {

		Sort sort;
		if (order.equalsIgnoreCase("ASC")) {
			sort = Sort.by(sortColumn).ascending();
		} else {
			sort = Sort.by(sortColumn).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		List<Item> itemList = itemRepository.findByItemName(filter, pageable).getContent();
		int itemCount;
		if(filter.isBlank() || filter.isEmpty()) {
			itemCount = getAllItems().size();
		}else {
			itemCount = itemRepository.findByItemName(filter).size();
		}
		
		JSONObject json = new JSONObject();
		json.put("itemList", itemList);
		json.put("itemCount", itemCount);
		return json;
	}

	public void save(Item item) {
		itemRepository.save(item);
	}

	public Item get(Integer id) {
		return itemRepository.findById(id).get();
	}

	public void delete(Integer id) {
		itemRepository.deleteById(id);
	}
}
