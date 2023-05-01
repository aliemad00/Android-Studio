package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RescheduleActivity extends AppCompatActivity {
    String key;
    String name = "Not found";
    Integer age = null;
    String date = "Not found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reschedule);

        setTitle("Reschedule Appointment");

        CalendarView calendarView = findViewById(R.id.calendarViewResheduled);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month+1) + "/" + year;
                TextView textViewDate = findViewById(R.id.textViewDateRetrieved);
                textViewDate.setText("Selected date: " + date);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getString("key");
            EditText editText = findViewById(R.id.editTextNumber);
            editText.setText(key);
        }

    }

    public void retrieve(View view) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference("Covid-19");

        EditText editText = findViewById(R.id.editTextNumber);
        key = editText.getText().toString();
        name = "Not found";
        age = null;
        date = "Not found";

        TextView textViewFullname = findViewById(R.id.textViewFullNameRetrieved);
        TextView textViewAge = findViewById(R.id.textViewAgeRetrieved);
        TextView textViewDate = findViewById(R.id.textViewDateRetrieved);
        // Do not change any code above this line
        // Add Java code for your answer to Task 5 below this line
        // Hint: use addChildEventListener to get data from Firebase realtime database

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String matchKey = snapshot.getKey();

                if (matchKey.equals(key)){

                    String aString = (String)snapshot.child("age").getValue();
                    age = Integer.valueOf(aString);
                    name = (String)snapshot.child("name").getValue();
                    date = (String)snapshot.child("date").getValue();

                    textViewAge.setText("Age: "+age);

                    textViewFullname.setText("Full name: "+name);

                    textViewDate.setText("Date: "+date);
                }else{
                    textViewAge.setText("Age: Not Found");

                    textViewFullname.setText("Full name: Not Found ");

                    textViewDate.setText("Date: Not Found");
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Answer to Task 5 ends here
        // Do not change any code below this line
    }

    public void resubmit(View view) {
        if (name.equals("Not found")) {
            TextView textView = findViewById(R.id.textViewError);
            textView.setText("Your details are not found. Please re-enter your appointment number");
            return;
        }

        Intent intent = new Intent(this, ConfirmActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        intent.putExtra("date", date);
        startActivity(intent);
    }
}