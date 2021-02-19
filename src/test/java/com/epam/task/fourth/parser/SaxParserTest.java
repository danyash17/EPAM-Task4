package com.epam.task.fourth.parser;

import com.epam.task.fourth.parser.sax.SaxParser;

public class SaxParserTest extends AbstractParserTest {
    @Override
    protected ExtensibleMarkupParser getParser() {
        return new SaxParser();
    }
}
