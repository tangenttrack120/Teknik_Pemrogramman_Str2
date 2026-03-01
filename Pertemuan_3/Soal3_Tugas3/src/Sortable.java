public abstract class Sortable {
    // Metode abstrak untuk membandingkan objek
    public abstract int compare(Sortable b);

    // Algoritma Shell Sort untuk mengurutkan array Sortable
    public static void shell_sort(Sortable[] a) {
        int n = a.length;
        // Penentuan gap (jarak) yang berkurang setengah setiap iterasi
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Sortable temp = a[i];
                int j;
                // Menggunakan metode compare untuk membandingkan
                for (j = i; j >= gap && a[j - gap].compare(temp) > 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }
}