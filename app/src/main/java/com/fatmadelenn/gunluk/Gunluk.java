package com.fatmadelenn.gunluk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DAY_OF_WEEK;

/**
 * Created by ftmdlnn on 15.12.2017.
 */

public class Gunluk extends AppCompatActivity {
    private EditText editTextKonu,editTextIcerik;
    private Button ekle;
    //private List<Yildizlanan> sayfaAdlari;
    private Veritabani veritabani;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String aylar[]={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
        String gunler[]={"Cumartesi","Pazar","Pazartesi","Salı","Çarşamba","Perşembe","Cuma"};
        Calendar simdi=Calendar.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunluk);


        veritabani=new Veritabani(Gunluk.this);

        TextView bugun =(TextView) findViewById(R.id.textViewTarih);
        bugun.setText(simdi.get(Calendar.DATE)+" "+aylar[simdi.get(Calendar.MONTH)]+" "+simdi.get(Calendar.YEAR)
                +"   "+simdi.get(Calendar.HOUR_OF_DAY)+":"+simdi.get(Calendar.MINUTE)+" "+gunler[simdi.get(Calendar.DAY_OF_WEEK)]);
            editTextKonu=(EditText)findViewById(R.id.editTextKonu);
            editTextIcerik=(EditText)findViewById(R.id.editTextIcerik);

            ekle=(Button)findViewById(R.id.buttonKaydet);
            ekle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    veritabani.sayfaEkle(editTextKonu.getText().toString().trim(),editTextIcerik.getText().toString().trim());
                }
            });






    }
}

