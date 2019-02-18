package com.tarikaskin.stok.models;


import javax.persistence.*;

@Entity
@Table(name = "FATURA")
public class Fatura implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUST_SEQ")
    @Column(name = "fatura_id")
    private int faturaId;

    @Column(name = "musteri_tc")
    private String musteriTc;

    @Column(name = "fatura_tutari")
    private float faturaTutari;

    @Column(name = "fatura_bilgisi")
    private String faturaBilgisi;

    @Column(name = "kullaniciId")
    private int kullaniciId;

    @Column(name = "adet")
    private int adet;

    @Column(name = "fatura_no")
    private int faturaNo;

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public int getFaturaNo() {
        return faturaNo;
    }

    public void setFaturaNo(int faturaNo) {
        this.faturaNo = faturaNo;
    }

    public int getFaturaId() {
        return faturaId;
    }

    public void setFaturaId(int faturaId) {
        this.faturaId = faturaId;
    }

    public String getMusteriTc() {
        return musteriTc;
    }

    public void setMusteriTc(String musteriTc) {
        this.musteriTc = musteriTc;
    }

    public float getFaturaTutari() {
        return faturaTutari;
    }

    public void setFaturaTutari(float faturaTutari) {
        this.faturaTutari = faturaTutari;
    }

    public String getFaturaBilgisi() {
        return faturaBilgisi;
    }

    public void setFaturaBilgisi(String faturaBilgisi) {
        this.faturaBilgisi = faturaBilgisi;
    }

    public Fatura() {
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public Fatura(String musteriTc, float faturaTutari, String faturaBilgisi, int kullaniciId,int adet, int faturaNo) {
        this.musteriTc = musteriTc;
        this.faturaTutari = faturaTutari;
        this.faturaBilgisi = faturaBilgisi;
        this.kullaniciId = kullaniciId;
        this.adet = adet;
        this.faturaNo = faturaNo;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
