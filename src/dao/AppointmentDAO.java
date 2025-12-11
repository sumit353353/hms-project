package dao;

import models.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    /**
     * Schedule new appointment
     */
    public boolean scheduleAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment (patient_id, doctor_id, appointment_date, appointment_time, status, reason) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            ps.setString(4, appointment.getAppointmentTime());
            ps.setString(5, appointment.getStatus());
            ps.setString(6, appointment.getReason());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error scheduling appointment: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all appointments
     */
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment ORDER BY appointment_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Appointment apt = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("appointment_date"),
                        rs.getString("appointment_time"),
                        rs.getString("status"),
                        rs.getString("reason")
                );
                appointments.add(apt);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving appointments: " + e.getMessage());
        }

        return appointments;
    }

    /**
     * Get appointments for a specific patient
     */
    public List<Appointment> getPatientAppointments(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE patient_id = ? ORDER BY appointment_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment apt = new Appointment(
                            rs.getInt("appointment_id"),
                            rs.getInt("patient_id"),
                            rs.getInt("doctor_id"),
                            rs.getDate("appointment_date"),
                            rs.getString("appointment_time"),
                            rs.getString("status"),
                            rs.getString("reason")
                    );
                    appointments.add(apt);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving patient appointments: " + e.getMessage());
        }

        return appointments;
    }

    /**
     * Get appointments for a specific doctor
     */
    public List<Appointment> getDoctorAppointments(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE doctor_id = ? ORDER BY appointment_date, appointment_time";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment apt = new Appointment(
                            rs.getInt("appointment_id"),
                            rs.getInt("patient_id"),
                            rs.getInt("doctor_id"),
                            rs.getDate("appointment_date"),
                            rs.getString("appointment_time"),
                            rs.getString("status"),
                            rs.getString("reason")
                    );
                    appointments.add(apt);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving doctor appointments: " + e.getMessage());
        }

        return appointments;
    }

    /**
     * Cancel appointment
     */
    public boolean cancelAppointment(int appointmentId) {
        String sql = "UPDATE appointment SET status = 'Cancelled' WHERE appointment_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error cancelling appointment: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reschedule appointment
     */
    public boolean rescheduleAppointment(int appointmentId, java.util.Date newDate, String newTime) {
        String sql = "UPDATE appointment SET appointment_date = ?, appointment_time = ? WHERE appointment_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(newDate.getTime()));
            ps.setString(2, newTime);
            ps.setInt(3, appointmentId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error rescheduling appointment: " + e.getMessage());
            return false;
        }
    }
}
