import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Kelas utama program
public class App {
    private static String inputUsername;
    private static String username = "admin";
    private static String inputPw;
    private static String password = "admin1";
    private static String captcha = "12345";
    private static String inputCapt;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/asvrn_market";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "12345678";

    // Metode utama program
    public static void main(String[] args) throws Exception {
        Transaksi tampil = new Transaksi();
        tampil.greeting();
        try {
            try (Scanner scan = new Scanner(System.in)) {

                // Loop untuk autentikasi pengguna
                while (inputUsername != username || inputPw != password) {
                    System.out.println("");
                    System.out.print("Username\t: ");
                    inputUsername = scan.nextLine();

                    System.out.print("Password\t: ");
                    inputPw = scan.nextLine();

                    // Memeriksa username dan password
                    if (inputUsername.equals(username) && inputPw.equals(password)) {
                        System.out.println("\nKode Captcha " + captcha);
                        // Loop untuk memasukkan kode captcha
                        while (inputCapt != captcha) {
                            System.out.print("Entry Captcha\t:");
                            inputCapt = scan.nextLine();

                            // Memeriksa kevalidan kode captcha
                            if (inputCapt.equals(captcha)) {
                                boolean exitProgram = false;
                                // Loop utama program
                                while (!exitProgram) {

                                    System.out.println("\nPilih Menu:");
                                    System.out.println("1. Create Data");
                                    System.out.println("2. Read Data");
                                    System.out.println("3. Update Data");
                                    System.out.println("4. Delete Data");
                                    System.out.println("5. Menutup program\n");

                                    // Memilih opsi menu
                                    int choice = scan.nextInt();
                                    scan.nextLine();

                                    switch (choice) {
                                        case 1:
                                            // Create Data
                                            System.out.print("Masukkan Nama Pelanggan\t\t: ");
                                            String namaPelanggan = scan.nextLine();

                                            System.out.print("Masukkan No HP\t\t\t: ");
                                            String noHp = scan.nextLine();

                                            System.out.print("Masukkan Alamat\t\t\t: ");
                                            String alamat = scan.nextLine();

                                            System.out.print("Masukkan Kode Barang\t\t: ");
                                            String kodeBarang = scan.nextLine();

                                            System.out.print("Masukkan Nama Barang\t\t: ");
                                            String namaBarang = scan.nextLine();

                                            // Menggunakan inputValid untuk memastikan hargaBarang dan jumlahBeli valid
                                            double hargaBarang = 0;
                                            boolean inputValid = false;
                                            // Loop untuk memasukkan hargaBarang yang valid
                                            while (!inputValid) {
                                                try {
                                                    System.out.print("Masukkan Harga Barang\t\t: ");
                                                    hargaBarang = scan.nextDouble();
                                                    scan.nextLine();
                                                    // Membuat objek Transaksi untuk memastikan hargaBarang valid
                                                    new Transaksi("", "", "", namaBarang, namaBarang, hargaBarang, 0); // untuk
                                                                                                                       // memastikan
                                                                                                                       // hargaBarang
                                                                                                                       // valid
                                                    inputValid = true;
                                                } catch (InputMismatchException | IllegalArgumentException e) {
                                                    System.out.println(
                                                            "Masukan tidak valid. Harga barang harus berupa angka.");
                                                    scan.nextLine();
                                                }
                                            }

                                            int jumlahBeli;

                                            // Loop untuk memasukkan jumlahBeli yang valid
                                            do {
                                                try {
                                                    System.out.print("Masukkan Jumlah Beli (max 1000)\t: ");
                                                    jumlahBeli = scan.nextInt();
                                                    // Membuat objek Transaksi untuk memastikan jumlahBeli valid
                                                    new Transaksi("", "", "", namaBarang, namaBarang, 0, jumlahBeli); // untuk
                                                                                                                      // memastikan
                                                                                                                      // jumlahBeli
                                                                                                                      // valid
                                                } catch (IllegalArgumentException e) {
                                                    System.out.println(e.getMessage());
                                                    jumlahBeli = -1; // Mengatur jumlahBeli menjadi -1 agar loop terus
                                                                     // berlanjut
                                                }
                                            } while (jumlahBeli == -1);

                                            // Membuat objek Penjualan
                                            Penjualan penjualan = new Penjualan(namaPelanggan, noHp, alamat, kodeBarang,
                                                    namaBarang, hargaBarang, jumlahBeli);

                                            // Menyimpan data ke database
                                            saveToDatabase(penjualan);

                                            // Update data di database
                                            updateInDatabase(penjualan);

                                            // Menampilkan detail transaksi
                                            penjualan.tampilkanDetailTransaksi();
                                            break;

                                        case 2:
                                            // Retrieve Data
                                            retrieveFromDatabase();
                                            break;

                                        case 3:
                                            // Update Data
                                            System.out.print("Masukkan ID transaksi yang ingin diupdate: ");
                                            int updateTransactionId = scan.nextInt();
                                            scan.nextLine();

                                            // Memeriksa kevalidan ID transaksi
                                            if (isTransactionIdValid(updateTransactionId)) {

                                                System.out.print("Masukkan Nama Pelanggan\t: ");
                                                String updateNamaPelanggan = scan.nextLine();

                                                System.out.print("Masukkan No HP\t\t: ");
                                                String updateNoHp = scan.nextLine();

                                                System.out.print("Masukkan Alamat\t\t: ");
                                                String updateAlamat = scan.nextLine();

                                                System.out.print("Masukkan Kode Barang\t: ");
                                                String updateKodeBarang = scan.nextLine();

                                                System.out.print("Masukkan Nama Barang\t: ");
                                                String updateNamaBarang = scan.nextLine();

                                                System.out.print("Masukkan Harga Barang\t: ");
                                                double updateHargaBarang = scan.nextDouble();

                                                System.out.print("Masukkan Jumlah Beli\t: ");
                                                int updateJumlahBeli = scan.nextInt();

                                                // Membuat objek Penjualan dan update data di database
                                                Penjualan updatePenjualan = new Penjualan(updateNamaPelanggan,
                                                        updateNoHp, updateAlamat,
                                                        updateKodeBarang, updateNamaBarang, updateHargaBarang,
                                                        updateJumlahBeli);
                                                updatePenjualan.setTransactionID(updateTransactionId);
                                                updateInDatabase(updatePenjualan);

                                                System.out.println("Data berhasil diupdate di database.");
                                            } else {
                                                System.out.println("Transaction ID tidak valid atau tidak ditemukan.");
                                            }
                                            break;

                                        case 4:
                                            // Delete Data
                                            System.out.print("Masukkan ID transaksi yang ingin dihapus: ");
                                            int deleteTransactionId = scan.nextInt();
                                            scan.nextLine();
                                            // Memeriksa kevalidan ID transaksi
                                            if (isTransactionIdValid(deleteTransactionId)) {
                                                deleteFromDatabase(deleteTransactionId);
                                                System.out.println("Data berhasil dihapus dari database.");
                                            } else {
                                                System.out.println("Transaction ID tidak valid atau tidak ditemukan.");
                                            }
                                            break;

                                        case 5:
                                            // Menutup program
                                            System.out.println("\nHave a nice day!\n");
                                            exitProgram = true;
                                            break;

                                        default:
                                            System.out.println("Pilihan tidak valid.");
                                            break;
                                    }
                                }

                                break;
                            } else {
                                System.out.println("");
                                System.out.println("Kode captcha salah");
                            }
                        }
                        break;
                    } else {
                        System.out.println("Username atau Password Anda salah");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());

        }
    }

    // Metode untuk menyimpan transaksi ke database
    private static void saveToDatabase(Penjualan penjualan) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO transaksi (nama_pelanggan, no_hp, alamat, kode_barang, nama_barang, harga_barang, jumlah_beli) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, penjualan.getNamaPelanggan());
                preparedStatement.setString(2, penjualan.getNoHp());
                preparedStatement.setString(3, penjualan.getAlamat());
                preparedStatement.setString(4, penjualan.getKodeBarang());
                preparedStatement.setString(5, penjualan.getNamaBarang());
                preparedStatement.setDouble(6, penjualan.getHargaBarang());
                preparedStatement.setInt(7, penjualan.getJumlahBeli());

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error while saving to database: " + e.getMessage());
        }
    }

    // Metode untuk mengambil transaksi dari database
    private static void retrieveFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM transaksi";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Mengambil data dan menampilkan
                        System.out.println("\nTransaction ID\t: " + resultSet.getInt("id"));
                        System.out.println("Nama Pelanggan\t: " + resultSet.getString("nama_pelanggan"));
                        System.out.println("No HP\t\t: " + resultSet.getString("no_hp"));
                        System.out.println("Alamat\t\t: " + resultSet.getString("alamat"));
                        System.out.println("Kode Barang\t: " + resultSet.getString("kode_barang"));
                        System.out.println("Nama Barang\t: " + resultSet.getString("nama_barang"));
                        System.out.println("Harga Barang\t: " + resultSet.getDouble("harga_barang"));
                        System.out.println("Jumlah Beli\t: " + resultSet.getInt("jumlah_beli"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error while retrieving from database: " + e.getMessage());
        }
    }

    // Metode untuk mengupdate transaksi di database
    private static void updateInDatabase(Penjualan penjualan) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE transaksi SET nama_pelanggan=?, no_hp=?, alamat=?, kode_barang=?, nama_barang=?, harga_barang=?, jumlah_beli=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(8, penjualan.getTransactionID());
                preparedStatement.setString(1, penjualan.getNamaPelanggan());
                preparedStatement.setString(2, penjualan.getNoHp());
                preparedStatement.setString(3, penjualan.getAlamat());
                preparedStatement.setString(4, penjualan.getKodeBarang());
                preparedStatement.setString(5, penjualan.getNamaBarang());
                preparedStatement.setDouble(6, penjualan.getHargaBarang());
                preparedStatement.setInt(7, penjualan.getJumlahBeli());

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error while updating in the database: " + e.getMessage());
        }
    }

    // Metode untuk memeriksa kevalidan ID transaksi
    private static boolean isTransactionIdValid(int transactionID) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM transaksi WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, transactionID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If next() returns true, the transaction ID exists in the database
                }
            }
        } catch (Exception e) {
            System.out.println("Error while checking transaction ID: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk menghapus transaksi dari database
    private static void deleteFromDatabase(int transactionID) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM transaksi WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, transactionID);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error while deleting from the database: " + e.getMessage());
        }
    }

}
