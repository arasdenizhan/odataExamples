package com.denzhn.odatatraining.annotation;

import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

@Slf4j
@Path("/annotation")
@Singleton
public class AnnotationLocator extends ODataRootLocator implements ContainerRequestFilter {

    private final AnnotationODataServiceFactory serviceFactory;

    @Autowired
    public AnnotationLocator(AnnotationODataServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ODataServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.info("Request method: " + requestContext.getRequest().getMethod());
    }
}
