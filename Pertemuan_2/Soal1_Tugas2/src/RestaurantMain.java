public class RestaurantMain {
    public static void main(String[] args) {
        Restaurant menu = new Restaurant();

        // Memasukkan data awal
        menu.tambahMenuMakanan("Bala-Bala", 1000, 20);
        menu.tambahMenuMakanan("Gehu", 1000, 20);
        menu.tambahMenuMakanan("Tahu", 1000, 0);
        menu.tambahMenuMakanan("Molen", 1000, 20);

        System.out.println("Daftar Menu:");
        menu.tampilMenuMakanan();

        System.out.println("\nSimulasi Pesanan:");
        // Pesanan berhasil (stok mencukupi)
        menu.pesanMenu("Bala-Bala", 5);

        // Pesanan ditolak (stok tidak mencukupi)
        menu.pesanMenu("Gehu", 25);

        // Pesanan menu yang tidak ada
        menu.pesanMenu("Ayam Goreng", 2);

        System.out.println("\nDaftar Menu setelah Pesanan:");
        menu.tampilMenuMakanan();
    }
}