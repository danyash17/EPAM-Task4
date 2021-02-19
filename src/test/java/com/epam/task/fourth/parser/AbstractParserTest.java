package com.epam.task.fourth.parser;

import com.epam.task.fourth.entity.Destination;
import com.epam.task.fourth.entity.Letter;
import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.exception.ParserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.xml.XmlParser;
import org.xml.sax.SAXException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractParserTest {
    private ExtensibleMarkupParser parser;

    protected static final String XML_CORRECT = "./src/test/resources/Cards";
    protected static final Letter LETTER=new Letter(2, "regular","Belarus","Kurchatow","8","Daniil");
    protected static final Postcard POSTCARD= new Postcard(4.0, "vip","Belarus","Chervyakova","60");
    protected static final List<Postcard> EXPECTED = Arrays.asList(LETTER,POSTCARD);


    protected abstract ExtensibleMarkupParser getParser();

    @Before
    public void init() {
        parser = getParser();
    }

    @Test
    public void testCorrectPathCorrectXmlPathParsing() throws ParserException {
        List<Postcard> result = null;
        try {
            result = parser.parse(XML_CORRECT);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(EXPECTED,result);
    }
}
