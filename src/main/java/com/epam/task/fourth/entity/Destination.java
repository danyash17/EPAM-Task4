package com.epam.task.fourth.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(name = "Destination", namespace = "http://www.example.com/cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class Destination {
    @XmlElement(required = true, namespace = "http://www.example.com/cards")
    private String country;
    @XmlElement(required = true, namespace = "http://www.example.com/cards")
    private String street;
    @XmlElement(required = true, namespace = "http://www.example.com/cards")
    private String house;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination)) return false;
        Destination that = (Destination) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(street, that.street) &&
                Objects.equals(house, that.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, street, house);
    }
}
