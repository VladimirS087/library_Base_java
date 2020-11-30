package com.example.demo;

import java.sql.*;

public final class App {

    private static final String CREATE_QUERY =
            "CREATE TABLE EXAMPLE (GREETING VARCHAR(6), TARGET VARCHAR(6))";

    private static final String DATA_QUERY =
            "INSERT INTO EXAMPLE VALUES('Hello','World')";

    private App() {
    }
    public static void main(final String[] args) {
        try (Connection db = DriverManager.getConnection("jdbc:h2:mem:")) {
            try (Statement dataQuery = db.createStatement()) {
                dataQuery.execute(CREATE_QUERY);
                dataQuery.execute(DATA_QUERY);
            }
            try (PreparedStatement query =
                         db.prepareStatement("SELECT * FROM EXAMPLE")) {
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    System.out.println(String.format("%s, %s!",
                            rs.getString(1),
                            rs.getString("TARGET")));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("Database connection failure: "
                    + ex.getMessage());
        }
    }
}
