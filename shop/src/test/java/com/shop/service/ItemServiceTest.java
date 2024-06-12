package com.shop.service;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles()throws Exception {
        List<MultipartFile> multipartFiles = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            String path = "C:/shop/item";

            String imageName = "image" + i + ".jpg";

            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpeg", new byte[]{1, 2, 3, 4});
            multipartFiles.add(multipartFile);
        }
        return multipartFiles;
    }
    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveItem()throws Exception{
        ItemFormDto itemFormDto = ItemFormDto.builder()
                .itemNm("테스트 성공")
                .itemSellStatus(ItemSellStatus.SELL)
                .itemDetail("테스트 상품 입니다")
                .price(1000)
                .stockNumber(100)
                .build();

        List<MultipartFile>multipartFiles = createMultipartFiles();

        Long itemId = itemService.saveItem(itemFormDto, multipartFiles);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdDesc(itemId);

        Item item = itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException());

        assertEquals(itemFormDto.getItemNm(), item.getItemNm());

    }



}