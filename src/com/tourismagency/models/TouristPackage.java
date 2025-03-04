package com.tourismagency.models;

import java.util.List;
import java.util.Objects;

public class TouristPackage {
    private String destination;
    private int duration;
    private double price;
    private String description;
    private List<String> mainAttractions;

    public TouristPackage(String destination, int duration, double price, String description, List<String> mainAttractions) {
        this.destination = destination;
        this.duration = duration;
        this.price = price;
        this.description = description;
        this.mainAttractions = mainAttractions;
    }

    public String getDestination() {
        return destination;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMainAttractions() {
        return mainAttractions;
    }

    @Override
    public String toString() {
        return "TouristPackage{" +
                "destination='" + destination + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", mainAttractions=" + mainAttractions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouristPackage that = (TouristPackage) o;
        return duration == that.duration &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(description, that.description) &&
                Objects.equals(mainAttractions, that.mainAttractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, duration, price, description, mainAttractions);
    }
}
