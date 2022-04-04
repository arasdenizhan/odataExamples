package com.denzhn.odatatraining.config;

import com.denzhn.odatatraining.annotation.AnnotationLocator;
import com.denzhn.odatatraining.annotation.AnnotationODataServiceFactory;
import com.denzhn.odatatraining.annotation.CustomAnnotationLocator;
import com.denzhn.odatatraining.annotation.CustomAnnotationServiceFactory;
import com.denzhn.odatatraining.filter.EntityManagerFilter;
import com.denzhn.odatatraining.locator.JpaRootLocator;
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
    public JerseyConfig(DealerODataJpaServiceFactory serviceFactory,
                        AnnotationODataServiceFactory annotationODataServiceFactory,
                        CustomAnnotationServiceFactory customAnnotationServiceFactory,
                        EntityManagerFactory emf
    ) {
        ODataApplication oDataApplication = new ODataApplication();
        oDataApplication.getClasses().forEach(appClass -> {
            if(!ODataRootLocator.class.isAssignableFrom(appClass)){
                register(appClass);
            }
        });
        register(new EntityManagerFilter(emf));
        register(new JpaRootLocator(serviceFactory));
        register(new AnnotationLocator(annotationODataServiceFactory));
        register(new CustomAnnotationLocator(customAnnotationServiceFactory));
    }
}
