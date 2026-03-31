package edu.sdccd.cisc191.model;

public class Student {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name must not be blank");
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("gpa must be between 0.0 and 4.0");
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getGpa() { return gpa; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name must not be blank");
        this.name = name;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("gpa must be between 0.0 and 4.0");
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', gpa=" + gpa + "}";
    }
}
 