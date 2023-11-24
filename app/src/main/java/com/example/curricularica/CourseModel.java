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
    private String examTitle = "";
    private String examDate = "";
    private String examContent = "";
    private String assignmentTitle = "";
    private String assignmentDate = "";
    private String assignmentContent = "";


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
                ", examTitle='" + examTitle + '\'' +
                ", examDate='" + examDate + '\'' +
                ", examContent='" + examContent + '\'' +
                ", assignmentTitle='" + assignmentTitle + '\'' +
                ", assignmentDate='" + assignmentDate + '\'' +
                ", assignmentContent='" + assignmentContent + '\'' +
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

    public String getExamTitle() {
        return examTitle;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getExamContent() {
        return examContent;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public String getAssignmentDate() {
        return assignmentDate;
    }

    public String getAssignmentContent() {
        return assignmentContent;
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

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public void setAssignmentDate(String assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public void setAssignmentContent(String assignmentContent) {
        this.assignmentContent = assignmentContent;
    }

    public CourseModel() {
        // TODO Auto-generated constructor stub
    }

    public CourseModel(int id, String courseCode, int startTimePlot, int endTimePlot, int weekday,
                       String startTime, String endTime, String name, String teacher,
                       String classroom, String weekRange, String examTitle, String examDate,
                       String examContent, String assignmentTitle, String assignmentDate,
                       String assignmentContent) {
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
        this.examTitle = examTitle;
        this.examDate = examDate;
        this.examContent = examContent;
        this.assignmentTitle = assignmentTitle;
        this.assignmentDate = assignmentDate;
        this.assignmentContent = assignmentContent;
    }

}
