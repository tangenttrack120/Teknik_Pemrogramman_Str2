public class Restaurant {
    // Semua attribute bersifat private
    private String[] nama_makanan;
    private double[] harga_makanan;
    private int[] stok;
    private int id = 0;

    public Restaurant() {
        nama_makanan = new String[10];
        harga_makanan = new double[10];
        stok = new int[10];
    }

    // Akses data dilakukan melalui getter dan setter
    public String getNamaMakanan(int id) {
        return nama_makanan[id];
    }

    public void setNamaMakanan(int id, String nama) {
        this.nama_makanan[id] = nama;
    }

    public double getHargaMakanan(int id) {
        return harga_makanan[id];
    }

    public void setHargaMakanan(int id, double harga) {
        this.harga_makanan[id] = harga;
    }

    public int getStok(int id) {
        return stok[id];
    }

    // Validasi stok (stok tidak boleh negatif)
    public void setStok(int id, int stokBaru) {
        if (stokBaru >= 0) {
            this.stok[id] = stokBaru;
        } else {
            System.out.println("Stok tidak boleh negatif! Menetapkan stok ke 0.");
            this.stok[id] = 0;
        }
    }

    // Method untuk menambah menu
    public void tambahMenuMakanan(String nama, double harga, int stokAwal) {
        if (id < nama_makanan.length) {
            setNamaMakanan(id, nama);
            setHargaMakanan(id, harga);
            setStok(id, stokAwal);
            id++;
        } else {
            System.out.println("Kapasitas menu penuh!");
        }
    }

    // Method tampil menu
    public void tampilMenuMakanan() {
        for (int i = 0; i < id; i++) {
            if (!isOutOfStock(i)) {
                System.out.println(getNamaMakanan(i) + "[" + getStok(i) + "]" + "\tRp. " + getHargaMakanan(i));
            }
        }
    }

    private boolean isOutOfStock(int id) {
        return getStok(id) == 0;
    }

    // Pengembangan Fitur (Mini Case): Pemesanan menu
    public void pesanMenu(String namaPesanan, int jumlahPesanan) {
        System.out.println("Memproses pesanan: " + jumlahPesanan + " " + namaPesanan);

        if (jumlahPesanan <= 0) {
            System.out.println("Jumlah pesanan tidak valid.");
            return;
        }

        boolean menuDitemukan = false;
        for (int i = 0; i < id; i++) {
            // Mencari menu yang namanya cocok (mengabaikan huruf besar/kecil)
            if (getNamaMakanan(i).equalsIgnoreCase(namaPesanan)) {
                menuDitemukan = true;

                // Cek apakah stok mencukupi
                if (getStok(i) >= jumlahPesanan) {
                    // Stok otomatis berkurang setelah pemesanan
                    setStok(i, getStok(i) - jumlahPesanan);
                    System.out.println("Pesanan berhasil. Sisa stok: " + getStok(i));
                } else {
                    // Pesan ditolak jika stok tidak mencukupi
                    System.out.println("Maaf, stok tidak mencukupi. Sisa stok saat ini: " + getStok(i));
                }
                break; // Keluar dari loop jika menu sudah diproses
            }
        }

        if (!menuDitemukan) {
            System.out.println("Menu " + namaPesanan + " tidak ditemukan.");
        }
    }
}
