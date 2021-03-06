package com.denzhn.odatatraining.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.olingo.odata2.api.annotation.edm.EdmComplexType;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

@NoArgsConstructor
@AllArgsConstructor
@EdmComplexType
public class Address {
    @EdmProperty
    private String street;
    @EdmProperty
    private String apartment;
    @EdmProperty
    private String city;
}
