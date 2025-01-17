package com.madhu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Data
public class Address {

    @Id
    @UuidGenerator
    private String id;
    private String street;
    private String landmark;
    private String description;

    @ToString.Exclude
    @OneToOne
    private Customer customer;

    @ToString.Exclude
    @ManyToOne
    private Village village;


}
