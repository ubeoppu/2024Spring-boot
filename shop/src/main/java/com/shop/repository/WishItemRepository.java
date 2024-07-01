package com.shop.repository;

import com.shop.entity.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishItemRepository extends JpaRepository<WishItem, Long> {
}
