package com.denzhn.odatatraining.annotation;

import org.apache.olingo.odata2.api.annotation.edm.*;

import java.util.List;

@EdmEntityType
@EdmEntitySet
public class Author {
    @EdmKey
    private Long id;
    @EdmProperty
    private String name;
    @EdmProperty
    private Integer age;
    @EdmNavigationProperty
    private List<Books> books;
}
