package BUS;

import DAL.NXBDAL;
import DTO.NXBDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class NXBBUS {

    private NXBDAL nxbDAL;
    private ArrayList<NXBDTO> ds;
    public NXBBUS() throws SQLException {
        nxbDAL = new NXBDAL();
        ds = nxbDAL.getAllNXB();
    }

    // Thêm nhà xuất bản mới
    public String addNXB(NXBDTO nxbDTO) {
        if(nxbDAL.hasID(nxbDTO.getId())){
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = nxbDAL.addNXB(nxbDTO);
        if(isAdded){
            ds.add(nxbDTO);
            return "Thêm nhà xuất bản " + nxbDTO.getTen().toUpperCase() +" thành công!";
        }
        return "Thêm nhà xuất bản không thành công!";
    }

    // Cập nhật nhà xuất bản
    public String updateNXB(NXBDTO nxbDTO) {
        boolean isUpdated = nxbDAL.updateNXB(nxbDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(nxbDTO.getId())) {
                    ds.set(i, nxbDTO);
                    break;
                }
            }
            return "Cập nhật thông tin nhà xuất bản "+nxbDTO.getTen().toUpperCase()+" thành công!";
        }
        return "Cập nhật không thành công!";
    }

    // Xóa nhà xuất bản
    public String deleteNXB(String id) {
        boolean isDeleted = nxbDAL.deleteNXB(id);
        if(isDeleted){
            ds.removeIf(nxb -> nxb.getId().equals(id));
            return "Xóa mã "+id+" thành công!";
        }
        return "Xóa không thành công!";
    }

    // Lấy danh sách tất cả nhà xuất bản
    public ArrayList<NXBDTO> getAllNXB() {
        return ds;
    }

    // Tìm kiếm nhà xuất bản theo tên
    public ArrayList<NXBDTO> searchNXBByName(String name) {
        ArrayList<NXBDTO> result = new ArrayList<>();
        for(NXBDTO nxb:ds){
            if(nxb.getTen().toLowerCase().contains(name.toLowerCase())){
                result.add(nxb);
            }
        }
        return result;
    }

    // Lấy nhà xuất bản theo ID
    public NXBDTO getNXBById(String id) {
        for(NXBDTO nxb : ds){
            if(nxb.getId().equals(id)){
                return nxb;
            }
        }
        return null;
    }
    
    public int getSoLuongSachofNXB(String id){
        return nxbDAL.getSoLuongSachofNXB(id);
    }
    
    public int getSLCL(String id){
        return nxbDAL.getSoLuongSachConlai(id);
    }
    
    public int  getSoLuongSachConLai(String id){
        int a = getSoLuongSachofNXB(id);
        int b = getSLCL(id);
return a - b >= 0 ? a-b : 0 ;    }
}
