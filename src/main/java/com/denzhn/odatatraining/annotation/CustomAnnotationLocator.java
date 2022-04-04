package com.denzhn.odatatraining.annotation;

import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

@Slf4j
@Path("/custom-annotation")
@Singleton
public class CustomAnnotationLocator extends ODataRootLocator implements ContainerRequestFilter {

    private final CustomAnnotationServiceFactory customAnnotationServiceFactory;

    @Autowired
    public CustomAnnotationLocator(CustomAnnotationServiceFactory customAnnotationServiceFactory) {
        this.customAnnotationServiceFactory = customAnnotationServiceFactory;
    }

    @Override
    public ODataServiceFactory getServiceFactory() {
        return customAnnotationServiceFactory;
    }

    public void filter(ContainerRequestContext requestContext) throws IOException {
        UriInfo uriInfo = requestContext.getUriInfo();
        log.info("Request method: " + requestContext.getRequest().getMethod());
        log.info("Request raw path: " + uriInfo.getAbsolutePath().getRawPath());
        log.info("Request query: " + uriInfo.getRequestUri().getQuery());
    }
}
