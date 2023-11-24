package com.example.curricularica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ExamsAdapter extends ArrayAdapter<CourseModel> {
    public ExamsAdapter(Context context, List<CourseModel> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CourseModel course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_exams, parent, false);
        }
        // Lookup view for data population


        TextView tvTitle = convertView.findViewById(R.id.exam_title);
        TextView tvDate = convertView.findViewById(R.id.exam_date);
        TextView tvContent = convertView.findViewById(R.id.exam_content);

        // Populate the data into the template view using the data object
        if (!course.getExamTitle().equals("")) {
            tvTitle.setText(course.getExamTitle()); // In future this will be replaced since database will be added
            tvDate.setText(course.getExamDate()); // In future this will be replaced since database will be added
            tvContent.setText(course.getExamContent()); // In future this will be replaced since database will be added
        }


        // Return the completed view to render on screen
        return convertView;
    }
}
