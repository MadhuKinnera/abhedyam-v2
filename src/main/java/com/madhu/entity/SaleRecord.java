package com.madhu.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SaleRecord {

    @Id
    @UuidGenerator
    private String id;
    private LocalDateTime timestamp;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;
    private BigDecimal dueAmount;
    private Integer quantity = 1;
    private String description;

    @ManyToOne
    private Occasion occasion;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saleRecord")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saleRecord")
    private List<Remainder> remainders = new ArrayList<>();

}
