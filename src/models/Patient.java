package models;

import java.util.Date;

public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String contactNo;
    private String email;
    private String disease;
    private Date admitDate;
    private Date dischargeDate;
    private String status;
    private int deptId;
    private int doctorId;

    // Constructor
    public Patient(int patientId, String name, int age, String gender,
                   String contactNo, String email, String disease,
                   Date admitDate, String status, int deptId, int doctorId) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactNo = contactNo;
        this.email = email;
        this.disease = disease;
        this.admitDate = admitDate;
        this.status = status;
        this.deptId = deptId;
        this.doctorId = doctorId;
    }

    // Getters and Setters
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }

    public Date getAdmitDate() { return admitDate; }
    public void setAdmitDate(Date admitDate) { this.admitDate = admitDate; }

    public Date getDischargeDate() { return dischargeDate; }
    public void setDischargeDate(Date dischargeDate) { this.dischargeDate = dischargeDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", disease='" + disease + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
