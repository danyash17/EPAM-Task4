package com.epam.task.fourth.parser.sax;


import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.handler.CardHandler;
import com.epam.task.fourth.parser.ExtensibleMarkupParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;

public class SaxParser extends DefaultHandler implements ExtensibleMarkupParser {
    private ArrayList<Postcard> cards;
    private static final Logger LOGGER = LogManager.getLogger(SaxParser.class);
    private CardHandler cardHandler;
    private XMLReader reader;


    public SaxParser() {

    }

    public ArrayList<Postcard> parse(String filename) throws SAXException {
        cardHandler = new CardHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(cardHandler);
        } catch (SAXException e) {
            e.printStackTrace();
            LOGGER.warn("SAX parser error");
        }
        try {
            reader.parse(filename);
            cards = cardHandler.getCards();
        } catch (IOException e) {
            LOGGER.warn("IOException", e);
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, cards);
        return cards;
    }
}
