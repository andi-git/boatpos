package org.boatpos.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

/**
 * This component tests a class if it is a java-bean:
 * <pre>
 * <ul>
 *     <li>has a not-arg-constructor</li>
 *     <li>hat getter and setter for all attributes</li>
 *     <li>check if getter and setter work correct</li>
 * </ul>
 * </pre>
 */
public class JavaBeanTester {

    private static final Logger log = LoggerFactory.getLogger(JavaBeanTester.class);

    public void test(Class<?> type) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        test(type, type);
    }

    private void test(Class<?> baseType, Class<?> currentType) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        assertNotNull(baseType);
        assertNotNull(currentType);
        if (currentType.getSuperclass() != null && currentType.getSuperclass() != Object.class) {
            test(baseType, currentType.getSuperclass());
        }
        if (isBeanOfThisProject(currentType)) {
            log.info("check for bean: " + currentType.getName());
            checkNoArgConstructor(currentType);
            checkGetterAndSetter(currentType);
            checkValuesOfGetterAndSetter(baseType, currentType);
        }
    }


    private boolean isBeanOfThisProject(Class<?> type) {
        return type.getName().startsWith("org.boatpos");
    }

    private void checkNoArgConstructor(Class<?> type) {
        boolean noArgConstructor = false;
        for (Constructor constructor : type.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 0) {
                noArgConstructor = true;
                break;
            }
        }
        assertTrue(type + " has only constructor with arguments", noArgConstructor);
    }

    private void checkGetterAndSetter(Class<?> type) throws NoSuchMethodException {
        for (Field field : type.getDeclaredFields()) {
            if (!isJacocoField(field)) {
                assertNotNull(getterFor(field));
                assertNotNull(setterFor(field));
            }
        }
    }

    private boolean isJacocoField(Field field) {
        return field.getName().contains("jacoco");
    }

    private void checkValuesOfGetterAndSetter(Class<?> baseType, Class<?> currentType) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        assertFalse(baseType.getName() + " must not be abstract", Modifier.isAbstract(baseType.getModifiers()));
        Object object = baseType.newInstance();
        for (Field field : currentType.getDeclaredFields()) {
            if (!isJacocoField(field)) {
                SimpleType simpleType = SimpleType.get(field.getType());
                Object testValue;
                if (simpleType == SimpleType.NoSimpleType && isBeanOfThisProject(field.getType())) {
                    test(field.getType());
                    testValue = field.getType().newInstance();
                } else {
                    testValue = simpleType.getTestValue();
                }
                if (testValue != null) {
                    setterFor(field).invoke(object, testValue);
                    assertEquals(testValue, getterFor(field).invoke(object));
                }
            }
        }
    }

    private Method getterFor(Field field) throws NoSuchMethodException {
        return field.getDeclaringClass().getDeclaredMethod(getterNameFor(field));
    }

    private String getterNameFor(Field field) {
        if (field.getType() == Boolean.class || field.getType() == boolean.class) {
            return "is" + firstCharToUpperCase(field.getName());
        } else {
            return "get" + firstCharToUpperCase(field.getName());
        }
    }

    private Method setterFor(Field field) throws NoSuchMethodException {
        return field.getDeclaringClass().getDeclaredMethod(setterNameFor(field), field.getType());
    }

    private String setterNameFor(Field field) {
        return "set" + firstCharToUpperCase(field.getName());
    }

    private String firstCharToUpperCase(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    @SuppressWarnings("unused")
    public enum SimpleType {
        BooleanSimple(boolean.class, false, false),
        BooleanComplex(Boolean.class, false, false),
        IntegerSimple(int.class, -1, false),
        IntegerComplex(Integer.class, -1, false),
        LongSimple(long.class, -1L, false),
        LongComplex(Long.class, -1L, false),
        BigDecimal(BigDecimal.class, new BigDecimal("-1.1"), false),
        String(String.class, "x", false),
        List(List.class, new ArrayList(), false),
        Set(Set.class, new HashSet(), false),
        Map(Map.class, new HashMap(), false),
        LocalDateType(LocalDate.class, LocalDate.now(), false),
        LocalDateTimeType(LocalDateTime.class, LocalDateTime.now(), false),
        NoSimpleType(Object.class, new Object(), true);

        private final Class<?> type;

        private final Object testValue;

        private final boolean complex;

        SimpleType(Class<?> type, Object testValue, boolean complex) {
            this.type = type;
            this.testValue = testValue;
            this.complex = complex;
        }

        public boolean isComplex() {
            return complex;
        }

        public Object getTestValue() {
            return testValue;
        }

        public Class<?> getType() {
            return type;
        }

        public boolean canHandle(Class<?> type) {
            return this.type == type;
        }

        public static SimpleType get(Class<?> type) {
            SimpleType result = NoSimpleType;
            for (SimpleType simpleType : values()) {
                if (simpleType.canHandle(type)) {
                    result = simpleType;
                    break;
                }
            }
            return result;
        }
    }
}
