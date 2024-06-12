package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList)throws  Exception {
        //상품 등록
        log.info("테스트 01번");
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록9*
        for(int i = 0; i < itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            log.info("테스트 0번");

            if(i == 0){
                itemImg.setRepimgYn("Y");//첫 번째 사진이 대표이미지
                log.info("테스트1번");
            }else{
                itemImg.setRepimgYn("N");
                itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
                log.info("테스트 2번");
            }
        }
        return item.getId();
    }

    public ItemFormDto getItemDtl(Long itemId) throws Exception{

        List<ItemImg>itemImgList = itemImgRepository.findByItemIdOrderByIdDesc(itemId);

        List<ItemImgDto> itemImgDtoList = new ArrayList<>();

        for(ItemImg itemImg : itemImgList){
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Optional<Item> result = itemRepository.findById(itemId);
        Item item = result.orElseThrow(() -> new EntityNotFoundException());

        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);

        return itemFormDto;

    }


}
