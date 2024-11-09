/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ReaderDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HUY LONG
 */
public class ReaderDAO {

    private Connection con = null;

    public static ReaderDAO getInstance() {
        return new ReaderDAO();
    }

    public boolean openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://LAPTOP-40C6I9LE\\THUONG:1433;databaseName=QLTV2;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String password = "12345";
          
            con = DriverManager.getConnection(url, user, password);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<ReaderDTO> getAllReaders() {
        ArrayList<ReaderDTO> list = new ArrayList<>();
        if (openConnection()) {
            try {
                String sql = "SELECT * FROM DocGia";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String tenDN = rs.getString(1);
                    String tenDG = rs.getString(2);
                    String matKhau = rs.getString(3);
                    String sdt = rs.getString(4);
                    String email = rs.getString(5);
                    String diaChi = rs.getString(6);
                    int trangThai = rs.getInt(7);
                    ReaderDTO reader = new ReaderDTO(tenDN, tenDG, matKhau,
                            sdt, email, diaChi, trangThai);
                    list.add(reader);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return list;
    }

    public boolean hasTenDN(String tenDN) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "SELECT * FROM DocGia WHERE TenDN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, tenDN);
                ResultSet rs = stmt.executeQuery();
                result = rs.next();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean addReader(ReaderDTO reader) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "INSERT INTO DocGia values (?,?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, reader.getTenDN());
                stmt.setString(2, reader.getTenDG());
                stmt.setString(3, reader.getMatKhau());
                stmt.setString(4, reader.getSDT());
                stmt.setString(5, reader.getEmail());
                stmt.setString(6, reader.getDiaChi());
                stmt.setInt(7, reader.getTrangThai());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean removeReader(ReaderDTO reader) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "DELETE FROM TheMuon WHERE MaDG = ?;"
                        + "DELETE FROM DocGia WHERE TenDN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, reader.getTenDN());
                stmt.setString(2,reader.getTenDN());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean updateReader(ReaderDTO reader) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "UPDATE DocGia SET TenDG = ?,MatKhau = ?,"
                        + " SDT = ?, Email = ?, DiaChi = ? "
                        + " WHERE TenDN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, reader.getTenDG());
                stmt.setString(2, reader.getMatKhau());
                stmt.setString(3, reader.getSDT());
                stmt.setString(4, reader.getEmail());
                stmt.setString(5, reader.getDiaChi());
                stmt.setString(6, reader.getTenDN());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean lockReader(ReaderDTO reader) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "UPDATE DocGia SET TrangThai = ? WHERE TenDN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, reader.getTrangThai());
                stmt.setString(2, reader.getTenDN());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }

        return result;
    }

    public boolean unlockReader(ReaderDTO reader) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "UPDATE DocGia SET TrangThai = ? WHERE TenDN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, reader.getTrangThai());
                stmt.setString(2, reader.getTenDN());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }

        return result;
    }

    public ReaderDTO selectById(String tendn) {
        ReaderDTO readerFind = new ReaderDTO();
        if (openConnection()) {
            try {
                String sql = "SELECT * FROM DocGia WHERE TENDN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, tendn);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String tenDN = (String) rs.getString(1);
                    String tenDG = (String) rs.getString(2);
                    String matKhau = (String) rs.getString(3);
                    String SDT = (String) rs.getString(4);
                    String email = (String) rs.getString(5);
                    String diaChi = (String) rs.getString(6);
                    int trangThai =(int) rs.getInt(7);
                    readerFind = new ReaderDTO(tenDN, tenDG, matKhau,  SDT, email, diaChi, trangThai);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }

        return readerFind;
    }

    public ArrayList<ReaderDTO> getReadersByCondition(String condition) {
        ArrayList<ReaderDTO> list = new ArrayList<>();
        if (openConnection()) {
            try {
                String sql = "SELECT * FROM DocGia WHERE " + condition;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String tenDN =(String) rs.getString(1);
                    String tenDG =(String) rs.getString(2);
                    String matKhau =(String) rs.getString(3);
                    String SDT =(String) rs.getString(4);
                    String email =(String) rs.getString(5);
                    String diaChi =(String) rs.getString(6);
                    int trangThai =(int) rs.getInt(7);
                    ReaderDTO reader = new ReaderDTO(tenDN, tenDG, matKhau, SDT,
                            email, diaChi, trangThai);
                    list.add(reader);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return list;
    }
}
