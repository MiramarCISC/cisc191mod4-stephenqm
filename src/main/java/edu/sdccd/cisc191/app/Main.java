package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.JdbcCourseRepository;
import edu.sdccd.cisc191.repository.JdbcStudentRepository;
import edu.sdccd.cisc191.service.StudentService;
import edu.sdccd.cisc191.util.DatabaseInitializer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize database tables
        DatabaseInitializer.initialize();

        // Create repositories and service
        JdbcStudentRepository studentRepo = new JdbcStudentRepository();
        JdbcCourseRepository courseRepo   = new JdbcCourseRepository();
        StudentService service            = new StudentService(studentRepo);

        // Add at least 3 students
        service.addStudent(new Student(1, "Chris",   3.9));
        service.addStudent(new Student(2, "Jace",     3.4));
        service.addStudent(new Student(3, "Andre", 3.7));

        // Add at least 3 courses linked to students
        courseRepo.save(new Course(1, "Calculus I",  1));
        courseRepo.save(new Course(2, "Intro to Python",   1));
        courseRepo.save(new Course(3, "Calculus II",  2));
        courseRepo.save(new Course(4, "Creative Writing",   3));

        // Print all students before changes
        System.out.println("All Students (before changes)");
        service.getAllStudents().forEach(System.out::println);

        // Find one student by ID
        System.out.println("Find Student by ID 2");
        Student found = service.getStudent(2);
        System.out.println(found);

        // Print courses for student 1
        System.out.println("Courses for Student 1 (Alice)");
        courseRepo.findByStudentId(1).forEach(System.out::println);

        // Update one student's GPA
        System.out.println("Updating Bob's GPA to 3.6");
        service.changeGpa(2, 3.6);

        // Delete one student (must delete their courses first due to FK constraint)
        System.out.println("Deleting Student 3 (Charlie)");
        // Delete Charlie's courses first to satisfy FK constraint
        List<Course> charlieCourses = courseRepo.findByStudentId(3);
        for (Course c : charlieCourses) {
            courseRepo.deleteById(c.getId());
        }
        service.removeStudent(3);

        // Print remaining students and courses after changes
        System.out.println("All Students (after changes)");
        service.getAllStudents().forEach(System.out::println);

        System.out.println("All Courses (after changes)");
        courseRepo.findAll().forEach(System.out::println);
    }
}