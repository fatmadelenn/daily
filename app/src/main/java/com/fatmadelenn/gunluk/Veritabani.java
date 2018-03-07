package com.fatmadelenn.gunluk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.DAY_OF_WEEK;


public class Veritabani extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="gunlugum.db";
    //Tablolar
    private static final String TABLE_KULLANICILAR="kullanicilar";
    private static final String TABLE_BOLUMLER="bolumler";
    private static final String TABLE_SAYFALAR="sayfalar";
    private static final String TABLE_TIKLANAN_BOLUM="tiklanan";
  //  private static final String TABLE_YILDIZLANAN="yildizlanan";
    private static final String TABLE_TIKLANAN_SAYFA="ayrinti";

    //Kullanıcı Tablosu
    private static final String SUTUN_ID="kullanici_id";
    private static final String SUTUN_AD_SOYAD="ad_soyad";
    private static final String SUTUN_KULLANICI_ADI="kullanici_adi";
    private static final String SUTUN_SIFRE="sifre";
    private static final String SUTUN_GUVENLIK_SORU="soru";
    private static final String SUTUN_GUVENLIK_CEVAP="cevap";

    //Bolumler kolonları
    private static final String SUTUN_BOLUM_ID="bolum_id";
    private static final String SUTUN_BOLUM_ADI="bolum_adi";

    //Sayfa tablosu

    private static final String SUTUN_SAYFA_BOLUMU="sayfa_bolumu";
    private static final String SUTUN_SAYFA_ID="sayfa_id";
    private static final String SUTUN_SAYFA_KONU="sayfa_konu";
    private static final String SUTUN_SAYFA_ICERİK="sayfa_icerik";
    private static final String SUTUN_SAYFA_TARIH="sayfa_tarih";
    private static final String SUTUN_SAYFA_YILDIZ_DURUMU="yildiz_durumu";

    //yildizlanan
    //private static final String SUTUN_YILDIZLANAN_SAYFA_ID="yildizlanan_id";
    //private static final String SUTUN_YILDIZLANAN_SAYFA="yildizlanan_sayfa";
    //private static final String SUTUN_YILDIZLANMA_DURUMU="durum";

    String tablo_kulanicilar="CREATE TABLE "+TABLE_KULLANICILAR+"("
            +SUTUN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +SUTUN_AD_SOYAD+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_ADI+" TEXT NOT NULL,"
            +SUTUN_SIFRE+" TEXT NOT NULL,"
            +SUTUN_GUVENLIK_SORU+" TEXT NOT NULL,"
            +SUTUN_GUVENLIK_CEVAP+" TEXT NOT NULL"+")";


    String tablo_bolumler="CREATE TABLE "+TABLE_BOLUMLER+"("
            +SUTUN_BOLUM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +SUTUN_BOLUM_ADI+" TEXT NOT NULL"+")";

    String tablo_sayfalar="CREATE TABLE "+TABLE_SAYFALAR+"("
            +SUTUN_SAYFA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +SUTUN_SAYFA_KONU+" TEXT NOT NULL,"
            +SUTUN_SAYFA_ICERİK+" TEXT NOT NULL,"
            +SUTUN_SAYFA_BOLUMU+" INTEGER NOT NULL, "
            +SUTUN_SAYFA_TARIH+" TEXT NOT NULL,"
            +SUTUN_SAYFA_YILDIZ_DURUMU+" INTEGER DEFAULT 0"+")";

    String tablo_tiklanan_sayfa="CREATE TABLE "+TABLE_TIKLANAN_SAYFA+"("
            +SUTUN_SAYFA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +SUTUN_SAYFA_KONU+" TEXT NOT NULL,"
            +SUTUN_SAYFA_ICERİK+" TEXT NOT NULL,"
            +SUTUN_SAYFA_BOLUMU+" INTEGER NOT NULL, "
            +SUTUN_SAYFA_TARIH+" TEXT NOT NULL,"
            +SUTUN_SAYFA_YILDIZ_DURUMU+" INTEGER DEFAULT 0"+")";

    String tablo_tiklanan="CREATE TABLE "+TABLE_TIKLANAN_BOLUM+"("
            +SUTUN_BOLUM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +SUTUN_BOLUM_ADI+" TEXT NOT NULL"+")";

/*
    String tablo_yildizlanan="CREATE TABLE "+TABLE_YILDIZLANAN+"("
            +SUTUN_YILDIZLANAN_SAYFA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +SUTUN_YILDIZLANAN_SAYFA+" TEXT NOT NULL,"
            +SUTUN_YILDIZLANMA_DURUMU+" flag INTEGER DEFAULT 0"+")";*/

    private Context context;
    private SistemUyarilari sistemUyarilari;


    public Veritabani(Context context) {
        super(context, DATABASE_NAME,null,1);
        this.context=context;
        sistemUyarilari =new SistemUyarilari(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Kullanıcılar Tablosu oluştur
        db.execSQL(tablo_kulanicilar);
        db.execSQL(tablo_bolumler);
        db.execSQL(tablo_sayfalar);
        db.execSQL(tablo_tiklanan);
        db.execSQL(tablo_tiklanan_sayfa);
        //db.execSQL(tablo_yildizlanan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_KULLANICILAR);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BOLUMLER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SAYFALAR);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIKLANAN_BOLUM);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIKLANAN_SAYFA);
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_YILDIZLANAN);
        onCreate(db);
    }

    public void kullaniciEkle(Kullanici kullanici){
        sistemUyarilari.uyariBaslat("","İşleminiz devam ediyor. Lütfen bekleyin");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUTUN_AD_SOYAD,kullanici.getAd_soyad());
        values.put(SUTUN_KULLANICI_ADI,kullanici.getKullanici_adi());
        values.put(SUTUN_SIFRE,kullanici.getSifre());
        values.put(SUTUN_GUVENLIK_SORU,kullanici.getSoru());
        values.put(SUTUN_GUVENLIK_CEVAP,kullanici.getCevap());
        db.insert(TABLE_KULLANICILAR,null,values);
        sistemUyarilari.uyariDurdur();
        sistemUyarilari.kayitBasarili();
        db.close();
    }

    public List<Kullanici> tumKullanicilar(){
        List<Kullanici> kullanicilar=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String[]sutunlar={SUTUN_ID,SUTUN_AD_SOYAD,SUTUN_KULLANICI_ADI,SUTUN_SIFRE,SUTUN_GUVENLIK_SORU,SUTUN_GUVENLIK_CEVAP};
        Cursor cursor=db.query(TABLE_KULLANICILAR,sutunlar,null,null,null,null,SUTUN_ID+" DESC");
        while(cursor.moveToNext()){
            Kullanici kullanici=new Kullanici(
                    cursor.getString(cursor.getColumnIndex(SUTUN_AD_SOYAD)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_ADI)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_SIFRE)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_GUVENLIK_SORU)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_GUVENLIK_CEVAP))
                    );
            kullanici.setId(Integer.valueOf(cursor.getColumnIndex(SUTUN_ID)));
            kullanicilar.add(kullanici);

        }
        return kullanicilar;
    }



    public void girisYapanKullaniciKaydet(Kullanici kullanici){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUTUN_AD_SOYAD,kullanici.getAd_soyad());
        values.put(SUTUN_KULLANICI_ADI,kullanici.getKullanici_adi());
        values.put(SUTUN_SIFRE,kullanici.getSifre());
        values.put(SUTUN_GUVENLIK_SORU,kullanici.getSoru());
        values.put(SUTUN_GUVENLIK_CEVAP,kullanici.getCevap());
        db.insert(TABLE_KULLANICILAR,null, values);
        db.close();
    }

    public Kullanici girisYapanKullanici(){
        SQLiteDatabase db=this.getWritableDatabase();
        Kullanici kullanici;
        String[]sutunlar={SUTUN_ID,SUTUN_AD_SOYAD,SUTUN_KULLANICI_ADI,SUTUN_SIFRE,SUTUN_GUVENLIK_SORU,SUTUN_GUVENLIK_CEVAP};
        Cursor cursor=db.query(TABLE_KULLANICILAR,sutunlar,null,null,null,null,null);
        while(cursor.moveToNext()){
            kullanici=new Kullanici(
                    cursor.getString(cursor.getColumnIndex(SUTUN_AD_SOYAD)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_ADI)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_SIFRE)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_GUVENLIK_SORU)),
                    cursor.getString(cursor.getColumnIndex(SUTUN_GUVENLIK_CEVAP))
            );
            kullanici.setId(Integer.valueOf(cursor.getColumnIndex(SUTUN_ID)));
            return kullanici;

        }
        return null;
    }


    public void girisYapanlarTabloSil() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_KULLANICILAR);
            db.execSQL(tablo_kulanicilar);
        } catch (SQLException e) {

        }
    }
    public void sifreGuncelle(String sifre){
        Kullanici kullanici=girisYapanKullanici();
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUTUN_AD_SOYAD,kullanici.getAd_soyad());
        values.put(SUTUN_KULLANICI_ADI,kullanici.getKullanici_adi());
        values.put(SUTUN_SIFRE,sifre);
        values.put(SUTUN_GUVENLIK_SORU,kullanici.getSoru());
        values.put(SUTUN_GUVENLIK_CEVAP,kullanici.getCevap());
        db.update(TABLE_KULLANICILAR,values,null,null);
        db.close();

    }
    public void bolumEkle(String bolum_adi){
        sistemUyarilari.uyariBaslat("","İşleminiz devam ediyor. Lütfen bekleyin");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUTUN_BOLUM_ADI,bolum_adi);
        db.insert(TABLE_BOLUMLER,null,values);
        sistemUyarilari.uyariDurdur();
        db.close();
    }
    public List<Bolum> tumBolumleriGetir(){
        List<Bolum> bolumler=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String[] sutunlar={SUTUN_BOLUM_ID,SUTUN_BOLUM_ADI};
        Cursor cursor=db.query(TABLE_BOLUMLER,sutunlar,null,null,null,null,null);
        while(cursor.moveToNext()) {
            Bolum bolum = new Bolum();
            bolum.setId(cursor.getInt(cursor.getColumnIndex(SUTUN_BOLUM_ID)));
            bolum.setAd(cursor.getString(cursor.getColumnIndex(SUTUN_BOLUM_ADI)));
            bolumler.add(bolum);
        }

        return bolumler;

    }

    public void sayfaEkle(String sayfa_konu,String sayfa_icerik){
        sistemUyarilari.uyariBaslat("","İşleminiz devam ediyor. Lütfen bekleyin...");
        SQLiteDatabase db=this.getWritableDatabase();
        String aylar[]={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
        Calendar simdi=Calendar.getInstance();
            ContentValues values=new ContentValues();
            values.put(SUTUN_SAYFA_BOLUMU,tiklananGetir().getId());
            values.put(SUTUN_SAYFA_KONU,sayfa_konu);
            values.put(SUTUN_SAYFA_ICERİK,sayfa_icerik);
            values.put(SUTUN_SAYFA_TARIH,simdi.get(Calendar.DATE)+" "+aylar[simdi.get(Calendar.MONTH)]+" "+simdi.get(Calendar.YEAR)
                    +"   "+simdi.get(Calendar.HOUR)+":"+simdi.get(Calendar.MINUTE)+" "+simdi.get(DAY_OF_WEEK));
            values.put(SUTUN_SAYFA_YILDIZ_DURUMU,0);
            db.insert(TABLE_SAYFALAR,null,values);
            sistemUyarilari.uyariDurdur();
            sistemUyarilari.onay("Sayfa Eklendi!");
            db.close();


    }

    public List<Sayfa> tumSayfalariGetir(){
            List<Sayfa> sayfalar=new ArrayList<Sayfa>();
            SQLiteDatabase db=this.getReadableDatabase();
            String[]sutunlar={SUTUN_SAYFA_ID,SUTUN_SAYFA_KONU,SUTUN_SAYFA_ICERİK,SUTUN_SAYFA_BOLUMU,SUTUN_SAYFA_TARIH,SUTUN_SAYFA_YILDIZ_DURUMU};
            Cursor cursor=db.query(TABLE_SAYFALAR,sutunlar,null,null,null,null,null);
            while(cursor.moveToNext()){
                if(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_BOLUMU))==tiklananGetir().getId()){
                    Sayfa sayfa=new Sayfa();
                    sayfa.setId(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_ID)));
                    sayfa.setKonu(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_KONU)));
                    sayfa.setIcerik(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_ICERİK)));
                    sayfa.setBolum_id(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_BOLUMU)));
                    sayfa.setTarih(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_TARIH)));
                    sayfa.setYildiz_durumu(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_YILDIZ_DURUMU)));
                    sayfalar.add(sayfa);
                }
            }

            return sayfalar;


    }

    private void tiklananBosalt(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIKLANAN_BOLUM);
            db.execSQL(tablo_tiklanan);
        } catch (SQLException e) {

        }
    }
    public void tiklananEkle(int deger,String ad){
        tiklananBosalt();
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUTUN_BOLUM_ID,deger);
        values.put(SUTUN_BOLUM_ADI,ad);
        db.insert(TABLE_TIKLANAN_BOLUM,null,values);
        db.close();
    }
    public Bolum tiklananGetir(){
        SQLiteDatabase db=this.getWritableDatabase();
        Bolum bolum=new Bolum();
        String[]sutunlar={SUTUN_BOLUM_ID,SUTUN_BOLUM_ADI};
        Cursor cursor=db.query(TABLE_TIKLANAN_BOLUM,sutunlar,null,null,null,null,null);
        while(cursor.moveToNext()){
            bolum.setAd(cursor.getString(cursor.getColumnIndex(SUTUN_BOLUM_ADI)));
            bolum.setId(cursor.getInt(cursor.getColumnIndex(SUTUN_BOLUM_ID)));
        }

        return bolum;
    }
    private void ayrintiBosalt(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIKLANAN_SAYFA);
            db.execSQL(tablo_tiklanan_sayfa);
        } catch (SQLException e) {
        }
    }
    public void ayrintiEkle(Sayfa sayfa){
        ayrintiBosalt();
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUTUN_SAYFA_ID,sayfa.getId());
        values.put(SUTUN_SAYFA_KONU,sayfa.getKonu());
        values.put(SUTUN_SAYFA_ICERİK, sayfa.getIcerik());
        values.put(SUTUN_SAYFA_BOLUMU, sayfa.getBolum_id());
        values.put(SUTUN_SAYFA_TARIH, sayfa.getTarih());
        values.put(SUTUN_SAYFA_YILDIZ_DURUMU, sayfa.getYildiz_durumu());
        db.insert(TABLE_TIKLANAN_SAYFA,null,values);
        db.close();
    }
    public void sayfaSil(){
        Sayfa sayfa=tiklananSayfaGetir();
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_SAYFALAR,SUTUN_SAYFA_ID+"="+sayfa.getId(),null);
        db.close();
    }
    public Sayfa tiklananSayfaGetir(){
        SQLiteDatabase db=this.getWritableDatabase();
        Sayfa sayfa=new Sayfa();
        String[]sutunlar={SUTUN_SAYFA_ID,SUTUN_SAYFA_KONU,SUTUN_SAYFA_ICERİK,SUTUN_SAYFA_BOLUMU,SUTUN_SAYFA_TARIH,SUTUN_SAYFA_YILDIZ_DURUMU};
        Cursor cursor=db.query(TABLE_TIKLANAN_SAYFA,sutunlar,null,null,null,null,null);
        while(cursor.moveToNext()){
            sayfa.setId(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_ID)));
            sayfa.setKonu(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_KONU)));
            sayfa.setTarih(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_TARIH)));
            sayfa.setIcerik(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_ICERİK)));
            sayfa.setBolum_id(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_BOLUMU)));
            sayfa.setYildiz_durumu(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_YILDIZ_DURUMU)));

        }

        return sayfa;
    }

    public void yildizla(){
        Sayfa sayfa=tiklananSayfaGetir();
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUTUN_SAYFA_ID,sayfa.getId());
        values.put(SUTUN_SAYFA_KONU,sayfa.getKonu());
        values.put(SUTUN_SAYFA_ICERİK,sayfa.getIcerik());
        values.put(SUTUN_SAYFA_BOLUMU,sayfa.getBolum_id());
        values.put(SUTUN_SAYFA_TARIH,sayfa.getTarih());
        values.put(SUTUN_SAYFA_YILDIZ_DURUMU,1);
        db.update(TABLE_SAYFALAR,values,SUTUN_SAYFA_ID+" = "+sayfa.getId(),null);
        db.close();
    }
    public void yildizVazgec(){

        SQLiteDatabase db=this.getWritableDatabase();
        Sayfa sayfa=tiklananSayfaGetir();

        ContentValues values=new ContentValues();
        values.put(SUTUN_SAYFA_ID,sayfa.getId());
        values.put(SUTUN_SAYFA_KONU,sayfa.getKonu());
        values.put(SUTUN_SAYFA_ICERİK,sayfa.getIcerik());
        values.put(SUTUN_SAYFA_BOLUMU,sayfa.getBolum_id());
        values.put(SUTUN_SAYFA_TARIH,sayfa.getTarih());
        values.put(SUTUN_SAYFA_YILDIZ_DURUMU,0);
        db.update(TABLE_SAYFALAR,values,SUTUN_SAYFA_ID+" = "+sayfa.getId(),null);
        db.close();
    }
    public List<Sayfa> veritabanindakiTumSayfalariGetir(){
        List<Sayfa> sayfalar=new ArrayList<Sayfa>();
        SQLiteDatabase db=this.getReadableDatabase();
        String[]sutunlar={SUTUN_SAYFA_ID,SUTUN_SAYFA_KONU,SUTUN_SAYFA_ICERİK,SUTUN_SAYFA_BOLUMU,SUTUN_SAYFA_TARIH,SUTUN_SAYFA_YILDIZ_DURUMU};
        Cursor cursor=db.query(TABLE_SAYFALAR,sutunlar,null,null,null,null,null);
        while(cursor.moveToNext()){
                Sayfa sayfa=new Sayfa();
                sayfa.setId(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_ID)));
                sayfa.setKonu(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_KONU)));
                sayfa.setIcerik(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_ICERİK)));
                sayfa.setBolum_id(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_BOLUMU)));
                sayfa.setTarih(cursor.getString(cursor.getColumnIndex(SUTUN_SAYFA_TARIH)));
                sayfa.setYildiz_durumu(cursor.getInt(cursor.getColumnIndex(SUTUN_SAYFA_YILDIZ_DURUMU)));
                sayfalar.add(sayfa);
        }

        return sayfalar;


    }
    public List<Sayfa> yildizlilariGetir(){
        List<Sayfa> sayfalar=veritabanindakiTumSayfalariGetir();
        List<Sayfa> yildizlilar=new ArrayList<>();
        for (int i=0;i<sayfalar.size();i++){
            if (sayfalar.get(i).getYildiz_durumu()==1){
                yildizlilar.add(sayfalar.get(i));
            }
        }


        return yildizlilar;
    }
    public void HesapSil()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_KULLANICILAR);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_BOLUMLER);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_SAYFALAR);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIKLANAN_BOLUM);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIKLANAN_SAYFA);
            db.execSQL(tablo_kulanicilar);
            db.execSQL(tablo_bolumler);
            db.execSQL(tablo_sayfalar);
            db.execSQL(tablo_tiklanan);
            db.execSQL(tablo_tiklanan_sayfa);
        } catch (SQLException e) {

        }

    }
}
