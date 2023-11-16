package de.ma_vin.util.layer.generator.config.elements;

import static de.ma_vin.util.layer.generator.config.ConfigElementsUtil.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * Description of an entity which will be used to generate domain object, dto or dao
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "de.ma_vin.util.gen.model")
@Data
@EqualsAndHashCode(exclude = {"grouping", "parentRefs", "realParent"})
@ToString(exclude = {"references", "parentRefs", "fields", "indices", "realParent", "versions"})
public class Entity {

    /**
     * Base name of the objects, which will be extended by some postfix for dto or dao
     */
    @XmlAttribute(required = true)
    private String baseName;

    /**
     * Name of Table at database. if {@code null} the {@code baseName} is used
     */
    @XmlAttribute
    private String tableName;

    /**
     * Which objects should be generated
     */
    @XmlAttribute
    private Models models;

    /**
     * Description of the attribute
     */
    @XmlAttribute
    private String description;

    /**
     * Prefix which will be added in front of dto and domain id
     */
    @XmlAttribute
    private String identificationPrefix;

    /**
     * super entity
     */
    @XmlAttribute
    private String parent;

    /**
     * indication if the generated java class should be abstract
     */
    @XmlAttribute
    private Boolean isAbstract = Boolean.FALSE;

    /**
     * Entity to derive from.
     * Generated Mapper work only one way, from domain model to transport one
     */
    @XmlAttribute
    private String derivedFrom;

    /**
     * Indicator whether to generate identification at entity, if the entity exists only at dto model
     */
    @XmlAttribute
    private Boolean genIdIfDto = Boolean.TRUE;

    /**
     * Attributes of the entity
     */
    @XmlElementWrapper
    @XmlElement(name = "field")
    private List<Field> fields;

    /**
     * Indices of the entity
     */
    @XmlElementWrapper
    @XmlElement(name = "index")
    private List<Index> indices;

    /**
     * References to other entities
     */
    @XmlElementWrapper
    @XmlElement(name = "reference")
    private List<Reference> references;

    /**
     * Versions of data transport objects for this entity
     */
    @XmlElementWrapper
    @XmlElement(name = "version")
    private List<Version> versions;

    @XmlTransient
    private List<Reference> parentRefs;

    @XmlTransient
    private Entity realParent;

    @XmlTransient
    private Entity realDerivedFrom;

    @XmlTransient
    private Grouping grouping;

    public boolean hasParent() {
        return parent != null && !parent.trim().isEmpty();
    }

    public boolean hasNoParent() {
        return !hasParent();
    }

    public boolean isValid(List<String> messages) {
        return validateRequired(baseName, messages, "baseName")
                && validateNonRequired(tableName, messages, "tableName")
                && validateNonRequired(description, messages, "description")
                && validateNonRequired(identificationPrefix, messages, "identificationPrefix")
                && validateNonRequired(parent, messages, "parent")
                && validateNonRequired(derivedFrom, messages, "derivedFrom")
                && (fields == null || fields.stream().allMatch(f -> f.isValid(messages)))
                && (indices == null || indices.stream().allMatch(i -> i.isValid(messages)))
                && (references == null || (references.stream().allMatch(r -> r.isValid(messages))
                && Reference.isFilterFieldValid(baseName, references, messages)))
                && (versions == null || versions.stream().allMatch(r -> r.isValid(messages, this)));
    }

    public Models getModels() {
        if (derivedFrom != null) {
            return Models.DTO;
        }
        return models != null ? models : Models.DOMAIN_DAO_DTO;
    }

    // needed by jaxb2-maven-plugin:schemagen generated classes - it is not compatible with lombok
    public String getBaseName() {
        return baseName;
    }

    // needed by jaxb2-maven-plugin:schemagen generated classes - it is not compatible with lombok
    public List<Field> getFields() {
        return fields;
    }

    // needed by jaxb2-maven-plugin:schemagen generated classes - it is not compatible with lombok
    public List<Reference> getReferences() {
        return references;
    }

    // needed by jaxb2-maven-plugin:schemagen generated classes - it is not compatible with lombok
    public List<Version> getVersions() {
        return versions;
    }
}
