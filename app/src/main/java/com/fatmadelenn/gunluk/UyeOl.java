package com.fatmadelenn.gunluk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class UyeOl extends AppCompatActivity {

    private EditText adSoyad,kullaniciAdi,sifre,sifreTekrar,cevap;
    private Button uyeOl;
    private Spinner spinner;
    private ArrayList<String> list;
    private ArrayAdapter adapter;
    private Veritabani veritabani;
    private TextView giris;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);


        adSoyad=(EditText)findViewById(R.id.uyeol_adsoyad);
        kullaniciAdi=(EditText)findViewById(R.id.uyeol_kullaniciadi);
        sifre=(EditText)findViewById(R.id.uyeol_sifre);
        sifreTekrar=(EditText)findViewById(R.id.uyeol_sifreTekrar);
        cevap=(EditText)findViewById(R.id.uyeol_cevap);

        //Spinner
        spinner=(Spinner)findViewById(R.id.spinner);
        list=new ArrayList<>();
        list.add("En sevdiğiniz kişi?");
        list.add("En sevdiğiniz renk?");
        list.add("Babanızın doğum tarihi nedir?");
        list.add("Uğurlu sayınız?");
        list.add("En sevdiğiniz ünlü?");
        list.add("En iyi çocukluk arkadaşın?");
        list.add("En sevdiğiniz ilkokul öğretmeni?");
        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);

        //Veritabanı
        veritabani=new Veritabani(UyeOl.this);

        //Button
        uyeOl=(Button)findViewById(R.id.uyeol);
        uyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kullanici kullanici=new Kullanici(
                        adSoyad.getText().toString().trim(),
                        kullaniciAdi.getText().toString().trim(),
                        sifre.getText().toString().trim(),
                        spinner.getSelectedItem().toString().trim(),
                        cevap.getText().toString().trim()
                );
                veritabani.kullaniciEkle(kullanici);
            }
        });


        giris=(TextView)findViewById(R.id.giris_uyeol);
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UyeOl.this,MainActivity.class));
                finish();
            }
        });

    }
}
