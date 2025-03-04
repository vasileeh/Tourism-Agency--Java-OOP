package com.tourismagency.interfaces;

public interface Searchable {
    void searchByDestination(String destination);
    void searchByPriceRange(double minPrice, double maxPrice);
}
