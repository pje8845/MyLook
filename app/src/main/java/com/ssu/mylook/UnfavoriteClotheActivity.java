package com.ssu.mylook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

public class UnfavoriteClotheActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfavorite_clothe);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");

    }
}