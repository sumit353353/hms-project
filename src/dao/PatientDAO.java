package dao;

import models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    /**
     * Add new patient
     */
    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO patient (name, age, gender, contact_no, email, disease, admit_date, status, dept_id, doctor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getContactNo());
            ps.setString(5, patient.getEmail());
            ps.setString(6, patient.getDisease());
            ps.setDate(7, new java.sql.Date(patient.getAdmitDate().getTime()));
            ps.setString(8, patient.getStatus());
            ps.setInt(9, patient.getDeptId());
            ps.setInt(10, patient.getDoctorId());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error adding patient: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all patients
     */
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contact_no"),
                        rs.getString("email"),
                        rs.getString("disease"),
                        rs.getDate("admit_date"),
                        rs.getString("status"),
                        rs.getInt("dept_id"),
                        rs.getInt("doctor_id")
                );
                patients.add(patient);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving patients: " + e.getMessage());
        }

        return patients;
    }

    /**
     * Get patient by ID
     */
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("contact_no"),
                            rs.getString("email"),
                            rs.getString("disease"),
                            rs.getDate("admit_date"),
                            rs.getString("status"),
                            rs.getInt("dept_id"),
                            rs.getInt("doctor_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving patient: " + e.getMessage());
        }

        return null;
    }

    /**
     * Update patient information
     */
    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE patient SET name=?, age=?, gender=?, contact_no=?, email=?, disease=?, status=?, doctor_id=? WHERE patient_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getContactNo());
            ps.setString(5, patient.getEmail());
            ps.setString(6, patient.getDisease());
            ps.setString(7, patient.getStatus());
            ps.setInt(8, patient.getDoctorId());
            ps.setInt(9, patient.getPatientId());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error updating patient: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete patient
     */
    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM patient WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting patient: " + e.getMessage());
            return false;
        }
    }

    /**
     * Search patient by name
     */
    public List<Patient> searchPatientByName(String name) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Patient patient = new Patient(
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("contact_no"),
                            rs.getString("email"),
                            rs.getString("disease"),
                            rs.getDate("admit_date"),
                            rs.getString("status"),
                            rs.getInt("dept_id"),
                            rs.getInt("doctor_id")
                    );
                    patients.add(patient);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching patients: " + e.getMessage());
        }

        return patients;
    }

    /**
     * Get active patients
     */
    public List<Patient> getActivePatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE status = 'Admitted'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contact_no"),
                        rs.getString("email"),
                        rs.getString("disease"),
                        rs.getDate("admit_date"),
                        rs.getString("status"),
                        rs.getInt("dept_id"),
                        rs.getInt("doctor_id")
                );
                patients.add(patient);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving active patients: " + e.getMessage());
        }

        return patients;
    }
}
