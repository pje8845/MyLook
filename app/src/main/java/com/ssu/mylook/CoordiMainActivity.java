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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ssu.mylook.adapter.CoordiMainAdapter;
import com.ssu.mylook.dto.CustomDTO;

import java.util.ArrayList;



public class CoordiMainActivity extends AppCompatActivity implements View.OnClickListener {

    //이 화면에서 특정 코디 클릭 시 그 코디의 상세보기 화면으로 넘어가는 함수, 변수 구현필요

    private CoordiMainAdapter adapter;
    private GridView MyGridView;

    final ArrayList<CustomDTO> CoordiList = new ArrayList<>();
    final static String TAG = "Database";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    //List<Object> Array = new ArrayList<Object>();
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;


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


        //textView tv 대신 그리드뷰 적용되게 변경해보기
        final TextView tv = (TextView)findViewById(R.id.arrange_text);
        Spinner s = (Spinner)findViewById(R.id.arrange_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText("position : " + position + parent.getItemAtPosition(position));
                tv.setText(""+parent.getItemAtPosition(position));
                //MyGridView.setAdapter(adapter);
                setData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //setData();
        adapter = new CoordiMainAdapter(this);

        MyGridView =(GridView)findViewById(R.id.CoordiMainGridView);
        //initDB();

        MyGridView.setAdapter(adapter);
        adapter.setListCustom(CoordiList);
        MyGridView.setAdapter(adapter);


//        mReference = mDataBase.getReference("child 이름"); // 변경값을 확인할 child 이름
//        mReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //수정필요
//               // adapter.clear();
//                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
//                    // child 내에 있는 데이터만큼 반복합니다.
//                    String msg2 = messageData.getValue().toString();
//                    Array.add(msg2);
//                    //수정필요
//                 //   adapter.addItem();
//                }
//                adapter.notifyDataSetChanged();
//                MyGridView.setSelection(adapter.getCount() - 1);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
//    private void initDB(){
//        mDataBase = FirebaseDatabase.getInstance();
//        mReference = mDataBase.getReference("log");
//        mReference.child("log").setValue("check");
//
//        mChild = new ChildEventListener(){
//
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        mReference.addChildEventListener(mChild);
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mReference.removeEventListener(mChild);
//    }

    private void setData(int position) {
        if(position==0){
            //ArrayList<CustomDTO> CoordiList;
                    db.collection("coordi").orderBy("reg_date", Query.Direction.DESCENDING)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    ArrayList<CustomDTO> list = new ArrayList<>();
                                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                        CustomDTO item = doc.toObject(CustomDTO.class);
                                        item.setId(doc.getId());
                                        list.add(item);
                                    }
                                    adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                                    MyGridView.setAdapter(adapter);
                                }});
            adapter.setListCustom(CoordiList);
            showToast("CoordiList 등록날짜순 출력중");
        } else if(position==1){
            //ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", false);
            ArrayList<CustomDTO> CoodiList;
            db.collection("coordi").orderBy("rating", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<CustomDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CustomDTO item = doc.toObject(CustomDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            MyGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 별점 오름차순 출력중");
        } else if(position==2){
            //ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", true);
            //adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("rating", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<CustomDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CustomDTO item = doc.toObject(CustomDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            MyGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 별점 내림차순 출력중");
        } else if(position==3) {
//            ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "count", false);
//            adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("count", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<CustomDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CustomDTO item = doc.toObject(CustomDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            MyGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 횟수 내림차순 출력중");
        } else if(position==4){
//            ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "count", true);
//            adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("count", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<CustomDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CustomDTO item = doc.toObject(CustomDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            MyGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 횟수 오름차순 출력중");
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

        ArrayList<CustomDTO> a = DBUtil.getData("rating",false);

        adapter.setListCustom(a);

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


//    public void clickTab(View v) {
//        ImageView[] img = new ImageView[3];
//        img[0] = findViewById(R.id.tab_closet);
//        img[1] = findViewById(R.id.tab_coordi);
//        img[2] = findViewById(R.id.tab_analysis);
//
//        if (v == img[0]) {
//            Intent intent = new Intent(this, ClosetActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(intent);
//            overridePendingTransition(0, 0);
//        } else if (v == img[1]) {
//            Intent intent = new Intent(this, CoordiMainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(intent);
//            overridePendingTransition(0, 0);
//        } else if (v == img[2]) {
//            Intent intent = new Intent(this, StyleAnalysisActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(intent);
//            overridePendingTransition(0, 0);
//        }
//
//
//    }

//    public ArrayList<CustomDTO> getDatasforDate(String collection, String criteria, boolean order) {
//        if (order) { //true : 내림차순 정렬
//            db.collection("coordi").orderBy("reg_date", Query.Direction.DESCENDING)
//                    .get()
//                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                        @Override
//                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            ArrayList<CustomDTO> list = new ArrayList<>();
//                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
//                                CustomDTO item = doc.toObject(CustomDTO.class);
//                                item.setId(doc.getId());
//                                list.add(item);
//                            }
//                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
//
//                            MyGridView.setAdapter(adapter);
//                        }});
//        }else { //false : 오름차순 정렬
//            db.collection(collection).orderBy(criteria, Query.Direction.ASCENDING)
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    //a.add(CustomDTO.mapToDTO(document.getData()));
//                                }
//                            } else {
//                                //Log.w(TAG, "Error getting documents.", task.getException());
//                            }
//                        }
//                    });
//        }
//        return CoordiList;
//    }

}
