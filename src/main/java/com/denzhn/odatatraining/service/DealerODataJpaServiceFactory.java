package com.denzhn.odatatraining.service;

import com.denzhn.odatatraining.filter.EntityManagerFilter;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@Component
public class DealerODataJpaServiceFactory extends ODataJPAServiceFactory {
    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext oDataJpaContext = getODataJPAContext();
        ODataContext oDataContext = oDataJpaContext.getODataContext();
        HttpServletRequest request = (HttpServletRequest) oDataContext.getParameter(
                ODataContext.HTTP_SERVLET_REQUEST_OBJECT);
        EntityManager entityManager = (EntityManager) request.getAttribute(
                EntityManagerFilter.EM_REQUEST_ATTRIBUTE
        );

        oDataJpaContext.setEntityManager(entityManager);
        oDataJpaContext.setPersistenceUnitName("default");
        oDataJpaContext.setContainerManaged(true);
        return oDataJpaContext;
    }
}
