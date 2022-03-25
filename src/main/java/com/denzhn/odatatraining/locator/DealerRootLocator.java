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
import java.io.IOException;

@Slf4j
@Path("/")
@Singleton
public class DealerRootLocator extends ODataRootLocator implements ContainerRequestFilter {
    private final DealerODataJpaServiceFactory serviceFactory;

    @Autowired
    public DealerRootLocator(DealerODataJpaServiceFactory serviceFactory) {
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
