package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address; // 우편번호

    private String streetaddress; // 지번 주소

    private String detailaddress;  // 상세 주소

    @Enumerated(EnumType.STRING) //열거형 타입을 뜻함, 상수들의 지합
    //Ex:Role { 'Admin', 'Member'}
    private Role role;

    // 이거 대신 Mapper란 것 이용할 수 있음.
    public static  Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        return Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .role(Role.ADMIN)
                .build();
    }

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = Role.USER;

    }
    public void updatePassword(String encodePw) {
        this.password = encodePw;
    }


}
