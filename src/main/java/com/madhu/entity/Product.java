package com.madhu.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String productName;
    private Integer boughtPrice;
    private Integer sellPrice;
    private String imageUrl;
    private String description;

    @ToString.Exclude
    @ManyToOne
    private User user;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<SaleRecord> saleRecords = new ArrayList<>();


}
