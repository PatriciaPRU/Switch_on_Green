package com.example.switchongreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Charging_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charging_page);

        new Handler().postDelayed(() -> {
            Intent intent=new Intent(Charging_Page.this,MainActivity.class);
            startActivity(intent);
        },3000);
    }
}
