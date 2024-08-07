package com.github.ma_vin.util.layer_generator.sources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MethodTest {
    @Mock
    private JavaDoc javaDoc;

    private Method cut;

    @BeforeEach
    public void setUp() {
        cut = new Method("methodName");
        initMocks(this);
    }

    @Test
    public void testGenerateDefault() {
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(2, result.size(), "Wrong number of lines");
        assertEquals("private void methodName() {", result.get(0));
        assertEquals("}", result.get(1));
    }

    @Test
    public void testGenerateBody() {
        cut.addLine("abc");
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(3, result.size(), "Wrong number of lines");
        assertEquals("private void methodName() {", result.get(0));
        assertEquals(AbstractGenerateLines.TAB + "abc", result.get(1));
        assertEquals("}", result.get(2));
    }

    @Test
    public void testGenerateBodyViaConstructor() {
        cut = new Method("methodName", "abc");
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(3, result.size(), "Wrong number of lines");
        assertEquals("private void methodName() {", result.get(0));
        assertEquals(AbstractGenerateLines.TAB + "abc", result.get(1));
        assertEquals("}", result.get(2));
    }

    @Test
    public void testGenerateParameter() {
        cut.addParameter("parameterType", "parameterName");
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(2, result.size(), "Wrong number of lines");
        assertEquals("private void methodName(parameterType parameterName) {", result.get(0));
        assertEquals("}", result.get(1));
    }

    @Test
    public void testGenerateMultiParameter() {
        cut.addParameter("p1Type", "p1Name");
        cut.addParameter("p2Type", "p2Name");
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(2, result.size(), "Wrong number of lines");
        assertEquals("private void methodName(p1Type p1Name, p2Type p2Name) {", result.get(0));
        assertEquals("}", result.get(1));
    }

    @Test
    public void testGenerateMultiParameterLineBreak() {
        cut.addParameter("p1Type", "p1Name");
        cut.addParameter("p2Type", "p2Name");
        cut.addParameter("p3Type", "p3Name");
        cut.addParameter("p4Type", "p4Name");
        cut.addParameter("p5Type", "p5Name");
        cut.addParameter("p6Type", "p6Name");
        cut.addParameter("p7Type", "p7Name");
        cut.addParameter("p8Type", "p8Name");
        cut.addParameter("p9Type", "p9Name");
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(3, result.size(), "Wrong number of lines");
        assertEquals("private void methodName(p1Type p1Name, p2Type p2Name, p3Type p3Name, p4Type p4Name, p5Type p5Name, p6Type p6Name, p7Type p7Name, p8Type p8Name", result.get(0));
        assertEquals("		, p9Type p9Name) {", result.get(1));
        assertEquals("}", result.get(2));
    }

    @Test
    public void testGenerateQualifier() {
        cut.setQualifier(Qualifier.PUBLIC);
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(2, result.size(), "Wrong number of lines");
        assertEquals("public void methodName() {", result.get(0));
        assertEquals("}", result.get(1));
    }


    @Test
    public void testGenerateStatic() {
        cut.setStatic(true);
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(2, result.size(), "Wrong number of lines");
        assertEquals("private static void methodName() {", result.get(0));
        assertEquals("}", result.get(1));
    }

    @Test
    public void testGenerateJavaDoc() {
        when(javaDoc.generate()).thenReturn(Arrays.asList("commentPlaceHolder"));
        cut.setJavaDoc(javaDoc);
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(3, result.size(), "Wrong number of lines");
        assertEquals("commentPlaceHolder", result.get(0));
        assertEquals("private void methodName() {", result.get(1));
        assertEquals("}", result.get(2));
    }

    @Test
    public void testGenerateAndAddAnnotation() {
        cut.addAnnotation("TestAnnotation");
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(3, result.size(), "Wrong number of lines");
        assertEquals("@TestAnnotation", result.get(0));
        assertEquals("private void methodName() {", result.get(1));
        assertEquals("}", result.get(2));
    }

    @Test
    public void testGenerateAndAddAnnotationWithParameter() {
        cut.addAnnotation("TestAnnotation", "parameterName", "parameterValue");
        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(3, result.size(), "Wrong number of lines");
        assertEquals("@TestAnnotation(parameterName = parameterValue)", result.get(0));
        assertEquals("private void methodName() {", result.get(1));
        assertEquals("}", result.get(2));
    }

    @Test
    public void testGenerateGeneric() {
        cut.addGeneric("S");
        Generic secondGeneric = new Generic("T");
        secondGeneric.setExtension("U");
        cut.addGeneric(secondGeneric);

        List<String> result = cut.generate();
        assertNotNull(result, "There should be any result");
        assertEquals(2, result.size(), "Wrong number of lines");
        assertEquals("private <S, T extends U> void methodName() {", result.get(0));
        assertEquals("}", result.get(1));
    }

    @Test
    void testCompare() {
        List<Method> methods = new ArrayList<>();
        methods.add(getMethod("A", true));
        methods.add(getMethod("A", true, "p1Type", "p1Name"));
        methods.add(getMethod("A", true, "p3Type", "p1Name"));
        methods.add(getMethod("A", true, "p2Type", "p2Name"));
        methods.add(getMethod("B", true));
        methods.add(getMethod("A", false));

        TestUtil.checkComparisonOfSortedList(methods);
        TestUtil.checkSortingOfSortedList(methods);
    }

    @Test
    public void testAddEmptyLine() {
        cut.addLine("abc");

        cut.addEmptyLine();
        assertEquals(2, cut.getMethodBody().size(), "Wrong number of lines");
        assertEquals("", cut.getMethodBody().get(1), "Wrong added line");
    }

    @Test
    public void testAddEmptyLineLastLineIsEmpty() {
        cut.addLine("abc");
        cut.addLine("");

        cut.addEmptyLine();
        assertEquals(2, cut.getMethodBody().size(), "Wrong number of lines");
        assertEquals("", cut.getMethodBody().get(1), "Last line should be an empty one");
    }


    private Method getMethod(String methodName, boolean isStatic, String... parameters) {
        Method result = new Method(methodName);
        result.setStatic(isStatic);
        if (parameters.length % 2 == 1) {
            fail("number of parameters are not even");
        }
        for (int i = 0; i < parameters.length; i += 2) {
            result.addParameter(parameters[i], parameters[i + 1]);
        }
        return result;
    }
}
