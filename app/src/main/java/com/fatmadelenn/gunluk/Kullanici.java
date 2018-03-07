package com.fatmadelenn.gunluk;

/**
 * Created by Fatih on 13.12.2017.
 */

public class Kullanici {
    private int id;
    private String ad_soyad;
    private String kullanici_adi;
    private String sifre;
    private String soru;
    private String cevap;

    public Kullanici(String ad_soyad,String kullanici_adi,String sifre,String soru,String cevap){
        this.ad_soyad=ad_soyad;
        this.kullanici_adi=kullanici_adi;
        this.sifre=sifre;
        this.soru=soru;
        this.cevap=cevap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd_soyad() {
        return ad_soyad;
    }

    public void setAd_soyad(String ad_soyad) {
        this.ad_soyad = ad_soyad;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }
}
