package ca.sandbox.springrestapitraining.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sandbox.springrestapitraining.model.Item;
import ca.sandbox.springrestapitraining.repository.ItemRepository;
import ca.sandbox.springrestapitraining.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemRepository repository;
	
	public ItemServiceImpl() {
        super();
    }

	@Override
	public Iterable<Item> showItems() {
		return repository.findAll();
	}

	@Override
	public Item addItem(Item item) {
		return repository.save(item);
	}

	@Override
	public Optional<Item> findItem(Long itemID) {
		return repository.findById(itemID);
	}

	@Override
	public void removeItem(Long itemID) {
		repository.deleteById(itemID);
	}

}
