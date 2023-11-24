package com.example.curricularica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class CoursesListActivity extends Activity {

    private ListView listView;
    private CourseAdapter adapter;
    private List<CourseModel> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        listView = findViewById(R.id.list_courses);

        Intent intent = getIntent();
        courseList = (List<CourseModel>) intent.getSerializableExtra("TIMETABLE_LIST");

        adapter = new CourseAdapter(this, courseList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Create an intent to start the CourseDetailActivity
            Intent intent1 = new Intent(CoursesListActivity.this, CourseDetailsActivity.class);

            // Pass the selected course details to the CourseDetailActivity
            CourseModel selectedCourse = courseList.get(position);
            intent1.putExtra("COURSE_DETAILS", selectedCourse);

            // Start the new activity
            startActivityForResult(intent1, 2);
        });

    }
}
