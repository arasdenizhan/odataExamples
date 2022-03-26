package com.denzhn.odatatraining.annotation;

import org.apache.olingo.odata2.api.annotation.edm.*;

@EdmEntityType
@EdmEntitySet
public class Books {
    @EdmKey
    private Long id;
    @EdmProperty
    private String name;
    @EdmProperty
    private Integer pageNumber;
    @EdmNavigationProperty
    private Author author;
}
