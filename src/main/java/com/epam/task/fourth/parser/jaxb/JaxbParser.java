package com.epam.task.fourth.parser.jaxb;

import com.epam.task.fourth.entity.Cards;
import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.parser.ExtensibleMarkupParser;
import com.epam.task.fourth.exception.ParserException;
import com.epam.task.fourth.parser.sax.SaxParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class JaxbParser implements ExtensibleMarkupParser {
    private static final Logger LOGGER = LogManager.getLogger(JaxbParser.class);

    public ArrayList<Postcard> parse(String filename) throws ParserException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Cards.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Cards cards = (Cards) unmarshaller.unmarshal(new File(filename));
            LOGGER.info("Unmarshalling of " + filename + " done");
            return cards.getCardList();
        } catch (JAXBException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }
}
