package com.raulballeza;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        connectionAzure conn = new connectionAzure();
        CSV csv = new CSV("usuarios.csv");
        JSON json = new JSON("usuarios.json");
        migrateDBtoCSV(conn, csv);
        migrateCSVtoDB(csv, conn);
        migrateDBtoJSON(conn,json);
        migrateJSONtoDB(json,conn);
        migrateJSONtoCSV(json, csv);
        migrateCSVtoJSON(csv, json);
    }

    private static void migrateDBtoJSON(connectionAzure conn, JSON json) throws SQLException, IOException {
        TransNonTrans TNT = new TransNonTrans(conn);
        List<cliente> rowsDB;
        rowsDB = getDataDB(TNT);
        if (json.Open()) {
            json.Write(rowsDB);
        }
    }

    private static void migrateJSONtoDB(JSON json, connectionAzure conn) throws SQLException, IOException {
        TransNonTrans TNT = new TransNonTrans(conn);
        List<cliente> rowsJSON;
        if (json.Open()) {
            rowsJSON = json.Read();
            TNT.insertData(rowsJSON);
        }
    }

    private static void migrateJSONtoCSV(JSON json, CSV csv) throws IOException {
        List<cliente> rowsJSON;
        if (json.Open()) {
            rowsJSON = json.Read();
            if (csv.Open()) {
                csv.Write(rowsJSON);
            }
        }
    }

    private static void migrateCSVtoJSON(CSV csv, JSON json) throws IOException {
        List<cliente> rowsCSV;
        if (csv.Open()) {
            rowsCSV = csv.Read();
            if (json.Open()) {
                json.Write(rowsCSV);
            }
        }
    }

    private static void migrateCSVtoDB(CSV csv, connectionAzure conn) throws IOException, SQLException {
        TransNonTrans TNT = new TransNonTrans(conn);
        List<cliente> rowsCSV;
        if (csv.Open()) {
            rowsCSV = csv.Read();
            TNT.insertData(rowsCSV);
        }
    }

    private static List<cliente> getDataDB(TransNonTrans TNT) throws SQLException {
        ResultSet rs = TNT.getClientes();
        List<cliente> rows = new ArrayList<cliente>();
        while (rs.next()) {
            rows.add(new cliente(rs.getString("usuario"), rs.getString("contrasena")));
        }
        return rows;
    }

    private static void migrateDBtoCSV(connectionRules conn, CSV csv) throws SQLException, IOException {
        TransNonTrans TNT = new TransNonTrans(conn);
        List<cliente> rowsDB;
        rowsDB = getDataDB(TNT);
        csv.Open();
        csv.Write(rowsDB);
    }

}
