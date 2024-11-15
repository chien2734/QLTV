package DAL;

import DTO.PhieuMuonDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAL extends connectionDB {

    // Thêm phiếu mượn mới
    public boolean addPhieuMuon(PhieuMuonDTO phieuMuon) {
        String sql = "INSERT INTO PhieuMuon (id, maThe, ngayMuon, hanTra, tienCoc, trangThai) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuMuon.getId());
            pstmt.setString(2, phieuMuon.getMaThe());
            pstmt.setDate(3, new java.sql.Date(phieuMuon.getNgayMuon().getTime()));
            pstmt.setDate(4, new java.sql.Date(phieuMuon.getHanTra().getTime()));
            pstmt.setDouble(5, phieuMuon.getTienCoc());
            pstmt.setString(6, phieuMuon.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật phiếu mượn
    public boolean updatePhieuMuon(PhieuMuonDTO phieuMuon) {
        String sql = "UPDATE PhieuMuon SET maThe = ?, ngayMuon = ?, hanTra = ?, tienCoc = ?, trangThai = ? WHERE id = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuMuon.getMaThe());
            pstmt.setDate(2, new java.sql.Date(phieuMuon.getNgayMuon().getTime()));
            pstmt.setDate(3, new java.sql.Date(phieuMuon.getHanTra().getTime()));
            pstmt.setDouble(4, phieuMuon.getTienCoc());
            pstmt.setString(5, phieuMuon.getTrangThai());
            pstmt.setString(6, phieuMuon.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Xóa phiếu mượn
    public boolean deletePhieuMuon(String id) {
        String sql = "DELETE FROM PhieuMuon WHERE id = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả phiếu mượn
    public List<PhieuMuonDTO> getAllPhieuMuon() {
        List<PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PhieuMuonDTO phieuMuon = new PhieuMuonDTO(
                        rs.getString("id"),
                        rs.getString("maThe"),
                        rs.getDate("ngayMuon"),
                        rs.getDate("hanTra"),
                        rs.getDouble("tienCoc"),
                        rs.getString("trangThai")
                );
                list.add(phieuMuon);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all PhieuMuon: " + e.getMessage());
        }
        return list;
    }

    // Tìm kiếm phiếu mượn theo mã phiếu mượn
    public List<PhieuMuonDTO> searchPhieuMuonByMaPhieuMuon(String maPhieuMuon) {
        List<PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon WHERE id LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + maPhieuMuon + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PhieuMuonDTO phieuMuon = new PhieuMuonDTO(
                            rs.getString("id"),
                            rs.getString("maThe"),
                            rs.getDate("ngayMuon"),
                            rs.getDate("hanTra"),
                            rs.getDouble("tienCoc"),
                            rs.getString("trangThai")
                    );
                    list.add(phieuMuon);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching PhieuMuon by maPhieuMuon: " + e.getMessage());
        }
        return list;
    }

    // Tìm kiếm phiếu mượn theo mã thẻ
    public List<PhieuMuonDTO> searchPhieuMuonByMaThe(String maThe) {
        List<PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon WHERE maThe LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + maThe + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PhieuMuonDTO phieuMuon = new PhieuMuonDTO(
                            rs.getString("id"),
                            rs.getString("maThe"),
                            rs.getDate("ngayMuon"),
                            rs.getDate("hanTra"),
                            rs.getDouble("tienCoc"),
                            rs.getString("trangThai")
                    );
                    list.add(phieuMuon);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching PhieuMuon by maThe: " + e.getMessage());
        }
        return list;
    }

    // Tìm kiếm phiếu mượn theo ngày mượn
    public List<PhieuMuonDTO> searchPhieuMuonByNgayMuon(Date ngayMuon) {
        List<PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon WHERE ngayMuon = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, ngayMuon);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PhieuMuonDTO phieuMuon = new PhieuMuonDTO(
                            rs.getString("id"),
                            rs.getString("maThe"),
                            rs.getDate("ngayMuon"),
                            rs.getDate("hanTra"),
                            rs.getDouble("tienCoc"),
                            rs.getString("trangThai")
                    );
                    list.add(phieuMuon);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching PhieuMuon by ngayMuon: " + e.getMessage());
        }
        return list;
    }

    // Tìm kiếm phiếu mượn theo ngày trả
    public List<PhieuMuonDTO> searchPhieuMuonByHanTra(Date hanTra) {
        List<PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon WHERE hanTra = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, hanTra);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PhieuMuonDTO phieuMuon = new PhieuMuonDTO(
                            rs.getString("id"),
                            rs.getString("maThe"),
                            rs.getDate("ngayMuon"),
                            rs.getDate("hanTra"),
                            rs.getDouble("tienCoc"),
                            rs.getString("trangThai")
                    );
                    list.add(phieuMuon);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching PhieuMuon by hanTra: " + e.getMessage());
        }
        return list;
    }
}
