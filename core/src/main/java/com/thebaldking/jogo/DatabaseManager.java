package com.thebaldking.jogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost/the_bald_king";
    private static final String USER = "root";
    private static final String PASSWORD = " ";

    public static void saveScore(String playerName, int score, float survivalTime) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO high_scores (name, score, survivalTime) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, playerName);
                stmt.setInt(2, score);
                stmt.setFloat(3, survivalTime);
                stmt.executeUpdate();
            }
        }
    }
}

