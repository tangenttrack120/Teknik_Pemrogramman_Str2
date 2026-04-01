package JavaProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Class Employee
class Employee {
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}

public class ProblemOne {
    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee("John", 50000));
        list.add(new Employee("Rob", 70000));
        list.add(new Employee("Bob", 40000));
        list.add(new Employee("Alice", 10000));

        // Melakukan proses filtering/sorting menggunakan Stream API
        List<Employee> sortedEmp = list.stream() // [1] Ubah list ke stream
                // Mengurutkan employee berdasarkan nama
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName())) // [2] dan [3]
                // Mengumpulkan hasil akhir
                .collect( Collectors.toList() ); // [4] dan [5]

        for(Employee e : sortedEmp){
            System.out.println(e);
        }
    }
}