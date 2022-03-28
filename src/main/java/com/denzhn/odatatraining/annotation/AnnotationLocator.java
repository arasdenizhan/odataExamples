package com.denzhn.odatatraining.annotation;

import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;

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
        UriInfo uriInfo = requestContext.getUriInfo();
        log.info("Request method: " + requestContext.getRequest().getMethod());
        log.info("Request raw path: " + uriInfo.getAbsolutePath().getRawPath());
        log.info("Request query: " + uriInfo.getRequestUri().getQuery());
    }
}
