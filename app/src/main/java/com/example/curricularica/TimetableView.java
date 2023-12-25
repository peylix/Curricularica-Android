package com.example.curricularica;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class TimetableView extends LinearLayout {

    // Array showing the colors
    public static int colors[] = {R.drawable.select_label_soft_blue,
            R.drawable.select_label_orange, R.drawable.select_label_light_red,
            R.drawable.select_label_yellow, R.drawable.select_label_light_brown,
            R.drawable.select_label_blue, R.drawable.select_label_green_yellow,
            R.drawable.select_label_light_blue, R.drawable.select_label_light_pink,
            R.drawable.select_label_soft_green, R.drawable.select_label_light_purple,
            R.drawable.select_label_light_yellow, R.drawable.select_label_light_orange,
            R.drawable.select_label_green, R.drawable.select_label_grey};
    private final static int START = 0;

    // the maximum number of courses displayed in the timetable
    public final static int MAXIMUM_BLOCKS = 12;

    // total weekdays
    public final static int WEEKDAY = 7;

    // the height of a single timetable grid
    private final static int TIME_TABLE_HEIGHT = 50;
    private final static int TIME_TABLE_LINE_HEIGHT = 2;
    private final static int LEFT_TITLE_WIDTH = 20;
    private final static int WEEK_TITLE_HEIGHT = 30;

    // displaying weekday titles
    private LinearLayout horizontalLayout;
    private LinearLayout verticalLayout;
    private String[] weekTitles = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    public static String[] colorStr = new String[20];
    int colorNum = 0;
    private List<CourseModel> courseModels = new ArrayList<CourseModel>();

    private Context context;

    public TimetableView(Context context) {
        super(context);
    }

    public TimetableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    // horizontal line
    private View getWeekHorizontalLine() {
        View line = new View(getContext());
        line.setBackgroundColor(getResources().getColor(R.color.view_line));
        line.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, TIME_TABLE_LINE_HEIGHT));
        return line;
    }

    // vertical line
    private View getWeekVerticalLine() {
        View line = new View(context);
        line.setBackgroundColor(getResources().getColor(R.color.view_line));
        line.setLayoutParams(new ViewGroup.LayoutParams((TIME_TABLE_LINE_HEIGHT), dip2px(WEEK_TITLE_HEIGHT)));
        return line;
    }


    private void initView() {

        horizontalLayout = new LinearLayout(getContext());
        horizontalLayout.setOrientation(HORIZONTAL);
        verticalLayout = new LinearLayout(getContext());
        verticalLayout.setOrientation(HORIZONTAL);

        // form of the timetable
        for (int i = 0; i <= WEEKDAY; i++) {
            if (i == 0) {
                layoutLeftNumber();
            } else {
                layoutWeekTitleView(i);
                layoutContentView(i);
            }

            verticalLayout.addView(createTableVerticalLine());
            horizontalLayout.addView(getWeekVerticalLine());
        }
        addView(horizontalLayout);
        addView(getWeekHorizontalLine());
        addView(verticalLayout);
    }

    @NonNull
    private View createTableVerticalLine() {
        View l = new View(getContext());
        l.setLayoutParams(new ViewGroup.LayoutParams(TIME_TABLE_LINE_HEIGHT, dip2px(TIME_TABLE_HEIGHT * MAXIMUM_BLOCKS) + (MAXIMUM_BLOCKS - 2) * TIME_TABLE_LINE_HEIGHT));
        l.setBackgroundColor(getResources().getColor(R.color.view_line));
        return l;
    }

    private void layoutContentView(int weekday) {
        List<CourseModel> weekClassList = findWeekClassList(weekday);
        //添加
        LinearLayout mLayout = createWeekTimeTableView(weekClassList, weekday);
        mLayout.setOrientation(VERTICAL);
        mLayout.setLayoutParams(new ViewGroup.LayoutParams((getViewWidth() - dip2px(20)) / WEEKDAY, LayoutParams.MATCH_PARENT));
        mLayout.setWeightSum(1);
        verticalLayout.addView(mLayout);
    }

    // traverse the courses from Monday to Sunday then sort them
    @NonNull
    private List<CourseModel> findWeekClassList(int weekday) {
        List<CourseModel> list = new ArrayList<>();
        for (CourseModel cm : courseModels) {
            if (cm.getWeekday() == weekday) {
                list.add(cm);
            }
        }

        Collections.sort(list, Comparator.comparingInt(CourseModel::getStartTimePlot));

        return list;
    }

    private void layoutWeekTitleView(int weekNumber) {
        TextView weekText = new TextView(getContext());
        weekText.setTextColor(getResources().getColor(R.color.text_color));
        weekText.setWidth(((getViewWidth() - dip2px(LEFT_TITLE_WIDTH))) / WEEKDAY);
        weekText.setHeight(dip2px(WEEK_TITLE_HEIGHT));
        weekText.setGravity(Gravity.CENTER);
        weekText.setTextSize(16);
        weekText.setText(weekTitles[weekNumber - 1]);
        horizontalLayout.addView(weekText);
    }


    private void layoutLeftNumber() {
        // blank block
        TextView block = new TextView(context);
        block.setLayoutParams(new ViewGroup.LayoutParams(dip2px(LEFT_TITLE_WIDTH), dip2px(WEEK_TITLE_HEIGHT)));
        horizontalLayout.addView(block);

        // draw all these blocks from 1 to MAXIMUM_BLOCKS
        LinearLayout numberView = new LinearLayout(context);
        numberView.setLayoutParams(new ViewGroup.LayoutParams(dip2px(LEFT_TITLE_WIDTH), dip2px(MAXIMUM_BLOCKS * TIME_TABLE_HEIGHT) + MAXIMUM_BLOCKS * 2));
        numberView.setOrientation(VERTICAL);
        for (int j = 1; j <= MAXIMUM_BLOCKS; j++) {
            TextView number = createNumberView(j);
            numberView.addView(number);
            numberView.addView(getWeekHorizontalLine());
        }
        verticalLayout.addView(numberView);
    }

    @NonNull
    private TextView createNumberView(int j) {
        TextView number = new TextView(getContext());
        number.setLayoutParams(new ViewGroup.LayoutParams(dip2px(LEFT_TITLE_WIDTH), dip2px(TIME_TABLE_HEIGHT)));
        number.setGravity(Gravity.CENTER);
        number.setTextColor(getResources().getColor(R.color.text_color));
        number.setTextSize(14);
        number.setText(String.valueOf(j));
        return number;
    }

    private int getViewWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }


    // draw blanks
    private View addBlankView(int count, final int week, final int start) {
        LinearLayout blank = new LinearLayout(getContext());
        blank.setOrientation(VERTICAL);
        for (int i = 1; i < count; i++) {
            View classView = new View(getContext());
            classView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(TIME_TABLE_HEIGHT)));
            blank.addView(classView);
            blank.addView(getWeekHorizontalLine());
            final int num = i;
            // click the blank block
            classView.setOnClickListener(v -> Toast.makeText(getContext(), "Weekday " + week + ", Class " + (start + num), Toast.LENGTH_LONG).show());

        }
        return blank;
    }


    // courses from Monday to Sunday
    private LinearLayout createWeekTimeTableView(List<CourseModel> weekList, int weekday) {
        LinearLayout weekTableView = new LinearLayout(getContext());
        weekTableView.setOrientation(VERTICAL);
        int size = weekList.size();
        if (weekList.isEmpty()) {
            weekTableView.addView(addBlankView(MAXIMUM_BLOCKS + 1, weekday, 0));
        } else {
            for (int i = 0; i < size; i++) {
                CourseModel courseModel = weekList.get(i);
                if (i == 0) {
                    // add blanks until there is a course
                    weekTableView.addView(addBlankView(courseModel.getStartTimePlot(), weekday, 0));
                    weekTableView.addView(createClassView(courseModel));
                } else if (weekList.get(i).getStartTimePlot() - weekList.get(i - 1).getEndTimePlot() > 0) {
                    weekTableView.addView(addBlankView(weekList.get(i).getStartTimePlot() - weekList.get(i - 1).getEndTimePlot(), weekday, weekList.get(i - 1).getEndTimePlot()));
                    weekTableView.addView(createClassView(weekList.get(i)));
                }
                // draw other blanks
                if (i + 1 == size) {
                    weekTableView.addView(addBlankView(MAXIMUM_BLOCKS - weekList.get(i).getEndTimePlot() + 1, weekday, weekList.get(i).getEndTimePlot()));
                }
            }
        }
        return weekTableView;
    }

    // obtain the view of a timetable
    @SuppressWarnings("deprecation")
    private View createClassView(final CourseModel model) {
        LinearLayout timetableView = new LinearLayout(getContext());
        timetableView.setOrientation(VERTICAL);
        int num = (model.getEndTimePlot() - model.getStartTimePlot());
        timetableView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px((num + 1) * TIME_TABLE_HEIGHT) + (num + 1) * TIME_TABLE_LINE_HEIGHT));

        TextView timetableViewName = new TextView(getContext());
        timetableViewName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px((num + 1) * TIME_TABLE_HEIGHT) + (num) * TIME_TABLE_LINE_HEIGHT));

        timetableViewName.setTextColor(getContext().getResources().getColor(
                android.R.color.white));
        timetableViewName.setTextSize(16);
        timetableViewName.setGravity(Gravity.CENTER);
        timetableViewName.setText(model.getName() + "@" + model.getClassroom());

        timetableView.addView(timetableViewName);
        timetableView.addView(getWeekHorizontalLine());

        timetableView.setBackgroundDrawable(getContext().getResources()
                .getDrawable(colors[getColorNum(model.getName())]));

        // clicking a timetableView unit
        timetableView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(model.getCourseCode() + "\n" + model.getName());
            builder.setMessage("Room: " + model.getClassroom() + "\nFrom: " + model.getStartTime() + "\nTo: " + model.getEndTime() + "\nInstructor: " + model.getInstructor() + "\nWeek Range: " + model.getWeekRange());
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        return timetableView;
    }


    // convert from dip to px
    public int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }

    public void setCourse(List<CourseModel> courseModels) {
        this.courseModels = courseModels;
        for (CourseModel cm : courseModels) {
            addTimeName(cm.getName());
        }
        initView();
        invalidate();
    }


    // determine if the course has already been there
    private void addTimeName(String name) {
        boolean isRepeat = true;
        for (int i = 0; i < 20; i++) {
            if (name.equals(colorStr[i])) {
                isRepeat = true;
                break;
            } else {
                isRepeat = false;
            }
        }
        if (!isRepeat) {
            colorStr[colorNum] = name;
            colorNum++;
        }
    }


    // obtain the name of the course from colorNum
    public static int getColorNum(String name) {
        int num = 0;
        for (int i = 0; i < 20; i++) {
            if (name.equals(colorStr[i])) {
                num = i;
            }
        }
        return num;
    }
}
