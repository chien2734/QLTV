package DAL;

import DTO.CT_PhieuTraDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CT_PhieuTraDAL extends connectionDB {

    // Thêm chi tiết phiếu trả
    public boolean addCT_PhieuTra(CT_PhieuTraDTO ctPhieuTra) {
        String sql = "INSERT INTO CT_PhieuTra (maPhieuTra, maSach, tenSach, soLuong, trangThai) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ctPhieuTra.getMaPhieuTra());
            pstmt.setString(2, ctPhieuTra.getMaSach());
            pstmt.setString(3, ctPhieuTra.getTenSach());
            pstmt.setInt(4, ctPhieuTra.getSoLuong());
            pstmt.setString(5, ctPhieuTra.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding CT_PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật chi tiết phiếu trả
    public boolean updateCT_PhieuTra(CT_PhieuTraDTO ctPhieuTra) {
        String sql = "UPDATE CT_PhieuTra SET tenSach = ?, soLuong = ?, trangThai = ? WHERE maPhieuTra = ? AND maSach = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ctPhieuTra.getTenSach());
            pstmt.setInt(2, ctPhieuTra.getSoLuong());
            pstmt.setString(3, ctPhieuTra.getTrangThai());
            pstmt.setString(4, ctPhieuTra.getMaPhieuTra());
            pstmt.setString(5, ctPhieuTra.getMaSach());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating CT_PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Xóa chi tiết phiếu trả
    public boolean deleteCT_PhieuTra(String maPhieuTra, String maSach) {
        String sql = "DELETE FROM CT_PhieuTra WHERE maPhieuTra = ? AND maSach = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhieuTra);
            pstmt.setString(2, maSach);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting CT_PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả chi tiết phiếu trả
    public List<CT_PhieuTraDTO> getAllCT_PhieuTra() {
        List<CT_PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM CT_PhieuTra";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CT_PhieuTraDTO ctPhieuTra = new CT_PhieuTraDTO(
                        rs.getString("maPhieuTra"),
                        rs.getString("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("soLuong"),
                        rs.getString("trangThai")
                );
                list.add(ctPhieuTra);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all CT_PhieuTra: " + e.getMessage());
        }
        return list;
    }

    // Lấy chi tiết phiếu trả theo mã phiếu trả và mã sách
    public CT_PhieuTraDTO getCT_PhieuTraById(String maPhieuTra, String maSach) {
        String sql = "SELECT * FROM CT_PhieuTra WHERE maPhieuTra = ? AND maSach = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhieuTra);
            pstmt.setString(2, maSach);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CT_PhieuTraDTO(
                            rs.getString("maPhieuTra"),
                            rs.getString("maSach"),
                            rs.getString("tenSach"),
                            rs.getInt("soLuong"),
                            rs.getString("trangThai")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching CT_PhieuTra by ID: " + e.getMessage());
        }
        return null;
    }

    // Tìm kiếm chi tiết phiếu trả theo mã phiếu trả
    public List<CT_PhieuTraDTO> searchCT_PhieuTraByMaPhieuTra(String maPhieuTra) {
        List<CT_PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM CT_PhieuTra WHERE maPhieuTra LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + maPhieuTra + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CT_PhieuTraDTO ctPhieuTra = new CT_PhieuTraDTO(
                            rs.getString("maPhieuTra"),
                            rs.getString("maSach"),
                            rs.getString("tenSach"),
                            rs.getInt("soLuong"),
                            rs.getString("trangThai")
                    );
                    list.add(ctPhieuTra);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching CT_PhieuTra by maPhieuTra: " + e.getMessage());
        }
        return list;
    }
}
