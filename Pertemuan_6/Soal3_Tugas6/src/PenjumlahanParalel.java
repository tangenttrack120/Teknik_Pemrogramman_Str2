import java.util.Scanner;

// Class untuk menjalankan tugas per thread (menerapkan antarmuka Runnable)
class SumWorker implements Runnable {
    private int startNum;
    private int endNum;
    private int threadId;
    private long[] partialResults;

    // Constructor untuk mendistribusikan rentang angka dan array hasil
    public SumWorker(int threadId, int startNum, int endNum, long[] partialResults) {
        this.threadId = threadId;
        this.startNum = startNum;
        this.endNum = endNum;
        this.partialResults = partialResults;
    }

    @Override
    public void run() {
        long sum = 0;
        // Melakukan penjumlahan parsial pada rentang yang ditentukan
        for (int i = startNum; i <= endNum; i++) {
            sum += i;
        }

        // Thread Safety: Setiap thread menyimpan hasilnya ke indeks array miliknya sendiri
        // Sehingga tidak terjadi Race Condition pada saat penulisan data parsial.
        partialResults[threadId] = sum;

        System.out.println("Thread " + (threadId + 1) + " : Menjumlahkan " + startNum + " sampai " + endNum + " -> Hasil Parsial = " + sum);
    }
}

public class PenjumlahanParalel {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        // Menerima input dari pengguna
        System.out.print("Masukkan Jumlah Thread: ");
        int numThreads = sc.nextInt();
        System.out.print("Masukkan Angka Akhir: ");
        int targetNumber = sc.nextInt();

        // Array untuk menyimpan hasil parsial. Ukurannya mengikuti jumlah thread
        long[] partialSums = new long[numThreads];
        Thread[] threads = new Thread[numThreads];

        int chunkSize = targetNumber / numThreads;
        int remainder = targetNumber % numThreads;
        int currentStart = 1;

        System.out.println("\n--- Memulai Proses Penjumlahan Paralel ---");

        // Mekanisme Pembagian Tugas (Divide and Conquer)
        for (int i = 0; i < numThreads; i++) {
            int currentEnd = currentStart + chunkSize - 1;

            // Jika ini adalah thread terakhir, berikan semua sisa (remainder) kepadanya
            if (i == numThreads - 1) {
                currentEnd += remainder;
            }

            // Membuat dan memulai Thread
            SumWorker worker = new SumWorker(i, currentStart, currentEnd, partialSums);
            threads[i] = new Thread(worker);
            threads[i].start();

            // Majukan start point untuk thread berikutnya
            currentStart = currentEnd + 1;
        }

        long totalSum = 0;

        // Menggabungkan (Sinkronisasi) hasil dari semua thread menggunakan join()
        for (int i = 0; i < numThreads; i++) {
            threads[i].join(); // Menunggu tiap thread hingga selesai bekerja
            totalSum += partialSums[i]; // Menjumlahkan hasil parsial ke total akhir
        }

        System.out.println("\n--- HASIL AKHIR ---");
        System.out.println("Total Penjumlahan Akhir: " + totalSum);

        sc.close();
    }
}