package com.example.demoproject.repository;

import com.example.demoproject.domain.Item;
import com.example.demoproject.domain.enums.ItemStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByStatusAndProductId(ItemStatus status, Integer productId, Pageable pageable);

}
