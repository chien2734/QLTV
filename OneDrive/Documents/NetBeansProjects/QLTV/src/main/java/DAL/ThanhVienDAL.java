package DAL;

import DTO.ThanhVienDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAL extends connectionDB {

    // Thêm thành viên mới
    public boolean addThanhVien(ThanhVienDTO thanhVien) {
        String sql = "INSERT INTO ThanhVien (id, ten, CCCD, sdt, ngayDK, hanSD, phiDuyTri, trangThai) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, thanhVien.getId());
            pstmt.setString(2, thanhVien.getTen());
            pstmt.setString(3, thanhVien.getCCCD());
            pstmt.setString(4, thanhVien.getSdt());
            pstmt.setDate(5, new java.sql.Date(thanhVien.getNgayDK().getTime()));
            pstmt.setDate(6, new java.sql.Date(thanhVien.getHanSD().getTime()));
            pstmt.setDouble(7, thanhVien.getPhiDuyTri());
            pstmt.setString(8, thanhVien.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding ThanhVien: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật thông tin thành viên
    public boolean updateThanhVien(ThanhVienDTO thanhVien) {
        String sql = "UPDATE ThanhVien SET ten = ?, CCCD = ?, sdt = ?, ngayDK = ?, hanSD = ?, phiDuyTri = ?, trangThai = ? WHERE id = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, thanhVien.getTen());
            pstmt.setString(2, thanhVien.getCCCD());
            pstmt.setString(3, thanhVien.getSdt());
            pstmt.setDate(4, new java.sql.Date(thanhVien.getNgayDK().getTime()));
            pstmt.setDate(5, new java.sql.Date(thanhVien.getHanSD().getTime()));
            pstmt.setDouble(6, thanhVien.getPhiDuyTri());
            pstmt.setString(7, thanhVien.getTrangThai());
            pstmt.setString(8, thanhVien.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating ThanhVien: " + e.getMessage());
            return false;
        }
    }

    // Xóa thành viên
    public boolean deleteThanhVien(String id) {
        String sql = "DELETE FROM ThanhVien WHERE id = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting ThanhVien: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách tất cả thành viên
    public List<ThanhVienDTO> getAllThanhVien() {
        List<ThanhVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ThanhVien";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ThanhVienDTO thanhVien = new ThanhVienDTO(
                        rs.getString("id"),
                        rs.getString("ten"),
                        rs.getString("CCCD"),
                        rs.getString("sdt"),
                        rs.getDate("ngayDK"),
                        rs.getDate("hanSD"),
                        rs.getDouble("phiDuyTri"),
                        rs.getString("trangThai")
                );
                list.add(thanhVien);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all ThanhVien: " + e.getMessage());
        }
        return list;
    }

    // Lấy thông tin thành viên theo ID
    public ThanhVienDTO getThanhVienById(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE id = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ThanhVienDTO(
                            rs.getString("id"),
                            rs.getString("ten"),
                            rs.getString("CCCD"),
                            rs.getString("sdt"),
                            rs.getDate("ngayDK"),
                            rs.getDate("hanSD"),
                            rs.getDouble("phiDuyTri"),
                            rs.getString("trangThai")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching ThanhVien by ID: " + e.getMessage());
        }
        return null;
    }
    
    // Tìm kiếm thành viên theo tên
    public List<ThanhVienDTO> searchThanhVienByName(String name) {
        List<ThanhVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ThanhVien WHERE ten LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ThanhVienDTO thanhVienDTO = new ThanhVienDTO(
                        rs.getString("id"), 
                        rs.getString("ten"), 
                        rs.getString("CCCD"), 
                        rs.getString("sdt"), 
                        rs.getDate("ngayDK"),
                        rs.getDate("hanSD"),
                        rs.getDouble("phiDuyTri"),
                        rs.getString("trangThai")
                    );
                    list.add(thanhVienDTO);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching ThanhVien by name: " + e.getMessage());
        }
        return list;
    }
}
