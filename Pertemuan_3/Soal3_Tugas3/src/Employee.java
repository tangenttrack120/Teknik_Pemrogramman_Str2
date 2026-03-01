public class Employee extends Sortable {
    private String name;
    private double salary;
    private int hireday;
    private int hiremonth;
    private int hireyear;

    public Employee(String n, double s, int day, int month, int year) {
        name = n;
        salary = s;
        hireday = day;
        hiremonth = month;
        hireyear = year;
    }

    public void print() {
        System.out.println(name + " " + salary + " " + hireYear());
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public int hireYear() {
        return hireyear;
    }

    // Mengimplementasikan metode compare dari class Sortable
    @Override
    public int compare(Sortable b) {
        Employee eb = (Employee) b; // Casting dari Sortable ke Employee
        if (this.salary < eb.salary) return -1;
        if (this.salary > eb.salary) return 1;
        return 0; // Jika gaji sama
    }
}