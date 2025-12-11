package models;

import java.util.Date;

public class Bill {
    private int billId;
    private int patientId;
    private int doctorId;
    private double amount;
    private Date billDate;
    private String paymentStatus;

    // Constructor
    public Bill(int billId, int patientId, int doctorId, double amount,
                Date billDate, String paymentStatus) {
        this.billId = billId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.amount = amount;
        this.billDate = billDate;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getBillDate() { return billDate; }
    public void setBillDate(Date billDate) { this.billDate = billDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", patientId=" + patientId +
                ", amount=" + amount +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
