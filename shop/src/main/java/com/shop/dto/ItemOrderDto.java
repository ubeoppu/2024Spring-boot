package com.shop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemOrderDto {

    private Long id;

    private String itemNm;

    private Integer price;

    private String imgUrl;

    private int count;
}
