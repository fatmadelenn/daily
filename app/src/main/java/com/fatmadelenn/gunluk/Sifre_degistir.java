package com.fatmadelenn.gunluk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sifre_degistir extends AppCompatActivity {
    private EditText sifreTekrar1,sifre;
    private Veritabani veritabani;
    private Button degistir;
    private String yenisifre;
    private SistemUyarilari sistemUyarilari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_degistir);
        veritabani=new Veritabani(Sifre_degistir.this);
        sistemUyarilari =new SistemUyarilari(Sifre_degistir.this);
        sifreTekrar1=(EditText)findViewById(R.id.sifreTekrar1Text);
        sifre=(EditText) findViewById(R.id.sifre1Text);
        degistir=(Button) findViewById(R.id.degistir);
        degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sifre.getText().toString().trim().equals(sifreTekrar1.getText().toString().trim())) {
                    yenisifre = sifre.getText().toString();
                    veritabani.sifreGuncelle(yenisifre);
                    sistemUyarilari.yeniSifre(yenisifre);
                }
            }
        });
    }
}
