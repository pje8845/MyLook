package com.ssu.mylook.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ssu.mylook.R;
import com.ssu.mylook.dto.CustomDTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;


public class FavoriteClotheAdapter extends BaseAdapter {
    private ArrayList<CustomDTO> listCustom = new ArrayList<>();
    Context context;
    ArrayList<String> clicked = new ArrayList<>();


    public FavoriteClotheAdapter(Context context){
        this.context = context;
        listCustom = new ArrayList<>();
    }

    public FavoriteClotheAdapter(Context context, ArrayList<CustomDTO> list) {
        this.context=context;
        list.addAll(list);
        this.listCustom=list;
    }


    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 3)
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_clothe_item,null,false);

            holder = new CustomViewHolder();
            holder.textRank= (TextView)convertView.findViewById(R.id.item_ranking);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
            holder.textTitle = (TextView) convertView.findViewById(R.id.item_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.item_number);


            convertView.setTag(holder);

        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        CustomDTO dto = listCustom.get(position);

        //holder.textRank.setText(dto.getRank());
        //holder.imageView.setImageResource(dto.getResId());
        holder.textTitle.setText(dto.getName());
        new DBUtil().setImageViewFromDB(context,holder.imageView,dto.getImg());
        //holder.textContent.setText(dto.getContent());

        holder.textRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), (position+1)+"번째", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class CustomViewHolder {
        TextView textContent;
        ImageView imageView;
        TextView textTitle;
        TextView textRank;
    }

    // FavoriteClotheActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(CustomDTO dto) {
        listCustom.add(dto);
    }
}