package models;

public class Department {
    private int deptId;
    private String deptName;
    private String headDoctor;
    private int numOfBeds;

    // Constructor
    public Department(int deptId, String deptName, String headDoctor, int numOfBeds) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.headDoctor = headDoctor;
        this.numOfBeds = numOfBeds;
    }

    // Getters and Setters
    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public String getHeadDoctor() { return headDoctor; }
    public void setHeadDoctor(String headDoctor) { this.headDoctor = headDoctor; }

    public int getNumOfBeds() { return numOfBeds; }
    public void setNumOfBeds(int numOfBeds) { this.numOfBeds = numOfBeds; }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", numOfBeds=" + numOfBeds +
                '}';
    }
}
