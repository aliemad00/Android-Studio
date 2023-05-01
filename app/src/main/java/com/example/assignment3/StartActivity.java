package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setTitle("Covid-19 Vaccine Appointment");
    }

    public void book (View view) {
        Intent intent = new Intent(this, BookActivity.class);
        startActivity(intent);
    }

    public void reschedule (View view) {
        Intent intent = new Intent(this, RescheduleActivity.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, CancelActivity.class);
        startActivity(intent);
    }
}