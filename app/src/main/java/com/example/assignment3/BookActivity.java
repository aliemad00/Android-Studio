package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {
    String name; // do not change this code
    Integer age; // do not change this code
    String date; // do not change this code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        setTitle("Book an Appointment");

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month+1) + "/" + year;
                TextView textView = findViewById(R.id.textViewSelectDate);
                textView.setText("Selected date: " + date);
            }
        });
    }

    public void submit(View view) {
        // Do not change any code above this line
        // Add Java code for your answer to Task 1 below this line

        // date
        if (date == null) return;
        // name
        EditText editTextFullName = findViewById(R.id.editTextFullName);
        name = editTextFullName.getText().toString();
        // age
        EditText editTextAge = findViewById(R.id.editTextAge);

        //String checkName = "\\w*[a-zA-Z]\\w*";
        if (name.isEmpty()){
            editTextFullName.setError("Field cannot be empty");
            return;

        }
        else {
            editTextFullName.setError(null);
        }

        if (editTextAge.getText().toString().isEmpty()) {
            editTextAge.setError("Please enter your age");
            return;
        }

        try {
            age = Integer.valueOf(editTextAge.getText().toString());

        }catch (Exception e){
            editTextAge.setError("Please enter a valid age.");
        }

        //Code for checking if the input is between 0-100 in age input box and if the age input box is empty.


        if (age < 0 || age > 100){
            editTextAge.setError("Please enter your age");
            return;
        }

        // Answer to Task 1 ends here
        // Do not change any code below this line
        Intent intent = new Intent(this, ConfirmActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        intent.putExtra("date", date);
        startActivity(intent);
    }
}