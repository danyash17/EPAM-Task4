package com.epam.task.fourth.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;

@XmlRootElement(name = "cards", namespace = "http://www.example.com/cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cards {
    @XmlElements({
            @XmlElement(name = "letter", namespace = "http://www.example.com/cards", type = Letter.class),
            @XmlElement(name = "postcard", namespace = "http://www.example.com/cards", type = Postcard.class)
    })
    private ArrayList<Postcard> cardList = new ArrayList<>();

    public void addCard(Postcard card) {
        cardList.add(card);
    }

    public Cards(ArrayList<Postcard> cardList) {
        this.cardList = cardList;
    }

    public Cards() {
    }

    public ArrayList<Postcard> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Postcard> cardList) {
        this.cardList = cardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cards)) {
            return false;
        }
        Cards cards = (Cards) o;
        return Objects.equals(cardList, cards.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardList);
    }
}
