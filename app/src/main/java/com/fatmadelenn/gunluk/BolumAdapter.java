package com.fatmadelenn.gunluk;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Fatih on 8.08.2017.
 */

public class BolumAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Bolum> bolumler;
    private Activity activity;
    private Veritabani veritabani;


    public BolumAdapter(Activity activity, List<Bolum> bolumler) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.bolumler = bolumler;
        this.activity=activity;
        veritabani=new Veritabani(activity);
        Collections.reverse(this.bolumler);
    }

    @Override
    public int getCount() {
        return bolumler.size();
    }

    @Override
    public Bolum getItem(int position) {

        return bolumler.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.bolumler_list, null);
        TextView textView =(TextView) satirView.findViewById(R.id.textView5);
        textView.setText(bolumler.get(position).getAd().toString());
        satirView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,Sayfalar.class);
                bolumler=veritabani.tumBolumleriGetir();
                veritabani.tiklananEkle(bolumler.get(position).getId(),bolumler.get(position).getAd());
                activity.startActivity(intent);
            }
        });

        return satirView;
    }
}
