package com.fatmadelenn.gunluk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ftmdlnn on 21.12.2017.
 */

public class CizimYazdir extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Cizim(this));
    }
}

