package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

import static org.example.App.isDateValid;

public class DataBaseAPI implements DataBase {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Europe/Kiev";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password1234";
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initDB() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS Currency (id INT NOT NULL " +
                    "AUTO_INCREMENT PRIMARY KEY, dateOf VARCHAR(20) " +
                    "NOT NULL, saleRateUSD DOUBLE)");
        }
    }

    @Override
    public void addToBase(ObjectToDataBase object) throws SQLException, IOException {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Currency (dateOf, saleRateUSD) VALUES(?, ?)")) {
            ps.setString(1, object.getDate());
            ps.setDouble(2, object.getSaleRateUSD());
            ps.executeUpdate();
        }
    }

    public void add() throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date in format dd.mm.yyyy : ");
        String date = scanner.nextLine();
        if (!isDateValid(date)){
            System.out.println("Invalid date, try again.");
            add();
        }
        String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date="+date;
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        Base base = new Gson().fromJson(response.toString(), Base.class);
        Base.Exchange usd = base.findUSD();

        ObjectToDataBase object = new ObjectToDataBase(base.getDate(), usd.getSaleRateNB());
        addToBase(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Currency WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void selectAvg() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT AVG(saleRateUSD) FROM Currency")) {

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                System.out.println("Average saleRate is " + rs.getString(1));
            }
        }
    }

    public void selectAll() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Currency")) {
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            }
        }
    }

    @Override
    public void selectMax() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT MAX(saleRateUSD) FROM Currency")) {

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                System.out.println("Maximum saleRate is " + rs.getString(1));
            }
        }
    }

    @Override
    public void selectMin() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT MIN(saleRateUSD) FROM Currency")) {

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                System.out.println("Maximum saleRate is " + rs.getString(1));
            }
        }
    }
}
