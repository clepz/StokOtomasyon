package com.tarikaskin.stok.models;

public class SatisHazirlik {

    private String barkod;
    private int satilanAdet;
    private Musteri musteri;
    private int kullaniciId;

    public SatisHazirlik(String barkod, int satilanAdet, Musteri musteri, int kullaniciId) {
        this.barkod = barkod;
        this.satilanAdet = satilanAdet;
        this.musteri = musteri;
        this.kullaniciId = kullaniciId;
    }

    public SatisHazirlik() {
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public int getSatilanAdet() {
        return satilanAdet;
    }

    public void setSatilanAdet(int satilanAdet) {
        this.satilanAdet = satilanAdet;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }
}
