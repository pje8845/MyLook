package com.ssu.mylook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FavoriteClotheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_clothe);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");
    }
}
