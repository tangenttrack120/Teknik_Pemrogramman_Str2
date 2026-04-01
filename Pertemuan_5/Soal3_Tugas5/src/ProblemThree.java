package JavaProblems;

import java.util.Arrays;

public class ProblemThree {
    public static void main(String[] args) {
        int arr[] = {12, 4, 3, 1, 9, 657};
        int n = 3; // Target elemen yang dicari dan dalam soal ini adalah terbesar ke-3

        int ans = Arrays.stream(arr) // [1] Ubah array primitif ke IntStream
                .boxed()                                     // [2] Konversi ke Stream<Integer>
                .sorted((a, b) -> Integer.compare(b, a))     // Urutkan descending (besar ke kecil)
                .skip(n - 1)                                 // [3] Lewati (n-1) elemen pertama
                .findFirst()                                 // [4] Ambil elemen yang tersisa
                .orElse(0);                                  // [5] Nilai default jika tidak ada

        // Sesuai output yang diharapkan pada soal
        System.out.println("The 3rd largest element is: " + ans);
    }
}