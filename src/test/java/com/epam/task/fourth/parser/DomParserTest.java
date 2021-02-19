package com.epam.task.fourth.parser;

import com.epam.task.fourth.parser.dom.DomParser;

public class DomParserTest extends AbstractParserTest {
    @Override
    protected ExtensibleMarkupParser getParser() {
        return new DomParser();
    }
}
