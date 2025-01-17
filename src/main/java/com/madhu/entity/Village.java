package com.madhu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Village {


    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String villageName;
    private String imageUrl;
    private String mandal;
    private String district;
    private String state;
    private Integer pincode;
    private Integer productGoal;
    private Integer amountGoal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "village")
    private List<Address> addresses = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private User user;
}
