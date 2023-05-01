package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CancelActivity extends AppCompatActivity {
    String key;
    String name = "Not found";
    Integer age = null;
    String date = "Not found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        setTitle("Cancel Appointment");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getString("key");
            EditText editText = findViewById(R.id.editTextNumberCancel);
            editText.setText(key);
        }
    }

    public void retrieve(View view) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference("Covid-19");

        EditText editText = findViewById(R.id.editTextNumberCancel);
        key = editText.getText().toString();
        name = "Not found";
        age = null;
        date = "Not found";

        TextView textViewFullname = findViewById(R.id.textViewFullNameRetrievedCancel);
        TextView textViewAge = findViewById(R.id.textViewAgeRetrievedCancel);
        TextView textViewDate = findViewById(R.id.textViewDateRetrievedCancel);

        // Do not change any code above this line
        // However, you can add your code for Task 3 and Task 4 outside this method
        // Add Java code for your answer to Task 5 below this line

        // Hint: use addChildEventListener to get data from Firebase realtime database

        // Answer to Task 5 ends here
        // Do not change any code below this line


        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String matchKey = snapshot.getKey();

                if (matchKey.equals(key)) {

                    String aString = (String) snapshot.child("age").getValue();
                    age = Integer.valueOf(aString);
                    name = (String) snapshot.child("name").getValue();
                    date = (String) snapshot.child("date").getValue();

                    textViewAge.setText("Age: " + age);

                    textViewFullname.setText("Full name: " + name);

                    textViewDate.setText("Date: " + date);
                } else {
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
    }
    public void cancel (View view){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference("Covid-19");
        // Do not change any code above this line
        // However, you can add your code for Task 3 and Task 4 outside this method
        // Add Java code for your answer to Task 5 below this line

        // Answer to Task 5 ends here
        // Do not change any code below this line

        TextView textView = findViewById(R.id.textViewMessage);
        textView.setText("Your appointment has been cancelled.");
    }


    public void done (View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }


}