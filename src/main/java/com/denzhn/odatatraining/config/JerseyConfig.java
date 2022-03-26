package com.denzhn.odatatraining.config;

import com.denzhn.odatatraining.locator.DealerRootLocator;
import com.denzhn.odatatraining.service.DealerODataJpaServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/odata")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(DealerODataJpaServiceFactory serviceFactory, EntityManagerFactory emf) {
        ODataApplication oDataApplication = new ODataApplication();
        oDataApplication.getClasses().forEach(appClass -> {
            if(!ODataRootLocator.class.isAssignableFrom(appClass)){
                register(appClass);
            }
        });
        /*register(new EntityManagerFilter(emf));*/
        register(new DealerRootLocator(serviceFactory));
    }
}
