package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Course;
import java.util.List;

public interface CourseRepository {
    void save(Course course);
    List<Course> findByStudentId(int studentId);
    List<Course> findAll();
    void deleteById(int id);
}
