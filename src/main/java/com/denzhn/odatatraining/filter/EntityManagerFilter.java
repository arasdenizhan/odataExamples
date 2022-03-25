package com.denzhn.odatatraining.filter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityManagerFilter implements ContainerRequestFilter {

    public static final String EM_REQUEST_ATTRIBUTE =
            EntityManagerFilter.class.getName() + "_ENTITY_MANAGER";

    private final EntityManagerFactory emf;

    @Context
    private HttpServletRequest httpServletRequest;

    public EntityManagerFilter(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        EntityManager entityManager = this.emf.createEntityManager();
        httpServletRequest.setAttribute(EM_REQUEST_ATTRIBUTE, entityManager);
        if(!"GET".equalsIgnoreCase(containerRequestContext.getMethod())){
            entityManager.getTransaction().begin();
        }
    }

}
