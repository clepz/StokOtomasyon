package com.tarikaskin.stok.models;

public class Kasiyer implements Cloneable {

    private String urun;
    private int miktar;
    private float fiyat;
    private float tutar;
    private String seriNO;
    private String musteriTc;

    public Kasiyer() {
    }

    public Kasiyer(String urun, int miktar, float fiyat, float tutar, String seriNO,String tc) {
        this.urun = urun;
        this.miktar = miktar;
        this.fiyat = fiyat;
        this.tutar = tutar;
        this.seriNO = seriNO;
        this.musteriTc = tc;
    }

    public String getUrun() {
        return urun;
    }

    public void setUrun(String urun) {
        this.urun = urun;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public float getFiyat() {
        return fiyat;
    }

    public void setFiyat(float fiyat) {
        this.fiyat = fiyat;
    }

    public float getTutar() {
        return tutar;
    }

    public void setTutar(float tutar) {
        this.tutar = tutar;
    }

    public String getSeriNO() {
        return seriNO;
    }

    public void setSeriNO(String seriNO) {
        this.seriNO = seriNO;
    }

    public String getMusteriTc() {
        return musteriTc;
    }

    public void setMusteriTc(String musteriTc) {
        this.musteriTc = musteriTc;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
