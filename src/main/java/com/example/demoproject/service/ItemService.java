package com.example.demoproject.service;

import com.example.demoproject.domain.Item;
import com.example.demoproject.model.ItemForm;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAll();

    Optional<Item> findById(Integer itemId);

    Item add(ItemForm form);

    Item update(ItemForm form);

}
