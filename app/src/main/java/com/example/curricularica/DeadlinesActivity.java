package com.example.curricularica;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeadlinesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.deadlines);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.timetable) {
                Intent timetableIntent = new Intent(this, MainActivity.class);
                startActivity(timetableIntent);
                return true;
            } else if (item.getItemId() == R.id.deadlines) {
                Intent deadlinesIntent = new Intent(this, DeadlinesActivity.class);
                startActivity(deadlinesIntent);
                return true;
            }
            return true;
        });
    }
}
