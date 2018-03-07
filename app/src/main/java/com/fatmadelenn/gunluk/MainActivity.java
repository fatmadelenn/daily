package com.fatmadelenn.gunluk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean temp=false;
    private TextView uyeOl;
    private Button girisyap;
    private Veritabani veritabani;
    private List<Kullanici> kullanicilar;
    private EditText kullaniciAdi,sifre;
    private SistemUyarilari sistemUyarilari;
    private String m_Text = "";
    private String yenisifre="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        veritabani=new Veritabani(MainActivity.this);
        sistemUyarilari =new SistemUyarilari(MainActivity.this);


        kullaniciAdi=(EditText)findViewById(R.id.giris_kullaniciadi);
        sifre=(EditText)findViewById(R.id.giris_sifre);

        girisyap=(Button)findViewById(R.id.girisyap);
        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kullanicilar=veritabani.tumKullanicilar();
                for (int i=0;i<kullanicilar.size();i++){
                    if (kullaniciAdi.getText().toString().trim().equals(kullanicilar.get(i).getKullanici_adi())
                            &&sifre.getText().toString().trim().equals(kullanicilar.get(i).getSifre())){
                        veritabani.girisYapanlarTabloSil();
                        veritabani.girisYapanKullaniciKaydet(kullanicilar.get(i));
                        startActivity(new Intent(MainActivity.this,Drawer.class));
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finish();
                        temp=true;
                        break;

                    }else{
                        temp=false;
                    }
                }
                if (!temp){
                    sistemUyarilari.hata("Kullanıcı Adınız veya Şifreniz Yanlış!");
                }
            }
        });

        uyeOl=(TextView)findViewById(R.id.giris_uyeol);
        if (veritabani.tumKullanicilar().size()>0){
            uyeOl.setText("Şifrenizi mi Unuttunuz?");
        }else{
            uyeOl.setText("Hesabınız Yok mu? Kaydolun");
        }
        uyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (veritabani.tumKullanicilar().size()>0){
                    sifremiUnuttum();
                }else{
                    startActivity(new Intent(MainActivity.this,UyeOl.class));
                }
            }
        });


    }
    public void sifremiUnuttum(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(veritabani.girisYapanKullanici().getSoru());
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (veritabani.girisYapanKullanici().getCevap().toString().trim().equals(m_Text.toString().trim())){

                    //Toast.makeText(MainActivity.this,"sdfghjkl",Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Yeni Şifrenizi Oluşturunuz: ");
                    final EditText input = new EditText(MainActivity.this);
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    builder.setView(input);
                    builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            yenisifre = input.getText().toString();
                            veritabani.sifreGuncelle(yenisifre);
                            sistemUyarilari.yeniSifre(yenisifre);

                        }
                    });
                    builder.show();


                }else{
                    sistemUyarilari.hata("Cevap yanlış!");
                }


            }
        });
        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}

