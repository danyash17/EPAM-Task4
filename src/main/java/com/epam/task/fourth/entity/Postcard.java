package com.epam.task.fourth.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(name = "Postcard")
@XmlAccessorType(XmlAccessType.FIELD)
public class Postcard {
    @XmlElement(required = true, namespace = "http://www.example.com/cards")
    private double cost;
    @XmlElement(required = true, namespace = "http://www.example.com/cards")
    private String status;
    @XmlElement(required = true, namespace = "http://www.example.com/cards")
    private Destination destination = new Destination();

    public Postcard() {
    }

    public Postcard(double cost, String status, String country, String street, String house) {
        this.cost = cost;
        this.status = status;
        this.destination.setCountry(country);
        this.destination.setStreet(street);
        this.destination.setHouse(house);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Postcard)) {
            return false;
        }
        Postcard postcard = (Postcard) o;
        return Double.compare(postcard.cost, cost) == 0 &&
                Objects.equals(status, postcard.status) &&
                Objects.equals(destination, postcard.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, status, destination);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
