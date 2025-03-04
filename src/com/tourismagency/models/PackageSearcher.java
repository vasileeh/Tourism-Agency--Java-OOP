package com.tourismagency.models;

import com.tourismagency.interfaces.Searchable;
import com.tourismagency.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageSearcher implements Searchable {

    @Override
    public void searchByDestination(String destination) {
        String sql = "SELECT * FROM tourist_packages WHERE destination = ?";
        try (Connection conn = DatabaseUtils.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destination);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Found package: " + rs.getString("destination") +
                        ", Duration: " + rs.getInt("duration") +
                        ", Price: " + rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT * FROM tourist_packages WHERE price BETWEEN ? AND ?";
        try (Connection conn = DatabaseUtils.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, minPrice);
            pstmt.setDouble(2, maxPrice);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Found package: " + rs.getString("destination") +
                        ", Duration: " + rs.getInt("duration") +
                        ", Price: " + rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
