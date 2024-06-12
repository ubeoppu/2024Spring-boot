package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Log4j2
public class ItemImgDto {

    private long id;

    private String imgName; //이미지 파일명
    private String oriImgName;  //원본 이미지 파일명
    private String oriUrl;  //이미지 조회 경로

    private String repImgYn;    //대표 이미지(이미지가 여러 장일 때, 메인페이지에서 보이는 이미지)

    private static ModelMapper modelMapper = new ModelMapper();

    //ItemImg 엔티티를 받아서 ItemImgDto 변환
    public static ItemImgDto of(ItemImg itemImg){
        log.info("=-------------------------------------------------------emfdjkdjfklsdjklfsdf-----------------------");
        log.info("itemImg : " + itemImg);
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
