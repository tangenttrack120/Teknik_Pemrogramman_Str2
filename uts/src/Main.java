import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

// Soal 1: Buat class-class dari diagram kelas 1 (interface departmenr)
// Diagram Kelas 1: Interface Koperasi
interface IKoperasi {
    double getPinjamanKoperasi();
    void setPinjamanKoperasi(double amount);
}

// Soal 1: Buat class-class dari diagram kelas 1 (Jabatan)
// Department / Jabatan
enum Jabatan {
    MANAGER("staf_manager"),
    PROGRAMMER("staf_programer"),
    ANALIS("staf_analis");

    private final String name;

    Jabatan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Soal 1: Buat class-class dari diagram kelas 1 (Employee)
// Soal 2: terapkan solusi-solusi keterbatasan multiple inheritance di java melalui single inheritance dan interface implementation pada program yang dibuat
// Saal 3: Enkapsulasi Employee class: public, digunakan dalam class lain juga
// Abstract base class Employee
abstract class Employee {
    protected String name;
    protected Jabatan jabatan;
    protected LocalDate startWorkingDate;
    protected List<OvertimeRecord> overtimeRecords = new ArrayList<>();
    protected int childrenCount;

    // 4. gunakan java collection (list/set/map) untuk menyimpan informasi karyawan dan data lembur
    public Employee(String name, Jabatan jabatan, LocalDate startWorkingDate, int childrenCount) {
        this.name = name;
        this.jabatan = jabatan;
        this.startWorkingDate = startWorkingDate;
        this.childrenCount = childrenCount;
    }

    public String getName() {
        return name;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public long getLamaBekerja(LocalDate referenceDate) {
        return ChronoUnit.YEARS.between(startWorkingDate, referenceDate);
    }

    // gaji_pokok (berdasarkan level jabatan dan lama bekerja)
    /*
     * staf_manager =
     * if ( lama_bekerja > 0 && lama_bekerja <= 2) {
     * gaji_pokok = 5000000;
     * } else if ( lama_bekerja > 2 && <= 5) {
     * gaji_pokok = 6000000;
     * } else if ( lama_bekerja > 5) {
     * gaji_pokok = 7000000;
     * } else {
     * print("lama kerja invalid");
     * }
     *
     * staf_programer =
     * if ( lama_bekerja > 0 && lama_bekerja <= 2) {
     * gaji_pokok = 3000000;
     * } else if ( lama_bekerja > 2 && <= 5) {
     * gaji_pokok = 4000000;
     * } else if ( lama_bekerja > 5) {
     * gaji_pokok = 5000000;
     * } else {
     * print("lama kerja invalid");
     * }
     *
     * staf_analis =
     * if ( lama_bekerja > 0 && lama_bekerja <= 2) {
     * gaji_pokok = 3000000;
     * } else if ( lama_bekerja > 2 && <= 5) {
     * gaji_pokok = 3500000;
     * } else if ( lama_bekerja > 5) {
     * gaji_pokok = 4500000;
     * } else {
     * print("lama kerja invalid");
     * }
     */

    // beralih dari yang awalnya ingin if-else ke switch case
    public double getGajiPokok(LocalDate ref) {
        long yrs = getLamaBekerja(ref);
        int i = (yrs <= 2) ? 0 : (yrs <= 5) ? 1 : 2;
        if (yrs < 0) System.out.println("lama kerja invalid");
        return switch (jabatan) {
            case MANAGER -> new double[]{5000000, 6000000, 7000000}[i];
            case PROGRAMMER -> new double[]{3000000, 4000000, 5000000}[i];
            case ANALIS -> new double[]{3000000, 3500000, 4500000}[i];
        };
    }

    // tunjangan_lembur = (hari_kerja = 30000 * hour) (Hari_libur = 50000 * hour)
    public double getTunjanganLembur() {
        // shorthand if-else
        return overtimeRecords.stream().mapToDouble(r -> r.getDurationHours() * (r.isWeekend() ? 50000 : 30000)).sum();
    }

    public double getHours(boolean weekend) {
        return overtimeRecords.stream().filter(r -> r.isWeekend() == weekend).mapToDouble(OvertimeRecord::getDurationHours).sum();
    }

    // format pencetakan gaji:
    /*
    ==== Slip Gaji Bulan **** ====
    Tanggal Terbit: 1 **** 20**
    Tanggal Pembayaran: 1 **** 20**

    Nama: ****
    Jabatan: ****
    Total Jam Kerja: X jam
    Total Lembur: x jam (Akhir Pekan x Jam)
    Total Tunjangan ****:       Rp.****
    Total Tunjangan ****:       Rp.****
    Total Pinjaman Koperasi:    Rp.****
    Total Gaji:                 Rp.****
    */
    public void printSlip(LocalDate month, double... extra) {
        LocalDate payDate = month.plusMonths(1).withDayOfMonth(1);
        while (payDate.getDayOfWeek().getValue() >= 6) payDate = payDate.minusDays(1);
        System.out.printf("==== Slip Gaji Bulan %s ====\nTanggal Terbit: 1 %s %d\nTanggal Pembayaran: %d %s %d\n\nNama: %s\nJabatan: %s\n",
                month.getMonth(), month.plusMonths(1).getMonth(), month.getYear(), payDate.getDayOfMonth(), payDate.getMonth(), payDate.getYear(), name, jabatan.getName());
        double totalOvertime = getHours(true) + getHours(false);
        System.out.printf("Total Jam Kerja: %.1f jam\nTotal Lembur: %.1f jam (Akhir Pekan %.1f Jam)\n", 22 * 8.0 + totalOvertime, totalOvertime, getHours(true));
    }

    public abstract double getSalary(LocalDate referenceDate);
    public abstract void printSalarySlip(LocalDate monthDate);
}

// Saal 3: Enkapsulasi class lembur: public kecuali waktu mulai dan selesai yang hanya digunakan di class OvertimeRecord
class OvertimeRecord {
    private LocalDateTime start;
    private LocalDateTime end;

    public OvertimeRecord(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public double getDurationHours() {
        return ChronoUnit.MINUTES.between(start, end) / 60.0;
    }

    public boolean isWeekend() {
        int dayOfWeek = start.getDayOfWeek().getValue();
        return dayOfWeek == 6 || dayOfWeek == 7; // Saturday or Sunday
    }
}

// Soal 1: Buat class-class dari diagram kelas 1 (Fulltime)
// Saal 3: Enkapsulasi FulltimeEmployee class: public kecuali pinjaman koperasi yang hanya di pakai di perhitungan gaji kayawan fulltime
// Soal 5: menerapkan polymorphism pada metode perhitungan gaji karyawan parttime
class FulltimeEmployee extends Employee implements IKoperasi {
    private double pinjamanKoperasi;

    // Soal 4: gunakan java collection (list/set/map) untuk menyimpan informasi karyawan dan data lembur
    public FulltimeEmployee(String name, Jabatan jabatan, LocalDate startWorkingDate, int childrenCount) {
        super(name, jabatan, startWorkingDate, childrenCount);
    }

    public void setPinjamanKoperasi(double amount) { this.pinjamanKoperasi = amount; }
    public double getPinjamanKoperasi() { return pinjamanKoperasi; }

    // tunjangan_jabatan (diberikan jika bekerja >= 3 tahun)
    /*
     * staf_manager =
     * if ( lama_bekerja >= 3 ) {
     * tunjangan_jabatan = 5000000;
     * }
     *
     * staf_programer =
     * if ( lama_bekerja >= 3 ) {
     * tunjangan_jabatan = 2000000;
     * }
     *
     * staf_analis =
     * if ( lama_bekerja >= 3 ) {
     * tunjangan_jabatan = 3000000;
     * }
     */

    // Beralih ke switch case untuk efisiensi
    public double getTunjanganJabatan(LocalDate ref) {
        if (getLamaBekerja(ref) < 3) return 0;
        return switch (jabatan) {
            case MANAGER -> 5000000;
            case PROGRAMMER -> 2000000;
            case ANALIS -> 3000000;
        };
    }

    // bonus_produktivitas = if (work more than 200 hour a month get 10% bonus of gaji_pokok)
    public double getBonusProduktivitas(LocalDate ref) {
        return (22 * 8.0 + getHours(true) + getHours(false) > 200) ? 0.1 * getGajiPokok(ref) : 0;
    }

    @Override
    public double getSalary(LocalDate ref) {
        return getGajiPokok(ref) + getTunjanganLembur() + getTunjanganJabatan(ref) + Math.min(childrenCount, 3) * 500000 + 500000 + getBonusProduktivitas(ref) - pinjamanKoperasi;
    }

    // gaji_fulltime = gaji_pokok + tunjangan_lembur + tunjangan_jabatan + tunjangan_anak + tunjangan_komunikasi + bonus_produktivitas - pinjaman_koperasi
    // for fulltime_employee
    // tunjangan_komunikasi = 500000;
    // tunjangan_anak = (maxchildren = 3) (500000 * children)
    public void printSalarySlip(LocalDate month) {
        super.printSlip(month);
        System.out.printf("Total Tunjangan Jabatan:    Rp.%,.0f\nTotal Tunjangan Anak:       Rp.%,.0f\nTotal Tunjangan Komunikasi: Rp.%,.0f\nTotal Tunjangan Lembur:     Rp.%,.0f\nBonus Produktivitas:        Rp.%,.0f\nTotal Pinjaman Koperasi:    Rp.%,.0f\nTotal Gaji:                 Rp.%,.0f\n\n",
                getTunjanganJabatan(month), Math.min(childrenCount, 3) * 500000.0, 500000.0, getTunjanganLembur(), getBonusProduktivitas(month), pinjamanKoperasi, getSalary(month));
    }
}

// Soal 1: Buat class-class dari diagram kelas 1 (Parttime)
// Soal 3: Enkapsulasi ParttimeEmoloyee class: public kecuali proyek yang hanya terbatas pada karyawan parttime
// Soal 5: menerapkan polymorphism pada metode perhitungan gaji karyawan parttime
class ParttimeEmployee extends Employee {
    private int projectsDone = 0;

    // 4. gunakan java collection (list/set/map) untuk menyimpan informasi karyawan dan data lembur
    public ParttimeEmployee(String name, Jabatan jabatan, LocalDate startWorkingDate, int childrenCount) {
        super(name, jabatan, startWorkingDate, childrenCount);
    }

    // bonus_proyek = (for parttime_employee) for each project done get 200000 bonus
    public void setProjectsDone(int projectsDone) { this.projectsDone = projectsDone; }

    @Override
    public double getSalary(LocalDate ref) {
        return getGajiPokok(ref) + getTunjanganLembur() + projectsDone * 200000.0;
    }

    // gaji_parttime = gaji_pokok + tunjangan_lembur + bonus_proyek
    // for parttime_employee
    public void printSalarySlip(LocalDate month) {
        super.printSlip(month);
        System.out.printf("Total Tunjangan Lembur:     Rp.%,.0f\nTotal Bonus Proyek:         Rp.%,.0f\nTotal Gaji:                 Rp.%,.0f\n\n",
                getTunjanganLembur(), projectsDone * 200000.0, getSalary(month));
    }
}

// Soal 3: Enkapsulasi main class: public
// soal 6: main untuk menampilkan gaji
public class Main {
    public static void main(String[] args) {
        /*
        1. Asep: fulltime_employee, staf_programer, bekerja sejak 1 januari 2021, memiliki 2 anak
         */
        FulltimeEmployee asep = new FulltimeEmployee("Asep", Jabatan.PROGRAMMER, LocalDate.of(2021, 1, 1), 2);
        // memiliki pinjaman koperasi sebesar 500000/bulan
        asep.setPinjamanKoperasi(500000);
        // pada tanggal 15 maret (sabtu), asep lembur dari 09:00 - 12:00
        asep.overtimeRecords.add(new OvertimeRecord(LocalDateTime.of(2026, 3, 15, 9, 0), LocalDateTime.of(2026, 3, 15, 12, 0)));
        // pada tanggal 16 maret (minggu), asep lembur dari 20:00 - 23:00
        asep.overtimeRecords.add(new OvertimeRecord(LocalDateTime.of(2026, 3, 16, 20, 0), LocalDateTime.of(2026, 3, 16, 23, 0)));

        /*
        2. ujang: staf_programer, parttime_employee, bekerja sejak 1 januari 2025, belum menikah
        */
        ParttimeEmployee ujang = new ParttimeEmployee("Ujang", Jabatan.PROGRAMMER, LocalDate.of(2025, 1, 1), 0);
        // tanggal 30 maret 2026 menyelesaikan proyek
        ujang.setProjectsDone(1);
        // pada tanggal 15 maret (sabtu), ujang lembur dari 13:00 - 18:00
        ujang.overtimeRecords.add(new OvertimeRecord(LocalDateTime.of(2026, 3, 15, 13, 0), LocalDateTime.of(2026, 3, 15, 18, 0)));
        // pada tanggal 16 maret (minggu), ujang lembur dari 10:00 - 14:00
        ujang.overtimeRecords.add(new OvertimeRecord(LocalDateTime.of(2026, 3, 16, 10, 0), LocalDateTime.of(2026, 3, 16, 14, 0)));

        LocalDate march = LocalDate.of(2026, 3, 1);
        List.of(asep, ujang).forEach(e -> e.printSalarySlip(march));
    }
}