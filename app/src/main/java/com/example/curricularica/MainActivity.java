package com.example.curricularica;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    private TimetableView timeTableView;
    private List<CourseModel> courseModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



//        courseModels = new ArrayList<>();
//        timeTableView = findViewById(R.id.main_timetable_ly);
//        addList();
//        timeTableView.setCourse(courseModels);



        if (isSharedPreferencesEmpty()) {
            courseModels = new ArrayList<>();
            addList();
            saveCourses(courseModels);
        }

        timeTableView = findViewById(R.id.main_timetable_ly);
        timeTableView.setCourse(loadCourses());


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.timetable);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.timetable) {
                Intent timetableIntent = new Intent(this, MainActivity.class);
                startActivity(timetableIntent);
                return true;
            } else if (item.getItemId() == R.id.deadlines) {
                Intent deadlinesIntent = new Intent(this, DeadlinesActivity.class);
//                deadlinesIntent.putExtra("TIMETABLE_LIST", (Serializable) courseModels);
//                deadlinesIntent.putExtra("TIMETABLE_LIST_2", (Serializable) courseModels);

                startActivity(deadlinesIntent);
                return true;
            } else if (item.getItemId() == R.id.copilot) {
                Intent copilotIntent = new Intent(this, ChatActivity.class);
                startActivity(copilotIntent);
                return true;
            }
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.menu_show_courses) {
//            Intent coursesIntent = new Intent(this, CoursesListActivity.class);
//            coursesIntent.putExtra("TIMETABLE_LIST", (Serializable) courseModels);
//            startActivity(coursesIntent);
//            return true;
//        } else
        if (id == R.id.menu_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (id == R.id.menu_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        } else if (id == R.id.action_favorite) {
            openAddCourseDialog();
        } else if (id == R.id.menu_removing_courses) {
            openRemoveCourseDialog();
        }

        return super.onOptionsItemSelected(item);
    }
    private void openAddCourseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_new_course_popup, null);
        builder.setView(dialogView);

        EditText courseNameEditText = dialogView.findViewById(R.id.course_name);
        EditText courseCodeEditText = dialogView.findViewById(R.id.course_code);
        EditText instructorEditText = dialogView.findViewById(R.id.instructor);
        EditText startTimePlotEditText = dialogView.findViewById(R.id.start_time_plot);
        EditText endTimePlotEditText = dialogView.findViewById(R.id.end_time_plot);
        EditText weekdayEditText = dialogView.findViewById(R.id.weekday);
        EditText startTimeEditText = dialogView.findViewById(R.id.start_time);
        EditText endTimeEditText = dialogView.findViewById(R.id.end_time);
        EditText classroomEditText = dialogView.findViewById(R.id.classroom);
        EditText weekRangeEditText = dialogView.findViewById(R.id.week_range);
        EditText examTitleEditText = dialogView.findViewById(R.id.exam_title);
        EditText examContentEditText = dialogView.findViewById(R.id.exam_content);
        EditText examDateEditText = dialogView.findViewById(R.id.exam_date);
        EditText assignmentTitleEditText = dialogView.findViewById(R.id.assignment_title);
        EditText assignmentContentEditText = dialogView.findViewById(R.id.assignment_content);
        EditText assignmentDateEditText = dialogView.findViewById(R.id.assignment_date);


        builder.setPositiveButton("Save", (dialog, id) -> {
            // Retrieve text from EditText fields
            String courseName = courseNameEditText.getText().toString();
            String courseCode = courseCodeEditText.getText().toString();
            String instructor = instructorEditText.getText().toString();
            String startTimePlot = startTimePlotEditText.getText().toString();
            String endTimePlot = endTimePlotEditText.getText().toString();
            String weekday = weekdayEditText.getText().toString();
            String startTime = startTimeEditText.getText().toString();
            String endTime = endTimeEditText.getText().toString();
            String classroom = classroomEditText.getText().toString();
            String weekRange = weekRangeEditText.getText().toString();
            String examTitle = examTitleEditText.getText().toString();
            String examContent = examContentEditText.getText().toString();
            String examDate = examDateEditText.getText().toString();
            String assignmentTitle = assignmentTitleEditText.getText().toString();
            String assignmentContent = assignmentContentEditText.getText().toString();
            String assignmentDate = assignmentDateEditText.getText().toString();

            List<CourseModel> list = loadCourses();

            int newId = list.size(); // Ensure unique ID for each course
            CourseModel newCourse = new CourseModel(11, courseCode,
                    Integer.parseInt(startTimePlot), Integer.parseInt(endTimePlot),
                    Integer.parseInt(weekday), startTime, endTime, courseName, instructor,
                    classroom, weekRange, examTitle, examDate, examContent,
                    assignmentTitle, assignmentDate, assignmentContent);

            // Add the new course and save

            list.add(newCourse);
            saveCourses(list);

            // Update the UI
            timeTableView.setCourse(list);

            // Reload the MainActivity
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });

        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openRemoveCourseDialog() {
        // Load current courses
        List<CourseModel> courses = loadCourses();
        String[] courseNames = new String[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            courseNames[i] = courses.get(i).getName(); // Assuming CourseModel has a getName() method
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a Course to Remove");

        builder.setItems(courseNames, (dialog, which) -> {
            // Remove selected course
            confirmRemoveCourse(courses, which);
        });

        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void confirmRemoveCourse(List<CourseModel> courses, int courseIndex) {
        CourseModel courseToRemove = courses.get(courseIndex);
        AlertDialog.Builder confirmDialog = new AlertDialog.Builder(this);
        confirmDialog.setTitle("Confirm Removal");
        confirmDialog.setMessage("Are you sure you want to remove " + courseToRemove.getName() + "?");

        confirmDialog.setPositiveButton("Remove", (dialog, which) -> {
            courses.remove(courseIndex);
            saveCourses(courses);
            timeTableView.setCourse(courses);

            // Optionally, reload the MainActivity
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });

        confirmDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        confirmDialog.show();
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                CourseModel updatedCourse = (CourseModel) data.getSerializableExtra("UPDATED_COURSE");
                for (int i = 0; i < courseModels.size(); i++) {
                    if (courseModels.get(i).getId() == updatedCourse.getId()) { // assuming there's an getId() method
                        courseModels.set(i, updatedCourse);
                        break;
                    }
                }

                // Update the timetable view to reflect the changes
                timeTableView.setCourse(courseModels);
            }
        }
    }





    private void addList() {
        courseModels.add(new CourseModel(0, "COMP3017J", 3, 4, 1, "9:55", "11:30", "Software Methodology",
                "Qing Mi", "4-414", "1-16", "COMP3017J Final Exam", "2023-12-25", "Location: TB4-201", "Technical Interview Project", "2023-12-15", "Should submit a docker image."));
        courseModels.add(new CourseModel(2, "COMP3033J", 3, 4, 2, "9:55", "11:30", "Computer Graphics",
                "Mohamed Saadeldin", "4-214", "1-15", "COMP3033J Final Exam", "2023-12-25", "Location: TB4-201", "Technical Interview Project", "2023-12-15", "Should submit a docker image."));
        courseModels.add(new CourseModel(1, "COMP3011J", 5, 6, 1, "13:30", "15:05", "Mobile Computing",
                "Mohamed Saadeldin", "4-414", "1-15", "", "", "", "Final Submission", "2023-12-21", "Go to CS Moodle for more details."));


        courseModels.add(new CourseModel(3, "COMP3019J", 5, 6, 2, "13:30", "15:05", "Web App Dev",
                "Aiden Murphy", "3-324", "1-15", "", "", "", "Project Milestone 2", "2023-12-10", "Go to CS Moodle for more details."));

        courseModels.add(new CourseModel(4, "COMP3008J", 1, 2, 3, "8:00", "9:35", "Distributed Systems",
                "Aiden Murphy", "4-214", "1-15", "COMP3008J Final Exam", "2023-12-21", "Location: TB4-302", "Case Study", "2023-12-01", "Go to CS Moodle for more details."));
        courseModels.add(new CourseModel(5, "COMP3008J", 3, 4, 3, "9:55", "11:30", "Distributed Systems",
                "Aiden Murphy", "4-102", "1-15", "COMP3008J Final Exam", "2023-12-21", "Location: TB4-302", "Case Study", "2023-12-01", "Go to CS Moodle for more details."));

        courseModels.add(new CourseModel(6, "COMP3033J", 1, 2, 4, "8:00", "9:35", "Computer Graphics",
                "Mohamed Saadeldin", "4-614", "1-15", "COMP3033J Final Exam", "2023-12-27", "Location: TB4-502", "Project 2", "2023-12-31", "Go to CS Moodle for more details."));
        courseModels.add(new CourseModel(7, "COMP3011", 3, 4, 4, "9:55", "11:30", "Mobile Computing",
                "Mohamed Saadeldin", "4-614", "1-15", "", "", "", "Final Submission", "2023-12-21", "Go to CS Moodle for more details."));
        courseModels.add(new CourseModel(8, "COMP3019J", 5, 6, 4, "13:30", "15:05", "Web App Dev",
                "Aiden Murphy", "4-214", "1-15", "", "", "", "Project Milestone 2", "2023-12-10", "Go to CS Moodle for more details."));

        courseModels.add(new CourseModel(9, "COMP3013J", 1, 2, 5, "8:00", "9:35", "Object-oriented Design",
                "Sean Russell", "3-516", "1-15", "COMP3013J Final Exam", "2023-12-19", "Location: TB4-102", "Assignment Part 4", "2023-12-15", "Go to CS Moodle for more details. Note that we should submit a docker image."));
        courseModels.add(new CourseModel(10, "COMP3013J", 3, 4, 5, "9:55", "11:30", "Object-oriented Design",
                "Sean Russell", "3-516", "1-15", "COMP3013J Final Exam", "2023-12-19", "Location: TB4-102", "Assignment Part 4", "2023-12-15", "Go to CS Moodle for more details. Note that we should submit a docker image."));

    }


    private void saveCourses(List<CourseModel> courses) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(courses);
        editor.putString("courses", json);
        editor.apply();
    }


    public List<CourseModel> loadCourses() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("courses", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CourseModel>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
//            courseModels = new ArrayList<>();
//            addList();
//            return courseModels;
            return new ArrayList<>();
        }
    }

    public static List<CourseModel> loadCoursesStatic(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("courses", null);
        List<CourseModel> filteredCourses = new ArrayList<>();

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CourseModel>>() {}.getType();
            List<CourseModel> allCourses = gson.fromJson(json, type);

            CourseModel closestCourse = getClosestCourse(allCourses);
            if (closestCourse != null) {
                filteredCourses.add(closestCourse);
            }
        }

        return filteredCourses;
    }

    private static CourseModel getClosestCourse(List<CourseModel> courses) {
        CourseModel closestCourse = null;
        long minTimeDiff = Long.MAX_VALUE;
        long currentTime = System.currentTimeMillis();

        for (CourseModel course : courses) {
            long courseTime = parseCourseTime(course.getStartTime());
            long timeDiff = Math.abs(courseTime - currentTime);

            if (timeDiff < minTimeDiff) {
                minTimeDiff = timeDiff;
                closestCourse = course;
            }
        }

        return closestCourse;
    }

    private static long parseCourseTime(String timeString) {
        // Parse the course start time string into a timestamp
        // This will depend on the format of your start time string
        // Example: assuming format "HH:mm"
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date date = sdf.parse(timeString);
            return date != null ? date.getTime() : 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


    private boolean isSharedPreferencesEmpty() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("courses", null);

        return json == null || json.isEmpty();
    }





}
