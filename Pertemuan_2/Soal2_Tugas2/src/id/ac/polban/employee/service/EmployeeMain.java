package id.ac.polban.employee.service;

import id.ac.polban.employee.model.Department;
import id.ac.polban.employee.model.Employee;
import id.ac.polban.employee.model.EmploymentType;

public class EmployeeMain {
    public static void main(String[] args) {
        // Membuat object Department
        Department deptIT = new Department("IT Development");
        Department deptHR = new Department("Human Resources");

        // Membuat object Employee
        Employee emp1 = new Employee("Rizky", deptIT, EmploymentType.SENIOR, 15000000);
        Employee emp2 = new Employee("Siti", deptHR, EmploymentType.JUNIOR, 8000000);
        Employee emp3 = new Employee("Budi", deptIT, EmploymentType.INTERN, 3000000);

        // Menampilkan Data
        System.out.println("--- DAFTAR PEGAWAI ---");
        System.out.println(emp1.toString());
        System.out.println(emp2.toString());
        System.out.println(emp3.toString());

        // Menampilkan penggunaan Static Method
        System.out.println("\nTotal ID yang telah tergenerate: " + (Employee.getCurrentIdCounter() - 1));
    }
}