package com.barbera.barberahomesalon.Admin;

import android.content.Intent;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pubnub.kaushik.realtimetaxiandroiddemo.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_admin);

        EditText day = findViewById(R.id.day);
        EditText stat = findViewById(R.id.sat);
        EditText region = findViewById(R.id.region);
        EditText reg = findViewById(R.id.reg);
        Button swap = findViewById(R.id.swap);
        Button update = findViewById(R.id.update);
        Button up = findViewById(R.id.update1);
        EditText randomid  = findViewById(R.id.randomid);
        EditText customeruid = findViewById(R.id.customerUid);
        EditText barberuid = findViewById(R.id.barberUID);
        Button assign = findViewById(R.id.assign);
        up.setOnClickListener(v -> {
            startActivity(new Intent(this,UpdateItemActicity.class));
        });

        Map<String,Object> map = new HashMap<>();

        assign.setOnClickListener(v->{
            if(randomid.getText().toString()!=null || barberuid.getText().toString()!=null || customeruid.getText().toString()!=null)
                assignbarber(randomid.getText().toString(), barberuid.getText().toString(), customeruid.getText().toString());
        });


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
        swap.setOnClickListener(v -> {
            String Region = "Region"+reg.getText().toString().trim();
            for(int i=2;i<=7;i++){
                int finalI = i;
                FirebaseFirestore.getInstance().collection("DaytoDayBooking").document("Day" +i)
                        .collection("Region").document(Region).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        swap(task, finalI,Region);
                    }
                });
            }
        });

//        call();
    }

    private void call() {
        Map<String,Object> map = new HashMap<>();
        map.put("discount",0);
        FirebaseFirestore.getInstance().collection("Women's Salon").get()
                .addOnCompleteListener(task -> {
                    for (DocumentSnapshot documentSnapshot : task.getResult()){
                        FirebaseFirestore.getInstance().collection("Women's Salon").
                                document(documentSnapshot.getId()).update(map)
                                .addOnCompleteListener(task1 ->{});
                    }
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
                });

    }

    private void assignbarber(String random, String barber,String customer) {
        FirebaseFirestore.getInstance().collection("Users").document(customer).collection("Bookings").get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                            if(documentSnapshot.get("randomId").toString().equals(random)){
                                Map<String,Object> map=new HashMap<>();
                                map.put("assignedTo",barber);
                                FirebaseFirestore.getInstance().collection("Users").document(customer)
                                        .collection("Bookings").document(documentSnapshot.getId()).update(map)
                                        .addOnCompleteListener(task1 -> {
                                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                        });

                                break;
                            }
                        }
                    }
                });
    }


    private void swap(Task<DocumentSnapshot> task, int index, String region) {
        Map<String,Object> map = new HashMap<>();
        map.put("7_m",task.getResult().get("7_m"));
        map.put("8_m",task.getResult().get("8_m"));
        map.put("9_m",task.getResult().get("9_m"));
        map.put("10_m",task.getResult().get("10_m"));
        map.put("11_m",task.getResult().get("11_m"));
        map.put("12_m",task.getResult().get("12_m"));
        map.put("16_m",task.getResult().get("16_m"));
        map.put("17_m",task.getResult().get("17_m"));
        map.put("18_m",task.getResult().get("18_m"));
        map.put("19_m",task.getResult().get("19_m"));
        map.put("9_f",task.getResult().get("9_f"));
        map.put("10_f",task.getResult().get("10_f"));
        map.put("11_f",task.getResult().get("11_f"));
        map.put("12_f",task.getResult().get("12_f"));
        map.put("13_f",task.getResult().get("13_f"));
        map.put("14_f",task.getResult().get("14_f"));
        map.put("15_f",task.getResult().get("15_f"));
        map.put("16_f",task.getResult().get("16_f"));
        map.put("17_f",task.getResult().get("17_f"));

        FirebaseFirestore.getInstance().collection("DaytoDayBooking").document("Day" +(index-1))
                .collection("Region").document(region).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            }
        });
    }
}