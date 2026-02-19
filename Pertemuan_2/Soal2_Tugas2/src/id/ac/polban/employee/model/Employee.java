package id.ac.polban.employee.model;

public class Employee {
    private static int idCounter = 1;

    private int id;
    private String name;
    private Department department;
    private EmploymentType type;
    private double salary;

    public Employee(String name, Department department, EmploymentType type, double salary) {
        this.id = idCounter++;
        this.name = name;
        this.department = department;
        this.type = type;
        this.salary = salary;
    }

    public static int getCurrentIdCounter() {
        return idCounter;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public EmploymentType getType() { return type; }
    public double getSalary() { return salary; }

    public String toString() {
        return String.format("ID: %d | Name: %s | Dept: %s | Type: %s | Salary: %.2f",
                id, name, department.getName(), type, salary);
    }
}