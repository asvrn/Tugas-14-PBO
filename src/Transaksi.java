import java.text.SimpleDateFormat;
import java.util.Date;

// Kelas Transaksi untuk merepresentasikan informasi transaksi pembelian barang
public class Transaksi {
    private String namaPelanggan;
    private String noHp;
    private String namaBarang;
    protected double hargaBarang;
    protected int jumlahBeli;
    private String namaSuperMarket = "asvrn market";
    private String alamat;
    private String kodeBarang;
    protected String kasir = "Anies Baswedan";
    protected int transactionID;

    Date date = new Date();
    SimpleDateFormat tanggal = new SimpleDateFormat("E dd/MM/yyyy"); // membuat objek untuk menampilkan tanggal
    SimpleDateFormat jam = new SimpleDateFormat("hh:mm:ss zzzz"); // membuat objek untuk menampilkan jam
    public Object greeting;

    // Konstruktor untuk membuat objek Transaksi dengan informasi pembelian barang
    public Transaksi(String namaPelanggan, String noHp, String alamat, String kodeBarang, String namaBarang,
            double hargaBarang, int jumlahBeli) {
        this.namaPelanggan = namaPelanggan;
        this.noHp = noHp;
        this.alamat = alamat;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.setJumlahBeli(jumlahBeli);
    }

    // Konstruktor default
    public Transaksi() {
    }

    // Setter untuk mengatur jumlah beli dengan validasi
    public void setJumlahBeli(int jumlahBeli) {
        if (jumlahBeli > 1000) {
            throw new IllegalArgumentException("Jumlah beli tidak boleh lebih dari 1000");
        }
        this.jumlahBeli = jumlahBeli;
    }

    // Metode untuk menampilkan sambutan
    public void greeting() {
        System.out.println("SELAMAT DATANG DI " + namaSuperMarket.toUpperCase());
        System.out.println("=========================");
        System.out.println("Log in");
    }

    // Getter untuk mendapatkan nama pelanggan
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    // Getter untuk mendapatkan nomor HP
    public String getNoHp() {
        return noHp;
    }

    // Getter untuk mendapatkan alamat
    public String getAlamat() {
        return alamat;
    }

    // Getter untuk mendapatkan kode barang
    public String getKodeBarang() {
        return kodeBarang;
    }

    // Getter untuk mendapatkan nama barang
    public String getNamaBarang() {
        return namaBarang;
    }

    // Getter untuk mendapatkan harga barang
    public double getHargaBarang() {
        return hargaBarang;
    }

    // Setter untuk mengatur ID transaksi
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    // Getter untuk mendapatkan ID transaksi
    public int getTransactionID() {
        return transactionID;
    }

    // Metode untuk menampilkan detail transaksi
    public void tampilkanDetailTransaksi() {
        System.out.println("");
        System.out.println(namaSuperMarket.toUpperCase());
        System.out.println("Tanggal\t\t: " + tanggal.format(date));
        System.out.println("Waktu\t\t: " + jam.format(date));
        System.out.println("=========================");
        System.out.println("DATA PELANGGAN");
        System.out.println("---------------------------");
        System.out.println("Nama Pelanggan\t: " + namaPelanggan);
        System.out.println("No HP\t\t: " + noHp);
        System.out.println("Alamat\t\t: " + alamat);
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("DATA PEMBELIAN BARANG");
        System.out.println("---------------------------");
        System.out.println("Kode barang\t: " + kodeBarang);
        System.out.println("Nama Barang\t: " + namaBarang);
        System.out.println("Harga Barang\t: " + hargaBarang);
        System.out.println("Jumlah Beli\t: " + jumlahBeli);
    }
}
