package models;

public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String contactNo;
    private String email;
    private int deptId;
    private String availability;

    // Constructor
    public Doctor(int doctorId, String name, String specialization,
                  String contactNo, String email, int deptId, String availability) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.contactNo = contactNo;
        this.email = email;
        this.deptId = deptId;
        this.availability = availability;
    }

    // Getters and Setters
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}