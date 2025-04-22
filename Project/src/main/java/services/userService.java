package services;

import managers.dbManager;
import models.User;

import java.sql.*;

public class userService {

    public static boolean register(String username, String password) {
        if ("admin".equalsIgnoreCase(username)) {
            System.out.println("Cannot register name 'admin'.");
            return false;
        }

        try (Connection conn = dbManager.getConnection()) {
            String check = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(check);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("This user already exists.");
                return false;
            }

            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'user')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    public static User login(String username, String password) {
        try (Connection conn = dbManager.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }
}
