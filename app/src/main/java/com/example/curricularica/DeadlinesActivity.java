package com.example.curricularica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
            }
            return true;
        });

        listView = findViewById(R.id.list_exams);

        Intent examsIntent = getIntent();
        courseList = (List<CourseModel>) examsIntent.getSerializableExtra("TIMETABLE_LIST");

        examAdapter = new ExamsAdapter(this, courseList);
        listView.setAdapter(examAdapter);

        listView = findViewById(R.id.list_assignments);

        Intent assignmentsIntent = getIntent();
        courseList2 = (List<CourseModel>) assignmentsIntent.getSerializableExtra("TIMETABLE_LIST_2");

        assignmentsAdapter = new AssignmentsAdapter(this, courseList2);
        listView.setAdapter(assignmentsAdapter);
//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            // Create an intent to start the CourseDetailActivity
//            Intent intent1 = new Intent(DeadlinesActivity.this, CourseDetailsActivity.class);
//
//            // Pass the selected course details to the CourseDetailActivity
//            CourseModel selectedCourse = courseList.get(position);
//            intent1.putExtra("COURSE_DETAILS", selectedCourse);
//
//            // Start the new activity
//            startActivityForResult(intent1, 2);
//        });

    }
}
