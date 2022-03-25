package com.denzhn.odatatraining.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Distributor extends BaseEntity{
    @Getter @Setter
    @ManyToMany
    private List<Seller> sellers;
}
