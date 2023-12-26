// Kelas Penjualan merupakan turunan dari kelas Transaksi dan mengimplementasikan interface Pembayaran
public class Penjualan extends Transaksi implements Pembayaran {

    // Konstruktor untuk membuat objek Penjualan dengan parameter yang diberikan
    public Penjualan(String namaPelanggan, String noHp, String alamat, String kodeBarang, String namaBarang,
            double hargaBarang, int jumlahBeli) {
        // Memanggil konstruktor kelas induk (Transaksi) dengan menggunakan keyword
        // super
        super(namaPelanggan, noHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
    }

    // Implementasi metode hitungTotalBayar() dari interface Pembayaran
    @Override
    public double hitungTotalBayar() {
        return hargaBarang * getJumlahBeli();
    }

    // Override metode setJumlahBeli() dari kelas dasar (Transaksi)
    @Override
    public void setJumlahBeli(int jumlahBeli) {
        super.setJumlahBeli(jumlahBeli);
    }

    // Mendapatkan nilai jumlahBeli dari kelas dasar (Transaksi)
    public int getJumlahBeli() {
        return super.jumlahBeli;
    }

    // Override metode tampilkanDetailTransaksi() dari kelas dasar (Transaksi)
    @Override
    public void tampilkanDetailTransaksi() {
        // Memanggil metode tampilkanDetailTransaksi() dari kelas dasar (Transaksi)
        super.tampilkanDetailTransaksi();

        // Menampilkan informasi tambahan untuk transaksi penjualan
        System.out.println("Total Bayar\t: " + hitungTotalBayar());
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("Kasir\t\t: " + kasir);
    }

    // Metode-metode untuk mendapatkan nilai atribut dari kelas dasar (Transaksi)
    public String getNamaPelanggan() {
        return super.getNamaPelanggan();
    }

    public String getNoHp() {
        return super.getNoHp();
    }

    public String getAlamat() {
        return super.getAlamat();
    }

    public String getKodeBarang() {
        return super.getKodeBarang();
    }

    public String getNamaBarang() {
        return super.getNamaBarang();
    }

    public double getHargaBarang() {
        return super.getHargaBarang();
    }

    // Metode-metode untuk mengakses nilai atribut transactionID dari kelas dasar
    // (Transaksi)
    public void setTransactionID(int transactionID) {
        super.setTransactionID(transactionID);
    }

    public int getTransactionID() {
        return transactionID;
    }
}
