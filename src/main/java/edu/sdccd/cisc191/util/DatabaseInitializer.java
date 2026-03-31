package edu.sdccd.cisc191.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        String createStudents = """
                CREATE TABLE IF NOT EXISTS students (
                    id   INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    gpa  DOUBLE NOT NULL
                )""";

        String createCourses = """
                CREATE TABLE IF NOT EXISTS courses (
                    id         INT PRIMARY KEY,
                    title      VARCHAR(100) NOT NULL,
                    student_id INT,
                    FOREIGN KEY (student_id) REFERENCES students(id)
                )""";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStudents);
            stmt.execute(createCourses);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
    public static void reset() {
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            // Delete courses first to satisfy the foreign key constraint
            stmt.execute("DELETE FROM courses");
            stmt.execute("DELETE FROM students");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to reset database", e);
        }
    }
}

