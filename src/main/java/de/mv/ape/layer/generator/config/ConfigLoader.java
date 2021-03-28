package de.mv.ape.layer.generator.config;

import de.mv.ape.layer.generator.config.elements.Config;
import de.mv.ape.layer.generator.config.elements.Entity;
import de.mv.ape.layer.generator.config.elements.Grouping;
import de.mv.ape.layer.generator.config.elements.Reference;
import lombok.Data;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.xml.sax.SAXException;

@Data
public class ConfigLoader {
    private File configFile;
    private File schemaFile = null;
    private Config config;
    private Log logger;

    public ConfigLoader(File configFile, Log logger) {
        this.logger = logger;
        this.configFile = configFile;
    }

    public ConfigLoader(File configFile, Log logger, File schemaFile) {
        this(configFile, logger);
        this.schemaFile = schemaFile;
    }

    public boolean load() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (schemaFile != null) {
                SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema configSchema = sf.newSchema(schemaFile);
                unmarshaller.setSchema(configSchema);
            }

            FileReader reader = new FileReader(configFile);
            config = (Config) unmarshaller.unmarshal(reader);
            return validate() && complete();
        } catch (JAXBException | FileNotFoundException | SAXException e) {
            logger.error("Could not load config file:");
            logger.error(e);
            return false;
        }
    }

    private boolean validate() {
        return config.isValid();
    }

    public boolean complete() {
        removeNullLists();
        if (!completeParents()) {
            logger.error("Completion of parents could not be completed");
            return false;
        }

        return true;
    }

    private void removeNullLists() {
        if (config.getEntities() == null) {
            config.setEntities(new ArrayList<>());
        }
        if (config.getGroupings() == null) {
            config.setGroupings(new ArrayList<>());
        }
        config.getEntities().forEach(e -> {
            if (e.getReferences() == null) {
                e.setReferences(new ArrayList<>());
            }
            if (e.getFields() == null) {
                e.setFields(new ArrayList<>());
            }
            e.setParentRefs(new ArrayList<>());
        });
        config.getGroupings().forEach(g -> {
            if (g.getEntities() == null) {
                g.setEntities(new ArrayList<>());
            }
            g.getEntities().forEach(e -> {
                if (e.getReferences() == null) {
                    e.setReferences(new ArrayList<>());
                }
                if (e.getFields() == null) {
                    e.setFields(new ArrayList<>());
                }
                e.setParentRefs(new ArrayList<>());
            });
        });
    }

    private boolean completeParents() {
        if (!completeEntityParents(config.getEntities())) {
            return false;
        }
        for (Grouping g : config.getGroupings()) {
            g.getEntities().forEach(e -> e.setGrouping(g));
            if (!completeEntityParents(g.getEntities())) {
                return false;
            }
        }
        return true;
    }

    private boolean completeEntityParents(List<Entity> entityList) {
        for (Entity e : entityList) {
            if (e.getReferences() == null) {
                continue;
            }
            for (Reference r : e.getReferences()) {
                if (!completeParents(r.getTargetEntity(), e, r)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean completeParents(String targetEntityName, Entity actualEntity, Reference actualReference) {
        for (Entity e : config.getEntities()) {
            if (e.getBaseName().equals(targetEntityName)) {
                addParentRef(actualEntity, actualReference, e);
                return true;
            }
        }
        for (Grouping g : config.getGroupings()) {
            for (Entity e : g.getEntities()) {
                if (e.getBaseName().equals(targetEntityName)) {
                    addParentRef(actualEntity, actualReference, e);
                    return true;
                }
            }
        }
        logger.error(String.format("The target of reference %s could not be found", actualReference.getTargetEntity()));
        return false;
    }

    private void addParentRef(Entity actualEntity, Reference actualReference, Entity targetEntity) {
        Reference parentRef = new Reference();
        parentRef.setTargetEntity(actualEntity.getBaseName());
        parentRef.setRealTargetEntity(actualEntity);
        parentRef.setParent(targetEntity);
        parentRef.setOwner(actualReference.isOwner());
        parentRef.setList(actualReference.isList());
        parentRef.setReferenceName(actualReference.getReferenceName());

        targetEntity.getParentRefs().add(parentRef);


        actualReference.setParent(actualEntity);
        actualReference.setRealTargetEntity(targetEntity);
    }
}
