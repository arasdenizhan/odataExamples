package com.denzhn.odatatraining.annotation;

import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationInMemoryDs;
import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.edm.AnnotationEdmProvider;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.rt.RuntimeDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAnnotationServiceFactory extends ODataServiceFactory {

    private static final String packageToScan = "com.denzhn.odatatraining.annotation";

    private ODataService MY_ODATA_SERVICE;

    @Override
    public ODataService createService(ODataContext ctx) throws ODataException {
        if (this.MY_ODATA_SERVICE == null) {
            AnnotationEdmProvider edmProvider = new AnnotationEdmProvider(packageToScan);
            AnnotationInMemoryDs dataSource = new AnnotationInMemoryDs(packageToScan);
            AnnotationValueAccess valueAccess = new AnnotationValueAccess();
            this.MY_ODATA_SERVICE = RuntimeDelegate.createODataSingleProcessorService(edmProvider, new AnnotationODataProcessor());
            log.debug("MyODataServiceFactory service created");
        }
        return this.MY_ODATA_SERVICE;
    }
}
