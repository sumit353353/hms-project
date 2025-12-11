package dao;

import models.Bill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    /**
     * Generate bill for patient
     */
    public boolean generateBill(Bill bill) {
        String sql = "INSERT INTO bill (patient_id, doctor_id, amount, bill_date, payment_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bill.getPatientId());
            ps.setInt(2, bill.getDoctorId());
            ps.setDouble(3, bill.getAmount());
            ps.setDate(4, new java.sql.Date(bill.getBillDate().getTime()));
            ps.setString(5, bill.getPaymentStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error generating bill: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all bills
     */
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill ORDER BY bill_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bill bill = new Bill(
                        rs.getInt("bill_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDouble("amount"),
                        rs.getDate("bill_date"),
                        rs.getString("payment_status")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving bills: " + e.getMessage());
        }

        return bills;
    }

    /**
     * Get bills for a patient
     */
    public List<Bill> getPatientBills(int patientId) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill WHERE patient_id = ? ORDER BY bill_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bill bill = new Bill(
                            rs.getInt("bill_id"),
                            rs.getInt("patient_id"),
                            rs.getInt("doctor_id"),
                            rs.getDouble("amount"),
                            rs.getDate("bill_date"),
                            rs.getString("payment_status")
                    );
                    bills.add(bill);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving patient bills: " + e.getMessage());
        }

        return bills;
    }

    /**
     * Update payment status
     */
    public boolean updatePaymentStatus(int billId, String status) {
        String sql = "UPDATE bill SET payment_status = ? WHERE bill_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, billId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating payment status: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get pending bills
     */
    public List<Bill> getPendingBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill WHERE payment_status = 'Pending' ORDER BY bill_date";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bill bill = new Bill(
                        rs.getInt("bill_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDouble("amount"),
                        rs.getDate("bill_date"),
                        rs.getString("payment_status")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pending bills: " + e.getMessage());
        }

        return bills;
    }

    /**
     * Calculate total revenue
     */
    public double calculateTotalRevenue() {
        String sql = "SELECT SUM(amount) as total FROM bill WHERE payment_status = 'Paid'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating revenue: " + e.getMessage());
        }

        return 0.0;
    }
}
