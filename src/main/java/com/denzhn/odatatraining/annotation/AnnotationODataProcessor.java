package com.denzhn.odatatraining.annotation;

import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;

@Slf4j
public class AnnotationODataProcessor extends ODataSingleProcessor {

    @Override
    public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) throws ODataException {
       log.info("BURAYA GELDI, TargetEntitySet: {}",uriInfo.getTargetEntitySet().getName());
        CsdlEntitySet csdlEntitySet = new CsdlEntitySet();
        csdlEntitySet.setName("Books");
        csdlEntitySet.setType("com.denzhn.odatatraining.annotation.Book");
        return ODataResponse.newBuilder().status(HttpStatusCodes.OK).entity(csdlEntitySet).build();
    }


}
