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

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public List<Item> getAllItems() {
		List<Item> itemList = (List<Item>) itemRepository.findAll();
		return itemList;
	}

	public List<Item> getItemsWithParam(Integer pageNumber, Integer pageSize, String order) {

		Sort sort;
		if (order.equalsIgnoreCase("ASC")) {
			sort = Sort.by("itemName").ascending();
		} else {
			sort = Sort.by("itemName").descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		List<Item> itemList = itemRepository.findAll(pageable).getContent();
		return itemList;
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
