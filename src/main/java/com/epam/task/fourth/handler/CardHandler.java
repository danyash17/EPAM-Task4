package com.epam.task.fourth.handler;

import com.epam.task.fourth.entity.Destination;
import com.epam.task.fourth.entity.Letter;
import com.epam.task.fourth.entity.Postcard;
import com.epam.task.fourth.enumerations.CardEnum;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class CardHandler extends DefaultHandler {
    private ArrayList<Postcard> cards;
    private Postcard current;
    private CardEnum currentEnum;

    public CardHandler() {
        cards = new ArrayList<>();
    }

    public ArrayList<Postcard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Postcard> cards) {
        this.cards = cards;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (localName) {
            case "postcard":
                current = new Postcard();
                break;
            case "letter":
                Letter authorLetter = new Letter();
                String author = attributes.getValue("author");
                authorLetter.setAuthor(author);
                current = authorLetter;
                break;
            default:
                currentEnum = CardEnum.valueOf(localName.toUpperCase());
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("postcard".equals(localName) || "letter".equals(localName)) {
            cards.add(current);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String elementString = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case CARDS:
                    break;
                case COST:
                    current.setCost(Double.parseDouble(elementString));
                    break;
                case STATUS:
                    current.setStatus(elementString);
                    break;
                case DESTINATION:
                    break;
                case COUNTRY:
                    Postcard countryPostcard = (Postcard) current;
                    Destination countryPostcardDestination;
                    if (countryPostcard.getDestination() == null) {
                        countryPostcardDestination = new Destination();
                    } else {
                        countryPostcardDestination = countryPostcard.getDestination();
                    }
                    countryPostcardDestination.setCountry(elementString);
                    countryPostcard.setDestination(countryPostcardDestination);
                    current = countryPostcard;
                    break;
                case STREET:
                    Postcard streetPostcard = (Postcard) current;
                    Destination streetPostcardDestination;
                    if (streetPostcard.getDestination() == null) {
                        streetPostcardDestination = new Destination();
                    } else {
                        streetPostcardDestination = streetPostcard.getDestination();
                    }
                    streetPostcardDestination.setStreet(elementString);
                    streetPostcard.setDestination(streetPostcardDestination);
                    current = streetPostcard;
                    break;
                case HOUSE:
                    Postcard housePostcard = (Postcard) current;
                    Destination housePostcardDestination;
                    if (housePostcard.getDestination() == null) {
                        housePostcardDestination = new Destination();
                    } else {
                        housePostcardDestination = housePostcard.getDestination();
                    }
                    housePostcardDestination.setHouse(elementString);
                    housePostcard.setDestination(housePostcardDestination);
                    current = housePostcard;
                    break;
            }
        }
        currentEnum = null;
    }
}
