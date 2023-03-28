package ca.sandbox.springrestapitraining.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.sandbox.springrestapitraining.model.Item;
import ca.sandbox.springrestapitraining.service.ItemService;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@MockBean
	private ItemService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void GetAllItems_SomeItemsArePresent_Expected200() throws Exception {

		Iterable<Item> items = getDefaultItemsList();

		// When
		when(service.showItems())
			.thenReturn(items);

		// Then
		mockMvc.perform(get("/items"))
			.andExpect(status().isOk());

		// Verify
		verify(service, times(1))
			.showItems();
	}

	@Test
	public void GetItemById_ItemIsPresent_Expected200() throws Exception {

		Item item = getDefaultItem();

		// When
		when(service.findItem(item.getId()))
			.thenReturn(Optional.of(item));

		// Then
		mockMvc.perform(get("/items/" + item.getId()))
			.andExpect(status().isOk());

		// Verify
		verify(service, times(1))
			.findItem(item.getId());
	}

	@Test
	public void GetItemById_ItemIsNotPresent_Expected404() throws Exception {

		long givenId = 678l;

		// When
		when(service.findItem(givenId))
			.thenReturn(Optional.empty());

		// Then
		mockMvc.perform(get("/items/" + givenId))
			.andExpect(status().isNotFound());

		// Verify
		verify(service, times(1))
			.findItem(givenId);
	}

	@Test
	public void DeleteItemById_ItemIsPresent_Expected200() throws Exception {

		Item item = getDefaultItem();

		// When
		doNothing().when(service).removeItem(item.getId());

		// Then
		mockMvc.perform(delete("/items/" + item.getId()))
			.andExpect(status().isOk());

		// Verify
		verify(service, times(1))
			.findItem(item.getId());
		verify(service, times(1))
			.removeItem(item.getId());
	}

	@Test
	public void DeleteItemById_ItemIsNotPresent_Expected404() throws Exception {

		long givenId = 678l;

		// When
		when(service.findItem(givenId))
			.thenReturn(Optional.empty());
		//doNothing().when(service).removeItem(givenId);

		// Then
		mockMvc.perform(delete("/items/" + givenId))
			.andExpect(status().isNotFound());

		// Verify
		verify(service, times(1))
			.findItem(givenId);
		verify(service, times(0))
			.removeItem(givenId);
	}

	public List<Item> getDefaultItemsList() {
		Item item1 = new Item(1l, "Egg", 6);
		Item item2 = new Item(2l, "Bacon", 3);
		Item item3 = new Item(3l, "Pepper", 1);

		return Arrays.asList(item1, item2, item3);
	}

	public Item getDefaultItem() {
		return new Item(1l, "Egg", 6);
	}

}
