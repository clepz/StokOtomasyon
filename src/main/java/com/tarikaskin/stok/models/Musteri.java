package com.tarikaskin.stok.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "musteri")
public class Musteri {

    @Id
    @Column(name = "tc")
    private int tc;

    @Column(name = "ad")
    private String ad;

    @Column(name = "soyad")
    private String soyad;

    @Column(name = "adres")
    private String adres;

    @Column(name = "cinsiyet")
    private char cinsiyet;

    @Column(name = "telefon")
    private int telefon;

    @Column(name = "ev_telefonu")
    private int ev_telefonu;

    @Column(name = "musteri_notu")
    private String musteri_notu;

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public char getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(char cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public int getEv_telefonu() {
        return ev_telefonu;
    }

    public void setEv_telefonu(int ev_telefonu) {
        this.ev_telefonu = ev_telefonu;
    }

    public String getMusteri_notu() {
        return musteri_notu;
    }

    public void setMusteri_notu(String musteri_notu) {
        this.musteri_notu = musteri_notu;
    }
}
