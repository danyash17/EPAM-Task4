package com.epam.task.fourth.handler;

import com.epam.task.fourth.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class CardsErrorHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger(XmlValidator.class);

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw new SAXException(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        throw new SAXException(e);
    }
}
