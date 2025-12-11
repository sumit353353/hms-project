package dao;

import models.Medicine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    /**
     * Add new medicine
     */
    public boolean addMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicine (medicine_name, department, quantity, price, manufacturer, expiry_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medicine.getMedicineName());
            ps.setString(2, medicine.getDepartment());
            ps.setInt(3, medicine.getQuantity());
            ps.setDouble(4, medicine.getPrice());
            ps.setString(5, medicine.getManufacturer());
            ps.setDate(6, new java.sql.Date(medicine.getExpiryDate().getTime()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding medicine: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all medicines
     */
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicine";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Medicine med = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("medicine_name"),
                        rs.getString("department"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("manufacturer"),
                        rs.getDate("expiry_date")
                );
                medicines.add(med);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving medicines: " + e.getMessage());
        }

        return medicines;
    }

    /**
     * Get medicines by department
     */
    public List<Medicine> getMedicinesByDepartment(String department) {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicine WHERE department = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, department);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medicine med = new Medicine(
                            rs.getInt("medicine_id"),
                            rs.getString("medicine_name"),
                            rs.getString("department"),
                            rs.getInt("quantity"),
                            rs.getDouble("price"),
                            rs.getString("manufacturer"),
                            rs.getDate("expiry_date")
                    );
                    medicines.add(med);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving medicines: " + e.getMessage());
        }

        return medicines;
    }

    /**
     * Update medicine quantity
     */
    public boolean updateQuantity(int medicineId, int newQuantity) {
        String sql = "UPDATE medicine SET quantity = ? WHERE medicine_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newQuantity);
            ps.setInt(2, medicineId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating quantity: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get expired medicines
     */
    public List<Medicine> getExpiredMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicine WHERE expiry_date < CURDATE()";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Medicine med = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("medicine_name"),
                        rs.getString("department"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("manufacturer"),
                        rs.getDate("expiry_date")
                );
                medicines.add(med);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving expired medicines: " + e.getMessage());
        }

        return medicines;
    }

    /**
     * Get low stock medicines (quantity < 10)
     */
    public List<Medicine> getLowStockMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicine WHERE quantity < 10";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Medicine med = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("medicine_name"),
                        rs.getString("department"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("manufacturer"),
                        rs.getDate("expiry_date")
                );
                medicines.add(med);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving low stock medicines: " + e.getMessage());
        }

        return medicines;
    }
}
