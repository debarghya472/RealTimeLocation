package com.barbera.barberahomesalon.Admin;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pubnub.kaushik.realtimetaxiandroiddemo.R;

import java.util.HashMap;
import java.util.Map;

public class FirebaseAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_admin);

        EditText day = findViewById(R.id.day);
        EditText stat = findViewById(R.id.sat);
        EditText region = findViewById(R.id.region);
        Button update = findViewById(R.id.update);

        Map<String,Object> map = new HashMap<>();


        update.setOnClickListener(view -> {
            map.put("7_m",stat.getText().toString());
            map.put("8_m",stat.getText().toString());
            map.put("9_m",stat.getText().toString());
            map.put("10_m",stat.getText().toString());
            map.put("11_m",stat.getText().toString());
            map.put("12_m",stat.getText().toString());
            map.put("16_m",stat.getText().toString());
            map.put("17_m",stat.getText().toString());
            map.put("18_m",stat.getText().toString());
            map.put("19_m",stat.getText().toString());
            map.put("9_f",stat.getText().toString());
            map.put("10_f",stat.getText().toString());
            map.put("11_f",stat.getText().toString());
            map.put("12_f",stat.getText().toString());
            map.put("13_f",stat.getText().toString());
            map.put("14_f",stat.getText().toString());
            map.put("15_f",stat.getText().toString());
            map.put("16_f",stat.getText().toString());
            map.put("17_f",stat.getText().toString());

            if(!region.getText().toString().equals("0")) {
                FirebaseFirestore.getInstance().collection("DaytoDayBooking").document("Day" + day.getText().toString())
                        .collection("Region").document("Region" + region.getText().toString())
                        .update(map).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                for (int i =1 ;i<=11;i++){
                    FirebaseFirestore.getInstance().collection("DaytoDayBooking").document("Day" + day.getText().toString())
                            .collection("Region").document("Region" +i)
                            .update(map).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}