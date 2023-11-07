package com.example.curricularica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    private TimetableView timeTableView;
    private List<CourseModel> courseModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseModels = new ArrayList<>();
        timeTableView = findViewById(R.id.main_timetable_ly);
        addList();
        timeTableView.setCourse(courseModels);

        findViewById(R.id.button_show_courses).setOnClickListener(v -> {
            Intent intent = new Intent(this, CoursesListActivity.class);
            intent.putExtra("TIMETABLE_LIST", (Serializable) courseModels);
            startActivity(intent);

        });

        findViewById(R.id.button_to_deadlines).setOnClickListener(v -> {
            Intent intent = new Intent(this, DeadlinesActivity.class);
            startActivity(intent);
        });

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
                "Qing Mi", "4-414", "1-16"));
        courseModels.add(new CourseModel(1, "COMP3011J", 5, 6, 1, "13:30", "15:05", "Mobile Computing",
                "Mohamed Saadeldin", "4-414", "1-15"));

        courseModels.add(new CourseModel(2, "COMP3033J", 3, 4, 2, "9:55", "11:30", "Computer Graphics",
                "Mohamed Saadeldin", "4-214", "1-15"));
        courseModels.add(new CourseModel(3, "COMP3019J", 5, 6, 2, "13:30", "15:05", "Web App Dev",
                "Aiden Murphy", "3-324", "1-15"));

        courseModels.add(new CourseModel(4, "COMP3008J", 1, 2, 3, "8:00", "9:35", "Distributed Systems",
                "Aiden Murphy", "4-214", "1-15"));
        courseModels.add(new CourseModel(5, "COMP3008J", 3, 4, 3, "9:55", "11:30", "Distributed Systems",
                "Aiden Murphy", "4-102", "1-15"));

        courseModels.add(new CourseModel(6, "COMP3033J", 1, 2, 4, "8:00", "9:35", "Computer Graphics",
                "Mohamed Saadeldin", "4-614", "1-15"));
        courseModels.add(new CourseModel(7, "COMP3011", 3, 4, 4, "9:55", "11:30", "Mobile Computing",
                "Mohamed Saadeldin", "4-614", "1-15"));
        courseModels.add(new CourseModel(8, "COMP3019J", 5, 6, 4, "13:30", "15:05", "Web App Dev",
                "Aiden Murphy", "4-214", "1-15"));

        courseModels.add(new CourseModel(9, "COMP3013J", 1, 2, 5, "8:00", "9:35", "Object-oriented Design",
                "Sean Russell", "3-516", "1-15"));
        courseModels.add(new CourseModel(10, "COMP3013J", 3, 4, 5, "9:55", "11:30", "Object-oriented Design",
                "Sean Russell", "3-516", "1-15"));

    }

}
