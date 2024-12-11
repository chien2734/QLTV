package DAL;

import DTO.PhieuTraDTO;
import java.sql.*;
import java.util.ArrayList;

public class PhieuTraDAL {

    private final Connection conn;

    public PhieuTraDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm phiếu trả mới
    public boolean addPhieuTra(PhieuTraDTO phieuTra) {
        String sql = "INSERT INTO PhieuTra (id, maPhieuMuon, maThe, ngayMuon, ngayTra, phiDenBu, phiTreHan, trangThai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuTra.getId());
            pstmt.setString(2, phieuTra.getMaPhieuMuon());
            pstmt.setString(3, phieuTra.getMaThe());
            pstmt.setDate(4, new java.sql.Date(phieuTra.getNgayMuon().getTime()));
            pstmt.setDate(5, new java.sql.Date(phieuTra.getNgayTra().getTime()));
            pstmt.setDouble(6, phieuTra.getPhiDenBu());
            pstmt.setDouble(7, phieuTra.getPhiTreHan());
            pstmt.setNString(8, phieuTra.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật phiếu trả
    public boolean updatePhieuTra(PhieuTraDTO phieuTra) {
        String sql = "UPDATE PhieuTra SET maPhieuMuon = ?, maThe = ?, ngayMuon = ?, ngayTra = ?, phiDenBu = ?, phiTreHan = ?, trangThai = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuTra.getMaPhieuMuon());
            pstmt.setString(2, phieuTra.getMaThe());
            pstmt.setDate(3, new java.sql.Date(phieuTra.getNgayMuon().getTime()));
            pstmt.setDate(4, new java.sql.Date(phieuTra.getNgayTra().getTime()));
            pstmt.setDouble(5, phieuTra.getPhiDenBu());
            pstmt.setDouble(6, phieuTra.getPhiTreHan());
            pstmt.setNString(7, phieuTra.getTrangThai());
            pstmt.setString(8, phieuTra.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Xóa phiếu trả
    public boolean deletePhieuTra(String id) {
        String sql = "DELETE FROM PhieuTra WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả phiếu trả
    public ArrayList<PhieuTraDTO> getAllPhieuTra() {
        ArrayList<PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuTra";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); 
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PhieuTraDTO phieuTra = new PhieuTraDTO(
                        rs.getString("id"),
                        rs.getString("maPhieuMuon"),
                        rs.getString("maThe"),
                        rs.getDate("ngayMuon"),
                        rs.getDate("ngayTra"),
                        rs.getDouble("phiDenBu"),
                        rs.getDouble("phiTreHan"),
                        rs.getNString("trangThai")
                );
                list.add(phieuTra);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all PhieuTra: " + e.getMessage());
        }
        return list;
    }

    public boolean hasID(String id) {
        boolean checked = false;
        String sql = "select * from PhieuTra where id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return checked;
    }

    public int TinhSoNgay(java.sql.Date ngayTra, java.sql.Date hanTra) {
        int soNgay = 0;
        String sql = "select DATEDIFF(DAY, ?, ?) as SoNgay";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, hanTra);
            pstmt.setDate(2, ngayTra);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                soNgay = rs.getInt("SoNgay");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return soNgay;
    }
    
    public ArrayList<Object[]> CacLoaiPhi(int month, int year){
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "use QLTVOOAD "
                + "SELECT "
                + "(select SUM(tienCoc) from PhieuMuon where MONTH(ngayMuon) = ? and YEAR(ngayMuon) = ? ) as TienCoc, "
                + "(select SUM(phiDenBu) from PhieuTra where MONTH(ngayTra) = ? and YEAR(ngayTra) = ? ) as PhiDenBu, "
                + "(select SUM(phiTreHan) from PhieuTra where MONTH(ngayTra) = ? and YEAR(ngayTra) = ? ) as PhiTreHan ";               
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, month);
            pstmt.setInt(2, year);
            pstmt.setInt(3, month);
            pstmt.setInt(4, year);
            pstmt.setInt(5, month);
            pstmt.setInt(6, year);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Object[] row = {rs.getDouble(1), rs.getDouble(2), rs.getDouble(3)};
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
