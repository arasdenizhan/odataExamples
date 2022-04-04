package com.denzhn.odatatraining.custom.controller;

import com.denzhn.odatatraining.provider.EntitiesEdmProvider;
import org.apache.olingo.commons.api.edmx.EdmxReference;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping(CustomController.URI)
public class CustomController {

    public static final String URI = "/deneme";

    private final EntitiesEdmProvider edmProvider;

    @Autowired
    public CustomController(EntitiesEdmProvider edmProvider) {
        this.edmProvider = edmProvider;
    }

    @RequestMapping("/cars/*")
    public void getDeneme(HttpServletRequest request, HttpServletResponse response){

        OData odata = OData.newInstance();
        ServiceMetadata edm = odata.createServiceMetadata(edmProvider, new ArrayList<EdmxReference>());
        ODataHttpHandler handler = odata.createHandler(edm);
        handler.process(new HttpServletRequestWrapper(request) {
            // Spring MVC matches the whole path as the servlet path
            // Olingo wants just the prefix, ie upto /odata, so that it
            // can parse the rest of it as an OData path. So we need to override
            // getServletPath()
            @Override
            public String getServletPath() {
                return CustomController.URI+"/cars";
            }
        }, response);
    }

    @RequestMapping("/manufacturers/*")
    public void getManufacturers(HttpServletRequest request, HttpServletResponse response){

        OData odata = OData.newInstance();
        ServiceMetadata edm = odata.createServiceMetadata(edmProvider, new ArrayList<EdmxReference>());
        ODataHttpHandler handler = odata.createHandler(edm);
        handler.process(new HttpServletRequestWrapper(request) {
            // Spring MVC matches the whole path as the servlet path
            // Olingo wants just the prefix, ie upto /odata, so that it
            // can parse the rest of it as an OData path. So we need to override
            // getServletPath()
            @Override
            public String getServletPath() {
                return CustomController.URI+"/manufacturers";
            }
        }, response);
    }
}
