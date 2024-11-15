package DAL;

import DTO.CT_PhieuMuonDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CT_PhieuMuonDAL extends connectionDB {

    // Thêm chi tiết phiếu mượn
    public boolean addCT_PhieuMuon(CT_PhieuMuonDTO ctPhieuMuon) {
        String sql = "INSERT INTO CT_PhieuMuon (maPhieuMuon, maSach, tenSach, soLuong, trangThai) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ctPhieuMuon.getMaPhieuMuon());
            pstmt.setString(2, ctPhieuMuon.getMaSach());
            pstmt.setString(3, ctPhieuMuon.getTenSach());
            pstmt.setInt(4, ctPhieuMuon.getSoLuong());
            pstmt.setString(5, ctPhieuMuon.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding CT_PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật chi tiết phiếu mượn
    public boolean updateCT_PhieuMuon(CT_PhieuMuonDTO ctPhieuMuon) {
        String sql = "UPDATE CT_PhieuMuon SET tenSach = ?, soLuong = ?, trangThai = ? WHERE maPhieuMuon = ? AND maSach = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ctPhieuMuon.getTenSach());
            pstmt.setInt(2, ctPhieuMuon.getSoLuong());
            pstmt.setString(3, ctPhieuMuon.getTrangThai());
            pstmt.setString(4, ctPhieuMuon.getMaPhieuMuon());
            pstmt.setString(5, ctPhieuMuon.getMaSach());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating CT_PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Xóa chi tiết phiếu mượn
    public boolean deleteCT_PhieuMuon(String maPhieuMuon, String maSach) {
        String sql = "DELETE FROM CT_PhieuMuon WHERE maPhieuMuon = ? AND maSach = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhieuMuon);
            pstmt.setString(2, maSach);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting CT_PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả chi tiết phiếu mượn
    public List<CT_PhieuMuonDTO> getAllCT_PhieuMuon() {
        List<CT_PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM CT_PhieuMuon";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CT_PhieuMuonDTO ctPhieuMuon = new CT_PhieuMuonDTO(
                        rs.getString("maPhieuMuon"),
                        rs.getString("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("soLuong"),
                        rs.getString("trangThai")
                );
                list.add(ctPhieuMuon);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all CT_PhieuMuon: " + e.getMessage());
        }
        return list;
    }

    // Lấy chi tiết phiếu mượn theo mã phiếu mượn và mã sách
    public CT_PhieuMuonDTO getCT_PhieuMuonById(String maPhieuMuon, String maSach) {
        String sql = "SELECT * FROM CT_PhieuMuon WHERE maPhieuMuon = ? AND maSach = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhieuMuon);
            pstmt.setString(2, maSach);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CT_PhieuMuonDTO(
                            rs.getString("maPhieuMuon"),
                            rs.getString("maSach"),
                            rs.getString("tenSach"),
                            rs.getInt("soLuong"),
                            rs.getString("trangThai")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching CT_PhieuMuon by ID: " + e.getMessage());
        }
        return null;
    }

    // Tìm kiếm chi tiết phiếu mượn theo mã phiếu mượn
    public List<CT_PhieuMuonDTO> searchCT_PhieuMuonByMaPhieuMuon(String maPhieuMuon) {
        List<CT_PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM CT_PhieuMuon WHERE maPhieuMuon LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + maPhieuMuon + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CT_PhieuMuonDTO ctPhieuMuon = new CT_PhieuMuonDTO(
                            rs.getString("maPhieuMuon"),
                            rs.getString("maSach"),
                            rs.getString("tenSach"),
                            rs.getInt("soLuong"),
                            rs.getString("trangThai")
                    );
                    list.add(ctPhieuMuon);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching CT_PhieuMuon by maPhieuMuon: " + e.getMessage());
        }
        return list;
    }
}
