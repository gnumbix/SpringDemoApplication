package com.example.demoproject.service;

import com.example.demoproject.domain.Item;
import com.example.demoproject.model.ItemForm;
import com.example.demoproject.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(Integer itemId) {
        return this.itemRepository.findById(itemId);
    }

    @Override
    @Transactional
    public Item add(ItemForm form) {
        Item item = new Item();
        item.setName(form.getName());
        item.setStatus(form.getStatus());
        item.setProductId(form.getProductId());
        item.setContent(form.getContent());
        return this.itemRepository.save(item);
    }

    @Override
    @Transactional
    public Item update(ItemForm form) {
        Optional<Item> itemOpt = this.findById(form.getId());
        if(itemOpt.isEmpty()) {
            return null;
        }
        Item item = itemOpt.get();
        item.setName(form.getName());
        item.setStatus(form.getStatus());
        item.setProductId(form.getProductId());
        item.setContent(form.getContent());
        return item;
    }
}
