package com.denzhn.odatatraining.annotation;

import lombok.Getter;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.*;

import java.util.List;

@Setter
@Getter
@EdmEntityType
@EdmEntitySet(name = "Authors")
public class Author {
    @EdmKey
    private Long id;
    @EdmProperty(facets = @EdmFacets(nullable = false, maxLength = 20))
    private String name;
    @EdmProperty
    private Integer age;
    @EdmNavigationProperty
    private List<Book> books;
    @EdmProperty
    private Address address;
}
