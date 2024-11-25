package DAL;

import DTO.NXBDTO;
import java.sql.*;
import java.util.ArrayList;

public class NXBDAL extends connectionDB {

    private final Connection conn;
    public NXBDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm nhà xuất bản
    public boolean addNXB(NXBDTO nxb) {
        String sql = "INSERT INTO NXB (id, ten) VALUES (?, ?)";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nxb.getId());
            pstmt.setString(2, nxb.getTen());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding NXB: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật nhà xuất bản
    public boolean updateNXB(NXBDTO nxb) {
        String sql = "UPDATE NXB SET ten = ? WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nxb.getTen());
            pstmt.setString(2, nxb.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating NXB: " + e.getMessage());
            return false;
        }
    }

    // Xóa nhà xuất bản
    public boolean deleteNXB(String id) {
        String sql = "DELETE FROM NXB WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting NXB: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách tất cả nhà xuất bản
    public ArrayList<NXBDTO> getAllNXB() {
        ArrayList<NXBDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NXB";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NXBDTO nxb = new NXBDTO(rs.getString("id"), rs.getString("ten"));
                list.add(nxb);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching NXB: " + e.getMessage());
        }
        return list;
    }
    public boolean hasID(String id){
        boolean checked = false;
        String sql = "select * from NXB where id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return checked;
    }

}
