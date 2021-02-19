package com.epam.task.fourth.parser.dom;

import com.epam.task.fourth.entity.Destination;
import com.epam.task.fourth.entity.Letter;
import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.enumerations.CardEnum;
import com.epam.task.fourth.parser.ExtensibleMarkupParser;
import com.epam.task.fourth.exception.ParserException;
import com.epam.task.fourth.parser.sax.SaxParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DomParser implements ExtensibleMarkupParser {
    private static final Logger LOGGER = LogManager.getLogger(DomParser.class);
    public DomParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    }


    public ArrayList<Postcard> parse(String filename) throws ParserException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        Document document;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            ArrayList<Postcard> cards = new ArrayList<>();
            document = builder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList letterList = root.getElementsByTagName(CardEnum.letter);

            for (int i = 0; i < letterList.getLength(); ++i) {
                Element letterElement = (Element) letterList.item(i);
                Letter letter = (Letter) buildCard(letterElement);
                cards.add(letter);
            }
            NodeList postcardList = root.getElementsByTagName(CardEnum.postcard);

            for (int i = 0; i < postcardList.getLength(); ++i) {
                Element postcardElement = (Element) postcardList.item(i);
                Postcard postcard = buildCard(postcardElement);
                cards.add(postcard);
            }

            return cards;

        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOGGER.warn("Parsing exception");
            throw new ParserException(e.getMessage(), e);
        }
    }


    private Postcard buildCard(Element element) {
        Postcard card;

        if (element.hasAttribute(CardEnum.author)) {
            card = new Letter();
            String author = element.getAttribute(CardEnum.author);
            ((Letter) card).setAuthor(author);

        } else {
            card = new Postcard();
        }

        Double cost = Double.parseDouble(getElementTextContent(element, CardEnum.cost));
        card.setCost(cost);

        card.setStatus(getElementTextContent(element, CardEnum.status));
        Destination destination = new Destination();
        destination.setCountry(getElementTextContent(element, CardEnum.country));
        destination.setStreet(getElementTextContent(element, CardEnum.street));
        destination.setHouse(getElementTextContent(element, CardEnum.house));
        card.setDestination(destination);

        return card;
    }


    private String getElementTextContent(Element element, String name) {
        NodeList nodes = element.getElementsByTagName(name);
        Node node = nodes.item(0);
        return node.getTextContent();
    }
}
