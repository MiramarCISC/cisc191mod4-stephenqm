package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.util.DatabaseConfig;

import java.sql.*;
import java.util.*;

/* replaced: 
import java.util.ArrayList;
import java.util.List; */

public class JdbcCourseRepository implements CourseRepository {

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses (id, title, student_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, course.getId());
            ps.setString(2, course.getTitle());
            ps.setInt(3, course.getStudentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save course", e);
        }
    }

    @Override
    public List<Course> findByStudentId(int studentId) {
        String sql = "SELECT id, title, student_id FROM courses WHERE student_id = ?";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(rs.getInt("id"), rs.getString("title"), rs.getInt("student_id")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find courses by student id", e);
        }
        return courses;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete course", e);
        }
    }

    @Override
    public List<Course> findAll() {
        String sql = "SELECT id, title, student_id FROM courses";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("title"), rs.getInt("student_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list courses", e);
        }
        return courses;
    }
}
