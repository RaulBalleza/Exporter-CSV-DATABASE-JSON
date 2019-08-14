package com.raulballeza;

import java.sql.Connection;
import java.sql.SQLException;

public interface connectionRules {
    public Connection Connect() throws SQLException;
    public void Close() throws SQLException;
}
