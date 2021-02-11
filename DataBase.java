package org.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

public interface DataBase {
    void addToBase(ObjectToDataBase object) throws SQLException, IOException;
    void delete(int id) throws SQLException;
    void selectAll() throws SQLException;
    void selectAvg() throws SQLException;
    void selectMax() throws SQLException;
    void selectMin() throws SQLException;
}
