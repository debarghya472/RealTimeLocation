package com.barbera.barberahomesalon.Admin;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.pubnub.kaushik.realtimetaxiandroiddemo.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateItemActicity extends AppCompatActivity {
    private final HashMap<String,String> hashMap=new HashMap<>();
    private LinearLayout ll;
    private LinearLayout ll2;

    private Button done;

    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;
    private EditText edit5;
    private EditText edit6;
    private EditText edit8;
    private EditText edit7;
    private EditText edit9;
    private EditText edit10;
    private EditText edit11;
    private EditText edit12;
    private EditText edit13;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_acticity);

        ll =findViewById(R.id.list);
        ll2 = findViewById(R.id.ll2);
        done = findViewById(R.id.done);

        Button s1 = findViewById(R.id.service1);
        Button s2 = findViewById(R.id.service2);
        Button s3 = findViewById(R.id.service3);
        Button s4 = findViewById(R.id.service4);
        Button s5 = findViewById(R.id.service5);
        Button s6 = findViewById(R.id.service6);
        Button s7 = findViewById(R.id.service7);
        Button s8 = findViewById(R.id.service8);
        Button s9 = findViewById(R.id.service9);
        Button s10 = findViewById(R.id.service10);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);
        edit5 = findViewById(R.id.edit5);
        edit6 = findViewById(R.id.edit6);
        edit7 = findViewById(R.id.edit7);
        edit8 = findViewById(R.id.edit8);
        edit9 = findViewById(R.id.edit9);
        edit10 = findViewById(R.id.edit10);
        edit12 = findViewById(R.id.edit12);
        edit11 = findViewById(R.id.edit11);
        edit13 = findViewById(R.id.edit13);




        FirebaseFirestore.getInstance().collection("Service").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for(QueryDocumentSnapshot documentSnapshot :  Objects.requireNonNull(task.getResult())){
                    hashMap.put(documentSnapshot.getString("channel_name"),documentSnapshot.getId());
                    ll.setVisibility(View.VISIBLE);
                }
            }
        });

        s1.setOnClickListener(v -> selectItems("Service 1"));
        s2.setOnClickListener(v -> selectItems("Service 2"));
        s3.setOnClickListener(v -> selectItems("Service 3"));
        s4.setOnClickListener(v -> selectItems("Service 4"));
        s5.setOnClickListener(v -> selectItems("Service 5"));
        s6.setOnClickListener(v -> selectItems("Service 6"));
        s7.setOnClickListener(v -> selectItems("Service 7"));
        s8.setOnClickListener(v -> selectItems("Service 8"));
        s9.setOnClickListener(v -> selectItems("Service 9"));
        s10.setOnClickListener(v -> selectItems("Service 10"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void selectItems(String s) {
        String selectedUid = hashMap.get(s);
        ll.setVisibility(View.GONE);
        ll2.setVisibility(View.VISIBLE);
        //Toast.makeText(getApplicationContext(),selectedUid,Toast.LENGTH_SHORT).show();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        done.setOnClickListener(v -> {Map<String,Object> map = new HashMap<>();
            map.put("bag",edit1.getText().toString());
            map.put("Tripod",edit2.getText().toString());
            map.put("Mirror",edit3.getText().toString());
            map.put("Neck Tape",edit4.getText().toString());
            map.put("Carpet",edit5.getText().toString());
            map.put("cleansing cream",edit6.getText().toString());
            map.put("after lotion",edit7.getText().toString());
            map.put("shaving foam",edit8.getText().toString());
            map.put("T-shirt",edit9.getText().toString());
            map.put("Tissue",edit10.getText().toString());
            map.put("Disposable",edit11.getText().toString());
            map.put("Apron",edit12.getText().toString());
            map.put("shaving apron",edit13.getText().toString());
            map.put("date",dtf.format(now));
//        map.put("date",)
        int random =(int)Math.floor(Math.random()*(100));
            FirebaseFirestore.getInstance().collection("Service").document(selectedUid).collection("Item")
                    .document("list"+random).set(map).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });});


    }
}