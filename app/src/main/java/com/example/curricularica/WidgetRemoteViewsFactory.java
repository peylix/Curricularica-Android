package com.example.curricularica;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.util.Log;

import java.util.List;

class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private List<CourseModel> courses;

    public WidgetRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        // Load the courses data here
        courses = MainActivity.loadCoursesStatic(context);
    }

    @Override
    public void onDataSetChanged() {
        // Load the courses data here
        courses = MainActivity.loadCoursesStatic(context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return courses != null ? courses.size() : 0;
    }

    // Implement other required methods like getViewAt, getCount, etc.
    // Example for getViewAt:
    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);

        if (position < 0 || position >= courses.size()) {
            Log.e("WidgetRemoteViewsFactory", "Invalid position: " + position);
            return null;
        }

        CourseModel course = courses.get(position);


        Log.d("WidgetRemoteViewsFactory", "Course at position " + position + ": " + course.toString());

        rv.setTextViewText(R.id.widget_course_name, course.getName());
//        rv.setTextViewText(R.id.widget_start_time, course.getStartTime());
//        rv.setTextViewText(R.id.widget_end_time, course.getEndTime());
        // Set other data as needed

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // ... other required methods ...
}
