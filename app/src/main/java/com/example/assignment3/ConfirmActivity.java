package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmActivity extends AppCompatActivity {

    String key;
    String name;
    Integer age;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        setTitle("Appointment Details");

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        age = extras.getInt("age");
        date = extras.getString("date");
        if (extras.getString("key") != null)
            key = extras.getString("key");

        TextView textView = findViewById(R.id.textViewReceived);
        textView.setText("Please confirm your details:\nFull name: " + name +
                "\nAge: " + age + "\nAppointment date: " + date);
    }

    public void bookAppointment(View view) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference("Covid-19");
        // Do not change any code above this line
        // Add Java code for your answer to Task 5 below this line

        //for appointment number
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

        key = dateFormat.format(new Date());

        //age, date and name
        //String key = dbRef.push().getKey();
        dbRef.child(key).child("age").setValue(age.toString());
        dbRef.child(key).child("name").setValue(name);
        dbRef.child(key).child("date").setValue(date);

        // Answer to Task 5 ends here
        // Do not change any code below this line
        TextView textView = findViewById(R.id.textViewConfirmed);
        textView.setText(
                "Booked. Please arrive in Calvary Hospital at 11:00 on " + date +
                ". Save the number below to reschedule or cancel your appointment.");

        TextView textViewKey = findViewById(R.id.textViewAppointmentNumber);
        textViewKey.setText(key);
    }

    public void done(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void goToMaps(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("latitude", -35.253865);
        intent.putExtra("longitude", 149.089252);
        intent.putExtra("zoom", 14);

        startActivity(intent);
    }

}