package com.dustin.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Audit {
    private static final long serialVersionUID = -3869225360246516120L;
    @CreatedBy
    @Column(
            name = "created_by",
            length = 15,
            nullable = true
    )
    private String createdBy;
    @CreatedDate
    @Column(
            name = "created_on",
            nullable = false
    )
    private Timestamp createdOn;
    @LastModifiedBy
    @Column(
            name = "modified_by",
            length = 15,
            nullable = true
    )
    private String modifiedBy;
    @LastModifiedDate
    @Column(
            name = "modified_on",
            nullable = false
    )
    private Timestamp modifiedOn;
}
