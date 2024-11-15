package DAL;

import DTO.PhieuTraDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuTraDAL extends connectionDB {

    // Thêm phiếu trả mới
    public boolean addPhieuTra(PhieuTraDTO phieuTra) {
        String sql = "INSERT INTO PhieuTra (id, maPhieuMuon, maThe, ngayMuon, ngayTra, phiDenBu, phiTreHan) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả phiếu trả
    public List<PhieuTraDTO> getAllPhieuTra() {
        List<PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuTra";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
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

    // Lấy phiếu trả theo ID
    public PhieuTraDTO getPhieuTraById(String id) {
        String sql = "SELECT * FROM PhieuTra WHERE id = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new PhieuTraDTO(
                            rs.getString("id"),
                            rs.getString("maPhieuMuon"),
                            rs.getString("maThe"),
                            rs.getDate("ngayMuon"),
                            rs.getDate("ngayTra"),
                            rs.getDouble("phiDenBu"),
                            rs.getDouble("phiTreHan")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching PhieuTra by ID: " + e.getMessage());
        }
        return null;
    }

    // Tìm kiếm phiếu trả theo mã phiếu mượn
    public List<PhieuTraDTO> searchPhieuTraByMaPhieuMuon(String maPhieuMuon) {
        List<PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuTra WHERE maPhieuMuon LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + maPhieuMuon + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error searching PhieuTra by maPhieuMuon: " + e.getMessage());
        }
        return list;
    }

    // Tìm kiếm phiếu trả theo mã thẻ
    public List<PhieuTraDTO> searchPhieuTraByMaThe(String maThe) {
        List<PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuTra WHERE maThe LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + maThe + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error searching PhieuTra by maThe: " + e.getMessage());
        }
        return list;
    }

    // Tìm kiếm phiếu trả theo ngày trả
    public List<PhieuTraDTO> searchPhieuTraByNgayTra(Date ngayTra) {
        List<PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuTra WHERE ngayTra = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, ngayTra);
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error searching PhieuTra by ngayTra: " + e.getMessage());
        }
        return list;
    }
}
