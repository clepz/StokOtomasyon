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
    private String tc;

    @Column(name = "ad")
    private String ad;

    @Column(name = "soyad")
    private String soyad;

    @Column(name = "adres")
    private String adres;

    @Column(name = "cinsiyet")
    private String cinsiyet;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "ev_telefon")
    private String ev_telefonu;

    @Column(name = "musteri_notu")
    private String musteri_notu;

    public Musteri(String tc, String ad, String soyad, String adres, String cinsiyet, String telefon, String ev_telefonu, String musteri_notu) {
        this.tc = tc;
        this.ad = ad;
        this.soyad = soyad;
        this.adres = adres;
        this.cinsiyet = cinsiyet;
        this.telefon = telefon;
        this.ev_telefonu = ev_telefonu;
        this.musteri_notu = musteri_notu;
    }

    public Musteri() {
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
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

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEv_telefonu() {
        return ev_telefonu;
    }

    public void setEv_telefonu(String ev_telefonu) {
        this.ev_telefonu = ev_telefonu;
    }

    public String getMusteri_notu() {
        return musteri_notu;
    }

    public void setMusteri_notu(String musteri_notu) {
        this.musteri_notu = musteri_notu;
    }
}
