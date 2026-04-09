class Account {
    int balance = 150;
}

public class TransferFulus {
    public static void main(String[] args) throws InterruptedException {
        Account acc1 = new Account();
        Account acc2 = new Account();

        // Thread 1: Menjumlahkan/ transfer fulus dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            synchronized (acc1) { // Mengunci objek acc1 agar tidak dimodifikasi oleh thread lain secara bersamaan.
                System.out.println("Thread 1: Berhasil mengunci acc1, menunggu acc2...");

                // Simulasi jeda. Exception diperlukan karena Thread.sleep() melempar InterruptedException
                // jika thread ini dihentikan (interrupted) paksa saat sedang tertidur.
                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (acc2) { // Mengunci objek acc2 untuk menyelesaikan pemindahan dana.
                    System.out.println("Thread 1: Berhasil mengunci acc2, memproses transfer...");
                    acc2.balance += acc1.balance;
                }
            }
        });

        // Thread 2: Menjumlahkan/ transfer fulus dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            // Solusi: Urutan penguncian (lock order) disamakan dengan Thread 1 untuk mencegah Deadlock.
            synchronized (acc1) { // Mengunci objek acc1 terlebih dahulu.
                System.out.println("Thread 2: Berhasil mengunci acc1, menunggu acc2...");
                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (acc2) { // Kemudian mengunci objek acc2.
                    System.out.println("Thread 2: Berhasil mengunci acc2, memproses transfer...");
                    acc1.balance += acc2.balance;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Hasil Akhir: ");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
        System.out.println("Saldo Akhir acc2: " + acc2.balance);
    }
}