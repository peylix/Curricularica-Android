package com.example.curricularica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailsActivity extends AppCompatActivity {
    private EditText courseCode, courseName, lecturerName, roomNumber, startTime, endTime, weekday, startTimePlot, endTimePlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_course);

        // Get the Intent that started this activity and extract the course details
        Intent intent = getIntent();
        CourseModel course = (CourseModel) intent.getSerializableExtra("COURSE_DETAILS");

        courseCode = findViewById(R.id.edit_course_code);
        courseName = findViewById(R.id.edit_course_name);
        lecturerName = findViewById(R.id.edit_lecturer_name);
        roomNumber = findViewById(R.id.edit_room_number);
        startTime = findViewById(R.id.edit_start_time);
        endTime = findViewById(R.id.edit_end_time);
        weekday = findViewById(R.id.edit_weeks);
        startTimePlot = findViewById(R.id.edit_start_time_plot);
        endTimePlot = findViewById(R.id.edit_end_time_plot);


        findViewById(R.id.button_finish).setOnClickListener(v -> {
            // Update the course object with new user inputs
            course.setCourseCode(courseCode.getText().toString());
            course.setName(courseName.getText().toString());
            course.setInstructor(lecturerName.getText().toString());
            course.setClassroom(roomNumber.getText().toString());
            course.setStartTime(startTime.getText().toString());
            course.setEndTime(endTime.getText().toString());
            course.setWeekday(Integer.parseInt(weekday.getText().toString()));
            course.setStartTimePlot(Integer.parseInt(startTimePlot.getText().toString()));
            course.setEndTimePlot(Integer.parseInt(endTimePlot.getText().toString()));


            // Prepare data intent for result
            Intent data = new Intent();
            data.putExtra("UPDATED_COURSE", course);
            setResult(RESULT_OK, data);

            // Finish activity (and returns to MainActivity)
            finish();
        });

    }
}
