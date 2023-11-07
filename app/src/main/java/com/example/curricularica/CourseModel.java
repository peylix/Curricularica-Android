package com.example.curricularica;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseModel implements Serializable {
    private int id;
    private String courseCode;
    private int startTimePlot;
    private int endTimePlot;
    private int weekday;
    private String startTime = "";
    private String endTime = "";
    private String name = "";
    private String instructor = "";
    private String classroom = "";
    private String weekRange = "";
    private ArrayList<String> deadlineDate = new ArrayList<>();
    private ArrayList<String> deadlineTitle = new ArrayList<>();
    private ArrayList<String> deadlineContent = new ArrayList<>();


    @Override
    public String toString() {
        return "CourseModel{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", startTimePlot=" + startTimePlot +
                ", endTimePlot=" + endTimePlot +
                ", weekday=" + weekday +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                ", classroom='" + classroom + '\'' +
                ", weekRange='" + weekRange + '\'' +
                ", deadlineDate=" + deadlineDate +
                ", deadlineTitle=" + deadlineTitle +
                ", deadlineContent=" + deadlineContent +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getStartTimePlot() {
        return startTimePlot;
    }

    public int getEndTimePlot() {
        return endTimePlot;
    }

    public int getWeekday() {
        return weekday;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getWeekRange() {
        return weekRange;
    }
    public ArrayList<String> getDeadlineDate() {
        return deadlineDate;
    }

    public ArrayList<String> getDeadlineTitle() {
        return deadlineTitle;
    }

    public ArrayList<String> getDeadlineContent() {
        return deadlineContent;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setStartTimePlot(int startTimePlot) {
        this.startTimePlot = startTimePlot;
    }

    public void setEndTimePlot(int endTimePlot) {
        this.endTimePlot = endTimePlot;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setWeekRange(String weekRange) {
        this.weekRange = weekRange;
    }
    public void setDeadlineDate(String date) {
        this.deadlineDate.add(date);
    }

    public void setDeadlineTitle(String title) {
        this.deadlineTitle.add(title);
    }

    public void setDeadlineContent(String content) {
        this.deadlineContent.add(content);
    }

    public CourseModel() {
        // TODO Auto-generated constructor stub
    }

    public CourseModel(int id, String courseCode, int startTimePlot, int endTimePlot, int weekday,
                       String startTime, String endTime, String name, String teacher,
                       String classroom, String weekRange) {
        super();
        this.id = id;
        this.courseCode = courseCode;
        this.startTimePlot = startTimePlot;
        this.endTimePlot = endTimePlot;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.instructor = teacher;
        this.classroom = classroom;
        this.weekRange = weekRange;
    }

}
