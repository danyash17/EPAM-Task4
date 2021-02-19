package com.epam.task.fourth.validator;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    private final XmlValidator validator = new XmlValidator();
    private static final String XML_CORRECT = "./src/test/resources/Cards";
    private static final String XML_INCORRECT = "./src/test/resources/CardsIncorrect";
    private static final String INCORRECT_XML_PATH = "./src/test/resources/Example";
    private static final String SCHEMA_PATH = "./src/test/resources/CardsDefinition.xsd";

    @Test
    public void testCorrectPathCorrectXmlValidation() {
        boolean result = validator.isValid(XML_CORRECT, SCHEMA_PATH);

        Assert.assertTrue(result);
    }

    @Test
    public void testCorrectPathIncorrectXmlValidation() {
        boolean result = validator.isValid(XML_INCORRECT, SCHEMA_PATH);

        Assert.assertFalse(result);
    }

    @Test
    public void testIncorrectPathCorrectXmlValidation() {
        boolean result = validator.isValid(INCORRECT_XML_PATH, SCHEMA_PATH);

        Assert.assertFalse(result);
    }
}
