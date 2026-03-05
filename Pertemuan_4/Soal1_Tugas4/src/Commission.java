//********************************************************************
// Commision.java Author: Richard
//
// Represents a special type of employee who has two sources of income at once
//********************************************************************

public class Commission extends Hourly {
    private double totalSales;
    private double commissionRate;

    // Constructor menerima 6 parameter
    public Commission(String eName, String eAddress, String ePhone, String socSecNumber, double rate, double commRate) {
        super(eName, eAddress, ePhone, socSecNumber, rate);
        this.commissionRate = commRate;
        this.totalSales = 0;
    }

    // Menambahkan jumlah penjualan
    public void addSales(double totalSales) {
        this.totalSales += totalSales;
    }

    // Override metode pay
    @Override
    public double pay() {
        // Gaji = Gaji per jam (dari parent) + (total penjualan * persentase komisi)
        double payment = super.pay() + (totalSales * commissionRate);
        totalSales = 0; // Kembalikan total penjualan ke 0
        return payment;
    }

    // Override metode toString
    @Override
    public String toString() {
        String result = super.toString();
        result += "\nTotal Sales: " + totalSales;
        return result;
    }
}