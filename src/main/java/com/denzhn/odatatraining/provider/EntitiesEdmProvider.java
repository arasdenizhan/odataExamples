package com.denzhn.odatatraining.provider;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlComplexType;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationPropertyBinding;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class EntitiesEdmProvider extends CsdlAbstractEdmProvider {
    // Service Namespace
    public static final String NAMESPACE = "olingo.odatademo";
    // EDM Container
    public static final String CONTAINER_NAME = "default";
    public static final FullQualifiedName CONTAINER_FQN = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);
    // Entity Types Names
    public static final FullQualifiedName ET_CAR = new FullQualifiedName(NAMESPACE, "Car");
    public static final FullQualifiedName ET_MANUFACTURER = new FullQualifiedName(NAMESPACE, "Manufacturer");
    // Complex Type Names
    public static final FullQualifiedName CT_ADDRESS = new FullQualifiedName(NAMESPACE, "Address");
    // Entity Set Names
    public static final String ES_CARS_NAME = "Cars";
    public static final String ES_MANUFACTURER_NAME = "Manufacturers";
    @Override
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {
        if (ET_CAR.equals(entityTypeName)) {
            return new CsdlEntityType().setName(ET_CAR.getName()).setKey(List.of(new CsdlPropertyRef().setName("Id"))).setProperties(
                    Arrays.asList(
                            new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int16.getFullQualifiedName()),
                            new CsdlProperty().setName("Model").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName()),
                            new CsdlProperty().setName("ModelYear").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName())
                                    .setMaxLength(4),
                            new CsdlProperty().setName("Price").setType(EdmPrimitiveTypeKind.Decimal.getFullQualifiedName())
                                    .setScale(2),
                            new CsdlProperty().setName("Currency").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName())
                                    .setMaxLength(3))).setNavigationProperties(List.of(
                            new CsdlNavigationProperty().setName("Manufacturer").setType(ET_MANUFACTURER)
                    )
            );
        } else if (ET_MANUFACTURER.equals(entityTypeName)) {
            return new CsdlEntityType()
                    .setName(ET_MANUFACTURER.getName())
                    .setKey(List.of(
                            new CsdlPropertyRef().setName("Id")))
                    .setProperties(Arrays.asList(
                            new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int16.getFullQualifiedName()),
                            new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName()),
                            new CsdlProperty().setName("Address").setType(CT_ADDRESS))).setNavigationProperties(List.of(new CsdlNavigationProperty().setName("Cars").setType(ET_CAR).setCollection(true)
                            )
                    );
        }
        return null;
    }
    @Override
    public CsdlComplexType getComplexType(FullQualifiedName complexTypeName) throws ODataException {
        if (CT_ADDRESS.equals(complexTypeName)) {
            return new CsdlComplexType().setName(CT_ADDRESS.getName()).setProperties(Arrays.asList(
                    new CsdlProperty().setName("Street").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName()),
                    new CsdlProperty().setName("City").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName()),
                    new CsdlProperty().setName("ZipCode").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName()),
                    new CsdlProperty().setName("Country").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName())
            ));
        }
        return null;
    }
    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) throws ODataException {
        if (CONTAINER_FQN.equals(entityContainer)) {
            if (ES_CARS_NAME.equals(entitySetName)) {
                return new CsdlEntitySet().setName(ES_CARS_NAME).setType(ET_CAR).setNavigationPropertyBindings(Arrays.asList(new CsdlNavigationPropertyBinding().setPath("Manufacturer").setTarget(CONTAINER_FQN.getFullQualifiedNameAsString() + "/" + ES_MANUFACTURER_NAME)));
            } else if (ES_MANUFACTURER_NAME.equals(entitySetName)) {
                return new CsdlEntitySet().setName(ES_MANUFACTURER_NAME).setType(ET_MANUFACTURER).setNavigationPropertyBindings(Arrays.asList(new CsdlNavigationPropertyBinding().setPath("Cars").setTarget(CONTAINER_FQN.getFullQualifiedNameAsString() + "/" + ES_CARS_NAME)));
            }
        }
        return null;
    }
    @Override
    public List<CsdlSchema> getSchemas() throws ODataException {
        List<CsdlSchema> schemas = new ArrayList<>();
        CsdlSchema schema = new CsdlSchema();
        schema.setNamespace(NAMESPACE);
        // EntityTypes
        List<CsdlEntityType> entityTypes = new ArrayList<>();
        entityTypes.add(getEntityType(ET_CAR));
        entityTypes.add(getEntityType(ET_MANUFACTURER));
        schema.setEntityTypes(entityTypes);
        // ComplexTypes
        List<CsdlComplexType> complexTypes = new ArrayList<>();
        complexTypes.add(getComplexType(CT_ADDRESS));
        schema.setComplexTypes(complexTypes);
        // EntityContainer
        schema.setEntityContainer(getEntityContainer());
        schemas.add(schema);
        return schemas;
    }
    @Override
    public CsdlEntityContainer getEntityContainer() throws ODataException {
        CsdlEntityContainer container = new CsdlEntityContainer();
        container.setName(CONTAINER_FQN.getName());
        // EntitySets
        List<CsdlEntitySet> entitySets = new ArrayList<>();
        container.setEntitySets(entitySets);
        entitySets.add(getEntitySet(CONTAINER_FQN, ES_CARS_NAME));
        entitySets.add(getEntitySet(CONTAINER_FQN, ES_MANUFACTURER_NAME));
        return container;
    }
    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) throws ODataException {
        if (entityContainerName == null || CONTAINER_FQN.equals(entityContainerName)) {
            return new CsdlEntityContainerInfo().setContainerName(CONTAINER_FQN);
        }
        return null;
    }
}