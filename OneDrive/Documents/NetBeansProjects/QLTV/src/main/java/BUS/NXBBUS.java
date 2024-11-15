package BUS;

import DAL.NXBDAL;
import DTO.NXBDTO;
import java.util.List;

public class NXBBUS {

    private NXBDAL nxbDAL;

    public NXBBUS() {
        nxbDAL = new NXBDAL();
    }

    // Thêm nhà xuất bản mới
    public boolean addNXB(NXBDTO nxbDTO) {
        return nxbDAL.addNXB(nxbDTO);
    }

    // Cập nhật nhà xuất bản
    public boolean updateNXB(NXBDTO nxbDTO) {
        return nxbDAL.updateNXB(nxbDTO);
    }

    // Xóa nhà xuất bản
    public boolean deleteNXB(String id) {
        return nxbDAL.deleteNXB(id);
    }

    // Lấy danh sách tất cả nhà xuất bản
    public List<NXBDTO> getAllNXB() {
        return nxbDAL.getAllNXB();
    }

    // Tìm kiếm nhà xuất bản theo tên
    public List<NXBDTO> searchNXBByName(String name) {
        return nxbDAL.searchNXBByName(name);
    }

    // Lấy nhà xuất bản theo ID
    public NXBDTO getNXBById(String id) {
        return nxbDAL.getNXBById(id);
    }
}
