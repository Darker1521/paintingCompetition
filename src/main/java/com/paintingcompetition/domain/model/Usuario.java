package com.paintingcompetition.domain.model;

/**
 * Entidad de Dominio que representa a un Usuario del sistema.
 */
public class Usuario {

    private String id;
    private String nombreUsuario;
    private RolUsuario rol;
    private String clavePublicaHex;

    public Usuario() {
    }

    public Usuario(String id, String nombreUsuario, RolUsuario rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
    }

    public Usuario(String id, String nombreUsuario, RolUsuario rol, String clavePublicaHex) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
        this.clavePublicaHex = clavePublicaHex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public String getClavePublicaHex() {
        return clavePublicaHex;
    }

    public void setClavePublicaHex(String clavePublicaHex) {
        this.clavePublicaHex = clavePublicaHex;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", rol=" + rol +
                '}';
    }
}
