package com.raulballeza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV implements archivo {

    private static String ruta;
    private BufferedReader csvReader;

    CSV(String ruta) {
        CSV.ruta = ruta;
    }

    public boolean Open() {
        try {
            csvReader = new BufferedReader(new FileReader(ruta));
            System.out.println("El archivo ha sido abierto correctamente");
            return true;
        } catch (Exception e) {
            System.err.print("Error: ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void Close() {

    }

    public List<cliente> Read() throws IOException {
        List<cliente> rows = new ArrayList<>();
        String row;

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            rows.add(new cliente(data[0], data[1]));
        }

        return rows;
    }

    public void Write(List<cliente> clientes) throws IOException {
        try (FileWriter csvWriter = new FileWriter(ruta)) {
            for (cliente cl : clientes
            ) {
                csvWriter.append(cl.getUsuario());
                csvWriter.append(",");
                csvWriter.append(cl.getContrasena());
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
