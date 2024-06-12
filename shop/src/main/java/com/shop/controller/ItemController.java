package com.shop.controller;

import com.shop.dto.ItemFormDto;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/admin/item/new")
    public String itemForm(Model model){
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemImgDtoList(null); // 리스트를 null로 설정
         model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult  ,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){
        log.info("=========================");
        log.info("itemFormDto: " + itemFormDto);
        log.info("itemImgFileList: " + itemImgFileList.size());
        itemImgFileList.forEach(file -> log.info(file.toString()));
        log.info("=========================");
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫 번째 상품 이미지는 필수 입력 값 입니다." );
            return "item/itemForm";
        }

        try{
            itemService.saveItem(itemFormDto, itemImgFileList);
        }catch(Exception e){
            log.info("에러 오류 : "+e.getMessage());
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    @GetMapping(value="/admin/item/{itemId}")
    public String itemDtl(@PathVariable Long itemId, Model model){

        try{
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);

        }catch(Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            log.info("무슨 오류일까요"+e.getMessage());
            return "item/itemForm";
        }
        return "item/itemForm";
    }
}
