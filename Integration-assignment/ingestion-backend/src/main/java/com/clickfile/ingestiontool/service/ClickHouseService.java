package com.clickfile.ingestiontool.service;

import com.clickfile.ingestiontool.model.ConnectionRequest;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClickHouseService {

    public boolean testConnection(ConnectionRequest request) {
        try {
            String url = "jdbc:clickhouse://" + request.getHost() + ":" + request.getPort() + "/" + request.getDatabase();
            Connection connection = DriverManager.getConnection(url, request.getUser(), request.getJwtToken());
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getTables(ConnectionRequest request) {
        List<String> tables = new ArrayList<>();
        try {
            String url = "jdbc:clickhouse://" + request.getHost() + ":" + request.getPort() + "/" + request.getDatabase();
            Connection connection = DriverManager.getConnection(url, request.getUser(), request.getJwtToken());

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");

            while (rs.next()) {
                tables.add(rs.getString(1));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tables;
    }

    // Ingest data from a flat file into a ClickHouse table
    public boolean ingestDataFromFlatFile(ConnectionRequest request, String tableName, String filePath) {
        try {
            String url = "jdbc:clickhouse://" + request.getHost() + ":" + request.getPort() + "/" + request.getDatabase();
            Connection connection = DriverManager.getConnection(url, request.getUser(), request.getJwtToken());

            Statement stmt = connection.createStatement();

            String query = String.format("INSERT INTO %s FORMAT CSV", tableName);
            stmt.execute(String.format("INSERT INTO %s SELECT * FROM file('%s')", tableName, filePath));

            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Export data from ClickHouse table to a flat CSV file
    public boolean exportDataToFlatFile(ConnectionRequest request, String tableName, String outputFilePath) {
        try {
            String url = "jdbc:clickhouse://" + request.getHost() + ":" + request.getPort() + "/" + request.getDatabase();
            Connection connection = DriverManager.getConnection(url, request.getUser(), request.getJwtToken());

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

            FileWriter writer = new FileWriter(outputFilePath);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Write header
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnName(i));
                if (i < columnCount) writer.write(",");
            }
            writer.write("\n");

            // Write rows
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.write(rs.getString(i));
                    if (i < columnCount) writer.write(",");
                }
                writer.write("\n");
            }

            writer.close();
            connection.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
