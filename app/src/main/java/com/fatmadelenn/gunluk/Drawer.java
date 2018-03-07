package com.fatmadelenn.gunluk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Drawer extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView textView,kullanici;
    private ImageView image;
    private ActionBarDrawerToggle action_drawer;
    private Veritabani veritabani;
    private ListView listView;
    private List<Sayfa> liste=new ArrayList<>();
    private ArrayList<String> isimler=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        veritabani=new Veritabani(Drawer.this);
        textView=(TextView)findViewById(R.id.textView2);
        textView.setText(veritabani.girisYapanKullanici().getAd_soyad()+" adlı kişinin Günlüğü");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Drawer.this,Bolumler.class));
            }
        });
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navigationView=(NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.hesap_sil:
                        veritabani.HesapSil();
                        Intent i=new Intent(Drawer.this,MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.cikis:
                        Intent i2=new Intent(Drawer.this,MainActivity.class);
                        startActivity(i2);
                        break;
                    case R.id.anasayfa:
                        startActivity(new Intent(Drawer.this,Drawer.class));
                        finish();
                        break;
                    case R.id.sifre_degistir:
                        Intent i3=new Intent(Drawer.this,Sifre_degistir.class);
                        startActivity(i3);
                        break;
                    case R.id.takvim:
                        Intent i4=new Intent(Drawer.this,Takvim.class);
                        startActivity(i4);
                        break;
                }

                return true;
            }
        });

        action_drawer=new ActionBarDrawerToggle(Drawer.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(action_drawer);
        action_drawer.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//geri butonu

        View view=navigationView.getHeaderView(0);
        kullanici=(TextView)view.findViewById(R.id.textView3);
        kullanici.setText(veritabani.girisYapanKullanici().getAd_soyad()+"\n"+"@"+veritabani.girisYapanKullanici().getKullanici_adi());

        liste=veritabani.yildizlilariGetir();
        for (int i=0;i<liste.size();i++){
            isimler.add(liste.get(i).getKonu());
        }
        listView=(ListView)findViewById(R.id.liste_yildizlilar);
        ArrayAdapter adapter=new ArrayAdapter(Drawer.this,R.layout.support_simple_spinner_dropdown_item,isimler);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                veritabani.ayrintiEkle(liste.get(i));
                Intent intent=new Intent(Drawer.this,AyrintiSayfasi.class);


                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (action_drawer.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
