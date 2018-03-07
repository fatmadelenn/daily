package com.fatmadelenn.gunluk;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


public class SistemUyarilari {
    private ProgressDialog uyari;
    private AlertDialog.Builder builder;
    private Context context;

    public SistemUyarilari(Context context){
        this.context=context;
    }
    public void uyariBaslat(String baslik,String mesaj){
        uyari=new ProgressDialog(context);
        uyari.setTitle(baslik);
        uyari.setMessage(mesaj);
        uyari.show();
    }

    public void uyariDurdur(){
        uyari.dismiss();
    }
    public void kayitBasarili(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Başarılı!");
        builder.setMessage("İşleminiz başarıyla gerçekleşti.");
        builder.setIcon(R.drawable.tamam);
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void bolumEklendi(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Başarılı!");
        builder.setMessage("İşleminiz başarıyla gerçekleşti.");
        builder.setIcon(R.drawable.tamam);
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void kayitBasarisiz(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Başarısız!");
        builder.setMessage("İşleminiz gerçekleşirken bir problem oluştu. Bilgilerinizi kontrol edin ve tekrar deneyin.");
        builder.setIcon(R.drawable.hata);
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    public void hata(String mesaj){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Başarısız!");
        builder.setMessage(mesaj);
        builder.setIcon(R.drawable.hata);
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void onay(String mesaj){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Başarılı!");
        builder.setMessage(mesaj);
        builder.setIcon(R.drawable.tamam);
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                context.startActivity(new Intent(context,Sayfalar.class));
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void yeniSifre(String sifre){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Tebrikler!");
        builder.setMessage("Yeni Şifreniz: "+sifre);
        builder.setIcon(R.drawable.tamam);
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
