package com.raulballeza;

public class cliente {
    private String usuario;
    private String contrasena;

    cliente(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
