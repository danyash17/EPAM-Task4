package com.epam.task.fourth.director;

import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.exception.ParserException;
import com.epam.task.fourth.parser.ExtensibleMarkupParser;
import com.epam.task.fourth.validator.XmlValidator;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class Director {
    private final XmlValidator validator;
    private ExtensibleMarkupParser parser;

    public Director(XmlValidator validator) {
        this.validator = validator;
    }

    public ArrayList<Postcard> parseValid(String filename, String schemaFilename, ExtensibleMarkupParser parser) {
        if (!validator.isValid(filename, schemaFilename)) {
            return null;
        }
        this.parser = parser;
        ArrayList<Postcard> result = null;
        try {
            result = parser.parse(filename);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
