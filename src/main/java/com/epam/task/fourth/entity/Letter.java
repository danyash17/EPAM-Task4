package com.epam.task.fourth.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(name = "Letter", namespace = "http://www.example.com/cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class Letter extends Postcard {
    @XmlAttribute(required = true)
    private String author;

    public Letter() {
    }

    public Letter(double cost, String status, String country, String street, String house, String author) {
        super(cost, status, country, street, house);
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Letter)) {
            return false;
        }
        Letter letter = (Letter) o;
        return Objects.equals(author, letter.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
