package com.fatmadelenn.gunluk;

/**
 * Created by ftmdlnn on 16.12.2017.
 */

public class Sayfa {
    private int id,bolum_id;
    private String konu,icerik;
    private String tarih;

    private int yildiz_durumu;

    public int getYildiz_durumu() {
        return yildiz_durumu;
    }

    public void setYildiz_durumu(int yildiz_durumu) {
        this.yildiz_durumu = yildiz_durumu;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Sayfa(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBolum_id() {
        return bolum_id;
    }

    public void setBolum_id(int bolum_id) {
        this.bolum_id = bolum_id;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
}
