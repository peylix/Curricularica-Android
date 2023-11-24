package com.example.curricularica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AssignmentsAdapter extends ArrayAdapter<CourseModel> {
    public AssignmentsAdapter(Context context, List<CourseModel> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CourseModel course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_assignments, parent, false);
        }
        // Lookup view for data population


        TextView tvTitle = convertView.findViewById(R.id.assignment_title);
        TextView tvDate = convertView.findViewById(R.id.assignment_date);
        TextView tvContent = convertView.findViewById(R.id.assignment_content);

        // Populate the data into the template view using the data object
        if (!course.getAssignmentTitle().equals("")) {
            tvTitle.setText(course.getAssignmentTitle()); // In future this will be replaced since database will be added
            tvDate.setText(course.getAssignmentDate()); // In future this will be replaced since database will be added
            tvContent.setText(course.getAssignmentContent()); // In future this will be replaced since database will be added
        }


        // Return the completed view to render on screen
        return convertView;
    }
}
