package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository implements StudentRepository {

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO students (id, name, gpa) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setDouble(3, student.getGpa());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save student", e);
        }
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT id, name, gpa FROM students WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt("id"), rs.getString("name"), rs.getDouble("gpa"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find student by id", e);
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT id, name, gpa FROM students";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getDouble("gpa")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list students", e);
        }
        return students;
    }

    @Override
    public void updateGpa(int id, double newGpa) {
        String sql = "UPDATE students SET gpa = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newGpa);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update GPA", e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }
}