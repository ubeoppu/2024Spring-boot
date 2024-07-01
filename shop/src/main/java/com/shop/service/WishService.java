package com.shop.service;

import com.shop.repository.MemberRepository;
import com.shop.repository.WishItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class WishService {

    private final WishItemRepository wishItemRepository;
    private final MemberRepository memberRepository;

    public

}
