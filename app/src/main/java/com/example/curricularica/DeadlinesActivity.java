package com.example.curricularica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DeadlinesActivity extends AppCompatActivity {
    private ListView listView;
    private ExamsAdapter examAdapter;
    private AssignmentsAdapter assignmentsAdapter;
    private List<CourseModel> courseList;
    private List<CourseModel> courseList2;

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
            } else if (item.getItemId() == R.id.copilot) {
                Intent copilotIntent = new Intent(this, ChatActivity.class);
                startActivity(copilotIntent);
                return true;
            }
            return true;
        });

        listView = findViewById(R.id.list_exams);

//        Intent examsIntent = getIntent();
//        courseList = (List<CourseModel>) examsIntent.getSerializableExtra("TIMETABLE_LIST");

        courseList = loadCourses();

        examAdapter = new ExamsAdapter(this, courseList);
        listView.setAdapter(examAdapter);

        listView = findViewById(R.id.list_assignments);

//        Intent assignmentsIntent = getIntent();
//        courseList2 = (List<CourseModel>) assignmentsIntent.getSerializableExtra("TIMETABLE_LIST_2");

        courseList2 = loadCourses();

        assignmentsAdapter = new AssignmentsAdapter(this, courseList2);
        listView.setAdapter(assignmentsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (id == R.id.menu_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<CourseModel> loadCourses() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("courses", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CourseModel>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }


}
