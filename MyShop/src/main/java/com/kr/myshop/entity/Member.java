package com.kr.myshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //테이블, 엔티티 어노테이션
@Table(name = "member") //테이블명 지정
@Getter@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id//기본키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment..
    private Long id;

    private String name;

    @Column(unique = true) //컬럼 unique 설정(중복 허용X)
    private String email;

    private String password;

    private String address;
    //열거형 타입을 뜻함, 상수들의 집합
    //Ex: Role {'Admin', 'Member'}
    @Enumerated(EnumType.STRING)
    private Role role;

}
