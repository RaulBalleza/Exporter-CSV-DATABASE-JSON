package com.raulballeza;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface archivo {
    boolean Open() throws FileNotFoundException;
    void Close();
    List<cliente> Read() throws IOException;
    void Write(List<cliente> clientes) throws IOException;
}
