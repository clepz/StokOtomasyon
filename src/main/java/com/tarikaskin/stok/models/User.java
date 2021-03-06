package com.tarikaskin.stok.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "kullanicilar")
public class User implements Serializable {

    @Id
    @Column(name = "kullanici_adi")
    private String username;

    @Column(name = "parola")
    private String password;

    @Column(name = "rol")
    private String rol;

    @Column(name = "kullanici_id")
    private int kullaniciId;

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

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
