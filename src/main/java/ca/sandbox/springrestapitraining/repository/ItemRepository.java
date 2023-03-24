package ca.sandbox.springrestapitraining.repository;

import org.springframework.data.repository.CrudRepository;

import ca.sandbox.springrestapitraining.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
