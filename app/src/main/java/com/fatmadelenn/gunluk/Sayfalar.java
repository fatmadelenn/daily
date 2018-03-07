package com.fatmadelenn.gunluk;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ftmdlnn on 15.12.2017.
 */

public class Sayfalar extends AppCompatActivity {

    private List<Sayfa> sayfaAdlari;
    private Button camera,ciz;
    private TextView ekleSayfa;
    private ListView listView1;
    private Veritabani veritabani;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayfalar);
        veritabani=new Veritabani(Sayfalar.this);

            setTitle(veritabani.tiklananGetir().getAd());
            ekleSayfa=(TextView) findViewById(R.id.ekleSayfa);
            camera=(Button) findViewById(R.id.camera);


            ciz=(Button) findViewById(R.id.ciz);
            ekleSayfa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Sayfalar.this,Gunluk.class);
                    startActivity(i);
                }
            });
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //kamera aç
                }
            });

            ciz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Sayfalar.this,CizimYazdir.class);
                    startActivity(i);
                }
            });


            listView1=(ListView) findViewById(R.id.listView1);
            sayfaAdlari=veritabani.tumSayfalariGetir();
            ArrayList<String> isimler=new ArrayList<>();
            for (int i=0;i<sayfaAdlari.size();i++){
                isimler.add(sayfaAdlari.get(i).getKonu());
            }

            ArrayAdapter adapter=new ArrayAdapter(Sayfalar.this,R.layout.support_simple_spinner_dropdown_item,isimler);
            Collections.reverse(isimler);
            listView1.setAdapter(adapter);
            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                veritabani.ayrintiEkle(sayfaAdlari.get(i));
                Intent intent=new Intent(Sayfalar.this,AyrintiSayfasi.class);
                startActivity(intent);

            }
        });
    }
}

