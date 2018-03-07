package com.fatmadelenn.gunluk;

/**
 * Created by ftmdlnn on 22.12.2017.
 */

public class Yildizlanan {
    private int id;
    private boolean durum;
    private String konu;
    public Yildizlanan()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }
}
