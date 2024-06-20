package com.kr.solo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="reply")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity{

    @Id
    @Column(name="reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name ="member_email")
    private Member member;
}
