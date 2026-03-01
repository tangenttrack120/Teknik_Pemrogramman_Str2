public class EmployeeTest {
    public static void main(String[] args) {
        // Membuat array bertipe Employee berkapasitas 3
        Employee[] staff = new Employee[3];

        // Memasukkan data ke dalam array (termasuk objek Manager yang merupakan subclass Employee)
        staff[0] = new Employee("Harry Hacker", 35000, 1, 10, 1989);
        staff[1] = new Manager("Carl Cracker", 75000, 15, 12, 1987);
        staff[2] = new Employee("Tony Tester", 38000, 15, 3, 1990);

        // Menaikkan gaji setiap staf sebesar 5%
        for (int i = 0; i < 3; i++) {
            staff[i].raiseSalary(5);
        }

        System.out.println("--- Daftar Staf (Sebelum Diurutkan) ---");
        for (int i = 0; i < 3; i++) {
            staff[i].print();
        }

        // --- Memenuhi Instruksi: Memanggil metode compare secara manual ---
        System.out.println("\n--- Hasil Uji Pemanggilan Metode Compare ---");
        int hasilBanding = staff[0].compare(staff[1]);
        System.out.println("Perbandingan staff[0] (Harry) vs staff[1] (Carl) menghasilkan: " + hasilBanding);
        if (hasilBanding == -1) {
            System.out.println("-> Gaji Harry Hacker LEBIH KECIL dari Carl Cracker");
        }
        // ------------------------------------------------------------------

        // Memanggil fungsi pengurutan dari Sortable
        Sortable.shell_sort(staff);

        System.out.println("\n--- Daftar Staf (Setelah Diurutkan Berdasarkan Gaji) ---");
        for (int i = 0; i < 3; i++) {
            staff[i].print();
        }
    }
}