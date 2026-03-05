package com.klu;

public class Course {

    private String courseName;
    private int duration;

    public Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public String toString() {
        return courseName + " (" + duration + " months)";
    }
}
