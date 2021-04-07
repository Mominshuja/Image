package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Retrieving extends AppCompatActivity {

    private ImageView imageView;
    private EditText input;
    private Button f_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieving);
        imageView = findViewById(R.id.imageView2);
        f_button = findViewById(R.id.button5);
        input = findViewById(R.id.editTextTextPersonName2);
        //Intent intent = getIntent();
        //String str = intent.getStringExtra("input");
        //input.setText(str);
        f_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = input.getText().toString().trim();
                 FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                 DatabaseReference databaseReference = firebaseDatabase.getReference().child(input.getText().toString().trim());
                 DatabaseReference func = databaseReference.getRef().child("imageURL");

                func.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String link= snapshot.getValue(String.class);
                        Picasso.get().load(link).into(imageView);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                       Toast.makeText(Retrieving.this, "Error Loading Image", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    }