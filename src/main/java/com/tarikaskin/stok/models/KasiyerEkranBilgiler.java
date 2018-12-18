package com.tarikaskin.stok.models;

import java.util.List;

public class KasiyerEkranBilgiler {

    private Musteri musteri;
    private List<Kasiyer> kasiyerList;
    private List<Fatura> faturaList;


    public KasiyerEkranBilgiler(Musteri musteri, List<Kasiyer> kasiyerList, List<Fatura> faturaList) {
        this.musteri = musteri;
        this.kasiyerList = kasiyerList;
        this.faturaList = faturaList;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public List<Kasiyer> getKasiyerList() {
        return kasiyerList;
    }

    public void setKasiyerList(List<Kasiyer> kasiyerList) {
        this.kasiyerList = kasiyerList;
    }

    public List<Fatura> getFaturaList() {
        return faturaList;
    }

    public void setFaturaList(List<Fatura> faturaList) {
        this.faturaList = faturaList;
    }
}
