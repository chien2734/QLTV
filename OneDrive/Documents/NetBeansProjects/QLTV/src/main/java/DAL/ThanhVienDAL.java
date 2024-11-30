package DAL;

import DTO.ThanhVienDTO;
import java.sql.*;
import java.util.ArrayList;

public class ThanhVienDAL extends connectionDB {

    private final Connection conn;

    public ThanhVienDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm thành viên mới
    public boolean addThanhVien(ThanhVienDTO thanhVien) {
        String sql = "INSERT INTO ThanhVien (id, ten, CCCD, sdt, diaChi, ngayDK, hanSD, phiDuyTri, trangThai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, thanhVien.getId());
            pstmt.setNString(2, thanhVien.getTen());
            pstmt.setString(3, thanhVien.getCCCD());
            pstmt.setString(4, thanhVien.getSdt());
            pstmt.setNString(5, thanhVien.getDiaChi());
            pstmt.setDate(6, new java.sql.Date(thanhVien.getNgayDK().getTime()));
            pstmt.setDate(7, new java.sql.Date(thanhVien.getHanSD().getTime()));
            pstmt.setDouble(8, thanhVien.getPhiDuyTri());
            pstmt.setNString(9, thanhVien.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding ThanhVien: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật thông tin thành viên
    public boolean updateThanhVien(ThanhVienDTO thanhVien) {
        String sql = "UPDATE ThanhVien SET ten = ?, CCCD = ?, sdt = ?, diaChi =?, ngayDK = ?, hanSD = ?, phiDuyTri = ?, trangThai = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setNString(1, thanhVien.getTen());
            pstmt.setString(2, thanhVien.getCCCD());
            pstmt.setString(3, thanhVien.getSdt());
            pstmt.setNString(4, thanhVien.getDiaChi());
            pstmt.setDate(5, thanhVien.getNgayDK());
            pstmt.setDate(6, thanhVien.getHanSD());
            pstmt.setDouble(7, thanhVien.getPhiDuyTri());
            pstmt.setNString(8, thanhVien.getTrangThai());
            pstmt.setString(9, thanhVien.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating ThanhVien: " + e.getMessage());
            return false;
        }
    }

    public boolean updateTrangThaiThe(ThanhVienDTO thanhVien) {
        String sql = "UPDATE ThanhVien SET trangThai = ?, hanSD = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setNString(1, thanhVien.getTrangThai());
            pstmt.setDate(2, thanhVien.getHanSD());
            pstmt.setString(3, thanhVien.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating status Card: " + e.getMessage());
            return false;
        }
    }

    // Xóa thành viên
    public boolean deleteThanhVien(String id) {
        String sql = "DELETE FROM ThanhVien WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting ThanhVien: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách tất cả thành viên
    public ArrayList getAllThanhVien() {
        ArrayList list = new ArrayList<>();
        String sql = "SELECT * FROM ThanhVien";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ThanhVienDTO thanhVien = new ThanhVienDTO(
                        rs.getString("id"),
                        rs.getNString("ten"),
                        rs.getString("CCCD"),
                        rs.getString("sdt"),
                        rs.getNString("diaChi"),
                        rs.getDate("ngayDK"),
                        rs.getDate("hanSD"),
                        rs.getDouble("phiDuyTri"),
                        rs.getNString("trangThai")
                );
                list.add(thanhVien);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all ThanhVien: " + e.getMessage());
        }
        return list;
    }

    public boolean hasID(String id) {
        boolean checked = false;
        String sql = "select * from ThanhVien where id = ?";
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

    public ArrayList<Object[]> getDocGiaByNgayDK(int month, int year) {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT id , ten , ngayDK "
                + "FROM ThanhVien "
                + "WHERE MONTH(ngayDK) = ? AND YEAR(ngayDK) = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getNString(2),
                    rs.getDate(3)
                };
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return result;
    }

    public ArrayList<Object[]> getDocGiaKhoaThe() {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT id , ten , ngayDK "
                + "FROM ThanhVien "
                + "WHERE  trangThai like N'Bị khóa' ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getNString(2),
                    rs.getDate(3)
                };
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return result;
    }

    public ArrayList<Object[]> getDocGiaQuaHan() {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT id , ten , hanSD "
                + "FROM ThanhVien "
                + "WHERE  trangThai like N'Quá hạn' ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getNString(2),
                    rs.getDate(3)
                };
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return result;
    }

    public ArrayList<Object[]> getTop5() {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT TOP 5 tv.id, tv.ten, COUNT(pm.id) AS SoLuotMuon "
                + "FROM ThanhVien tv "
                + "INNER JOIN PhieuMuon pm ON tv.id = pm.maThe "
                + "GROUP BY dg.id, dg.tenDocGia "
                + "ORDER BY SoLuotMuon DESC;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Object[] row = {
                    rs.getString(1),
                    rs.getNString(2),
                    rs.getInt(3)
                };
                result.add(row);
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
}
