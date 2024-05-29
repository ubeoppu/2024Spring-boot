package com.membertest.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity(name="member") //테이블명 지정 기입 안했을 시 클래스명 == 테이블명
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id") // 컬럼명 지정
    private Long id;

    private String name;

    private int age;

    private String phone;

    private String address;

    //Ctrl + Shift + z (redo)
    //Ctrl + d 줄 복사
    //Ctrl + y (줄삭제 + redo)

}
