package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("과자");
        item.setPrice(1000);
        item.setStoackNumber(100);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setItemDetail("싼마이");
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item saveditem = itemRepository.save(item);

        log.info(saveditem);
    }

    @Test
    public void findItemTest(){
        Optional<Item> byId = itemRepository.findById(5L);

        log.info(byId.get());
    }

    @Test
    public void findItemNm(){
        Item item = itemRepository.findByItemNm("라면");

        log.info("값:" + item);
    }

    @Test
    @DisplayName("디테일 검색")
    public void findByItemDetailTest(){
        Item item = itemRepository.findByItemDetail("싼마이");

        log.info(item);
    }

    @Test
    public void findByDoubleTest(){
        Item item = itemRepository.findByItemNmAndItemDetail("라면", "싼마이");
        log.info(item);
    }

    @Test
    public void findByPriceLessThanTest(){
        List<Item> list = itemRepository.findByPriceLessThan(100);

        list.forEach(result -> log.info(result));
    }

    @Test
    public void findByPriceLessThanOrderByPriceDesc(){
        List<Item> list = itemRepository.findByPriceLessThanOrderByPriceDesc(3000);

        list.forEach(result -> log.info(result));

    }
    
    @Test
    public void findByPriceLessThanOrderByPriceAsc(){
        List<Item> list = itemRepository.findByItemDetailParam("싼마이");

        list.forEach(result -> log.info(result));
    }
    @Test
    public void findByDetailTest(){
        List<Item> list = itemRepository.findByItemDetailParamNative("라면");

        list.forEach(result -> log.info(result));
    }

}
