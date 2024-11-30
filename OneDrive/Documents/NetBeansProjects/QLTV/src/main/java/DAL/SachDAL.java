package DAL;

import DTO.NXBDTO;
import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import java.sql.*;
import java.util.ArrayList;

public class SachDAL {

    private final Connection conn;

    public SachDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm sách mới
    public boolean addSach(SachDTO sachDTO) {
        String sql = "INSERT INTO Sach (id, tenSach, theloai, tacGia, NXB, namXB, soLuong) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sachDTO.getId());
            pstmt.setNString(2, sachDTO.getTenSach());
            pstmt.setString(3, sachDTO.getTheloai());
            pstmt.setString(4, sachDTO.getTacGia());
            pstmt.setString(5, sachDTO.getNXB());
            pstmt.setInt(6, sachDTO.getNamXB());
            pstmt.setInt(7, sachDTO.getSoLuong());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding Sach: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật sách
    public boolean updateSach(SachDTO sachDTO) {
        String sql = "UPDATE Sach SET tenSach = ?, theloai = ?, tacGia = ?, NXB = ?, namXB = ?, soLuong = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setNString(1, sachDTO.getTenSach());
            pstmt.setString(2, sachDTO.getTheloai());
            pstmt.setString(3, sachDTO.getTacGia());
            pstmt.setString(4, sachDTO.getNXB());
            pstmt.setInt(5, sachDTO.getNamXB());
            pstmt.setInt(6, sachDTO.getSoLuong());
            pstmt.setString(7, sachDTO.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating Sach: " + e.getMessage());
            return false;
        }
    }

    // Xóa sách
    public boolean deleteSach(String id) {
        String sql = "DELETE FROM Sach WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting Sach: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả sách
    public ArrayList<SachDTO> getAllSach() {
        ArrayList<SachDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SachDTO sachDTO = new SachDTO(
                        rs.getString("id"),
                        rs.getNString("tenSach"),
                        rs.getString("theloai"),
                        rs.getString("tacGia"),
                        rs.getString("NXB"),
                        rs.getInt("namXB"),
                        rs.getInt("soLuong")
                );
                list.add(sachDTO);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all Sach: " + e.getMessage());
        }
        return list;
    }

    public boolean hasID(String id) {
        boolean checked = false;
        String sql = "select * from Sach where id = ?";
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

    public ArrayList<TheLoaiDTO> getAllTheLoai() {
        ArrayList<TheLoaiDTO> listTheLoai = new ArrayList<>();
        String query = "SELECT * FROM TheLoai";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TheLoaiDTO theLoai = new TheLoaiDTO();
                theLoai.setId(resultSet.getString("id"));
                theLoai.setTen(resultSet.getNString("ten"));
                listTheLoai.add(theLoai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTheLoai;
    }

    // Lấy tất cả tác giả
    public ArrayList<TacGiaDTO> getAllTacGia() {
        ArrayList<TacGiaDTO> listTacGia = new ArrayList<>();
        String query = "SELECT * FROM TacGia";  // Câu lệnh SQL lấy tất cả tác giả

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TacGiaDTO tacGia = new TacGiaDTO();
                tacGia.setId(resultSet.getString("id"));
                tacGia.setTen(resultSet.getNString("ten"));
                listTacGia.add(tacGia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTacGia;
    }

    // Lấy tất cả nhà xuất bản
    public ArrayList<NXBDTO> getAllNhaXuatBan() {
        ArrayList<NXBDTO> listNhaXuatBan = new ArrayList<>();
        String query = "SELECT * FROM NXB";  // Câu lệnh SQL lấy tất cả nhà xuất bản

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                NXBDTO nhaXuatBan = new NXBDTO();
                nhaXuatBan.setId(resultSet.getString("id"));
                nhaXuatBan.setTen(resultSet.getNString("ten"));
                listNhaXuatBan.add(nhaXuatBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNhaXuatBan;
    }

    public ArrayList<Object[]> getSoLuongSachBYTacGia() {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT s.tacGia, tg.Ten, SUM(s.SoLuong) AS SoLuongSach "
                + "FROM Sach s "
                + "INNER JOIN TacGia tg ON s.tacGia = tg.id "
                + "GROUP BY s.tacGia, tg.Ten";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Object[] e = {rs.getString(1), rs.getNString(2), rs.getInt(3)};
                result.add(e);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<Object[]> getSoLuongSachBYTheLoai() {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT s.theloai, tl.Ten, SUM(s.SoLuong) AS SoLuongSach "
                + "FROM Sach s "
                + "INNER JOIN TheLoai tl ON s.theloai = tl.id "
                + "GROUP BY s.theloai, tl.Ten";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Object[] e = {rs.getString(1), rs.getNString(2), rs.getInt(3)};
                result.add(e);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<Object[]> getSoLuongSachBYNXB() {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT s.NXB, nxb.Ten, SUM(s.SoLuong) AS SoLuongSach "
                + "FROM Sach s "
                + "INNER JOIN NXB nxb ON s.NXB = nxb.id "
                + "GROUP BY s.NXB, nxb.Ten";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Object[] e = {rs.getString(1), rs.getNString(2), rs.getInt(3)};
                result.add(e);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<Object[]> getSachKhongNguyenVen() {
        ArrayList<Object[]> result = new ArrayList<>();
        String sql = "SELECT s.id, s.tenSach, SUM(ctpt.soLuong) AS SoLuongSach, ctpt.trangThai "
                + "FROM Sach s "
                + "INNER JOIN CT_PhieuTra ctpt ON s.id = ctpt.maSach "
                + "WHERE ctpt.trangThai <> N'Nguyên vẹn' "
                + "GROUP BY s.id, s.tenSach, ctpt.trangThai";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Object[] e = {
                    rs.getString(1), 
                    rs.getNString(2), 
                    rs.getInt(3), 
                    rs.getNString(4)};
                result.add(e);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
}
