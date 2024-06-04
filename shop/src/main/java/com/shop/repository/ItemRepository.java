package com.shop.repository;


import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>{

    Item findByItemNm(String itemNm);

    Item findByItemDetail(String str);

    Item findByItemNmAndItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThan(int price);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    @Query("select i from Item i " +
            "where i.itemDetail like %:itemDetail% " +
            "order by i.price desc")
    List<Item> findByItemDetailParam(@Param("itemDetail") String detail);

    @Query("select i from Item i where i.itemNm like %:itemNm% AND i.itemDetail like %:itemDetail%")
    List<Item> findByItemNmParam(@Param("itemNm") String itemNm, @Param("itemDetail") String detail);

    @Query(value = "select * from item where item_detail like %:itemDetail% order by price desc" , nativeQuery = true)
    List<Item> findByItemDetailParamNative(@Param("itemDetail") String detail);
}
