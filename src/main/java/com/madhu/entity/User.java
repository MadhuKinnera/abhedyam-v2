package com.madhu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private String fullName;
    private String phoneNumber;
    private String qrImageUrl;
    private String profileImageUrl;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Customer> customers = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Product> products = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Village> villages = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CustomerRequest> customerRequests = new ArrayList<>();

}
