package com.madhu.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class CustomerRequest {

    @Id
    @UuidGenerator
    private String id;
    private LocalDateTime timestamp;
    private String message;
    @ElementCollection
    private List<String> referenceImages;
    @ToString.Exclude
    @ManyToOne
    private User user;
}
