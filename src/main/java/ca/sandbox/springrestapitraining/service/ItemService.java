package ca.sandbox.springrestapitraining.service;

import java.util.Optional;

import ca.sandbox.springrestapitraining.model.Item;

public interface ItemService {
	
	public Iterable<Item> showItems();
	
	public Item addItem(Item item);
	
	public Optional<Item> findItem(Long itemID);
	
	public void removeItem(Long itemID);

}
