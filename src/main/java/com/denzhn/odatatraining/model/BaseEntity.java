package com.denzhn.odatatraining.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String phoneNumber;
}
