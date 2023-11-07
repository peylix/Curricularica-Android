package com.example.curricularica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<CourseModel> {

    public CourseAdapter(Context context, List<CourseModel> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CourseModel course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_course, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = convertView.findViewById(R.id.text_course_title);
        TextView tvInfo = convertView.findViewById(R.id.text_course_info);

        // Populate the data into the template view using the data object
        tvTitle.setText(course.getCourseCode() + "\n" + course.getName()); // Replace with your method to get title
        tvInfo.setText("Instructor: " + course.getInstructor() + "\nRoom: " + course.getClassroom() + "\nTime: " + course.getStartTime() + " - " + course.getEndTime());

        // Return the completed view to render on screen
        return convertView;
    }
}
