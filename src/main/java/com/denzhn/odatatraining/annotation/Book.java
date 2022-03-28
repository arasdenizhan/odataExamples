package com.denzhn.odatatraining.annotation;

import lombok.Getter;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.*;

@Getter
@Setter
@EdmEntityType
@EdmEntitySet(name = "Books")
public class Book {
    @EdmKey
    private Long id;
    @EdmProperty
    private String name;
    @EdmProperty
    private Integer pageNumber;
    @EdmNavigationProperty
    private Author author;
}
