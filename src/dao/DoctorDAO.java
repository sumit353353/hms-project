package dao;

import models.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    /**
     * Add new doctor
     */
    public boolean addDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctor (name, specialization, contact_no, email, dept_id, availability) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getContactNo());
            ps.setString(4, doctor.getEmail());
            ps.setInt(5, doctor.getDeptId());
            ps.setString(6, doctor.getAvailability());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error adding doctor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all doctors
     */
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("contact_no"),
                        rs.getString("email"),
                        rs.getInt("dept_id"),
                        rs.getString("availability")
                );
                doctors.add(doctor);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving doctors: " + e.getMessage());
        }

        return doctors;
    }

    /**
     * Get doctor by ID
     */
    public Doctor getDoctorById(int doctorId) {
        String sql = "SELECT * FROM doctor WHERE doctor_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Doctor(
                            rs.getInt("doctor_id"),
                            rs.getString("name"),
                            rs.getString("specialization"),
                            rs.getString("contact_no"),
                            rs.getString("email"),
                            rs.getInt("dept_id"),
                            rs.getString("availability")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving doctor: " + e.getMessage());
        }

        return null;
    }

    /**
     * Get doctors by specialization
     */
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor WHERE specialization = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, specialization);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Doctor doctor = new Doctor(
                            rs.getInt("doctor_id"),
                            rs.getString("name"),
                            rs.getString("specialization"),
                            rs.getString("contact_no"),
                            rs.getString("email"),
                            rs.getInt("dept_id"),
                            rs.getString("availability")
                    );
                    doctors.add(doctor);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving doctors: " + e.getMessage());
        }

        return doctors;
    }

    /**
     * Update doctor information
     */
    public boolean updateDoctor(Doctor doctor) {
        String sql = "UPDATE doctor SET name=?, specialization=?, contact_no=?, email=?, availability=? WHERE doctor_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getContactNo());
            ps.setString(4, doctor.getEmail());
            ps.setString(5, doctor.getAvailability());
            ps.setInt(6, doctor.getDoctorId());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error updating doctor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete doctor
     */
    public boolean deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctor WHERE doctor_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting doctor: " + e.getMessage());
            return false;
        }
    }
}