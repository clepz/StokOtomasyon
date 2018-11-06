package com.tarikaskin.stok.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kullanicilar")
public class User {

    @Id
    @Column(name = "kullanici_adi")
    private String username;

    @Column(name = "parola")
    private String password;

    @Column(name = "rol")
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        //sifreyi burada çöz.
        return password;
    }

    public void setPassword(String password) {
        //sifreli kaydet.
        this.password = password;
    }
}
