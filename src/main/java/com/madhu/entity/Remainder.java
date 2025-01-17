package com.madhu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;


@Entity
@Data
public class Remainder {

    @Id
    @UuidGenerator
    private String id;
    private LocalDateTime timestamp;
    private String subject;
    private String message;

    @ToString.Exclude
    @ManyToOne
    private SaleRecord saleRecord;

}
