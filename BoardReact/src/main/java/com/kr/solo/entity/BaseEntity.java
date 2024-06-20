package com.kr.solo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(value ={AuditingEntityListener.class})
@MappedSuperclass
@Getter@Setter
public class BaseEntity extends BaseTimeEntity{
    
    @CreatedBy
    @Column(updatable = false)//수정 불가..
    private String createdBy;//생성한 사람
    
    @LastModifiedBy
    private String ModifiedBy;//마지막으로 수정한 사람.
}
