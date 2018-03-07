package com.fatmadelenn.gunluk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ftmdlnn on 15.12.2017.
 */

public class AyrintiSayfasi extends AppCompatActivity {

    private TextView textKonu,textİcerik,textTarih;
    private Veritabani veritabani;
    private Button yildiz,sariyildiz,sil;
    private RelativeLayout layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_ayrinti);
        veritabani=new Veritabani(AyrintiSayfasi.this);
        textKonu=(TextView)findViewById(R.id.TextKonu);
        textİcerik=(TextView)findViewById(R.id.TextIcerik);
        textTarih=(TextView)findViewById(R.id.textViewTarih);
        textKonu.setText(veritabani.tiklananSayfaGetir().getKonu());
        textİcerik.setText(veritabani.tiklananSayfaGetir().getIcerik());
        textTarih.setText(veritabani.tiklananSayfaGetir().getTarih());
        yildiz=(Button) findViewById(R.id.yildiz);
        sariyildiz=(Button) findViewById(R.id.sariyildiz);
        layout = (RelativeLayout) findViewById(R.id.the_id);
        layout.setBackgroundColor(Color.parseColor("#e7f3ff"));
        if (veritabani.tiklananSayfaGetir().getYildiz_durumu()==1){
            yildiz.setVisibility(View.GONE);
            sariyildiz.setVisibility(View.VISIBLE);
            layout.setBackgroundColor(Color.parseColor("#ffe7ea"));
        }
        yildiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yildiz.setVisibility(View.GONE);
                sariyildiz.setVisibility(View.VISIBLE);
                layout.setBackgroundColor(Color.parseColor("#ffe7ea"));
                veritabani.yildizla();


            }
        });
        sariyildiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sariyildiz.setVisibility(View.GONE);
                yildiz.setVisibility(View.VISIBLE);
                layout.setBackgroundColor(Color.parseColor("#e7f3ff"));
                veritabani.yildizVazgec();
            }
        });
        sil=(Button)findViewById(R.id.buttonSil);
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veritabani.sayfaSil();
                Intent i=new Intent(AyrintiSayfasi.this,Sayfalar.class);
                startActivity(i);
            }
        });
    }
}

