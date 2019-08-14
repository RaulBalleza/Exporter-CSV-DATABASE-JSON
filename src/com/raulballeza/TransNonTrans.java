package com.raulballeza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class TransNonTrans {
    private connectionRules connection;

    TransNonTrans(connectionRules connection) {
        this.connection = connection;
    }

    ResultSet getClientes() throws SQLException {
        return connection.Connect().createStatement().executeQuery("SELECT * FROM sesion");
    }

    void insertData(List<cliente> data) throws SQLException {
        String query = " insert into sesion (usuario, contrasena)"
                + " values (?, ?)";
        PreparedStatement preparedStmt = connection.Connect().prepareStatement(query);
        for (cliente cliente : data
        ) {
            try {
                preparedStmt.setString(1, cliente.getUsuario());
                preparedStmt.setString(2, cliente.getContrasena());
                preparedStmt.execute();
            } catch (Exception e) {
                System.err.println("HA OCURRIDO UN ERROR!");
                System.err.println(e.getMessage());
            }
        }

        connection.Close();
    }
}
