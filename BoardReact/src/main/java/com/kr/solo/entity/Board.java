package com.kr.solo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Board")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity{

    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_email")
    private Member member; //작성자..

    private String title;

    @Lob
    private String content;

}
