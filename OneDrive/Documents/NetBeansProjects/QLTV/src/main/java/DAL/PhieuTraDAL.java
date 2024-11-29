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
        String sql = "INSERT INTO PhieuTra (id, maPhieuMuon, maThe, ngayMuon, ngayTra, phiDenBu, phiTreHan) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuTra.getId());
            pstmt.setString(2, phieuTra.getMaPhieuMuon());
            pstmt.setString(3, phieuTra.getMaThe());
            pstmt.setDate(4, new java.sql.Date(phieuTra.getNgayMuon().getTime()));
            pstmt.setDate(5, new java.sql.Date(phieuTra.getNgayTra().getTime()));
            pstmt.setDouble(6, phieuTra.getPhiDenBu());
            pstmt.setDouble(7, phieuTra.getPhiTreHan());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật phiếu trả
    public boolean updatePhieuTra(PhieuTraDTO phieuTra) {
        String sql = "UPDATE PhieuTra SET maPhieuMuon = ?, maThe = ?, ngayMuon = ?, ngayTra = ?, phiDenBu = ?, phiTreHan = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuTra.getMaPhieuMuon());
            pstmt.setString(2, phieuTra.getMaThe());
            pstmt.setDate(3, new java.sql.Date(phieuTra.getNgayMuon().getTime()));
            pstmt.setDate(4, new java.sql.Date(phieuTra.getNgayTra().getTime()));
            pstmt.setDouble(5, phieuTra.getPhiDenBu());
            pstmt.setDouble(6, phieuTra.getPhiTreHan());
            pstmt.setString(7, phieuTra.getId());
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
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PhieuTraDTO phieuTra = new PhieuTraDTO(
                        rs.getString("id"),
                        rs.getString("maPhieuMuon"),
                        rs.getString("maThe"),
                        rs.getDate("ngayMuon"),
                        rs.getDate("ngayTra"),
                        rs.getDouble("phiDenBu"),
                        rs.getDouble("phiTreHan")
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
}
