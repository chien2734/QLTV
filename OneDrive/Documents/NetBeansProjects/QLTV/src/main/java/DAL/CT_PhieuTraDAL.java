package DAL;

import DTO.CT_PhieuTraDTO;
import java.sql.*;
import java.util.ArrayList;

public class CT_PhieuTraDAL  {

    private final Connection conn;
    public CT_PhieuTraDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm chi tiết phiếu trả
    public boolean addCT_PhieuTra(CT_PhieuTraDTO ctPhieuTra) {
        String sql = "INSERT INTO CT_PhieuTra (maPhieuTra, maSach, tenSach, soLuong, trangThai) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ctPhieuTra.getMaPhieuTra());
            pstmt.setString(2, ctPhieuTra.getMaSach());
            pstmt.setString(3, ctPhieuTra.getTenSach());
            pstmt.setInt(4, ctPhieuTra.getSoLuong());
            pstmt.setNString(5, ctPhieuTra.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding CT_PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật chi tiết phiếu trả
    public boolean updateCT_PhieuTra(CT_PhieuTraDTO ctPhieuTra) {
        String sql = "UPDATE CT_PhieuTra SET tenSach = ?, soLuong = ?, trangThai = ? WHERE maPhieuTra = ? AND maSach = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ctPhieuTra.getTenSach());
            pstmt.setInt(2, ctPhieuTra.getSoLuong());
            pstmt.setNString(3, ctPhieuTra.getTrangThai());
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
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhieuTra);
            pstmt.setString(2, maSach);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting CT_PhieuTra: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả chi tiết phiếu trả
    public ArrayList<CT_PhieuTraDTO> getAllCT_PhieuTra() {
        ArrayList<CT_PhieuTraDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM CT_PhieuTra";
        try (
                PreparedStatement pstmt = conn.prepareStatement(sql); 
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CT_PhieuTraDTO ctPhieuTra = new CT_PhieuTraDTO(
                        rs.getString("maPhieuTra"),
                        rs.getString("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("soLuong"),
                        rs.getNString("trangThai")
                );
                list.add(ctPhieuTra);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all CT_PhieuTra: " + e.getMessage());
        }
        return list;
    }
}
