package edu.sdccd.cisc191.model;

public class Course {
    private int id;
    private String title;
    private int studentId;

    public Course(int id, String title, int studentId) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title must not be blank");
        if (studentId <= 0) throw new IllegalArgumentException("studentId must be > 0");
        this.id = id;
        this.title = title;
        this.studentId = studentId;
    }

    public int getId() { return id; }

    public String getTitle() { return title; }

    public int getStudentId() { return studentId; }

    @Override
    public String toString() {
        return "Course{id=" + id + ", title='" + title + "', studentId=" + studentId + "}";
    }
}
 