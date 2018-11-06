package com.tarikaskin.stok.models;

import javax.persistence.*;

@Entity
@Table(name = "bundle")
public class Bundle {

    @Id
    @Column
    private String barkod;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Urun urun;

    @Column
    private String bolum_no;

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    public String getBolum_no() {
        return bolum_no;
    }

    public void setBolum_no(String bolum_no) {
        this.bolum_no = bolum_no;
    }
}