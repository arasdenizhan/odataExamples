package com.denzhn.odatatraining.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seller extends BaseEntity {
    @Getter @Setter
    @ManyToMany(mappedBy = "sellers")
    private List<Distributor> distributors;
}
