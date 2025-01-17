package com.madhu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Transaction extends BaseEntity {

    @Id
    @UuidGenerator
    private String id;
    private BigDecimal amount;
    private String description;
    private String modeOfPayment;
    private String referenceImage;

    @ToString.Exclude
    @ManyToOne
    private SaleRecord saleRecord;


}
