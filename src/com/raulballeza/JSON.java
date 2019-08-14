package com.raulballeza;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class JSON implements archivo {

    private static String ruta;
    private BufferedReader jsonReader;

    JSON(String ruta) {
        JSON.ruta = ruta;
    }

    @Override
    public boolean Open() throws FileNotFoundException {
        try {
            jsonReader = new BufferedReader(new FileReader(ruta));
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

    @Override
    public List<cliente> Read() throws IOException {
        JSONParser jsonParser = new JSONParser();
        List<cliente> clientes = new ArrayList<>();
        try (FileReader reader = new FileReader(ruta)) {

            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;

            for (Object o : employeeList
            ) {
                clientes.add(parseData((JSONObject) o));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return clientes;
    }

    private static cliente parseData(JSONObject data) {

        JSONObject dataObject = (JSONObject) data.get("cliente");
        String usuario = (String) dataObject.get("usuario");
        String contrasena = (String) dataObject.get("contrasena");

        return new cliente(usuario,contrasena);
    }

    @Override
    public void Write(List<cliente> data) throws IOException {
        JSONArray dataArray = new JSONArray();
        for (cliente cliente : data
        ) {
            JSONObject obj = new JSONObject();
            obj.put("usuario", cliente.getUsuario());
            obj.put("contrasena", cliente.getContrasena());
            JSONObject dataObject = new JSONObject();
            dataObject.put("cliente", obj);
            dataArray.add(dataObject);
        }
        try (FileWriter file = new FileWriter(ruta)) {

            file.write(dataArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
