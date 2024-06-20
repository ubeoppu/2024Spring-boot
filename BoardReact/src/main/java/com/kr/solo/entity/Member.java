package com.kr.solo.entity;

import com.kr.solo.constant.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id
    @Column(name="member_email")
    private String email;

    private String password;

    private String name;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
}
