package com.ssu.mylook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CoordiMainActivity extends AppCompatActivity implements View.OnClickListener {

    //이 화면에서 특정 코디 클릭 시 그 코디의 상세보기 화면으로 넘어가는 함수, 변수 구현필요

    private CoordiMainAdapter adapter;
    private GridView MyGridView;
    TextView Springtv;
    TextView Summertv;
    TextView Falltv;
    TextView Wintertv;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
       if(id==R.id.action_add){
//           Toast.makeText(this, "코디추가버튼 클릭",Toast.LENGTH_SHORT).show();
//           return true;
           startActivity(new Intent(this,CoordiRegisterActivity.class));
       }
       return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_main);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("코디 목록");
        Springtv=findViewById(R.id.spring_coordi);
        Summertv=findViewById(R.id.summer_coordi);
        Falltv=findViewById(R.id.fall_coordi);
        Wintertv=findViewById(R.id.winter_coordi);

        Springtv.setOnClickListener(this);
        Summertv.setOnClickListener(this);
        Falltv.setOnClickListener(this);
        Wintertv.setOnClickListener(this);

        adapter = new CoordiMainAdapter();
        MyGridView =(GridView)findViewById(R.id.CoordiMainGridView);

        //textView tv 대신 그리드뷰 적용되게 변경해보기
        final TextView tv = (TextView)findViewById(R.id.arrange_text);
        Spinner s = (Spinner)findViewById(R.id.arrange_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText("position : " + position + parent.getItemAtPosition(position));
                tv.setText(""+parent.getItemAtPosition(position));
                MyGridView.setAdapter(adapter);
                setData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //setData();

        MyGridView.setAdapter(adapter);
    }

    private void setData(int position) {
        if(position==0){
            ArrayList<CustomDTO> CoordiList = com.ssu.mylook.util.DBUtil.getDatas("Coordi", "reg_date", false);
            adapter.setListCustom(CoordiList);
            System.out.println("CoordiList 등록날짜순 출력중");
        } else if(position==1){
            ArrayList<CustomDTO> CoordiList = com.ssu.mylook.util.DBUtil.getDatas("Coordi", "rating", false);
            adapter.setListCustom(CoordiList);
            System.out.println("CoordiList 별점 내림차순 출력중");
        } else if(position==2){
            ArrayList<CustomDTO> CoordiList = com.ssu.mylook.util.DBUtil.getDatas("Coordi", "rating", true);
            adapter.setListCustom(CoordiList);
        } else if(position==3) {
            ArrayList<CustomDTO> CoordiList = com.ssu.mylook.util.DBUtil.getDatas("Coordi", "count", false);
            adapter.setListCustom(CoordiList);
        } else if(position==4){
            ArrayList<CustomDTO> CoordiList = com.ssu.mylook.util.DBUtil.getDatas("Coordi", "count", true);
            adapter.setListCustom(CoordiList);
        }
/*
        TypedArray arrResId = getResources().obtainTypedArray(R.array.coordi_Id);
        String[] titles = getResources().getStringArray(R.array.coordi_title);
        String[] ranks = getResources().getStringArray(R.array.coordi_rank);
        String[] contents = getResources().getStringArray(R.array.coordi_number);

        for (int i = 0; i < arrResId.length(); i++) {
            CustomDTO dto = new CustomDTO();
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);
            dto.setRank(ranks[i]);
            dto.setContent(contents[i]);

            adapter.addItem(dto);

        }*/
    }
    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v==Springtv){
            showToast("spring category");
        } else if(v==Summertv){
            showToast("summer category");
        }else if(v==Falltv){
            showToast("fall category");
        } else if(v==Wintertv){
            showToast("winter category");
        }
    }
}
