package com.tarikaskin.stok.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOLUMNO")
public class Bolum {

    @Column(name = "bolum_no")
    @Id
    private String bolumNo;

    @Column(name = "bolum_adi")
    private String bolumAdi;

    public String getBolumNo() {
        return bolumNo;
    }

    public void setBolumNo(String bolumNo) {
        this.bolumNo = bolumNo;
    }

    public String getBolumAdi() {
        return bolumAdi;
    }

    public Bolum(String bolumNo) {
        this.bolumNo = bolumNo;
    }

    public Bolum(String bolumNo, String bolumAdi) {
        this.bolumNo = bolumNo;
        this.bolumAdi = bolumAdi;
    }

    public void setBolumAdi(String bolumAdi) {
        this.bolumAdi = bolumAdi;
    }
}
