package com.epam.task.fourth.parser;

import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.exception.ParserException;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public interface ExtensibleMarkupParser {
    public ArrayList<Postcard> parse(String filename) throws SAXException, ParserException;
}
