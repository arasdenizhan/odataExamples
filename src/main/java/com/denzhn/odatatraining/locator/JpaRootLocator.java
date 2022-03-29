package com.denzhn.odatatraining.locator;

import com.denzhn.odatatraining.service.DealerODataJpaServiceFactory;
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
@Path("/")
@Singleton
public class JpaRootLocator extends ODataRootLocator implements ContainerRequestFilter {
    private final DealerODataJpaServiceFactory serviceFactory;

    @Autowired
    public JpaRootLocator(DealerODataJpaServiceFactory serviceFactory) {
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
