package edu.sdccd.cisc191.service;

import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public Student getStudent(int id) {
        return repository.findById(id);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void changeGpa(int id, double newGpa) {
        repository.updateGpa(id, newGpa);
    }

    public void removeStudent(int id) {
        repository.deleteById(id);
    }
}
