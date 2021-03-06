package de.ma_vin.util.layer.generator.generator;

import de.ma_vin.util.layer.generator.config.elements.Entity;
import de.ma_vin.util.layer.generator.config.elements.Models;
import de.ma_vin.util.layer.generator.log.LogImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainCreatorTest extends AbstractCreatorTest {

    @Mock
    private Entity parentEntity;

    private DomainCreator cut;

    private List<String> directoriesWhereRequestedToWrite = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        super.setUp();

        cut = new DomainCreator(config, new LogImpl()) {
            @Override
            protected BufferedWriter createBufferedWriter(File classFile) {
                List<String> fileContent = new ArrayList<>();
                writtenFileContents.put(classFile.getName(), fileContent);
                try {
                    // Assumption: after write is als a newLine statement
                    doAnswer(a -> fileContent.add(a.getArgument(0))).when(bufferedWriter).write(anyString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bufferedWriter;
            }

            @Override
            protected File createFile(File dir, String fileName) {
                directoriesWhereRequestedToWrite.add(dir.getName());
                File createdFile = mock(File.class);
                when(createdFile.getName()).thenReturn(fileName);
                when(createdFile.getParentFile()).thenReturn(dir);
                return createdFile;
            }
        };
    }

    @Test
    public void testCreateDomainObjectDefault() {
        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }


    @Test
    public void testCreateDomainObjectNoDomain() {
        when(entity.getModels()).thenReturn(Models.DAO);

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        assertEquals(0, writtenFileContents.size(), "Wrong number of files");
    }

    @Test
    public void testCreateDomainObjectNoGrouping() {
        when(entity.getGrouping()).thenReturn(null);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectUniqueRelation() {
        when(entity.getReferences()).thenReturn(Arrays.asList(targetReference));
        when(targetReference.getParent()).thenReturn(entity);
        when(targetReference.isList()).thenReturn(Boolean.FALSE);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.EqualsAndHashCode;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("import lombok.ToString;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@EqualsAndHashCode(exclude = {\"targetRef\"})");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("@ToString(exclude = {\"targetRef\"})");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("	private Target targetRef;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }


    @DisplayName("Create a domain object with an one to one relation, but the target does not support the domain model")
    @Test
    public void testCreateDomainObjectUniqueRelationButNonDomain() {
        when(entity.getReferences()).thenReturn(Arrays.asList(targetReference));
        when(targetReference.getParent()).thenReturn(entity);
        when(targetReference.isList()).thenReturn(Boolean.FALSE);
        when(targetEntity.getModels()).thenReturn(Models.DAO);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectRelation() {
        when(entity.getReferences()).thenReturn(Arrays.asList(targetReference));
        when(targetReference.getParent()).thenReturn(entity);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import java.util.Collection;");
        expected.add("import java.util.HashSet;");
        expected.add("import lombok.AccessLevel;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.EqualsAndHashCode;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("import lombok.Setter;");
        expected.add("import lombok.ToString;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@EqualsAndHashCode(exclude = {\"targetRef\"})");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("@ToString(exclude = {\"targetRef\"})");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("	@Setter(AccessLevel.PROTECTED)");
        expected.add("	private Collection<Target> targetRef = new HashSet<>();");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Adds a Target to targetRef");
        expected.add("	 * ");
        expected.add("	 * @param target Target to add");
        expected.add("	 */");
        expected.add("	public boolean addTargetRef(Target target) {");
        expected.add("		return targetRef.add(target);");
        expected.add("	}");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Removes a Target from targetRef");
        expected.add("	 * ");
        expected.add("	 * @param target Target to remove");
        expected.add("	 */");
        expected.add("	public boolean removeTargetRef(Target target) {");
        expected.add("		return targetRef.remove(target);");
        expected.add("	}");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }


    @DisplayName("Create a domain object with an one to many relation, but the target does not support the domain model")
    @Test
    public void testCreateDomainObjectRelationButNonDomain() {
        when(entity.getReferences()).thenReturn(Arrays.asList(targetReference));
        when(targetReference.getParent()).thenReturn(entity);
        when(targetEntity.getModels()).thenReturn(Models.DAO);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectField() {
        when(entity.getFields()).thenReturn(Arrays.asList(field));

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	private String anyField;");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectFieldEnum() {
        when(entity.getFields()).thenReturn(Arrays.asList(field));
        when(field.getIsTypeEnum()).thenReturn(Boolean.TRUE);
        when(field.getType()).thenReturn("AnyEnum");
        when(field.getTypePackage()).thenReturn("the.enum.package");

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("import the.enum.package.AnyEnum;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	private AnyEnum anyField;");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");


        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectUseIdGenerator() {
        when(config.isUseIdGenerator()).thenReturn(Boolean.TRUE);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	public static final String ID_PREFIX = \"DU\";");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Identification of Dummy");
        expected.add("	 */");
        expected.add("	private String identification;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectInterface() {
        List<String> expected = new ArrayList<>();

        expected.add("package de.test.package.domain;");
        expected.add("");
        expected.add("public interface IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * @return the id of the domain object");
        expected.add("	 */");
        expected.add("	Long getId();");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * @param id the id of the domain object");
        expected.add("	 */");
        expected.add("	void setId(Long id);");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObjectInterface(BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile(DomainCreator.DOMAIN_INTERFACE + ".java", expected);
    }

    @Test
    public void testCreateDomainObjectInterfaceUseIdGenerator() {
        when(config.isUseIdGenerator()).thenReturn(Boolean.TRUE);
        List<String> expected = new ArrayList<>();

        expected.add("package de.test.package.domain;");
        expected.add("");
        expected.add("public interface IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * @return the identification of the domain object");
        expected.add("	 */");
        expected.add("	String getIdentification();");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * @param identification the identification of the domain object");
        expected.add("	 */");
        expected.add("	void setIdentification(String identification);");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObjectInterface(BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile(DomainCreator.DOMAIN_INTERFACE + ".java", expected);
    }

    @Test
    public void testCreateDomainObjectGroupingWithDots() {
        when(grouping.getGroupingPackage()).thenReturn("group.subgroup");

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group.subgroup;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        assertFalse(directoriesWhereRequestedToWrite.contains("group.subgroup"), "Not any directories with dots should be used");
        assertTrue(directoriesWhereRequestedToWrite.contains(String.format("group%ssubgroup", File.separator)), "Dot should be replaced by backslash");

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectIsAbstract() {
        when(entity.getIsAbstract()).thenReturn(Boolean.TRUE);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@Data");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("public abstract class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectHasSuperClass() {
        when(entity.getParent()).thenReturn("AnotherDummy");
        when(entity.getRealParent()).thenReturn(parentEntity);
        when(entity.getFields()).thenReturn(Arrays.asList(field));
        when(entity.hasParent()).thenReturn(Boolean.TRUE);
        when(entity.hasNoParent()).thenReturn(Boolean.FALSE);
        when(parentEntity.getBaseName()).thenReturn("AnotherDummy");
        when(parentEntity.getModels()).thenReturn(Models.DOMAIN_DAO_DTO);
        when(parentEntity.getGrouping()).thenReturn(null);

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.AnotherDummy;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.EqualsAndHashCode;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("import lombok.ToString;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@EqualsAndHashCode(callSuper = true)");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings({\"java:S2160\", \"java:S1068\"})");
        expected.add("@ToString(callSuper = true)");
        expected.add("public class Dummy extends AnotherDummy {");
        expected.add("");
        expected.add("	private String anyField;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectUniqueRelationShortDescription() {
        when(entity.getReferences()).thenReturn(Arrays.asList(targetReference));
        when(targetReference.getParent()).thenReturn(entity);
        when(targetReference.isList()).thenReturn(Boolean.FALSE);
        when(targetReference.getShortDescription()).thenReturn("Some description");

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.EqualsAndHashCode;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("import lombok.ToString;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@EqualsAndHashCode(exclude = {\"targetRef\"})");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("@ToString(exclude = {\"targetRef\"})");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Some description");
        expected.add("	 */");
        expected.add("	private Target targetRef;");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }

    @Test
    public void testCreateDomainObjectRelationShortDescription() {
        when(entity.getReferences()).thenReturn(Arrays.asList(targetReference));
        when(targetReference.getParent()).thenReturn(entity);
        when(targetReference.getShortDescription()).thenReturn("Some description");

        List<String> expected = new ArrayList<>();
        expected.add("package de.test.package.domain.group;");
        expected.add("");
        expected.add("import de.ma_vin.util.layer.generator.annotations.model.BaseDomain;");
        expected.add("import de.test.package.domain.IIdentifiable;");
        expected.add("import java.util.Collection;");
        expected.add("import java.util.HashSet;");
        expected.add("import lombok.AccessLevel;");
        expected.add("import lombok.Data;");
        expected.add("import lombok.EqualsAndHashCode;");
        expected.add("import lombok.NoArgsConstructor;");
        expected.add("import lombok.Setter;");
        expected.add("import lombok.ToString;");
        expected.add("");
        expected.add("/**");
        expected.add(" * Generated domain class of Dummy");
        expected.add(" * <br>");
        expected.add(" * Dummy description");
        expected.add(" */");
        expected.add("@BaseDomain(\"de.test.package.domain\")");
        expected.add("@Data");
        expected.add("@EqualsAndHashCode(exclude = {\"targetRef\"})");
        expected.add("@NoArgsConstructor");
        expected.add("@SuppressWarnings(\"java:S1068\")");
        expected.add("@ToString(exclude = {\"targetRef\"})");
        expected.add("public class Dummy implements IIdentifiable {");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Id of Dummy");
        expected.add("	 */");
        expected.add("	private Long id;");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Some description");
        expected.add("	 */");
        expected.add("	@Setter(AccessLevel.PROTECTED)");
        expected.add("	private Collection<Target> targetRef = new HashSet<>();");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Adds a Target to targetRef");
        expected.add("	 * ");
        expected.add("	 * @param target Target to add");
        expected.add("	 */");
        expected.add("	public boolean addTargetRef(Target target) {");
        expected.add("		return targetRef.add(target);");
        expected.add("	}");
        expected.add("");
        expected.add("	/**");
        expected.add("	 * Removes a Target from targetRef");
        expected.add("	 * ");
        expected.add("	 * @param target Target to remove");
        expected.add("	 */");
        expected.add("	public boolean removeTargetRef(Target target) {");
        expected.add("		return targetRef.remove(target);");
        expected.add("	}");
        expected.add("");
        expected.add("}");

        assertTrue(cut.createDomainObject(entity, BASE_PACKAGE + ".domain", basePackageDir));

        checkSingleFile("Dummy.java", expected);
    }
}
