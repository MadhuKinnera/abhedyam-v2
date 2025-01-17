package com.madhu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @UuidGenerator
    private String id;
    private String customerName;
    private String profileImageUrl;
    private Integer age;
    private String profession;
    private String mobileNo;
    private String email;
    private String description;
    private String customerCode;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Address address = new Address();

    @ToString.Exclude
    @ManyToOne
    private User user;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<SaleRecord> saleRecords = new ArrayList<>();


}
