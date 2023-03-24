package ca.sandbox.springrestapitraining.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.sandbox.springrestapitraining.model.Item;
import ca.sandbox.springrestapitraining.service.ItemService;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

	@Autowired
	private ItemService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Item> findAll() {
		return service.showItems();
	}
	
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Item> findOne(@PathVariable Long id) {
		Optional<Item> item = service.findItem(id);
		if(item.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(item.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addItem(item));
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable Long id) {
		service.removeItem(id);
	}

}
