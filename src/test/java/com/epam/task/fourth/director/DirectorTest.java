package com.epam.task.fourth.director;

import com.epam.task.fourth.entity.Letter;
import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.exception.ParserException;
import com.epam.task.fourth.parser.ExtensibleMarkupParser;
import com.epam.task.fourth.validator.XmlValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.xml.sax.SAXException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectorTest {
    private static final String XML_CORRECT = "./src/test/resources/Cards";
    private static final String SCHEMA_PATH = "./src/test/resources/CardsDefinition.xsd";
    private static final Letter LETTER = new Letter(2, "regular", "Belarus", "Kurchatow", "8", "Daniil");
    private static final Postcard POSTCARD = new Postcard(4.0, "vip", "Belarus", "Chervyakova", "60");
    private static final ArrayList<Postcard> ACTUAL=new ArrayList<>();
    @Before
    public void init(){
        ACTUAL.add(LETTER);
        ACTUAL.add(POSTCARD);
    }

    @Test
    public void testDirector() throws ParserException, SAXException {
        ExtensibleMarkupParser parser=Mockito.mock(ExtensibleMarkupParser.class);
        XmlValidator validator = Mockito.mock(XmlValidator.class);
        Director director=new Director(validator);
        when(validator.isValid(Matchers.anyString(),Matchers.anyString())).thenReturn(true);
        when(parser.parse(anyString())).thenReturn(ACTUAL);
        ArrayList expected=director.parseValid(XML_CORRECT,SCHEMA_PATH,parser);
        Assert.assertEquals(ACTUAL,expected);
    }

}
