package com.raulballeza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionAzure implements connectionRules {
    private Connection conexion;

    @Override
    public Connection Connect() throws SQLException {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://ifix.database.windows.net;databaseName=ifixdb;user=raulballeza;password=ifixdb_ads_68;");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Conexion realizada correctamente");
        return conexion;
    }

    @Override
    public void Close() throws SQLException {
        conexion.close();
    }
}
