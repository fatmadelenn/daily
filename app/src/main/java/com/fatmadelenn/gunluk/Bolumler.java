package com.fatmadelenn.gunluk;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Bolumler extends AppCompatActivity {

    private Button bolumEkle;
    private ListView bolumlerim;
    private List<Bolum> bolumAdlari;

    private Veritabani veritabani;
    private SistemUyarilari sistemUyarilari;
    private BolumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolumler);

        veritabani=new Veritabani(Bolumler.this);
        sistemUyarilari =new SistemUyarilari(Bolumler.this);

        bolumlerim=(ListView)findViewById(R.id.bolumlerim);
        bolumAdlari=veritabani.tumBolumleriGetir();
        bolumlerim.setDivider(null);
        adapter=new BolumAdapter(Bolumler.this,bolumAdlari);
        bolumlerim.setAdapter(adapter);

        bolumEkle=(Button)findViewById(R.id.bolumEkle);
        bolumEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //sistemUyarilari.uyariBaslat("","İşleminiz devam ediyor. Lütfen bekleyiniz...");
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Bolumler.this);
                builder.setTitle("Bölüm adı girin: ");
                final EditText input = new EditText(Bolumler.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);

                builder.setView(input);
                bolumlerim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(Bolumler.this,String.valueOf(view.getId()),Toast.LENGTH_LONG).show();
                    }
                });
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        veritabani.bolumEkle(input.getText().toString().trim());
                       // sistemUyarilari.uyariDurdur();
                        bolumAdlari=veritabani.tumBolumleriGetir();
                        adapter=new BolumAdapter(Bolumler.this,bolumAdlari);
                        bolumlerim.setAdapter(adapter);
                    }
                });
                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // sistemUyarilari.uyariDurdur();
                        dialog.cancel();
                    }
                });

                builder.show();


            }
        });

    }
}
