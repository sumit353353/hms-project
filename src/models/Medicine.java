package models;

import java.util.Date;

public class Medicine {
    private int medicineId;
    private String medicineName;
    private String department;
    private int quantity;
    private double price;
    private String manufacturer;
    private Date expiryDate;

    // Constructor
    public Medicine(int medicineId, String medicineName, String department,
                    int quantity, double price, String manufacturer, Date expiryDate) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.department = department;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public int getMedicineId() { return medicineId; }
    public void setMedicineId(int medicineId) { this.medicineId = medicineId; }

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}