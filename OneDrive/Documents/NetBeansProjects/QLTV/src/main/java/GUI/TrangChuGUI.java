package GUI;

import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import BUS.*;
import DTO.*;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public final class TrangChuGUI extends javax.swing.JFrame {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private CardLayout cardLayout1, cardLayout2;
    private ThanhVienBUS tvBUS;
    private PhieuMuonBUS pmBUS;
    private PhieuTraBUS ptBUS;
    private CT_PhieuMuonBUS ctpmBUS;
    private CT_PhieuTraBUS ctptBUS;
    private SachBUS sachBUS;
    private TheLoaiBUS tlBUS;
    private NXBBUS nxbBUS;
    private TacGiaBUS tgBUS;
    private DefaultTableModel TV_DSMuonMuon, ChuaCoThe_DSMuonMuon, dsPM, dsMuon, dsPT, dsThe, bangTK;
    private int TVMuon_quantityBook = 0, ChuaCothe_quantityBook = 0;
    private HashMap<String, String[]> data;

    public TrangChuGUI() throws SQLException {
        initComponents();
        addComponentTojPanel_QLMuonTra();
        addComponentTojPanel_Main();
        TV_DSMuonMuon = (DefaultTableModel) tbl_TVMuon_DSMuonMuon.getModel();
        ChuaCoThe_DSMuonMuon = (DefaultTableModel) tbl_ChuaCoThe_DSMuonMuon.getModel();
        dsPM = (DefaultTableModel) tbl_DSMuon_DSPM.getModel();
        dsMuon = (DefaultTableModel) tbl_DSMuon_DSM.getModel();
        dsThe = (DefaultTableModel) tbl_DanhSachThe.getModel();
        dsPT = (DefaultTableModel) tbl_DSTra_DSPT.getModel();
        sachBUS = new SachBUS();
        tlBUS = new TheLoaiBUS();
        tgBUS = new TacGiaBUS();
        nxbBUS = new NXBBUS();
        ctptBUS = new CT_PhieuTraBUS();
        ctpmBUS = new CT_PhieuMuonBUS();
        pmBUS = new PhieuMuonBUS();
        ptBUS = new PhieuTraBUS();
        tvBUS = new ThanhVienBUS();
        QuyDinhVaChinhSach();

    }

    public void QuyDinhVaChinhSach() {
        String text = "<html>"
                + "    <h1>Quy định và Chính sách Mượn Trả Thư viện</h1>"
                + "    <h3>1. Đối tượng được mượn sách</h3>"
                + "    <ul>"
                + "        <li>Chỉ các bạn đọc đã đăng ký thẻ thư viện và còn thời hạn sử dụng thẻ mới được phép mượn sách.</li>"
                + "        <li>Mỗi thẻ thư viện chỉ được sử dụng bởi chủ thẻ, không được cho người khác mượn hoặc sử dụng thay.</li>"
                + "    </ul>"
                + "    <h2>2. Số lượng và thời hạn mượn sách</h2>"
                + "    <ul>"
                + "        <li>Mỗi bạn đọc được phép mượn tối đa <strong>5 cuốn sách/lần</strong>.</li>"
                + "        <li>Thời hạn mượn sách là <strong>21 ngày</strong> kể từ ngày mượn.</li>"
                + "        <li>Sách mượn quá hạn phải được trả ngay và có thể áp dụng các khoản phạt hành chính theo quy định.</li>"
                + "    </ul>"
                + "    <h2>3. Quy định về trả sách</h2>"
                + "    <ul>"
                + "        <li>Sách phải được trả đúng hạn và ở tình trạng nguyên vẹn như khi mượn.</li>"
                + "        <li>Nếu sách bị hư hỏng hoặc mất, bạn đọc phải bồi thường theo giá trị hiện tại của sách cộng với phí xử lý (nếu có).</li>"
                + "        <li>Đối với sách bị mất không còn tái bản, bạn đọc phải bồi thường bằng sách có nội dung tương đương do thư viện yêu cầu.</li>"
                + "    </ul>"
                + "    <h2>4. Chính sách gia hạn</h2>"
                + "    <ul>"
                + "        <li>Mỗi cuốn sách chỉ được gia hạn <strong>02 lần</strong> và thêm tối đa <strong>14 ngày</strong>.</li>"
                + "        <li>Yêu cầu gia hạn phải được thực hiện trước ngày hết hạn.</li>"
                + "        <li>Sách đã có người khác đặt trước hoặc đã quá hạn không được gia hạn.</li>"
                + "    </ul>"
                + "    <h2>5. Quy định về phạt</h2>"
                + "    <ul>"
                + "        <li><strong>Trả sách quá hạn</strong>: Phạt <strong>1.000 đồng/ngày/cuốn</strong>.</li>"
                + "        <li><strong>Làm hư hỏng sách</strong>: Bồi thường giá trị sách cộng phí xử lý 20%.</li>"
                + "        <li><strong>Mất sách</strong>: Bồi thường 150% giá trị sách hoặc thay thế bằng sách tương đương do thư viện quy định.</li>"
                + "    </ul>"
                + "    <h2>6. Thời gian hoạt động của thư viện</h2>"
                + "    <ul>"
                + "        <li>Thư viện mở cửa từ <strong>8:00 - 17:30</strong> từ thứ Hai đến thứ Bảy.</li>"
                + "        <li>Ngày lễ, Tết hoặc sự kiện đặc biệt, thư viện có thể đóng cửa, thông báo sẽ được cập nhật trên bảng tin hoặc website thư viện.</li>"
                + "    </ul>"
                + "    <h2>7. Các quy định khác</h2>"
                + "    <ul>"
                + "        <li>Bạn đọc phải giữ yên tĩnh và tôn trọng không gian chung khi sử dụng thư viện.</li>"
                + "        <li>Không mang đồ ăn, thức uống vào khu vực sách.</li>"
                + "        <li>Tuân thủ các hướng dẫn của nhân viên thư viện trong quá trình mượn trả và sử dụng dịch vụ.</li>"
                + "    </ul>"
                + "    <p>Lưu ý: Quy định có thể được thay đổi và cập nhật định kỳ. Vui lòng liên hệ thư viện để biết thêm chi tiết.</p>"
                + "</html>";
        lb_QuyDinhVaChinhSach.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_SideBar = new javax.swing.JPanel();
        jPanel_Admin = new javax.swing.JPanel();
        jLabel_Admin = new javax.swing.JLabel();
        jPanel_ChucNang = new javax.swing.JPanel();
        jButton_TrangChu = new javax.swing.JButton();
        jButton_QLLoaiSach = new javax.swing.JButton();
        jButton_QLSach = new javax.swing.JButton();
        jButton_QLNXB = new javax.swing.JButton();
        jButton_QLTacGia = new javax.swing.JButton();
        jButton_QLThe = new javax.swing.JButton();
        jButton_QLMuon = new javax.swing.JButton();
        jButton_QLTra = new javax.swing.JButton();
        jButton_ThongKevaBaoCao = new javax.swing.JButton();
        jButton_DangXuat = new javax.swing.JButton();
        jPanel_Main = new javax.swing.JPanel();
        jPanel_TrangChu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_QuyDinhVaChinhSach = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel_QLloaisach = new javax.swing.JPanel();
        SLSach_TheLoai = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_IDLoaiSach = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField_TenLoaiSach = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        SLCL_TheLoai = new javax.swing.JTextField();
        jButton_CapNhatTL = new javax.swing.JButton();
        jButton_XoaTL = new javax.swing.JButton();
        jTextField38 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_TimKiem_TheLoai = new javax.swing.JTextField();
        jButton_TimKiem_TheLoai = new javax.swing.JButton();
        ViewAll_TheLoai = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_TheLoai = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jButton_ThemTheLoai = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jPanel_QLsach = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSach = new javax.swing.JTextField();
        jButtonTimSach = new javax.swing.JButton();
        jComboBoxSach = new javax.swing.JComboBox<>();
        jButtonShowAllSach = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldMaSach = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTenSach = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldNamXuatBan = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxTheLoai = new javax.swing.JComboBox<>();
        jComboBoxTacGia = new javax.swing.JComboBox<>();
        jComboBoxNhaXuatBan = new javax.swing.JComboBox<>();
        jButtonCapNhatSach = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextFieldSoLuong = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTextFieldGiaSach = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableSach = new javax.swing.JTable();
        jButtonThemSach = new javax.swing.JButton();
        jPanel_QLNXB = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField_TimKiem_NXB = new javax.swing.JTextField();
        jButton_TimKiem_NXB = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField_IDNXB = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField_TenNXB = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        SLCL_NXB = new javax.swing.JTextField();
        jButton_CapNhatNXB = new javax.swing.JButton();
        jButton_XoaNXB = new javax.swing.JButton();
        SLCL_NXB1 = new javax.swing.JTextField();
        ViewAll_NXB = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_NXB = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jButton_ThemNXB = new javax.swing.JButton();
        jPanel_QLTacGia = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField_TimKiem_TacGia = new javax.swing.JTextField();
        jButton_TimKiem_TacGia = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextField_IDTacGia = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField_TenTacGia = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        SLCL_TacGia = new javax.swing.JTextField();
        SLCL_TacGia1 = new javax.swing.JTextField();
        jButton_CapNhatTacGia = new javax.swing.JButton();
        jButton_XoaTacGia = new javax.swing.JButton();
        ViewAll_TacGia = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_TacGia = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jButton_ThemTacGia = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        jTextField_TenTacGia1 = new javax.swing.JTextField();
        jTextField_IDTacGia1 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jPanel_QLThe = new javax.swing.JPanel();
        jPanel_QLTheHeader = new javax.swing.JPanel();
        jButton_DangKyThe = new javax.swing.JButton();
        jPanel_DSThe = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_TimKiemThe = new javax.swing.JTextField();
        btn_TimKiemThe = new javax.swing.JButton();
        btn_XemTatCaThe = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_DanhSachThe = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txt_QLThe_name = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_QLThe_cccd = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txt_QLThe_sdt = new javax.swing.JTextField();
        btn_QLthe_Update = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_QLThe_DiaChi = new javax.swing.JTextArea();
        jPanel12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txt_GHT_idThe = new javax.swing.JTextField();
        txt_GHT_trangThai = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txt_GHT_ngayDK = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_GHT_ngayHH = new javax.swing.JTextField();
        lb_PhiDuyTri = new javax.swing.JLabel();
        btn_QLThe_giaHan = new javax.swing.JButton();
        btn_QLThe_khoaThe = new javax.swing.JButton();
        btn_QLThe_moKhoaThe = new javax.swing.JButton();
        cbb_TimKiemThe = new javax.swing.JComboBox<>();
        jPanel_QLMuon = new javax.swing.JPanel();
        jPanel_QLMuonHeader = new javax.swing.JPanel();
        jButton_QLMuon_DanhSachMuon = new javax.swing.JButton();
        jButton_QLMuon_TVMuon = new javax.swing.JButton();
        jButton_QLMuon_ChuaCoThe = new javax.swing.JButton();
        jPanel_QLMuonTraMain = new javax.swing.JPanel();
        jPanel_TVMuon = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        txt_TVMuon_idSach = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_TVMuon_TenSach = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        spn_TVMuon_SoLuong = new javax.swing.JSpinner();
        jLabel42 = new javax.swing.JLabel();
        txt_TVMuon_TimKiemSach = new javax.swing.JTextField();
        btn_TVMuon_TimSach = new javax.swing.JButton();
        btn_TVMuon_ThemVaoPM = new javax.swing.JButton();
        btn_TVMuon_XoaKhoiPM = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txt_TVMuon_idThe = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txt_TVMuon_PhiDatCoc = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txt_TVMuon_NgayMuon = new javax.swing.JTextField();
        txt_TVMuon_HanTra = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txt_TVMuon_TrangThai = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_TVMuon_DSMuonMuon = new javax.swing.JTable();
        btn_TVMuon_XacNhanMuon = new javax.swing.JButton();
        btn_TVMuon_CapNhatPM = new javax.swing.JButton();
        btn_TVMuon_Clear = new javax.swing.JButton();
        jPanel_ChuaCoThe = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txt_ChuaCoThe_idSach = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txt_ChuaCoThe_TenSach = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        spn_ChuaCoThe_SoLuong = new javax.swing.JSpinner();
        jLabel51 = new javax.swing.JLabel();
        txt_ChuaCoThe_TimSach = new javax.swing.JTextField();
        btn_ChuaCoThe_TimKiem = new javax.swing.JButton();
        btn_ChuaCoThe_ThemVaoPM = new javax.swing.JButton();
        btn_ChuaCoThe_XoaKhoiPM = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        txt_ChuaCoThe_idThe = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txt_ChuaCoThe_PhiDatCoc = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txt_ChuaCoThe_NgayMuon = new javax.swing.JTextField();
        txt_ChuaCoThe_HanTra = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txt_ChuaCoThe_TrangThai = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_ChuaCoThe_DSMuonMuon = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txt_ChuaCoThe_HoTen = new javax.swing.JTextField();
        txt_ChuaCoThe_cccd = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txt_ChuaCoThe_sdt = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txt_ChuaCoThe_DiaChi = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        btn_ChuaCoThe_XacNhanMuon = new javax.swing.JButton();
        jPanel_DanhSachMuon = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        cbb_DSMuon_TimKiem = new javax.swing.JComboBox<>();
        txt_DSMuon_TimKiem = new javax.swing.JTextField();
        btn_DSMuon_TimKiem = new javax.swing.JButton();
        btn_DSMuon_XemTatCa = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_DSMuon_DSPM = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_DSMuon_DSM = new javax.swing.JTable();
        btn_DSMuon_XuatPhieu = new javax.swing.JButton();
        btn_DSMuon_GiaHan = new javax.swing.JButton();
        btn_DSMuon_ChinhSuaPM = new javax.swing.JButton();
        btn_DSMuon_TraSach = new javax.swing.JButton();
        jPanel_ThongKevaBaoCao = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        pn_ThongKe = new javax.swing.JPanel();
        lb_HienThi = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_BangDuLieu = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        cbb_ThongKe_LuaChon = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        txt_SoLuongSach = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        cbb_Thang = new javax.swing.JComboBox<>();
        jLabel75 = new javax.swing.JLabel();
        cbb_Nam = new javax.swing.JComboBox<>();
        btn_ThongKe = new javax.swing.JButton();
        cbb_CacLuaChon = new javax.swing.JComboBox<>();
        txt_SoDocGia = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txt_SoPhieuMuon = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txt_SoPhieuTra = new javax.swing.JTextField();
        pn_BieuDoTron = new javax.swing.JPanel();
        pn_BieuDo1 = new javax.swing.JPanel();
        pn_BieuDo2 = new javax.swing.JPanel();
        pn_BieuDo3 = new javax.swing.JPanel();
        pn_BieuDo4 = new javax.swing.JPanel();
        jPanel_QLTra = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        cbb_DSTra_TimKiem = new javax.swing.JComboBox<>();
        txt_DSTra_TimKiem = new javax.swing.JTextField();
        btn_DSTra_TimKiem = new javax.swing.JButton();
        btn_DSTra_XemTatCa = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbl_DSTra_DSPT = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbl_DSTra_DST = new javax.swing.JTable();
        btn_QLTra_ChinhSua = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang chủ");
        setPreferredSize(new java.awt.Dimension(2000, 1100));
        getContentPane().setLayout(new java.awt.BorderLayout(10, 0));

        jPanel_SideBar.setPreferredSize(new java.awt.Dimension(250, 1010));
        jPanel_SideBar.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel_Admin.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Admin.setPreferredSize(new java.awt.Dimension(250, 150));

        jLabel_Admin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_Admin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lib.png"))); // NOI18N
        jLabel_Admin.setText(" ADMIN");
        jLabel_Admin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel_Admin.setIconTextGap(10);

        javax.swing.GroupLayout jPanel_AdminLayout = new javax.swing.GroupLayout(jPanel_Admin);
        jPanel_Admin.setLayout(jPanel_AdminLayout);
        jPanel_AdminLayout.setHorizontalGroup(
            jPanel_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Admin, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel_AdminLayout.setVerticalGroup(
            jPanel_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Admin, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel_SideBar.add(jPanel_Admin, java.awt.BorderLayout.PAGE_START);

        jPanel_ChucNang.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ChucNang.setLayout(new java.awt.GridLayout(10, 1, 0, 5));

        jButton_TrangChu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_TrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trangchu.png"))); // NOI18N
        jButton_TrangChu.setText("Trang chủ");
        jButton_TrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TrangChuActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_TrangChu);

        jButton_QLLoaiSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loaisach.png"))); // NOI18N
        jButton_QLLoaiSach.setText("Quản lý loại sách");
        jButton_QLLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLLoaiSachActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_QLLoaiSach);

        jButton_QLSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book_mana.png"))); // NOI18N
        jButton_QLSach.setText("Quản lý sách");
        jButton_QLSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLSachActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_QLSach);

        jButton_QLNXB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/publisher.png"))); // NOI18N
        jButton_QLNXB.setText("Quản lý NXB");
        jButton_QLNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLNXBActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_QLNXB);

        jButton_QLTacGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor.png"))); // NOI18N
        jButton_QLTacGia.setText("Quản lý tác giả");
        jButton_QLTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLTacGiaActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_QLTacGia);

        jButton_QLThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLThe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/the.png"))); // NOI18N
        jButton_QLThe.setText("Quản lý thẻ");
        jButton_QLThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLTheActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_QLThe);

        jButton_QLMuon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muon.png"))); // NOI18N
        jButton_QLMuon.setText("Quản lý mượn");
        jButton_QLMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLMuonActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_QLMuon);

        jButton_QLTra.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tra.png"))); // NOI18N
        jButton_QLTra.setText("Quản lý trả");
        jButton_QLTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLTraActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_QLTra);

        jButton_ThongKevaBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_ThongKevaBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/thongke.png"))); // NOI18N
        jButton_ThongKevaBaoCao.setText("Thống kê ");
        jButton_ThongKevaBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThongKevaBaoCaoActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_ThongKevaBaoCao);

        jButton_DangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dxuat.png"))); // NOI18N
        jButton_DangXuat.setText("Đăng xuất");
        jButton_DangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DangXuatActionPerformed(evt);
            }
        });
        jPanel_ChucNang.add(jButton_DangXuat);

        jPanel_SideBar.add(jPanel_ChucNang, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel_SideBar, java.awt.BorderLayout.LINE_START);

        jPanel_Main.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Main.setAutoscrolls(true);
        jPanel_Main.setPreferredSize(new java.awt.Dimension(1800, 1100));
        jPanel_Main.setLayout(new java.awt.CardLayout());

        jPanel_TrangChu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main.jpg"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("THƯ VIỆN NGUYỄN VĂN CỪ");

        javax.swing.GroupLayout jPanel_TrangChuLayout = new javax.swing.GroupLayout(jPanel_TrangChu);
        jPanel_TrangChu.setLayout(jPanel_TrangChuLayout);
        jPanel_TrangChuLayout.setHorizontalGroup(
            jPanel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_TrangChuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_TrangChuLayout.createSequentialGroup()
                        .addComponent(lb_QuyDinhVaChinhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1464, 1464, 1464))
        );
        jPanel_TrangChuLayout.setVerticalGroup(
            jPanel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TrangChuLayout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_TrangChuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_QuyDinhVaChinhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_TrangChuLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(944, Short.MAX_VALUE))
        );

        jPanel_Main.add(jPanel_TrangChu, "card2");

        jPanel_QLloaisach.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_QLloaisach.setPreferredSize(new java.awt.Dimension(1780, 1010));

        SLSach_TheLoai.setBackground(new java.awt.Color(255, 255, 255));
        SLSach_TheLoai.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin loại sách", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N
        SLSach_TheLoai.setPreferredSize(new java.awt.Dimension(300, 732));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Mã loại sách");

        jTextField_IDLoaiSach.setEditable(false);
        jTextField_IDLoaiSach.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_IDLoaiSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Tên loại sách");

        jTextField_TenLoaiSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Số lượng sách");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Số lượng còn lại");

        SLCL_TheLoai.setEditable(false);
        SLCL_TheLoai.setBackground(new java.awt.Color(255, 255, 255));
        SLCL_TheLoai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton_CapNhatTL.setBackground(new java.awt.Color(255, 255, 51));
        jButton_CapNhatTL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_CapNhatTL.setText("Cập nhật");
        jButton_CapNhatTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CapNhatTLActionPerformed(evt);
            }
        });

        jButton_XoaTL.setBackground(new java.awt.Color(255, 51, 51));
        jButton_XoaTL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_XoaTL.setText("Xóa loại sách");

        jTextField38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout SLSach_TheLoaiLayout = new javax.swing.GroupLayout(SLSach_TheLoai);
        SLSach_TheLoai.setLayout(SLSach_TheLoaiLayout);
        SLSach_TheLoaiLayout.setHorizontalGroup(
            SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SLSach_TheLoaiLayout.createSequentialGroup()
                .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SLSach_TheLoaiLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(19, 19, 19)
                        .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_TenLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_IDLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SLSach_TheLoaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_CapNhatTL, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)))
                .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SLSach_TheLoaiLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SLCL_TheLoai)
                            .addComponent(jTextField38, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))
                    .addGroup(SLSach_TheLoaiLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jButton_XoaTL, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        SLSach_TheLoaiLayout.setVerticalGroup(
            SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SLSach_TheLoaiLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SLSach_TheLoaiLayout.createSequentialGroup()
                        .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_IDLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(57, 57, 57)
                        .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_TenLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(SLCL_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(SLSach_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_CapNhatTL, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_XoaTL, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Tìm kiếm:");

        jTextField_TimKiem_TheLoai.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField_TimKiem_TheLoai.setText("Nhập thông tin tìm kiếm......");
        jTextField_TimKiem_TheLoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_TimKiem_TheLoaiFocusGained(evt);
            }
        });

        jButton_TimKiem_TheLoai.setBackground(new java.awt.Color(204, 255, 255));
        jButton_TimKiem_TheLoai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_TimKiem_TheLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton_TimKiem_TheLoai.setText("Tìm kiếm");
        jButton_TimKiem_TheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimKiem_TheLoaiActionPerformed(evt);
            }
        });

        ViewAll_TheLoai.setBackground(new java.awt.Color(204, 255, 255));
        ViewAll_TheLoai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ViewAll_TheLoai.setText("Xem tất cả");
        ViewAll_TheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewAll_TheLoaiActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jTable_TheLoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable_TheLoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable_TheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><center style='font-size:16px'>Mã thể loại</html>", "<html><center style='font-size:16px'>Tên thể loại</html>", "<html><center style='font-size:16px'>Số lượng sách</htlml"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_TheLoai.setRowHeight(25);
        jTable_TheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_TheLoaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_TheLoai);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thêm loại sách", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jButton_ThemTheLoai.setBackground(new java.awt.Color(204, 255, 255));
        jButton_ThemTheLoai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_ThemTheLoai.setText("Thêm thể loại");
        jButton_ThemTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemTheLoaiActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setText("Mã loại");

        jTextField36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel61.setText("Tên loại sách");

        jTextField37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField36))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jButton_ThemTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ThemTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel_QLloaisachLayout = new javax.swing.GroupLayout(jPanel_QLloaisach);
        jPanel_QLloaisach.setLayout(jPanel_QLloaisachLayout);
        jPanel_QLloaisachLayout.setHorizontalGroup(
            jPanel_QLloaisachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLloaisachLayout.createSequentialGroup()
                .addGroup(jPanel_QLloaisachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_QLloaisachLayout.createSequentialGroup()
                        .addGap(464, 464, 464)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_TimKiem_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_TimKiem_TheLoai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewAll_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_QLloaisachLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel_QLloaisachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_QLloaisachLayout.createSequentialGroup()
                                .addComponent(SLSach_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1590, Short.MAX_VALUE))
        );
        jPanel_QLloaisachLayout.setVerticalGroup(
            jPanel_QLloaisachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLloaisachLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel_QLloaisachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_TimKiem_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_TimKiem_TheLoai)
                    .addComponent(ViewAll_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_QLloaisachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SLSach_TheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                .addGap(1067, 1067, 1067))
        );

        jPanel_Main.add(jPanel_QLloaisach, "card3");

        jPanel_QLsach.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Tìm kiếm theo:");

        jTextFieldSach.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextFieldSach.setText("Nhập thông tin muốn tìm kiếm...");
        jTextFieldSach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSachFocusGained(evt);
            }
        });

        jButtonTimSach.setBackground(new java.awt.Color(204, 255, 255));
        jButtonTimSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonTimSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButtonTimSach.setText("Tìm kiếm");
        jButtonTimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimSachActionPerformed(evt);
            }
        });

        jComboBoxSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jComboBoxSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Nhà xuất bản" }));

        jButtonShowAllSach.setBackground(new java.awt.Color(204, 255, 255));
        jButtonShowAllSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonShowAllSach.setText("Xem tất cả");
        jButtonShowAllSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowAllSachActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin sách", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Mã sách");

        jTextFieldMaSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tên sách");

        jTextFieldTenSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Năm xuất bản");

        jTextFieldNamXuatBan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Thể loại");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Tác giả");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Nhà xuất bản");

        jComboBoxTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jComboBoxTacGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jComboBoxNhaXuatBan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButtonCapNhatSach.setBackground(new java.awt.Color(255, 255, 51));
        jButtonCapNhatSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCapNhatSach.setText("Cập nhật");
        jButtonCapNhatSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCapNhatSachActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("Xóa sách");

        jTextFieldSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Số lượng");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel71.setText("Giá sách");

        jTextFieldGiaSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel6)
                    .addComponent(jLabel32)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(jTextFieldNamXuatBan, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel71))))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBoxTheLoai, javax.swing.GroupLayout.Alignment.LEADING, 0, 316, Short.MAX_VALUE)
                    .addComponent(jComboBoxNhaXuatBan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxTacGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldGiaSach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCapNhatSach, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBoxTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jButtonCapNhatSach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jComboBoxTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextFieldNamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jComboBoxNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel71)
                                .addComponent(jTextFieldGiaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel32)
                                .addComponent(jTextFieldSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jTableSach.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><center style='font-size: 16px'>Mã sách</html>", "<html><center style='font-size: 16px'>Tên sách</html>", "<html><center style='font-size: 16px; '>Thể loại</html>", "<html><center style='font-size: 16px; '>Tác giả</html>", "<html><center style='font-size: 16px; '>Nhà xuất bản</html>", "<html><center style='font-size: 16px; '>Năm xuất bản</html>", "<html><center style='font-size: 16px; '>Số lượng</html>", "<html><center style='font-size: 16px; '>Giá sách</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSach.setRowHeight(25);
        jTableSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSachMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableSach);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );

        jButtonThemSach.setBackground(new java.awt.Color(204, 255, 255));
        jButtonThemSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonThemSach.setForeground(new java.awt.Color(255, 51, 51));
        jButtonThemSach.setText("Thêm sách mới");
        jButtonThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_QLsachLayout = new javax.swing.GroupLayout(jPanel_QLsach);
        jPanel_QLsach.setLayout(jPanel_QLsachLayout);
        jPanel_QLsachLayout.setHorizontalGroup(
            jPanel_QLsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLsachLayout.createSequentialGroup()
                .addGroup(jPanel_QLsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_QLsachLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSach, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSach, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonShowAllSach, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_QLsachLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel_QLsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(1604, Short.MAX_VALUE))
        );
        jPanel_QLsachLayout.setVerticalGroup(
            jPanel_QLsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLsachLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel_QLsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_QLsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonShowAllSach, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_QLsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextFieldSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonTimSach)
                        .addComponent(jComboBoxSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1057, Short.MAX_VALUE))
        );

        jPanel_Main.add(jPanel_QLsach, "card4");

        jPanel_QLNXB.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_QLNXB.setPreferredSize(new java.awt.Dimension(1780, 1010));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("Tìm kiếm:");

        jTextField_TimKiem_NXB.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField_TimKiem_NXB.setText("Nhập thông tin tìm kiếm......");
        jTextField_TimKiem_NXB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_TimKiem_NXBFocusGained(evt);
            }
        });

        jButton_TimKiem_NXB.setBackground(new java.awt.Color(204, 255, 255));
        jButton_TimKiem_NXB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_TimKiem_NXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton_TimKiem_NXB.setText("Tìm kiếm");
        jButton_TimKiem_NXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimKiem_NXBActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin NXB", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 732));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Mã NXB");

        jTextField_IDNXB.setEditable(false);
        jTextField_IDNXB.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_IDNXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Tên NXB");

        jTextField_TenNXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Số lượng sách");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Số lượng còn lại");

        SLCL_NXB.setEditable(false);
        SLCL_NXB.setBackground(new java.awt.Color(255, 255, 255));
        SLCL_NXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton_CapNhatNXB.setBackground(new java.awt.Color(255, 255, 51));
        jButton_CapNhatNXB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_CapNhatNXB.setText("Cập nhật");
        jButton_CapNhatNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CapNhatNXBActionPerformed(evt);
            }
        });

        jButton_XoaNXB.setBackground(new java.awt.Color(255, 51, 51));
        jButton_XoaNXB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_XoaNXB.setText("Xóa NXB");

        SLCL_NXB1.setEditable(false);
        SLCL_NXB1.setBackground(new java.awt.Color(255, 255, 255));
        SLCL_NXB1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_IDNXB, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(jTextField_TenNXB)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_CapNhatNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SLCL_NXB1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(SLCL_NXB)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton_XoaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(SLCL_NXB1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SLCL_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_IDNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_TenNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))))
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_CapNhatNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_XoaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        ViewAll_NXB.setBackground(new java.awt.Color(204, 255, 255));
        ViewAll_NXB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ViewAll_NXB.setText("Xem tất cả");
        ViewAll_NXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewAll_NXBActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jTable_NXB.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable_NXB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><center style='font-size:16px'>Mã NXB</html>", "<html><center style='font-size:16px'>Tên nhà xuất bản</html>", "<html><center style='font-size:16px'>Số lượng sách</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_NXB.setRowHeight(25);
        jTable_NXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_NXBMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable_NXB);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1607, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thêm nhà xuất bản", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel62.setText("Mã NXB");

        jTextField39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextField40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel63.setText("Tên nhà xuất bản");

        jButton_ThemNXB.setBackground(new java.awt.Color(204, 255, 255));
        jButton_ThemNXB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_ThemNXB.setForeground(new java.awt.Color(255, 51, 51));
        jButton_ThemNXB.setText("Thêm NXB");
        jButton_ThemNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemNXBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63))
                .addGap(29, 29, 29)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField39, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(jTextField40))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ThemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ThemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel_QLNXBLayout = new javax.swing.GroupLayout(jPanel_QLNXB);
        jPanel_QLNXB.setLayout(jPanel_QLNXBLayout);
        jPanel_QLNXBLayout.setHorizontalGroup(
            jPanel_QLNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLNXBLayout.createSequentialGroup()
                .addGroup(jPanel_QLNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_QLNXBLayout.createSequentialGroup()
                        .addGap(441, 441, 441)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_TimKiem_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_TimKiem_NXB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewAll_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_QLNXBLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel_QLNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_QLNXBLayout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147)
                                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(1591, Short.MAX_VALUE))
        );
        jPanel_QLNXBLayout.setVerticalGroup(
            jPanel_QLNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLNXBLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel_QLNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_TimKiem_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_TimKiem_NXB)
                    .addComponent(ViewAll_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel_QLNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                .addContainerGap(1050, Short.MAX_VALUE))
        );

        jPanel_Main.add(jPanel_QLNXB, "card5");

        jPanel_QLTacGia.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
        jLabel25.setText("Tìm kiếm:");

        jTextField_TimKiem_TacGia.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField_TimKiem_TacGia.setText("Nhập thông tin tìm kiếm......");
        jTextField_TimKiem_TacGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_TimKiem_TacGiaFocusGained(evt);
            }
        });

        jButton_TimKiem_TacGia.setBackground(new java.awt.Color(204, 255, 255));
        jButton_TimKiem_TacGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_TimKiem_TacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton_TimKiem_TacGia.setText("Tìm kiếm");
        jButton_TimKiem_TacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimKiem_TacGiaActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin tác giả", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 732));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Mã tác giả");

        jTextField_IDTacGia.setEditable(false);
        jTextField_IDTacGia.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_IDTacGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("Tên tác giả");

        jTextField_TenTacGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("Số lượng sách");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("Số lượng còn lại");

        SLCL_TacGia.setEditable(false);
        SLCL_TacGia.setBackground(new java.awt.Color(255, 255, 255));
        SLCL_TacGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        SLCL_TacGia1.setEditable(false);
        SLCL_TacGia1.setBackground(new java.awt.Color(255, 255, 255));
        SLCL_TacGia1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton_CapNhatTacGia.setBackground(new java.awt.Color(255, 255, 51));
        jButton_CapNhatTacGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_CapNhatTacGia.setText("Cập nhật");
        jButton_CapNhatTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CapNhatTacGiaActionPerformed(evt);
            }
        });

        jButton_XoaTacGia.setBackground(new java.awt.Color(255, 51, 51));
        jButton_XoaTacGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_XoaTacGia.setText("Xóa tác giả");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_TenTacGia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_IDTacGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SLCL_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SLCL_TacGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jButton_CapNhatTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jButton_XoaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(SLCL_TacGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SLCL_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_IDTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_TenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_CapNhatTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_XoaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        ViewAll_TacGia.setBackground(new java.awt.Color(204, 255, 255));
        ViewAll_TacGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ViewAll_TacGia.setText("Xem tất cả");
        ViewAll_TacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewAll_TacGiaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jTable_TacGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable_TacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><center style='font-size:16px'>Mã tác giả</html>", "<html><center style='font-size:16px'>Tên tác giả</html>", "<html><center style='font-size:16px'>Số lượn sách</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_TacGia.setRowHeight(25);
        jTable_TacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_TacGiaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable_TacGia);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thêm tác giả", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jButton_ThemTacGia.setBackground(new java.awt.Color(204, 255, 255));
        jButton_ThemTacGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_ThemTacGia.setForeground(new java.awt.Color(255, 51, 51));
        jButton_ThemTacGia.setText("Thêm tác giả");
        jButton_ThemTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemTacGiaActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel64.setText("Mã tác giả");

        jTextField_TenTacGia1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextField_IDTacGia1.setEditable(false);
        jTextField_IDTacGia1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_IDTacGia1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel65.setText("Tên tác giả");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel64)
                            .addComponent(jLabel65))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_IDTacGia1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jTextField_TenTacGia1))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jButton_ThemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_IDTacGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64))
                .addGap(60, 60, 60)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_TenTacGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ThemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel_QLTacGiaLayout = new javax.swing.GroupLayout(jPanel_QLTacGia);
        jPanel_QLTacGia.setLayout(jPanel_QLTacGiaLayout);
        jPanel_QLTacGiaLayout.setHorizontalGroup(
            jPanel_QLTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLTacGiaLayout.createSequentialGroup()
                .addGroup(jPanel_QLTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_QLTacGiaLayout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_TimKiem_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_TimKiem_TacGia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewAll_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_QLTacGiaLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_QLTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel_QLTacGiaLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1601, Short.MAX_VALUE))
        );
        jPanel_QLTacGiaLayout.setVerticalGroup(
            jPanel_QLTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLTacGiaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel_QLTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_TimKiem_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_TimKiem_TacGia)
                    .addComponent(ViewAll_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_QLTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1035, Short.MAX_VALUE))
        );

        jPanel_Main.add(jPanel_QLTacGia, "card6");

        jPanel_QLThe.setBackground(new java.awt.Color(51, 153, 255));
        jPanel_QLThe.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel_QLTheHeader.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_QLTheHeader.setPreferredSize(new java.awt.Dimension(2153, 50));

        jButton_DangKyThe.setBackground(new java.awt.Color(242, 242, 242));
        jButton_DangKyThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_DangKyThe.setText("Đăng ký thẻ");
        jButton_DangKyThe.setPreferredSize(new java.awt.Dimension(133, 50));
        jButton_DangKyThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DangKyTheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_QLTheHeaderLayout = new javax.swing.GroupLayout(jPanel_QLTheHeader);
        jPanel_QLTheHeader.setLayout(jPanel_QLTheHeaderLayout);
        jPanel_QLTheHeaderLayout.setHorizontalGroup(
            jPanel_QLTheHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLTheHeaderLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jButton_DangKyThe, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2892, Short.MAX_VALUE))
        );
        jPanel_QLTheHeaderLayout.setVerticalGroup(
            jPanel_QLTheHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton_DangKyThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel_QLThe.add(jPanel_QLTheHeader, java.awt.BorderLayout.PAGE_START);

        jPanel_DSThe.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("Tìm kiếm thẻ");

        txt_TimKiemThe.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        txt_TimKiemThe.setText("Nhập thông tin tìm kiếm...");
        txt_TimKiemThe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TimKiemTheFocusGained(evt);
            }
        });

        btn_TimKiemThe.setBackground(new java.awt.Color(204, 255, 255));
        btn_TimKiemThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_TimKiemThe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btn_TimKiemThe.setText("Tìm kiếm");
        btn_TimKiemThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemTheActionPerformed(evt);
            }
        });

        btn_XemTatCaThe.setBackground(new java.awt.Color(204, 255, 255));
        btn_XemTatCaThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_XemTatCaThe.setText("Xem tất cả");
        btn_XemTatCaThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XemTatCaTheActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_DanhSachThe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_DanhSachThe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "<html><center style='font-size:16px'>Mã thẻ</html>", "<html><center style='font-size:16px'>Họ và tên</html>", "<html><center style='font-size:16px'>CCCD</html>", "<html><center style='font-size:16px'>Số điện thoại</html>", "<html><center style='font-size:16px'>Địa chỉ</html>", "<html><center style='font-size:16px'>Ngày đăng ký</html><center style='font-size:16px'>Ngày đăng ký</html>", "<html><center style='font-size:16px'>Ngày hết hạn</html>", "<html><center style='font-size:16px'>Trạng thái</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DanhSachThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachTheMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_DanhSachThe);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin cá nhân", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Họ và tên");

        txt_QLThe_name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Căn cước công dân");

        txt_QLThe_cccd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setText("Số điện thoại");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("Địa chỉ");

        txt_QLThe_sdt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btn_QLthe_Update.setBackground(new java.awt.Color(255, 255, 153));
        btn_QLthe_Update.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_QLthe_Update.setText("Cập nhật thông tin cá nhân");
        btn_QLthe_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLthe_UpdateActionPerformed(evt);
            }
        });

        txt_QLThe_DiaChi.setColumns(20);
        txt_QLThe_DiaChi.setRows(5);
        jScrollPane1.setViewportView(txt_QLThe_DiaChi);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_QLThe_name, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addComponent(txt_QLThe_cccd)
                                .addComponent(txt_QLThe_sdt))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(btn_QLthe_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_QLThe_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_QLThe_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txt_QLThe_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(btn_QLthe_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin thẻ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Mã thẻ");

        txt_GHT_idThe.setEditable(false);
        txt_GHT_idThe.setBackground(new java.awt.Color(255, 255, 255));
        txt_GHT_idThe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_GHT_trangThai.setEditable(false);
        txt_GHT_trangThai.setBackground(new java.awt.Color(255, 255, 255));
        txt_GHT_trangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("Trạng thái");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("Ngày đăng ký");

        txt_GHT_ngayDK.setEditable(false);
        txt_GHT_ngayDK.setBackground(new java.awt.Color(255, 255, 255));
        txt_GHT_ngayDK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setText("Ngày hết hạn");

        txt_GHT_ngayHH.setEditable(false);
        txt_GHT_ngayHH.setBackground(new java.awt.Color(255, 255, 255));
        txt_GHT_ngayHH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lb_PhiDuyTri.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_PhiDuyTri.setForeground(new java.awt.Color(255, 51, 51));
        lb_PhiDuyTri.setText("Phí duy trì: 100.000 đồng/6 tháng");

        btn_QLThe_giaHan.setBackground(new java.awt.Color(204, 255, 255));
        btn_QLThe_giaHan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_QLThe_giaHan.setForeground(new java.awt.Color(51, 153, 255));
        btn_QLThe_giaHan.setText("Gia hạn");
        btn_QLThe_giaHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLThe_giaHanActionPerformed(evt);
            }
        });

        btn_QLThe_khoaThe.setBackground(new java.awt.Color(255, 102, 102));
        btn_QLThe_khoaThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_QLThe_khoaThe.setForeground(new java.awt.Color(255, 255, 51));
        btn_QLThe_khoaThe.setText("Khóa thẻ");
        btn_QLThe_khoaThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLThe_khoaTheActionPerformed(evt);
            }
        });

        btn_QLThe_moKhoaThe.setBackground(new java.awt.Color(242, 242, 242));
        btn_QLThe_moKhoaThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_QLThe_moKhoaThe.setText("Mở khóa ");
        btn_QLThe_moKhoaThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLThe_moKhoaTheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(txt_GHT_idThe, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(txt_GHT_ngayDK, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_GHT_ngayHH)
                            .addComponent(txt_GHT_trangThai))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_PhiDuyTri)
                .addGap(166, 166, 166))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btn_QLThe_moKhoaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(btn_QLThe_khoaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btn_QLThe_giaHan, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txt_GHT_idThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txt_GHT_ngayDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txt_GHT_ngayHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txt_GHT_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(lb_PhiDuyTri)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_QLThe_giaHan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_QLThe_khoaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_QLThe_moKhoaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        cbb_TimKiemThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbb_TimKiemThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã thẻ", "Họ tên", "CCCD", "Số điện thoại" }));

        javax.swing.GroupLayout jPanel_DSTheLayout = new javax.swing.GroupLayout(jPanel_DSThe);
        jPanel_DSThe.setLayout(jPanel_DSTheLayout);
        jPanel_DSTheLayout.setHorizontalGroup(
            jPanel_DSTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DSTheLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel_DSTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_DSTheLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1608, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_DSTheLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbb_TimKiemThe, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TimKiemThe, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_TimKiemThe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_XemTatCaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1597, Short.MAX_VALUE))
        );
        jPanel_DSTheLayout.setVerticalGroup(
            jPanel_DSTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DSTheLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel_DSTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_TimKiemThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemThe, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XemTatCaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_TimKiemThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_DSTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1005, Short.MAX_VALUE))
        );

        jPanel_QLThe.add(jPanel_DSThe, java.awt.BorderLayout.CENTER);

        jPanel_Main.add(jPanel_QLThe, "card7");

        jPanel_QLMuon.setBackground(new java.awt.Color(51, 153, 255));
        jPanel_QLMuon.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel_QLMuonHeader.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_QLMuonHeader.setPreferredSize(new java.awt.Dimension(2259, 50));

        jButton_QLMuon_DanhSachMuon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLMuon_DanhSachMuon.setText("Danh sách phiếu mượn ");
        jButton_QLMuon_DanhSachMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLMuon_DanhSachMuonActionPerformed(evt);
            }
        });

        jButton_QLMuon_TVMuon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLMuon_TVMuon.setText("Thành viên mượn sách ");
        jButton_QLMuon_TVMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLMuon_TVMuonActionPerformed(evt);
            }
        });

        jButton_QLMuon_ChuaCoThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_QLMuon_ChuaCoThe.setText("Mượn sách chưa có thẻ");
        jButton_QLMuon_ChuaCoThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QLMuon_ChuaCoTheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_QLMuonHeaderLayout = new javax.swing.GroupLayout(jPanel_QLMuonHeader);
        jPanel_QLMuonHeader.setLayout(jPanel_QLMuonHeaderLayout);
        jPanel_QLMuonHeaderLayout.setHorizontalGroup(
            jPanel_QLMuonHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLMuonHeaderLayout.createSequentialGroup()
                .addComponent(jButton_QLMuon_DanhSachMuon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_QLMuon_TVMuon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_QLMuon_ChuaCoThe)
                .addGap(0, 2530, Short.MAX_VALUE))
        );
        jPanel_QLMuonHeaderLayout.setVerticalGroup(
            jPanel_QLMuonHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton_QLMuon_DanhSachMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(jButton_QLMuon_TVMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton_QLMuon_ChuaCoThe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_QLMuon.add(jPanel_QLMuonHeader, java.awt.BorderLayout.PAGE_START);

        jPanel_QLMuonTraMain.setLayout(new java.awt.CardLayout());

        jPanel_TVMuon.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setText("Mã sách");

        txt_TVMuon_idSach.setEditable(false);
        txt_TVMuon_idSach.setBackground(new java.awt.Color(255, 255, 255));
        txt_TVMuon_idSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setText("Tên sách");

        txt_TVMuon_TenSach.setEditable(false);
        txt_TVMuon_TenSach.setBackground(new java.awt.Color(255, 255, 255));
        txt_TVMuon_TenSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel41.setText("Số lượng");

        spn_TVMuon_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        spn_TVMuon_SoLuong.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 0));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 51, 51));
        jLabel42.setText("Tìm sách:");

        txt_TVMuon_TimKiemSach.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        txt_TVMuon_TimKiemSach.setText("Nhập mã sách để tìm...");
        txt_TVMuon_TimKiemSach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TVMuon_TimKiemSachFocusGained(evt);
            }
        });

        btn_TVMuon_TimSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_TVMuon_TimSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btn_TVMuon_TimSach.setText("Tìm kiếm");
        btn_TVMuon_TimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TVMuon_TimSachActionPerformed(evt);
            }
        });

        btn_TVMuon_ThemVaoPM.setBackground(new java.awt.Color(204, 255, 255));
        btn_TVMuon_ThemVaoPM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_TVMuon_ThemVaoPM.setText("Thêm vào PM");
        btn_TVMuon_ThemVaoPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TVMuon_ThemVaoPMActionPerformed(evt);
            }
        });

        btn_TVMuon_XoaKhoiPM.setBackground(new java.awt.Color(255, 102, 102));
        btn_TVMuon_XoaKhoiPM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_TVMuon_XoaKhoiPM.setText("Xóa khỏi PM");
        btn_TVMuon_XoaKhoiPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TVMuon_XoaKhoiPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_TVMuon_TenSach)
                    .addComponent(txt_TVMuon_TimKiemSach, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(txt_TVMuon_idSach)
                    .addComponent(spn_TVMuon_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_TVMuon_XoaKhoiPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_TVMuon_ThemVaoPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_TVMuon_TimSach, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txt_TVMuon_TimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txt_TVMuon_idSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TVMuon_ThemVaoPM))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btn_TVMuon_TimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txt_TVMuon_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TVMuon_XoaKhoiPM))
                .addGap(50, 50, 50)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(spn_TVMuon_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setText("Phí đặt cọc");

        txt_TVMuon_idThe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_TVMuon_idThe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_TVMuon_idTheFocusLost(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel44.setText("Mã thẻ");

        txt_TVMuon_PhiDatCoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_TVMuon_PhiDatCoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TVMuon_PhiDatCocFocusGained(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel45.setText("Ngày mượn");

        txt_TVMuon_NgayMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_TVMuon_HanTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_TVMuon_HanTra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TVMuon_HanTraFocusGained(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setText("Hạn trả");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel47.setText("Trạng thái");

        txt_TVMuon_TrangThai.setEditable(false);
        txt_TVMuon_TrangThai.setBackground(new java.awt.Color(255, 255, 255));
        txt_TVMuon_TrangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel44)
                    .addComponent(jLabel43)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addGap(77, 77, 77)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TVMuon_HanTra, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(txt_TVMuon_TrangThai)
                    .addComponent(txt_TVMuon_PhiDatCoc)
                    .addComponent(txt_TVMuon_NgayMuon)
                    .addComponent(txt_TVMuon_idThe))
                .addGap(60, 60, 60))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TVMuon_idThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(37, 37, 37)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TVMuon_PhiDatCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(37, 37, 37)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txt_TVMuon_NgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txt_TVMuon_HanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txt_TVMuon_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách đầu sách muốn mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_TVMuon_DSMuonMuon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_TVMuon_DSMuonMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><center style='font-size:16px'>Mã sách</html>", "<html><center style='font-size:16px'>Tên sách</html>", "<html><center style='font-size:16px'>Số lượng</html>", "<html><center style='font-size:16px'>Trạng thái</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_TVMuon_DSMuonMuon.setRowHeight(25);
        tbl_TVMuon_DSMuonMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TVMuon_DSMuonMuonMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_TVMuon_DSMuonMuon);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1581, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );

        btn_TVMuon_XacNhanMuon.setBackground(new java.awt.Color(204, 255, 255));
        btn_TVMuon_XacNhanMuon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_TVMuon_XacNhanMuon.setForeground(new java.awt.Color(51, 153, 255));
        btn_TVMuon_XacNhanMuon.setText("Xác nhận mượn sách");
        btn_TVMuon_XacNhanMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TVMuon_XacNhanMuonActionPerformed(evt);
            }
        });

        btn_TVMuon_CapNhatPM.setBackground(new java.awt.Color(255, 255, 204));
        btn_TVMuon_CapNhatPM.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_TVMuon_CapNhatPM.setForeground(new java.awt.Color(255, 153, 51));
        btn_TVMuon_CapNhatPM.setText("Cập nhật phiếu mượn");
        btn_TVMuon_CapNhatPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TVMuon_CapNhatPMActionPerformed(evt);
            }
        });

        btn_TVMuon_Clear.setBackground(new java.awt.Color(255, 204, 204));
        btn_TVMuon_Clear.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_TVMuon_Clear.setForeground(new java.awt.Color(204, 0, 51));
        btn_TVMuon_Clear.setText("Clear");
        btn_TVMuon_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TVMuon_ClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_TVMuonLayout = new javax.swing.GroupLayout(jPanel_TVMuon);
        jPanel_TVMuon.setLayout(jPanel_TVMuonLayout);
        jPanel_TVMuonLayout.setHorizontalGroup(
            jPanel_TVMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TVMuonLayout.createSequentialGroup()
                .addGroup(jPanel_TVMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_TVMuonLayout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(btn_TVMuon_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btn_TVMuon_XacNhanMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btn_TVMuon_CapNhatPM))
                    .addGroup(jPanel_TVMuonLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel_TVMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_TVMuonLayout.createSequentialGroup()
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(1602, Short.MAX_VALUE))
        );
        jPanel_TVMuonLayout.setVerticalGroup(
            jPanel_TVMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TVMuonLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_TVMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel_TVMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_TVMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_TVMuon_CapNhatPM, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_TVMuon_XacNhanMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_TVMuon_Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1048, Short.MAX_VALUE))
        );

        jPanel_QLMuonTraMain.add(jPanel_TVMuon, "card3");

        jPanel_ChuaCoThe.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel48.setText("Mã sách");

        txt_ChuaCoThe_idSach.setEditable(false);
        txt_ChuaCoThe_idSach.setBackground(new java.awt.Color(255, 255, 255));
        txt_ChuaCoThe_idSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel49.setText("Tên sách");

        txt_ChuaCoThe_TenSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel50.setText("Số lượng");

        spn_ChuaCoThe_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        spn_ChuaCoThe_SoLuong.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 0));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 51, 51));
        jLabel51.setText("Tìm sách:");

        txt_ChuaCoThe_TimSach.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        txt_ChuaCoThe_TimSach.setText("Nhập mã  sách để tìm kiếm...");
        txt_ChuaCoThe_TimSach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ChuaCoThe_TimSachFocusGained(evt);
            }
        });

        btn_ChuaCoThe_TimKiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_ChuaCoThe_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btn_ChuaCoThe_TimKiem.setText("Tìm kiếm");
        btn_ChuaCoThe_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChuaCoThe_TimKiemActionPerformed(evt);
            }
        });

        btn_ChuaCoThe_ThemVaoPM.setBackground(new java.awt.Color(204, 255, 255));
        btn_ChuaCoThe_ThemVaoPM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_ChuaCoThe_ThemVaoPM.setText("Thêm vào PM");
        btn_ChuaCoThe_ThemVaoPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChuaCoThe_ThemVaoPMActionPerformed(evt);
            }
        });

        btn_ChuaCoThe_XoaKhoiPM.setBackground(new java.awt.Color(255, 102, 102));
        btn_ChuaCoThe_XoaKhoiPM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_ChuaCoThe_XoaKhoiPM.setText("Xóa khỏi PM");
        btn_ChuaCoThe_XoaKhoiPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChuaCoThe_XoaKhoiPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ChuaCoThe_TimSach, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(txt_ChuaCoThe_TenSach)
                    .addComponent(txt_ChuaCoThe_idSach)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(spn_ChuaCoThe_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_ChuaCoThe_XoaKhoiPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ChuaCoThe_ThemVaoPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ChuaCoThe_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(txt_ChuaCoThe_TimSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(txt_ChuaCoThe_idSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ChuaCoThe_ThemVaoPM))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btn_ChuaCoThe_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txt_ChuaCoThe_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ChuaCoThe_XoaKhoiPM))
                .addGap(50, 50, 50)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(spn_ChuaCoThe_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel52.setText("Phí đặt cọc");

        txt_ChuaCoThe_idThe.setEditable(false);
        txt_ChuaCoThe_idThe.setBackground(new java.awt.Color(255, 255, 255));
        txt_ChuaCoThe_idThe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_ChuaCoThe_idThe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ChuaCoThe_idTheFocusGained(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel53.setText("Mã thẻ");

        txt_ChuaCoThe_PhiDatCoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_ChuaCoThe_PhiDatCoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ChuaCoThe_PhiDatCocFocusGained(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel54.setText("Ngày mượn");

        txt_ChuaCoThe_NgayMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_ChuaCoThe_HanTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel55.setText("Hạn trả");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel67.setText("Trạng thái");

        txt_ChuaCoThe_TrangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(jLabel53)
                    .addComponent(jLabel52)
                    .addComponent(jLabel55)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_ChuaCoThe_NgayMuon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(txt_ChuaCoThe_PhiDatCoc, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ChuaCoThe_idThe, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ChuaCoThe_HanTra)
                    .addComponent(txt_ChuaCoThe_TrangThai))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ChuaCoThe_idThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(43, 43, 43)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_ChuaCoThe_PhiDatCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(43, 43, 43)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txt_ChuaCoThe_NgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txt_ChuaCoThe_HanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(txt_ChuaCoThe_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách đầu sách muốn mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_ChuaCoThe_DSMuonMuon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_ChuaCoThe_DSMuonMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "<html><center style='font-size:16px'>Mã sách</html>", "<html><center style='font-size:16px'>Tên sách</html>", "<html><center style='font-size:16px'>Số lượng</html>", "<html><center style='font-size:16px'>Trạng thái</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_ChuaCoThe_DSMuonMuon.setRowHeight(25);
        tbl_ChuaCoThe_DSMuonMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChuaCoThe_DSMuonMuonMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbl_ChuaCoThe_DSMuonMuon);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 1599, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin người mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel56.setText("Họ tên");

        txt_ChuaCoThe_HoTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_ChuaCoThe_cccd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel57.setText("CCCD");

        txt_ChuaCoThe_sdt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel58.setText("Số điện thoại");

        txt_ChuaCoThe_DiaChi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel59.setText("Địa chỉ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_ChuaCoThe_HoTen))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ChuaCoThe_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(275, 275, 275))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_ChuaCoThe_sdt, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(txt_ChuaCoThe_cccd))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txt_ChuaCoThe_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txt_ChuaCoThe_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(txt_ChuaCoThe_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txt_ChuaCoThe_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        btn_ChuaCoThe_XacNhanMuon.setBackground(new java.awt.Color(204, 255, 255));
        btn_ChuaCoThe_XacNhanMuon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_ChuaCoThe_XacNhanMuon.setForeground(new java.awt.Color(51, 153, 255));
        btn_ChuaCoThe_XacNhanMuon.setText("Xác nhận mượn sách");
        btn_ChuaCoThe_XacNhanMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChuaCoThe_XacNhanMuonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ChuaCoTheLayout = new javax.swing.GroupLayout(jPanel_ChuaCoThe);
        jPanel_ChuaCoThe.setLayout(jPanel_ChuaCoTheLayout);
        jPanel_ChuaCoTheLayout.setHorizontalGroup(
            jPanel_ChuaCoTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChuaCoTheLayout.createSequentialGroup()
                .addGroup(jPanel_ChuaCoTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ChuaCoTheLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel_ChuaCoTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_ChuaCoTheLayout.createSequentialGroup()
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_ChuaCoTheLayout.createSequentialGroup()
                        .addGap(710, 710, 710)
                        .addComponent(btn_ChuaCoThe_XacNhanMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1598, Short.MAX_VALUE))
        );
        jPanel_ChuaCoTheLayout.setVerticalGroup(
            jPanel_ChuaCoTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChuaCoTheLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel_ChuaCoTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ChuaCoThe_XacNhanMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1033, Short.MAX_VALUE))
        );

        jPanel_QLMuonTraMain.add(jPanel_ChuaCoThe, "card4");

        jPanel_DanhSachMuon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 51, 51));
        jLabel38.setText("Tìm kiếm theo:");

        cbb_DSMuon_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbb_DSMuon_TimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu mượn", "Thẻ thành viên", "Ngày mượn", "Hạn trả", "Trạng thái" }));

        txt_DSMuon_TimKiem.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        txt_DSMuon_TimKiem.setText("Nhập thông tin tìm kiếm...");
        txt_DSMuon_TimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_DSMuon_TimKiemFocusGained(evt);
            }
        });

        btn_DSMuon_TimKiem.setBackground(new java.awt.Color(204, 255, 255));
        btn_DSMuon_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_DSMuon_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btn_DSMuon_TimKiem.setText("Tìm kiếm");
        btn_DSMuon_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DSMuon_TimKiemActionPerformed(evt);
            }
        });

        btn_DSMuon_XemTatCa.setBackground(new java.awt.Color(204, 255, 255));
        btn_DSMuon_XemTatCa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_DSMuon_XemTatCa.setText("Xem tất cả");
        btn_DSMuon_XemTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DSMuon_XemTatCaActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_DSMuon_DSPM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_DSMuon_DSPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "<html><center style='font-size:16px'>Mã phiếu mượn</html>", "<html><center style='font-size:16px'>Mã thẻ</html>", "<html><center style='font-size:16px'>Ngày mượn</html>", "<html><center style='font-size:16px'>Hạn trả</html>", "<html><center style='font-size:16px'>Tiền cọc</html>", "<html><center style='font-size:16px'>Tình trạng</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DSMuon_DSPM.setRowHeight(25);
        tbl_DSMuon_DSPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DSMuon_DSPMMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_DSMuon_DSPM);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Chi tiết phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_DSMuon_DSM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_DSMuon_DSM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "<html><center style='font-size:16px'>Mã sách</html>", "<html><center style='font-size:16px'>Tên sách</html>", "<html><center style='font-size:16px'>Số lượng</html>", "<html><center style='font-size:16px'>Trạng thái</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DSMuon_DSM.setRowHeight(25);
        jScrollPane9.setViewportView(tbl_DSMuon_DSM);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
        );

        btn_DSMuon_XuatPhieu.setBackground(new java.awt.Color(204, 255, 255));
        btn_DSMuon_XuatPhieu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_DSMuon_XuatPhieu.setForeground(new java.awt.Color(51, 153, 255));
        btn_DSMuon_XuatPhieu.setText("Xuất phiếu");

        btn_DSMuon_GiaHan.setBackground(new java.awt.Color(255, 204, 153));
        btn_DSMuon_GiaHan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_DSMuon_GiaHan.setForeground(new java.awt.Color(255, 51, 51));
        btn_DSMuon_GiaHan.setText("Gia hạn ");
        btn_DSMuon_GiaHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DSMuon_GiaHanActionPerformed(evt);
            }
        });

        btn_DSMuon_ChinhSuaPM.setBackground(new java.awt.Color(204, 255, 204));
        btn_DSMuon_ChinhSuaPM.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_DSMuon_ChinhSuaPM.setForeground(new java.awt.Color(0, 153, 51));
        btn_DSMuon_ChinhSuaPM.setText("Chỉnh sửa phiếu mượn");
        btn_DSMuon_ChinhSuaPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DSMuon_ChinhSuaPMActionPerformed(evt);
            }
        });

        btn_DSMuon_TraSach.setBackground(new java.awt.Color(255, 102, 102));
        btn_DSMuon_TraSach.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_DSMuon_TraSach.setForeground(new java.awt.Color(51, 51, 255));
        btn_DSMuon_TraSach.setText("Trả sách");
        btn_DSMuon_TraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DSMuon_TraSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_DanhSachMuonLayout = new javax.swing.GroupLayout(jPanel_DanhSachMuon);
        jPanel_DanhSachMuon.setLayout(jPanel_DanhSachMuonLayout);
        jPanel_DanhSachMuonLayout.setHorizontalGroup(
            jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DanhSachMuonLayout.createSequentialGroup()
                .addGroup(jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DanhSachMuonLayout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbb_DSMuon_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DSMuon_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_DSMuon_TimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_DSMuon_XemTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_DanhSachMuonLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 1622, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_DanhSachMuonLayout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(btn_DSMuon_XuatPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btn_DSMuon_GiaHan, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btn_DSMuon_ChinhSuaPM, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btn_DSMuon_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1591, Short.MAX_VALUE))
        );
        jPanel_DanhSachMuonLayout.setVerticalGroup(
            jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DanhSachMuonLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_DSMuon_XemTatCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(cbb_DSMuon_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_DSMuon_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_DSMuon_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_DanhSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_DSMuon_XuatPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_DSMuon_GiaHan, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_DSMuon_ChinhSuaPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_DSMuon_TraSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel_QLMuonTraMain.add(jPanel_DanhSachMuon, "card2");

        jPanel_QLMuon.add(jPanel_QLMuonTraMain, java.awt.BorderLayout.CENTER);

        jPanel_Main.add(jPanel_QLMuon, "card8");

        jPanel_ThongKevaBaoCao.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ThongKevaBaoCao.setPreferredSize(new java.awt.Dimension(1800, 1100));
        jPanel_ThongKevaBaoCao.setLayout(new java.awt.BorderLayout());

        jPanel23.setPreferredSize(new java.awt.Dimension(2358, 64));

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("THỐNG KÊ");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 3230, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );

        jPanel_ThongKevaBaoCao.add(jPanel23, java.awt.BorderLayout.PAGE_START);

        jPanel24.setPreferredSize(new java.awt.Dimension(2358, 50));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3230, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel_ThongKevaBaoCao.add(jPanel24, java.awt.BorderLayout.PAGE_END);

        jPanel27.setLayout(new java.awt.GridLayout(2, 1));

        pn_ThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pn_ThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        lb_HienThi.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_HienThi.setForeground(new java.awt.Color(51, 153, 255));
        lb_HienThi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_HienThi.setText("Bảng dữ liệu");

        tbl_BangDuLieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_BangDuLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_BangDuLieu.setRowHeight(25);
        jScrollPane13.setViewportView(tbl_BangDuLieu);

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 153, 255));
        jLabel66.setText("Thống kê:");

        cbb_ThongKe_LuaChon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbb_ThongKe_LuaChon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sách", "Đọc giả", "Mượn trả" }));
        cbb_ThongKe_LuaChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_ThongKe_LuaChonActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel69.setText("Tổng số lượng sách:");

        txt_SoLuongSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_SoLuongSach.setForeground(new java.awt.Color(255, 0, 0));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel70.setText("Tháng:");

        cbb_Thang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_Thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel75.setText("Năm:");

        cbb_Nam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_Nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" }));

        btn_ThongKe.setBackground(new java.awt.Color(204, 255, 255));
        btn_ThongKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_ThongKe.setForeground(new java.awt.Color(255, 0, 51));
        btn_ThongKe.setText("Thống kê");
        btn_ThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThongKeActionPerformed(evt);
            }
        });

        cbb_CacLuaChon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_SoDocGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_SoDocGia.setForeground(new java.awt.Color(255, 0, 0));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel72.setText("Tổng số đọc giả:");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel73.setText("Tổng số phiếu mượn:");

        txt_SoPhieuMuon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_SoPhieuMuon.setForeground(new java.awt.Color(255, 0, 0));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel74.setText("Tổng số phiếu trả:");

        txt_SoPhieuTra.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_SoPhieuTra.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pn_ThongKeLayout = new javax.swing.GroupLayout(pn_ThongKe);
        pn_ThongKe.setLayout(pn_ThongKeLayout);
        pn_ThongKeLayout.setHorizontalGroup(
            pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ThongKeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_ThongKeLayout.createSequentialGroup()
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_SoPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_ThongKeLayout.createSequentialGroup()
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbb_CacLuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_ThongKe_LuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(btn_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_ThongKeLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbb_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbb_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(156, 156, 156)
                .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addComponent(lb_HienThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1608, Short.MAX_VALUE))
        );
        pn_ThongKeLayout.setVerticalGroup(
            pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ThongKeLayout.createSequentialGroup()
                .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_ThongKeLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(cbb_ThongKe_LuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_CacLuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(cbb_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75)
                            .addComponent(cbb_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69)
                            .addComponent(txt_SoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(txt_SoPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(txt_SoDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(pn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_SoPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ThongKeLayout.createSequentialGroup()
                        .addComponent(lb_HienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(527, Short.MAX_VALUE))
        );

        jPanel27.add(pn_ThongKe);

        pn_BieuDoTron.setBackground(new java.awt.Color(255, 255, 255));
        pn_BieuDoTron.setLayout(new java.awt.GridLayout(1, 4, 4, 0));

        javax.swing.GroupLayout pn_BieuDo1Layout = new javax.swing.GroupLayout(pn_BieuDo1);
        pn_BieuDo1.setLayout(pn_BieuDo1Layout);
        pn_BieuDo1Layout.setHorizontalGroup(
            pn_BieuDo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        pn_BieuDo1Layout.setVerticalGroup(
            pn_BieuDo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
        );

        pn_BieuDoTron.add(pn_BieuDo1);

        javax.swing.GroupLayout pn_BieuDo2Layout = new javax.swing.GroupLayout(pn_BieuDo2);
        pn_BieuDo2.setLayout(pn_BieuDo2Layout);
        pn_BieuDo2Layout.setHorizontalGroup(
            pn_BieuDo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        pn_BieuDo2Layout.setVerticalGroup(
            pn_BieuDo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
        );

        pn_BieuDoTron.add(pn_BieuDo2);

        javax.swing.GroupLayout pn_BieuDo3Layout = new javax.swing.GroupLayout(pn_BieuDo3);
        pn_BieuDo3.setLayout(pn_BieuDo3Layout);
        pn_BieuDo3Layout.setHorizontalGroup(
            pn_BieuDo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        pn_BieuDo3Layout.setVerticalGroup(
            pn_BieuDo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
        );

        pn_BieuDoTron.add(pn_BieuDo3);

        javax.swing.GroupLayout pn_BieuDo4Layout = new javax.swing.GroupLayout(pn_BieuDo4);
        pn_BieuDo4.setLayout(pn_BieuDo4Layout);
        pn_BieuDo4Layout.setHorizontalGroup(
            pn_BieuDo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        pn_BieuDo4Layout.setVerticalGroup(
            pn_BieuDo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
        );

        pn_BieuDoTron.add(pn_BieuDo4);

        jPanel27.add(pn_BieuDoTron);

        jPanel_ThongKevaBaoCao.add(jPanel27, java.awt.BorderLayout.CENTER);

        jPanel_Main.add(jPanel_ThongKevaBaoCao, "card10");

        jPanel_QLTra.setBackground(new java.awt.Color(255, 255, 255));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 51, 51));
        jLabel68.setText("Tìm kiếm theo");

        cbb_DSTra_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbb_DSTra_TimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu trả", "Mã phiếu mượn", "Mã thẻ", "Ngày trả" }));

        txt_DSTra_TimKiem.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        txt_DSTra_TimKiem.setText("Nhập thông tin tìm kiếm...");
        txt_DSTra_TimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_DSTra_TimKiemFocusGained(evt);
            }
        });

        btn_DSTra_TimKiem.setBackground(new java.awt.Color(204, 255, 255));
        btn_DSTra_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_DSTra_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btn_DSTra_TimKiem.setText("Tìm kiếm");
        btn_DSTra_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DSTra_TimKiemActionPerformed(evt);
            }
        });

        btn_DSTra_XemTatCa.setBackground(new java.awt.Color(204, 255, 255));
        btn_DSTra_XemTatCa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_DSTra_XemTatCa.setText("Xem tất cả");
        btn_DSTra_XemTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DSTra_XemTatCaActionPerformed(evt);
            }
        });

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_DSTra_DSPT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_DSTra_DSPT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "<html><center style='font-size:16px'>Mã phiếu trả</html>", "<html><center style='font-size:16px'>Mã phiếu mượn</html>", "<html><center style='font-size:16px'>Mã thẻ</html>", "<html><center style='font-size:16px'>Ngày mượn</html>", "<html><center style='font-size:16px'>Ngày trả</html>", "<html><center style='font-size:16px'>Phí đền bù</html>", "<html><center style='font-size:16px'>Phí trễ hạn</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DSTra_DSPT.setRowHeight(25);
        tbl_DSTra_DSPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DSTra_DSPTMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tbl_DSTra_DSPT);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 1613, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Chi tiết phiếu trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_DSTra_DST.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_DSTra_DST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "<html><center style='font-size:16px'>Mã sách</html>", "<html><center style='font-size:16px'>Tên sách</html>", "<html><center style='font-size:16px'>Số lượng</html>", "<html><center style='font-size:16px'>Tình trạng sách</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DSTra_DST.setRowHeight(25);
        jScrollPane15.setViewportView(tbl_DSTra_DST);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btn_QLTra_ChinhSua.setBackground(new java.awt.Color(204, 255, 255));
        btn_QLTra_ChinhSua.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_QLTra_ChinhSua.setForeground(new java.awt.Color(51, 153, 255));
        btn_QLTra_ChinhSua.setText("Chỉnh sửa phiếu trả");
        btn_QLTra_ChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLTra_ChinhSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_QLTraLayout = new javax.swing.GroupLayout(jPanel_QLTra);
        jPanel_QLTra.setLayout(jPanel_QLTraLayout);
        jPanel_QLTraLayout.setHorizontalGroup(
            jPanel_QLTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLTraLayout.createSequentialGroup()
                .addGroup(jPanel_QLTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_QLTraLayout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbb_DSTra_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DSTra_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_DSTra_TimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_DSTra_XemTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_QLTraLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_QLTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_QLTraLayout.createSequentialGroup()
                        .addGap(637, 637, 637)
                        .addComponent(btn_QLTra_ChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1587, Short.MAX_VALUE))
        );
        jPanel_QLTraLayout.setVerticalGroup(
            jPanel_QLTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLTraLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_QLTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_DSTra_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_DSTra_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DSTra_TimKiem)
                    .addComponent(btn_DSTra_XemTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_QLTra_ChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(964, Short.MAX_VALUE))
        );

        jPanel_Main.add(jPanel_QLTra, "card10");

        getContentPane().add(jPanel_Main, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_TimKiem_TheLoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_TimKiem_TheLoaiFocusGained
        jTextField_TimKiem_TheLoai.setText("");
    }//GEN-LAST:event_jTextField_TimKiem_TheLoaiFocusGained

    private void jTextField_TimKiem_NXBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_TimKiem_NXBFocusGained
        jTextField_TimKiem_NXB.setText("");
    }//GEN-LAST:event_jTextField_TimKiem_NXBFocusGained

    private void jTextField_TimKiem_TacGiaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_TimKiem_TacGiaFocusGained
        jTextField_TimKiem_TacGia.setText("");
    }//GEN-LAST:event_jTextField_TimKiem_TacGiaFocusGained

    private void jButton_QLTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_QLTraActionPerformed
        cardLayout1.show(jPanel_Main, "QuanLyTra");
        try {
            ptBUS = new PhieuTraBUS();
            ctptBUS = new CT_PhieuTraBUS();
            ArrayList<PhieuTraDTO> dsPhieuTra = ptBUS.getAllPhieuTra();
            displayDataPhieuTra(dsPhieuTra);
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_QLTraActionPerformed

    private void displayDataPhieuTra(ArrayList<PhieuTraDTO> dsPhieuTra) {
        dsPT = (DefaultTableModel) tbl_DSTra_DST.getModel();
        dsPT.setRowCount(0);
        for (PhieuTraDTO phieu : dsPhieuTra) {
            Object[] row = {
                phieu.getId(),
                phieu.getMaPhieuMuon(),
                phieu.getMaThe(),
                phieu.getNgayMuon(),
                phieu.getNgayTra(),
                phieu.getPhiDenBu(),
                phieu.getPhiTreHan()
            };
            dsPT.addRow(row);
        }
    }
    private void jButton_TimKiem_TacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimKiem_TacGiaActionPerformed
        try {
            tgBUS = new TacGiaBUS();
            ArrayList<TacGiaDTO> listTacGiaSearch = tgBUS.searchTacGiaByName(jTextField_TimKiem_TacGia.getText());
            DefaultTableModel model = (DefaultTableModel) jTable_TacGia.getModel();
            model.setRowCount(0);
            for (TacGiaDTO tg : listTacGiaSearch) {
                Object[] row = {
                    tg.getId(),
                    tg.getTen(),
                    tg.getSoLuongSach()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_TimKiem_TacGiaActionPerformed

    private void ViewAll_TacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewAll_TacGiaActionPerformed
        try {
            tgBUS = new TacGiaBUS();
            ArrayList<TacGiaDTO> listTacGia = tgBUS.getAllTacGia();
            DefaultTableModel model = (DefaultTableModel) jTable_TacGia.getModel();
            model.setRowCount(0);
            for (TacGiaDTO tg : listTacGia) {
                Object[] row = {
                    tg.getId(),
                    tg.getTen(),
                    tg.getSoLuongSach()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ViewAll_TacGiaActionPerformed

    private void jButton_CapNhatTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CapNhatTacGiaActionPerformed
        try {
            tgBUS = new TacGiaBUS();
            TacGiaDTO tg = new TacGiaDTO();
            tg.setId(jTextField_IDTacGia.getText());
            tg.setTen(jTextField_TenTacGia.getText());
            tg.setId(SLCL_TacGia1.getText());
            JOptionPane.showMessageDialog(null, tgBUS.updateTacGia(tg), "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_CapNhatTacGiaActionPerformed

    private void jButton_ThemTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemTacGiaActionPerformed
        try {
            tgBUS = new TacGiaBUS();
            TacGiaDTO tg = new TacGiaDTO();
            tg.setId(jTextField_IDTacGia1.getText());
            tg.setTen(jTextField_TenTacGia1.getText());
            JOptionPane.showMessageDialog(null, tgBUS.addTacGia(tg), "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_ThemTacGiaActionPerformed

    private void jTable_TacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_TacGiaMouseClicked
        int selectedRow = jTable_TacGia.getSelectedRow();
        if (selectedRow != -1) {
            jTextField_IDTacGia.setText(jTable_TacGia.getValueAt(selectedRow, 0).toString());
            jTextField_TenTacGia.setText(jTable_TacGia.getValueAt(selectedRow, 1).toString());
            SLCL_TacGia1.setText(jTable_TacGia.getValueAt(selectedRow, 2).toString());
        }
    }//GEN-LAST:event_jTable_TacGiaMouseClicked

    private void jButton_TimKiem_NXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimKiem_NXBActionPerformed
        try {
            nxbBUS = new NXBBUS();
            ArrayList<NXBDTO> listNXBSearch = nxbBUS.searchNXBByName(jTextField_TimKiem_NXB.getText());
            DefaultTableModel model = (DefaultTableModel) jTable_NXB.getModel();
            model.setRowCount(0);
            for (NXBDTO nxb : listNXBSearch) {
                Object[] row = {
                    nxb.getId(),
                    nxb.getTen(),
                    nxb.getSoLuongSach()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_TimKiem_NXBActionPerformed

    private void ViewAll_NXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewAll_NXBActionPerformed
        try {
            nxbBUS = new NXBBUS();
            ArrayList<NXBDTO> listNXB = nxbBUS.getAllNXB();
            DefaultTableModel model = (DefaultTableModel) jTable_NXB.getModel();
            model.setRowCount(0);
            for (NXBDTO nxb : listNXB) {
                Object[] row = {
                    nxb.getId(),
                    nxb.getTen(),
                    nxb.getSoLuongSach()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ViewAll_NXBActionPerformed

    private void jTable_NXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_NXBMouseClicked
        int selectedRow = jTable_NXB.getSelectedRow();

        if (selectedRow != -1) {
            jTextField_IDNXB.setText(jTable_NXB.getValueAt(selectedRow, 0).toString());
            jTextField_TenNXB.setText(jTable_NXB.getValueAt(selectedRow, 1).toString());
            SLCL_NXB1.setText(jTable_NXB.getValueAt(selectedRow, 2).toString());
        }
    }//GEN-LAST:event_jTable_NXBMouseClicked

    private void jButton_CapNhatNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CapNhatNXBActionPerformed
        try {
            nxbBUS = new NXBBUS();
            NXBDTO nxb = new NXBDTO();
            nxb.setId(jTextField_IDLoaiSach.getText());
            nxb.setTen(jTextField_TenLoaiSach.getText());
            nxb.setId(SLCL_NXB1.getText());
            JOptionPane.showMessageDialog(null, nxbBUS.updateNXB(nxb), "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_CapNhatNXBActionPerformed

    private void jButton_ThemNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemNXBActionPerformed
        try {
            nxbBUS = new NXBBUS();
            NXBDTO nxb = new NXBDTO();
            nxb.setId(jTextField39.getText());
            nxb.setTen(jTextField40.getText());
            JOptionPane.showMessageDialog(null, nxbBUS.addNXB(nxb), "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_ThemNXBActionPerformed

    private void jButton_TimKiem_TheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimKiem_TheLoaiActionPerformed
        try {
            tlBUS = new TheLoaiBUS();
            ArrayList<TheLoaiDTO> listTheLoaiSearch = tlBUS.searchTheLoaiByName(jTextField_TimKiem_TheLoai.getText());
            DefaultTableModel model = (DefaultTableModel) jTable_TheLoai.getModel();
            model.setRowCount(0);
            for (TheLoaiDTO theLoai : listTheLoaiSearch) {
                Object[] row = {
                    theLoai.getId(),
                    theLoai.getTen(),
                    theLoai.getSoLuong()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_TimKiem_TheLoaiActionPerformed

    private void ViewAll_TheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewAll_TheLoaiActionPerformed
        try {
            tlBUS = new TheLoaiBUS();
            ArrayList<TheLoaiDTO> listTheLoaiSearch = tlBUS.getAllTheLoai();
            DefaultTableModel model = (DefaultTableModel) jTable_TheLoai.getModel();
            model.setRowCount(0);
            for (TheLoaiDTO theLoai : listTheLoaiSearch) {
                Object[] row = {
                    theLoai.getId(),
                    theLoai.getTen(),
                    theLoai.getSoLuong()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ViewAll_TheLoaiActionPerformed

    private void jButton_CapNhatTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CapNhatTLActionPerformed
        try {
            tlBUS = new TheLoaiBUS();
            TheLoaiDTO tlDTO = new TheLoaiDTO();
            tlDTO.setId(jTextField_IDLoaiSach.getText());
            tlDTO.setTen(jTextField_TenLoaiSach.getText());
            tlDTO.setId(jTextField38.getText());
            JOptionPane.showMessageDialog(null, tlBUS.updateTheLoai(tlDTO), "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }    }//GEN-LAST:event_jButton_CapNhatTLActionPerformed

    private void jTable_TheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_TheLoaiMouseClicked
        int selectedRow = jTable_TheLoai.getSelectedRow();
        if (selectedRow != -1) {
            jTextField_IDLoaiSach.setText(jTable_TheLoai.getValueAt(selectedRow, 0).toString());
            jTextField_TenLoaiSach.setText(jTable_TheLoai.getValueAt(selectedRow, 1).toString());
            jTextField38.setText(jTable_TheLoai.getValueAt(selectedRow, 2).toString());
        }
    }//GEN-LAST:event_jTable_TheLoaiMouseClicked

    private void jButton_ThemTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemTheLoaiActionPerformed
        try {
            tlBUS = new TheLoaiBUS();
            TheLoaiDTO tlDTO = new TheLoaiDTO();
            tlDTO.setId(jTextField36.getText());
            tlDTO.setTen(jTextField37.getText());
            System.out.println(tlDTO.getId());
            JOptionPane.showMessageDialog(null, tlBUS.addTheLoai(tlDTO), "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_ThemTheLoaiActionPerformed

    private void txt_ChuaCoThe_idTheFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ChuaCoThe_idTheFocusGained
        String cccd = txt_ChuaCoThe_cccd.getText();
        String sdt = txt_ChuaCoThe_sdt.getText();
        String idThe = "TV" + cccd.substring(cccd.length() - 4) + sdt.substring(sdt.length() - 4);
        txt_ChuaCoThe_idThe.setText(idThe);
    }//GEN-LAST:event_txt_ChuaCoThe_idTheFocusGained

    private void btn_DSMuon_ChinhSuaPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DSMuon_ChinhSuaPMActionPerformed
        int index = tbl_DSMuon_DSPM.getSelectedRow();
        int quantityRow = tbl_DSMuon_DSM.getRowCount();
        if (index != -1) {
            cardLayout2.show(jPanel_QLMuonTraMain, "ThanhVienMuonSach");
            jButton_QLMuon_TVMuon.setText("Chỉnh sửa phiếu mượn");
            btn_TVMuon_XacNhanMuon.setEnabled(false);
            txt_TVMuon_idThe.setText(tbl_DSMuon_DSPM.getValueAt(index, 1).toString());
            txt_TVMuon_NgayMuon.setText(tbl_DSMuon_DSPM.getValueAt(index, 2).toString());
            txt_TVMuon_HanTra.setText(tbl_DSMuon_DSPM.getValueAt(index, 3).toString());
            txt_TVMuon_PhiDatCoc.setText(tbl_DSMuon_DSPM.getValueAt(index, 4).toString());
            txt_TVMuon_TrangThai.setText(tbl_DSMuon_DSPM.getValueAt(index, 5).toString());

            DefaultTableModel dsSachMuon = (DefaultTableModel) tbl_TVMuon_DSMuonMuon.getModel();
            dsSachMuon.setRowCount(0);
            for (int i = 0; i < quantityRow; i++) {
                Object[] row = {
                    tbl_DSMuon_DSM.getValueAt(i, 0).toString(),
                    tbl_DSMuon_DSM.getValueAt(i, 1).toString(),
                    tbl_DSMuon_DSM.getValueAt(i, 2).toString(),
                    tbl_DSMuon_DSM.getValueAt(i, 3).toString()
                };
                dsSachMuon.addRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn phiếu mượn muốn chỉnh sửa!");
        }

    }//GEN-LAST:event_btn_DSMuon_ChinhSuaPMActionPerformed

    private void btn_DSMuon_TraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DSMuon_TraSachActionPerformed
        int selectedRow = tbl_DSMuon_DSPM.getSelectedRow();
        String maPM = tbl_DSMuon_DSPM.getValueAt(selectedRow, 0).toString();
        String mathe = tbl_DSMuon_DSPM.getValueAt(selectedRow, 1).toString();
        String tienCoc = tbl_DSMuon_DSPM.getValueAt(selectedRow, 4).toString();
        String ngayMuon = tbl_DSMuon_DSPM.getValueAt(selectedRow, 2).toString();
        String hanTra = tbl_DSMuon_DSPM.getValueAt(selectedRow, 3).toString();
        if (selectedRow != -1) {
            TraSachGUI traSachGUI;
            try {
                traSachGUI = new TraSachGUI(maPM, mathe, tienCoc, ngayMuon, hanTra);
                traSachGUI.setVisible(true);
                traSachGUI.setLocationRelativeTo(null);
            } catch (SQLException ex) {
                Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn phiếu mượn trước khi nhấn trả sách!");
        }

    }//GEN-LAST:event_btn_DSMuon_TraSachActionPerformed

    private void btn_DSMuon_GiaHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DSMuon_GiaHanActionPerformed
        int selectedRow = tbl_DSMuon_DSPM.getSelectedRow();
        String trangThai = tbl_DSMuon_DSPM.getValueAt(selectedRow, 5).toString();
        String hanTra = tbl_DSMuon_DSPM.getValueAt(selectedRow, 3).toString();
        PhieuMuonDTO pm = pmBUS.searchPhieuMuonByMaPhieuMuon(tbl_DSMuon_DSPM.getValueAt(selectedRow, 0).toString());
        pm.setSoLanGiaHan(pm.getSoLanGiaHan() + 1);
        if (!trangThai.equals("Đã trả") && pm.getSoLanGiaHan() <= 2) {
            Date date = null;
            try {
                date = formatter.parse(hanTra);
            } catch (ParseException ex) {
                Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            Date hantra = calendar.getTime();
            pm.setHanTra((java.sql.Date) hantra);
            pm.setTrangThai("Đang mượn");
            if (pmBUS.updateTrangThaiPhieuMuon(pm)) {
                dsPM.setValueAt(hantra, selectedRow, 3);
                dsPM.fireTableCellUpdated(selectedRow, 3);
                dsPM.setValueAt("Đang mượn", selectedRow, 5);
                dsPM.fireTableCellUpdated(selectedRow, 5);
                JOptionPane.showMessageDialog(rootPane, "Gia hạn phiếu mượn thành công đến ngày: " + hantra.toString());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Không hợp lệ do bạn đã trả sách hoặc gia hạn đã đủ 2 lần");
        }
    }//GEN-LAST:event_btn_DSMuon_GiaHanActionPerformed

    private void txt_TVMuon_HanTraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TVMuon_HanTraFocusGained
        String ngayMuon = txt_TVMuon_NgayMuon.getText();
        Date temp = null;
        try {
            temp = formatter.parse(ngayMuon);
        } catch (ParseException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(temp);
        calendar.add(Calendar.DAY_OF_MONTH, 21);
        String hanTra = formatter.format(calendar.getTime());
        txt_TVMuon_HanTra.setText(hanTra);
    }//GEN-LAST:event_txt_TVMuon_HanTraFocusGained

    private void txt_ChuaCoThe_PhiDatCocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ChuaCoThe_PhiDatCocFocusGained
        int column = 2;
        double sum = 0;
        for (int row = 0; row < ChuaCoThe_DSMuonMuon.getRowCount(); row++) {
            Object value = ChuaCoThe_DSMuonMuon.getValueAt(row, column);
            if (value instanceof Number number) {
                sum += number.doubleValue();
            }
            txt_ChuaCoThe_PhiDatCoc.setText(sum + "");
        }
    }//GEN-LAST:event_txt_ChuaCoThe_PhiDatCocFocusGained

    private void jTextFieldSachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSachFocusGained
        jTextFieldSach.setText("");
    }//GEN-LAST:event_jTextFieldSachFocusGained

    private void jButtonTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimSachActionPerformed
        String keyword = jTextFieldSach.getText();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập thông tin tìm kiếm!");
        } else {
            int searchField = jComboBoxSach.getSelectedIndex();
            ArrayList<SachDTO> listSach = new ArrayList<>();
            DefaultTableModel model = (DefaultTableModel) jTableSach.getModel();
            model.setRowCount(0);
            switch (searchField) {
                case 1 -> {
                    listSach = sachBUS.getSachByName(keyword);
                }
                case 2 -> {
                    listSach = sachBUS.getSachByTheLoai(keyword);
                }
                case 3 -> {
                    listSach = sachBUS.getSachByTacGia(keyword);
                }
                case 4 -> {
                    listSach = sachBUS.getSachByNXB(keyword);
                }
                default -> {
                    SachDTO sach = sachBUS.getSachById(keyword);
                    listSach.add(sach);
                }
            }
            for (SachDTO sach : listSach) {
                TheLoaiDTO tl = tlBUS.getTheLoaiById(sach.getTheloai());
                TacGiaDTO tg = tgBUS.getTacGiaById(sach.getTacGia());
                NXBDTO nxb = nxbBUS.getNXBById(sach.getNXB());
                model.addRow(new Object[]{
                    sach.getId(),
                    sach.getTenSach(),
                    tl.getTen(),
                    tg.getTen(),
                    nxb.getTen(),
                    sach.getNamXB(),
                    sach.getSoLuong(),
                    sach.getGiaSach()
                });
            }
        }
    }//GEN-LAST:event_jButtonTimSachActionPerformed

    private void jButtonShowAllSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowAllSachActionPerformed
        try {
            loadDataBookToTable();
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonShowAllSachActionPerformed

    private void jButtonCapNhatSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCapNhatSachActionPerformed
        int selectedRow = jTableSach.getSelectedRow();
        if (selectedRow >= 0) {
            String maSach = jTextFieldMaSach.getText();
            String tenSach = jTextFieldTenSach.getText();
            String namXuatBan = jTextFieldNamXuatBan.getText();
            String theLoai = (String) jComboBoxTheLoai.getSelectedItem();
            String tacGia = (String) jComboBoxTacGia.getSelectedItem();
            String nhaXuatBan = (String) jComboBoxNhaXuatBan.getSelectedItem();
            String soLuong = jTextFieldSoLuong.getText();
            Double giaSach = Double.valueOf(jTextFieldGiaSach.getText());
            // Cập nhật thông tin vào bảng (JTable)
            jTableSach.setValueAt(maSach, selectedRow, 0);
            jTableSach.setValueAt(tenSach, selectedRow, 1);
            jTableSach.setValueAt(theLoai, selectedRow, 2);
            jTableSach.setValueAt(tacGia, selectedRow, 3);
            jTableSach.setValueAt(nhaXuatBan, selectedRow, 4);
            jTableSach.setValueAt(namXuatBan, selectedRow, 5);
            jTableSach.setValueAt(soLuong, selectedRow, 6);
            jTableSach.setValueAt(giaSach, selectedRow, 7);
            String theLoaiId = null;
            String nhaXuatBanId = null;
            String tacGiaId = null;
            // Lấy id của thể loại
            try {
                theLoaiId = sachBUS.getIdByTenTheLoai(theLoai);
                tacGiaId = sachBUS.getIdByTenTacGia(tacGia);
                nhaXuatBanId = sachBUS.getIdByTenNhaXuatBan(nhaXuatBan);
            } catch (SQLException ex) {
                Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            SachDTO sach = new SachDTO(maSach, tenSach, theLoaiId, tacGiaId, nhaXuatBanId, Integer.parseInt(namXuatBan), Integer.parseInt(soLuong), giaSach);
            JOptionPane.showMessageDialog(rootPane, sachBUS.updateSach(sach));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn sách để cập nhật.");
        }


    }//GEN-LAST:event_jButtonCapNhatSachActionPerformed

    private void jTableSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSachMouseClicked
        int selectedRow = jTableSach.getSelectedRow();
        String maSach = jTableSach.getValueAt(selectedRow, 0).toString();
        String tenSach = jTableSach.getValueAt(selectedRow, 1).toString();
        String theLoai = jTableSach.getValueAt(selectedRow, 2).toString();
        String tacGia = jTableSach.getValueAt(selectedRow, 3).toString();
        String nhaXuatBan = jTableSach.getValueAt(selectedRow, 4).toString();
        String namXuatBan = jTableSach.getValueAt(selectedRow, 5).toString();
        int soLuong = Integer.parseInt(jTableSach.getValueAt(selectedRow, 6).toString());
        double gia = Double.parseDouble(jTableSach.getValueAt(selectedRow, 7).toString());
        jTextFieldMaSach.setText(maSach);
        jTextFieldTenSach.setText(tenSach);
        jTextFieldNamXuatBan.setText(namXuatBan);
        jTextFieldSoLuong.setText(soLuong + "");
        jTextFieldGiaSach.setText(gia + "");
        jComboBoxTheLoai.setSelectedItem(theLoai);
        jComboBoxTacGia.setSelectedItem(tacGia);
        jComboBoxNhaXuatBan.setSelectedItem(nhaXuatBan);
    }//GEN-LAST:event_jTableSachMouseClicked

    private void jButtonThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemSachActionPerformed
        ThemSachGUI themSachGUI = null;
        try {
            themSachGUI = new ThemSachGUI();
            themSachGUI.setVisible(true);
            themSachGUI.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadDataBookToTable();
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonThemSachActionPerformed

    private void txt_DSTra_TimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_DSTra_TimKiemFocusGained
        if (txt_DSTra_TimKiem.getText().equals("Nhập thông tin tìm kiếm...")) {
            txt_DSTra_TimKiem.setText("");
        }
    }//GEN-LAST:event_txt_DSTra_TimKiemFocusGained

    private void btn_DSTra_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DSTra_TimKiemActionPerformed
        String searchText = txt_DSTra_TimKiem.getText();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập thông tin trước khi bạn muốn tìm kiếm!");
        } else {
            ArrayList<PhieuTraDTO> dsPhieuTra = new ArrayList<>();
            int indexSearch = cbb_DSTra_TimKiem.getSelectedIndex();
            switch (indexSearch) {
                case 1 -> {
                    dsPhieuTra = ptBUS.searchPhieuTraByMaPhieuMuon(searchText);
                }
                case 2 -> {
                    dsPhieuTra = ptBUS.searchPhieuTraByMaThe(searchText);
                }
                case 3 -> {
                    Date ngayTra = null;
                    try {
                        ngayTra = formatter.parse(searchText);
                    } catch (ParseException e) {
                        Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, e);
                    }
                    java.sql.Date ngaytra = new java.sql.Date(ngayTra.getTime());
                    dsPhieuTra = ptBUS.searchPhieuTraByNgayTra(ngaytra);
                }
                default -> {
                    dsPhieuTra = ptBUS.getPhieuTraByID(searchText);
                }
            }
            displayDataPhieuTra(dsPhieuTra);
        }
    }//GEN-LAST:event_btn_DSTra_TimKiemActionPerformed

    private void btn_DSTra_XemTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DSTra_XemTatCaActionPerformed
        displayDataPhieuTra(ptBUS.getAllPhieuTra());
    }//GEN-LAST:event_btn_DSTra_XemTatCaActionPerformed

    private void tbl_DSTra_DSPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DSTra_DSPTMouseClicked
        int selectedRow = tbl_DSTra_DSPT.getSelectedRow();
        String maPT = tbl_DSTra_DSPT.getValueAt(selectedRow, 0).toString();
        ArrayList<CT_PhieuTraDTO> dsCT_PhieuTra = ctptBUS.searchCT_PhieuTraByMaPhieuTra(maPT);
        DefaultTableModel ctpt_Model = (DefaultTableModel) tbl_DSTra_DST.getModel();
        ctpt_Model.setRowCount(0);
        for (CT_PhieuTraDTO ctptDTO : dsCT_PhieuTra) {
            Object[] row = {ctptDTO.getMaPhieuTra(), ctptDTO.getMaSach(), ctptDTO.getTenSach(), ctptDTO.getSoLuong(), ctptDTO.getTrangThai()};
            ctpt_Model.addRow(row);
        }

    }//GEN-LAST:event_tbl_DSTra_DSPTMouseClicked

    private void btn_TVMuon_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TVMuon_ClearActionPerformed
        int check = JOptionPane.showConfirmDialog(rootPane, "Làm trống các ô thông tin?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            txt_TVMuon_TrangThai.setText("Mới");
            TV_DSMuonMuon.setRowCount(0);
            txt_TVMuon_idSach.setText("");
            txt_TVMuon_TenSach.setText("");
            txt_TVMuon_idThe.setText("");
            txt_TVMuon_PhiDatCoc.setText("");
            txt_TVMuon_NgayMuon.setText("");
            txt_TVMuon_HanTra.setText("");
        }
    }//GEN-LAST:event_btn_TVMuon_ClearActionPerformed

    private void cbb_ThongKe_LuaChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_ThongKe_LuaChonActionPerformed
        String selectedCategory = (String) cbb_ThongKe_LuaChon.getSelectedItem();
        if (selectedCategory != null) {
            String[] options = data.get(selectedCategory);
            cbb_CacLuaChon.setModel(new DefaultComboBoxModel<>(options));
        }
    }//GEN-LAST:event_cbb_ThongKe_LuaChonActionPerformed

    private void btn_ThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThongKeActionPerformed
        int thongKeType = cbb_ThongKe_LuaChon.getSelectedIndex();

        ArrayList<Object[]> result = new ArrayList<>();
        switch (thongKeType) {
            case 0 -> {
                int option0 = cbb_CacLuaChon.getSelectedIndex();
                if (option0 == 0) {
                    TaoBangThreeColumn("Mã thể loại", "Thể loại", "Số lượng sách");
                    result = sachBUS.getSoLuongSachBYTheLoai();
                    lb_HienThi.setText("Sách theo thể loại");
                }
                if (option0 == 1) {
                    TaoBangThreeColumn("Mã tác giả", "Tác giả", "Số lượng sách");
                    result = sachBUS.getSoLuongSachBYTacGia();
                    lb_HienThi.setText("Sách theo tác giả");
                }
                if (option0 == 2) {
                    TaoBangThreeColumn("Mã nhà xuất bản", "Nhà xuất bản", "Số lượng sách");
                    result = sachBUS.getSoLuongSachBYNXB();
                    lb_HienThi.setText("Sách theo nhà xuất bản");
                }
                if (option0 == 3) {
                    TaoBangFourColumn("Mã sách", "tên sách", "Số lượng", "Trạng thái");
                    result = sachBUS.getSachKhongNguyenVen();
                    lb_HienThi.setText("Danh sách Sách bị mất hoặc hỏng");
                }
            }
            case 1 -> {
                int option1 = cbb_CacLuaChon.getSelectedIndex();
                if (option1 == 0) {
                    int month = cbb_Thang.getSelectedIndex() + 1;
                    int year = (int) cbb_Nam.getSelectedIndex() + 2020;
                    result = tvBUS.getDocGiaBYNgayDK(month, year);
                    TaoBangThreeColumn("Mã thẻ", "Họ tên đọc giả", "Ngày đăng ký");
                    lb_HienThi.setText("Danh sách đăng ký " + month + "/" + year);
                }
                if (option1 == 1) {
                    result = tvBUS.getDocGiaKhoaThe();
                    TaoBangThreeColumn("Mã thẻ", "Họ tên đọc giả", "Ngày đăng ký");
                    lb_HienThi.setText("Danh sách thẻ bị khóa");
                }
                if (option1 == 2) {
                    result = tvBUS.getDocGiaQuaHan();
                    TaoBangThreeColumn("Mã thẻ", "Họ tên đọc giả", "Hạn sử dụng");
                    lb_HienThi.setText("Danh sách thẻ quá hạn");
                } else {
                    result = tvBUS.getTop5();
                    TaoBangThreeColumn("Mã thẻ", "Họ tên đọc giả", "Số lượt mượn");
                    lb_HienThi.setText("Danh sách đọc giả mượn nhiều nhất");
                }
            }
            default -> {
                int option2 = cbb_CacLuaChon.getSelectedIndex();
                if (option2 == 0) {
                    int month = cbb_Thang.getSelectedIndex() + 1;
                    int year = (int) cbb_Nam.getSelectedIndex() + 2020;
                    result = tvBUS.getDocGiaBYNgayDK(month, year);
                    TaoBangThreeColumn("Mã phiếu mượn", "Mã thẻ", "Ngày mượn");
                    lb_HienThi.setText("Danh sách mượn " + month + "/" + year);
                }
                if (option2 == 1) {
                    result = tvBUS.getDocGiaKhoaThe();
                    TaoBangFourColumn("Mã phiếu trả", "Mã phiếu mượn", "Hạn trả", "Ngày trả");
                    lb_HienThi.setText("Danh sách phiếu trả trễ hạn");
                }
//                if (option2 == 2) {
//                    result = tvBUS.getDocGiaQuaHan();
//                    TaoBangCase2("Hạn sử dụng");
//                    lb_HienThi.setText("Danh sách thẻ quá hạn");
//                } else {
//                    result = tvBUS.getTop5();
//                    TaoBangCase2("Số lượng");
//                    lb_HienThi.setText("Danh sách đọc giả mượn nhiều nhất");
//                }
            }
        }
        for (Object[] row : result) {
            bangTK.addRow(row);
        }
    }//GEN-LAST:event_btn_ThongKeActionPerformed

    private void txt_TVMuon_idTheFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TVMuon_idTheFocusLost
        String mathe = txt_TVMuon_idThe.getText();
        if (mathe.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã thẻ");
            txt_TVMuon_idThe.requestFocus();
        } else {
            if (!tvBUS.hasID(mathe)) {
                JOptionPane.showMessageDialog(rootPane, "Mã này không tồn tại! Vui lòng nhập mã khác");
                txt_TVMuon_idThe.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_TVMuon_idTheFocusLost

    private void btn_TVMuon_CapNhatPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TVMuon_CapNhatPMActionPerformed
        String maThe = txt_TVMuon_idThe.getText();
        String maPM = "PM" + maThe.substring(maThe.length() - 4) + txt_TVMuon_NgayMuon.getText();
        PhieuMuonDTO pm = pmBUS.searchPhieuMuonByMaPhieuMuon(maPM);
        Date ngayMuon = null, hanTra = null;
        try {
            ngayMuon = formatter.parse(txt_TVMuon_NgayMuon.getText());
            hanTra = formatter.parse(txt_TVMuon_HanTra.getText());
        } catch (ParseException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date NgayMuon = new java.sql.Date(ngayMuon.getTime());
        java.sql.Date HanTra = new java.sql.Date(hanTra.getTime());
        pm.setNgayMuon(NgayMuon);
        pm.setHanTra(HanTra);
        ArrayList<CT_PhieuMuonDTO> dsTruoc = ctpmBUS.getCT_PhieuMuonByPhieuMuonId(maPM);
        ArrayList<CT_PhieuMuonDTO> dsSau = new ArrayList<>();
        ArrayList<CT_PhieuMuonDTO> them, xoa;
        for (int i = 0; i < TV_DSMuonMuon.getRowCount(); i++) {
            dsSau.add(new CT_PhieuMuonDTO(
                    maPM,
                    TV_DSMuonMuon.getValueAt(i, 0).toString(),
                    TV_DSMuonMuon.getValueAt(i, 1).toString(),
                    Integer.parseInt(TV_DSMuonMuon.getValueAt(i, 2).toString()),
                    TV_DSMuonMuon.getValueAt(i, 3).toString()
            ));
        }
        if (pmBUS.updatePhieuMuon(pm)) {
            them = new ArrayList<>(dsSau);
            them.removeAll(dsTruoc);
            xoa = new ArrayList<>(dsTruoc);
            xoa.removeAll(dsSau);
            ctpmBUS.addCT_PhieuMuon(them);
            ctpmBUS.deleteCT_PhieuMuon(xoa);
            JOptionPane.showMessageDialog(rootPane, "Cập nhật phiếu mượn " + maPM + " thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Cập nhật phiếu mượn " + maPM + " không thành công");
        }
    }//GEN-LAST:event_btn_TVMuon_CapNhatPMActionPerformed

    private void tbl_TVMuon_DSMuonMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TVMuon_DSMuonMuonMouseClicked
        int selectedRow = tbl_TVMuon_DSMuonMuon.getSelectedRow();
        if (selectedRow != -1) {
            txt_TVMuon_idSach.setText(TV_DSMuonMuon.getValueAt(selectedRow, 0).toString());
            txt_TVMuon_TenSach.setText(TV_DSMuonMuon.getValueAt(selectedRow, 1).toString());
        } else {
            System.out.println(selectedRow);
        }
    }//GEN-LAST:event_tbl_TVMuon_DSMuonMuonMouseClicked

    private void tbl_ChuaCoThe_DSMuonMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChuaCoThe_DSMuonMuonMouseClicked
        int selectedRow = tbl_ChuaCoThe_DSMuonMuon.getSelectedRow();
        if (selectedRow != -1) {
            txt_ChuaCoThe_idSach.setText(ChuaCoThe_DSMuonMuon.getValueAt(selectedRow, 0).toString());
            txt_ChuaCoThe_TenSach.setText(ChuaCoThe_DSMuonMuon.getValueAt(selectedRow, 1).toString());
        } else {
            System.out.println(selectedRow);
        }
    }//GEN-LAST:event_tbl_ChuaCoThe_DSMuonMuonMouseClicked

    private void txt_TVMuon_PhiDatCocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TVMuon_PhiDatCocFocusGained
        int column = 2;
        double sum = 0;
        for (int row = 0; row < TV_DSMuonMuon.getRowCount(); row++) {
            Object value = TV_DSMuonMuon.getValueAt(row, column);
            if (value instanceof Number number) {
                sum += number.doubleValue(); // Nếu là số, cộng dồn
            }
            txt_TVMuon_PhiDatCoc.setText(sum * 15000 + "");
        }

    }//GEN-LAST:event_txt_TVMuon_PhiDatCocFocusGained

    private void btn_QLTra_ChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QLTra_ChinhSuaActionPerformed
        int selectedRow = tbl_DSTra_DSPT.getSelectedRow();
        String maPM = dsPT.getValueAt(selectedRow, 1).toString();
        String mathe = dsPT.getValueAt(selectedRow, 2).toString();
        String tienCoc = dsPT.getValueAt(selectedRow, 3).toString();
        String ngayMuon = dsPT.getValueAt(selectedRow, 4).toString();
        String hanTra = dsPT.getValueAt(selectedRow, 5).toString();
        try {
            TraSachGUI trasach = new TraSachGUI(maPM, mathe, tienCoc, ngayMuon, hanTra);
            trasach.setVisible(true);
            trasach.updateButtonName("Cập nhật phiếu trả");
            trasach.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_QLTra_ChinhSuaActionPerformed

    private void TaoBangThreeColumn(String column1, String column2, String column3) {
        bangTK.setRowCount(0);
        bangTK.setColumnCount(0);
        bangTK.addColumn("<html><center style='font-size:16px'>" + column1 + "</html>");
        bangTK.addColumn("<html><center style='font-size:16px'>" + column2 + "</html>");
        bangTK.addColumn("<html><center style='font-size:16px'>" + column3 + "</html>");
    }

    private void TaoBangFourColumn(String column1, String column2, String column3, String column4) {
        bangTK.setRowCount(0);
        bangTK.setColumnCount(0);
        bangTK.addColumn("<html><center style='font-size:16px'>" + column1 + "</html>");
        bangTK.addColumn("<html><center style='font-size:16px'>" + column2 + "</html>");
        bangTK.addColumn("<html><center style='font-size:16px'>" + column3 + "</html>");
        bangTK.addColumn("<html><center style='font-size:16px'>" + column4 + "</html>");
    }

    private void btn_QLThe_khoaTheActionPerformed(java.awt.event.ActionEvent evt) {
        ThanhVienDTO tv = tvBUS.searchThanhVienById(txt_GHT_idThe.getText());
        tv.setTrangThai("Đã khóa");
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn khóa thẻ?", "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(rootPane, tvBUS.updateTrangThaiThe(tv));
            DefaultTableModel model_DSThe = (DefaultTableModel) tbl_DanhSachThe.getModel();
            int selectedRow = tbl_DanhSachThe.getSelectedRow();
            if (selectedRow >= 0) {
                model_DSThe.setValueAt("Đã khóa", selectedRow, 7);
                model_DSThe.fireTableCellUpdated(selectedRow, 7);
                txt_GHT_trangThai.setText("Đã khóa");
                ButtonStatus("đã khóa");
            }
        }
    }

    private void jButton_TrangChuActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "TrangChu");
    }

    private void jButton_QLTheActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "QuanLyThe");
        loadDataTrangThaiThe(tvBUS.getAllThanhVien());
    }

    private void jButton_QLMuonActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "QuanLyMuon");
    }

    private void jButton_QLSachActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "QuanLySach");
        loadDataToComboBoxes();
    }

    private void jButton_ThongKevaBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "ThongKevaBaoCao");
        bangTK = (DefaultTableModel) tbl_BangDuLieu.getModel();
        data = new HashMap<>();
        data.put("Sách", new String[]{"Số lượng sách theo thể loại", "Số lượng sách theo tác giả", "Số lượng sách theo nhà xuất bản", "Danh sách bị mất hoặc bị hỏng"});
        data.put("Đọc giả", new String[]{"Danh sách đăng ký mới", "Danh sách thẻ bị khóa", "Danh sách thẻ quá hạn", "Đọc giả mượn nhiều nhất", "Đọc giả mượn sách quá hạn và chưa trả"});
        data.put("Mượn trả", new String[]{"Danh sách phiếu mượn theo tháng", "Phiếu trả trễ hạn", "Sách chưa được trả", "Sách được trả cao nhất"});
        String selectedCategory = (String) cbb_ThongKe_LuaChon.getSelectedItem();
        if (selectedCategory != null) {
            String[] options = data.get(selectedCategory);
            cbb_CacLuaChon.setModel(new DefaultComboBoxModel<>(options));
        }
        txt_SoLuongSach.setText(sachBUS.getSum() + "");
        txt_SoDocGia.setText(tvBUS.getSum() + "");
        txt_SoPhieuMuon.setText(pmBUS.getSum() + "");
        txt_SoPhieuTra.setText(ptBUS.getSum() + "");
    }

    private void jButton_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        loginGUI.setLocationRelativeTo(null);
    }

    private void jButton_DangKyTheActionPerformed(java.awt.event.ActionEvent evt) {
        ThemTheThanhVien themTheThanhVien = null;
        try {
            themTheThanhVien = new ThemTheThanhVien();
            themTheThanhVien.setVisible(true);
            themTheThanhVien.setLocationRelativeTo(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            loadDataTrangThaiThe(tvBUS.getAllThanhVien());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void jButton_QLMuon_DanhSachMuonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton_QLMuon_DanhSachMuonActionPerformed
        cardLayout2.show(jPanel_QLMuonTraMain, "DanhSachMuon");
        dsPM.setRowCount(0);
        jButton_QLMuon_TVMuon.setText("Thành viên mượn sách");
        loadDataTrangThaiPhieuMuon(pmBUS.getAllPhieuMuon());
    }

    private void jButton_QLMuon_TVMuonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton_QLMuon_TVMuonActionPerformed
        cardLayout2.show(jPanel_QLMuonTraMain, "ThanhVienMuonSach");
        Calendar calendar = Calendar.getInstance();
        String currentTime = formatter.format(calendar.getTime());
        txt_TVMuon_NgayMuon.setText(currentTime);
        calendar.add(Calendar.DAY_OF_MONTH, 21);
        String hanTra = formatter.format(calendar.getTime());
        txt_TVMuon_HanTra.setText(hanTra);
        txt_TVMuon_TrangThai.setText("Mới");
        String[] options = {"Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất"};
        JComboBox<String> cbb = new JComboBox<>(options);
        cbb.setFont(new java.awt.Font("Segoe", java.awt.Font.PLAIN, 16));
        TableColumn column = tbl_TVMuon_DSMuonMuon.getColumnModel().getColumn(3);
        column.setCellEditor(new javax.swing.DefaultCellEditor(cbb));
    }

    private void jButton_QLMuon_ChuaCoTheActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton_QLMuon_ChuaCoTheActionPerformed
        cardLayout2.show(jPanel_QLMuonTraMain, "ChuaCoThe");
        jButton_QLMuon_TVMuon.setText("Thành viên mượn sách");
        Calendar calendar = Calendar.getInstance();
        String currentTime = formatter.format(calendar.getTime());
        txt_ChuaCoThe_NgayMuon.setText(currentTime);
        calendar.add(Calendar.DAY_OF_MONTH, 21);
        String hanTra = formatter.format(calendar.getTime());
        txt_ChuaCoThe_HanTra.setText(hanTra);
        txt_ChuaCoThe_TrangThai.setText("Mới");
        String[] options = {"Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất"};
        JComboBox<String> cbb = new JComboBox<>(options);
        cbb.setFont(new java.awt.Font("Segoe", java.awt.Font.PLAIN, 16));
        TableColumn column = tbl_ChuaCoThe_DSMuonMuon.getColumnModel().getColumn(3);
        column.setCellEditor(new javax.swing.DefaultCellEditor(cbb));
    }

    private void tbl_DanhSachTheMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tbl_DanhSachTheMouseClicked
        int selected = tbl_DanhSachThe.getSelectedRow();
        if (selected != -1) {
            // Thong tin the
            txt_QLThe_name.setText(tbl_DanhSachThe.getValueAt(selected, 1).toString());
            txt_QLThe_cccd.setText(tbl_DanhSachThe.getValueAt(selected, 2).toString());
            txt_QLThe_sdt.setText(tbl_DanhSachThe.getValueAt(selected, 3).toString());
            txt_QLThe_DiaChi.setText(tbl_DanhSachThe.getValueAt(selected, 4).toString());
            // Gia han the
            txt_GHT_idThe.setText(tbl_DanhSachThe.getValueAt(selected, 0).toString());
            txt_GHT_trangThai.setText(tbl_DanhSachThe.getValueAt(selected, 7).toString());
            txt_GHT_ngayDK.setText(tbl_DanhSachThe.getValueAt(selected, 5).toString());
            txt_GHT_ngayHH.setText(tbl_DanhSachThe.getValueAt(selected, 6).toString());
        } else {
            txt_QLThe_name.setText("");
            txt_QLThe_cccd.setText("");
            txt_QLThe_sdt.setText("");
            txt_QLThe_DiaChi.setText("");
            txt_GHT_idThe.setText("");
            txt_GHT_trangThai.setText("");
            txt_GHT_ngayDK.setText("");
            txt_GHT_ngayHH.setText("");
        }
        ButtonStatus(tbl_DanhSachThe.getValueAt(selected, 7).toString());
    }

    private void txt_TimKiemTheFocusGained(java.awt.event.FocusEvent evt) {
        txt_TimKiemThe.setText("");
    }

    private void btn_TimKiemTheActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_TimKiemTheActionPerformed
        dsThe.setRowCount(0);
        ArrayList<ThanhVienDTO> dsThanhVien = new ArrayList<>();
        ThanhVienDTO tv = new ThanhVienDTO();
        String searchText = txt_TimKiemThe.getText();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin muốn tìm kiếm", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int index = cbb_TimKiemThe.getSelectedIndex();
            switch (index) {
                case 1 -> {
                    dsThanhVien = tvBUS.searchThanhVienByName(searchText);
                }
                case 2 -> {
                    dsThanhVien = tvBUS.searchThanhVienByCCCD(searchText);
                }
                case 3 -> {
                    dsThanhVien = tvBUS.searchThanhVienBySDT(searchText);
                }
                default -> {
                    tv = tvBUS.searchThanhVienById(searchText);
                    dsThanhVien.add(tv);
                }
            }
            for (ThanhVienDTO tvDTO : dsThanhVien) {
                Object[] row = {tvDTO.getId(), tvDTO.getTen(), tvDTO.getCCCD(), tvDTO.getSdt(), tvDTO.getDiaChi(), tvDTO.getNgayDK(),
                    tvDTO.getHanSD(), tvDTO.getTrangThai()};
                dsThe.addRow(row);
            }
        }
    }

    private void btn_XemTatCaTheActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_XemTatCaTheActionPerformed
        dsThe.setRowCount(0);
        ArrayList<ThanhVienDTO> dsThanhVien = tvBUS.getAllThanhVien();
        ThanhVienDTO tv = new ThanhVienDTO();
        for (int i = 0; i < dsThanhVien.size(); i++) {
            tv = dsThanhVien.get(i);
            Object[] row = {tv.getId(), tv.getTen(), tv.getCCCD(), tv.getSdt(), tv.getDiaChi(), tv.getNgayDK(),
                tv.getHanSD(), tv.getTrangThai()};
            dsThe.addRow(row);
        }
    }

    private void btn_QLthe_UpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_QLthe_UpdateActionPerformed
        String ma, ten, cccd, sdt, diaChi;
        ma = txt_GHT_idThe.getText();
        ten = txt_QLThe_name.getText();
        cccd = txt_QLThe_cccd.getText();
        sdt = txt_QLThe_sdt.getText();
        RegexUtils.CheckRegex(cccd, sdt, jPanel_DSThe);
        diaChi = txt_QLThe_DiaChi.getText();
        ThanhVienDTO tv = tvBUS.searchThanhVienById(ma);
        tv.setCCCD(cccd);
        tv.setDiaChi(diaChi);
        tv.setSdt(sdt);
        tv.setTen(ten);
        JOptionPane.showMessageDialog(rootPane, tvBUS.updateThanhVien(tv), "Thông báo", HEIGHT);
        int selectedRow = tbl_DanhSachThe.getSelectedRow();
        if (selectedRow >= 0) {
            dsThe.setValueAt(ten, selectedRow, 1);
            dsThe.fireTableCellUpdated(selectedRow, 1);
            dsThe.setValueAt(cccd, selectedRow, 2);
            dsThe.fireTableCellUpdated(selectedRow, 2);
            dsThe.setValueAt(sdt, selectedRow, 3);
            dsThe.fireTableCellUpdated(selectedRow, 3);
            dsThe.setValueAt(diaChi, selectedRow, 4);
            dsThe.fireTableCellUpdated(selectedRow, 4);
        }
    }

    private void btn_QLThe_giaHanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_QLThe_giaHanActionPerformed
        ThanhVienDTO tv = tvBUS.searchThanhVienById(txt_GHT_idThe.getText());
        tv.setSoLan(tv.getSoLan() + 1);
        tv.setTrangThai("Đang hoạt động");
        String currentDateText = txt_GHT_ngayDK.getText();
        Date date = null;
        try {
            date = formatter.parse(currentDateText);
        } catch (ParseException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 6);

        Date futureDate = calendar.getTime();
        tv.setHanSD((java.sql.Date) futureDate);

        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn gia hạn thẻ?", "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(rootPane, tvBUS.updateTrangThaiThe(tv));
            int selectedRow = tbl_DanhSachThe.getSelectedRow();
            if (selectedRow >= 0) {
                dsThe.setValueAt("Đang hoạt động", selectedRow, 7);
                dsThe.fireTableCellUpdated(selectedRow, 7);
                dsThe.setValueAt(futureDate, selectedRow, 6);
                dsThe.fireTableCellUpdated(selectedRow, 7);
                txt_GHT_trangThai.setText("Đang hoạt động");
                ButtonStatus("đang hoạt động");
            }
        }
    }

    private void btn_QLThe_moKhoaTheActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_QLThe_moKhoaTheActionPerformed
        ThanhVienDTO tv = tvBUS.searchThanhVienById(txt_GHT_idThe.getText());
        tv.setTrangThai("Đang hoạt động");
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn mở khóa thẻ?", "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(rootPane, tvBUS.updateTrangThaiThe(tv));
            DefaultTableModel model_DSThe = (DefaultTableModel) tbl_DanhSachThe.getModel();
            int selectedRow = tbl_DanhSachThe.getSelectedRow();
            if (selectedRow >= 0) {
                model_DSThe.setValueAt("Đang hoạt động", selectedRow, 7);
                model_DSThe.fireTableCellUpdated(selectedRow, 7);
                txt_GHT_trangThai.setText("Đang hoạt động");
                ButtonStatus("đang hoạt động");
            }
        }
    }

    private void btn_DSMuon_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_DSMuon_TimKiemActionPerformed
        dsPM.setRowCount(0);
        ArrayList<PhieuMuonDTO> dsPhieuMuon = new ArrayList<>();
        PhieuMuonDTO pm = new PhieuMuonDTO();
        int selectedCBB = cbb_DSMuon_TimKiem.getSelectedIndex();
        String timkiem = txt_DSMuon_TimKiem.getText();
        switch (selectedCBB) {
            case 1 -> {
                dsPhieuMuon = pmBUS.searchPhieuMuonByMaThe(timkiem);
            }
            case 2 -> {
                Date ngayMuon = null;
                try {
                    ngayMuon = formatter.parse(timkiem);
                } catch (ParseException e) {
                    Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, e);
                }
                java.sql.Date NgMuon = new java.sql.Date(ngayMuon.getTime());
                dsPhieuMuon = pmBUS.searchPhieuMuonByNgayMuon(NgMuon);
            }
            case 3 -> {
                Date hanTra = null;
                try {
                    hanTra = formatter.parse(timkiem);
                } catch (ParseException e) {
                    Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, e);
                }
                java.sql.Date HanTra = new java.sql.Date(hanTra.getTime());
                dsPhieuMuon = pmBUS.searchPhieuMuonByHanTra(HanTra);
            }
            default -> {
                pm = pmBUS.searchPhieuMuonByMaPhieuMuon(timkiem);
                dsPhieuMuon.add(pm);
            }
        }
        for (int i = 0; i < dsPhieuMuon.size(); i++) {
            pm = dsPhieuMuon.get(i);
            Object[] row = {pm.getId(), pm.getMaThe(), pm.getNgayMuon(), pm.getHanTra(), pm.getTienCoc(), pm.getTrangThai()};
            dsPM.addRow(row);
        }
    }

    private void txt_DSMuon_TimKiemFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txt_DSMuon_TimKiemFocusGained
        txt_DSMuon_TimKiem.setText("");
    }

    private void btn_DSMuon_XemTatCaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_DSMuon_XemTatCaActionPerformed
        dsPM.setRowCount(0);
        ArrayList<PhieuMuonDTO> dsPhieuMuon = pmBUS.getAllPhieuMuon();
        PhieuMuonDTO pm = new PhieuMuonDTO();
        for (int i = 0; i < dsPhieuMuon.size(); i++) {
            pm = dsPhieuMuon.get(i);
            Object[] row = {pm.getId(), pm.getMaThe(), pm.getNgayMuon(), pm.getHanTra(), pm.getTienCoc(), pm.getTrangThai()};
            dsPM.addRow(row);
        }
    }

    private void tbl_DSMuon_DSPMMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tbl_DSMuon_DSPMMouseClicked
        dsMuon.setRowCount(0);
        int selectedRow = tbl_DSMuon_DSPM.getSelectedRow();
        ArrayList<CT_PhieuMuonDTO> ds = ctpmBUS.getCT_PhieuMuonByPhieuMuonId(tbl_DSMuon_DSPM.getValueAt(selectedRow, 0).toString());
        CT_PhieuMuonDTO ctpm = new CT_PhieuMuonDTO();
        for (int i = 0; i < ds.size(); i++) {
            ctpm = ds.get(i);
            Object[] row = {ctpm.getMaSach(), ctpm.getTenSach(), ctpm.getSoLuong(), ctpm.getTrangThai()};
            dsMuon.addRow(row);
        }
    }

    private void txt_TVMuon_TimKiemSachFocusGained(java.awt.event.FocusEvent evt) {
        txt_TVMuon_TimKiemSach.setText("");
    }

    private void btn_TVMuon_ThemVaoPMActionPerformed(java.awt.event.ActionEvent evt) {
        if (!txt_TVMuon_TrangThai.getText().equals("Đã trả")) {
            int soLuong = (int) spn_TVMuon_SoLuong.getValue();
            TVMuon_quantityBook += soLuong;
            if (TVMuon_quantityBook > 5) {
                JOptionPane.showMessageDialog(rootPane, "Chỉ có thể mượn tối đa 5 cuốn sách cho mỗi lần mượn!");
            } else {
                TV_DSMuonMuon.setRowCount(TVMuon_quantityBook);
                TV_DSMuonMuon.setValueAt(txt_TVMuon_idSach.getText(), TVMuon_quantityBook - 1, 0);
                TV_DSMuonMuon.setValueAt(txt_TVMuon_TenSach.getText(), TVMuon_quantityBook - 1, 1);
                TV_DSMuonMuon.setValueAt(soLuong, TVMuon_quantityBook - 1, 2);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Không thể chỉnh sửa phiếu đã trả!");
        }
        txt_TVMuon_idSach.setText("");
        txt_TVMuon_TenSach.setText("");
    }

    private void btn_TVMuon_TimSachActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_TVMuon_TimKiemSach.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã sách muốn mượn!");
        } else {
            SachDTO sach = sachBUS.getSachById(txt_TVMuon_TimKiemSach.getText());
            int slcl = sachBUS.getSoSachConLai(sach.getId());
            if (slcl == 0 || sach == null) {
                JOptionPane.showMessageDialog(rootPane, "Không có!");
            }
            if (TV_DSMuonMuon.getRowCount() > 0) {
                for (int i = 0; i < TV_DSMuonMuon.getRowCount(); i++) {
                    if (sach.getId().equals(TV_DSMuonMuon.getValueAt(i, 0).toString())) {
                        JOptionPane.showMessageDialog(rootPane, "Mã sách này đã có trong danh sách muốn mượn!");
                        break;
                    }
                }
            }
            txt_TVMuon_idSach.setText(sach.getId());
            txt_TVMuon_TenSach.setText(sach.getTenSach());
        }
    }

    private void btn_TVMuon_XoaKhoiPMActionPerformed(java.awt.event.ActionEvent evt) {
        if (!txt_TVMuon_TrangThai.getText().equals("Đã trả")) {
            int selectedRow = tbl_TVMuon_DSMuonMuon.getSelectedRow();
            if (selectedRow != -1) {
                TV_DSMuonMuon.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Không thể xóa!");
        }
        txt_TVMuon_idSach.setText("");
        txt_TVMuon_TenSach.setText("");
    }

    private void btn_TVMuon_XacNhanMuonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_TVMuon_XacNhanMuonActionPerformed
        String maThe = txt_TVMuon_idThe.getText().replace("-", "");
        Double phiDatCoc = Double.valueOf(txt_TVMuon_PhiDatCoc.getText());
        Date ngayMuon = null, hanTra = null;
        try {
            ngayMuon = formatter.parse(txt_TVMuon_NgayMuon.getText());
            hanTra = formatter.parse(txt_TVMuon_HanTra.getText());
        } catch (ParseException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date NgayMuon = new java.sql.Date(ngayMuon.getTime());
        java.sql.Date HanTra = new java.sql.Date(hanTra.getTime());
        String maPM = "PM" + maThe.substring(maThe.length() - 8) + txt_TVMuon_NgayMuon.getText();
        PhieuMuonDTO pmDTO = new PhieuMuonDTO(maPM, maThe, NgayMuon, HanTra, phiDatCoc, "Đã nhận sách", 0);
        int quantity = tbl_TVMuon_DSMuonMuon.getRowCount();
        if (quantity == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có cuốn sách nào trong danh sách mượn", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }
        if (pmBUS.hasID(maPM)) {
            JOptionPane.showMessageDialog(rootPane, "Quý khách đã mượn sách trong ngày hôm nay rồi. Vui lòng quay lại vào hôm sau!");
        } else {
            if (pmBUS.addPhieuMuon(pmDTO)) {
                ArrayList<CT_PhieuMuonDTO> dsCTPM = new ArrayList<>();
                for (int row = 0; row < TV_DSMuonMuon.getRowCount(); row++) {
                    String idSach = TV_DSMuonMuon.getValueAt(row, 0).toString();
                    String tenSach = TV_DSMuonMuon.getValueAt(row, 1).toString();
                    int soLuong = Integer.parseInt(TV_DSMuonMuon.getValueAt(row, 2).toString());
                    String tinhTrang = TV_DSMuonMuon.getValueAt(row, 3).toString();
                    dsCTPM.add(new CT_PhieuMuonDTO(maPM, idSach, tenSach, soLuong, tinhTrang));
                }
                if (ctpmBUS.addCT_PhieuMuon(dsCTPM)) {
                    JOptionPane.showMessageDialog(rootPane,
                            "Tạo phiếu mượn cho người dùng có mã thẻ là " + maThe + " thành công!");
                    TV_DSMuonMuon.setRowCount(0);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "Tạo phiếu mượn cho người dùng có mã thẻ là " + maThe + " thất bại!");

            }
        }
    }

    private void txt_ChuaCoThe_TimSachFocusGained(java.awt.event.FocusEvent evt) {
        txt_ChuaCoThe_TimSach.setText("");
    }

    private void btn_ChuaCoThe_ThemVaoPMActionPerformed(java.awt.event.ActionEvent evt) {
        int soLuong = (int) spn_ChuaCoThe_SoLuong.getValue();
        ChuaCothe_quantityBook += soLuong;
        if (ChuaCothe_quantityBook > 5) {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có thể mượn tối đa 5 cuốn sách cho mỗi lần mượn!");
        } else {
            ChuaCoThe_DSMuonMuon.setRowCount(ChuaCothe_quantityBook);
            ChuaCoThe_DSMuonMuon.setValueAt(txt_ChuaCoThe_idSach.getText(), ChuaCothe_quantityBook - 1, 0);
            ChuaCoThe_DSMuonMuon.setValueAt(txt_ChuaCoThe_TenSach.getText(), ChuaCothe_quantityBook - 1, 1);
            ChuaCoThe_DSMuonMuon.setValueAt(soLuong, ChuaCothe_quantityBook - 1, 2);
        }
        txt_ChuaCoThe_idSach.setText("");
        txt_ChuaCoThe_TenSach.setText("");
    }

    private void btn_ChuaCoThe_XoaKhoiPMActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_ChuaCoThe_XoaKhoiPMActionPerformed
        int selectedRow = tbl_ChuaCoThe_DSMuonMuon.getSelectedRow();
        if (selectedRow != -1) {
            ChuaCoThe_DSMuonMuon.removeRow(selectedRow);
        }
        txt_ChuaCoThe_idSach.setText("");
        txt_ChuaCoThe_TenSach.setText("");
    }

    private void btn_ChuaCoThe_XacNhanMuonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_ChuaCoThe_XacNhanMuonActionPerformed
        String maThe = txt_ChuaCoThe_idThe.getText();
        String hoten = txt_ChuaCoThe_HoTen.getText();
        String cccd = txt_ChuaCoThe_cccd.getText();
        String sdt = txt_ChuaCoThe_sdt.getText();
        String diaChi = txt_ChuaCoThe_DiaChi.getText();
        Double phiDatCoc = Double.valueOf(txt_ChuaCoThe_PhiDatCoc.getText());
        Date ngayMuon = null, hanTra = null;
        try {
            ngayMuon = formatter.parse(txt_ChuaCoThe_NgayMuon.getText());
            hanTra = formatter.parse(txt_ChuaCoThe_HanTra.getText());
        } catch (ParseException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date NgayMuon = new java.sql.Date(ngayMuon.getTime());
        java.sql.Date HanTra = new java.sql.Date(hanTra.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayMuon);
        calendar.add(Calendar.MONTH, 6);
        Date hanSD = calendar.getTime();
        java.sql.Date hanSd = new java.sql.Date(hanSD.getTime());
        String trangThai = txt_ChuaCoThe_TrangThai.getText();
        if (!tvBUS.hasID(maThe)) {
            ThanhVienDTO tv = new ThanhVienDTO(maThe, hoten, cccd, sdt, diaChi, NgayMuon, hanSd, 100000.0, "Đang hoạt động");
            JOptionPane.showMessageDialog(rootPane, tvBUS.addThanhVien(tv));
        } else {
            String maPM = "PM" + maThe.substring(maThe.length() - 4) + txt_ChuaCoThe_NgayMuon.getText();
            PhieuMuonDTO pmDTO = new PhieuMuonDTO(maPM, maThe, NgayMuon, HanTra, phiDatCoc, trangThai, 0);
            int quantity = tbl_ChuaCoThe_DSMuonMuon.getRowCount();
            if (quantity == 0) {
                JOptionPane.showMessageDialog(rootPane, "Chưa có cuốn sách nào trong danh sách mượn", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            }
            if (pmBUS.addPhieuMuon(pmDTO)) {
                ArrayList<CT_PhieuMuonDTO> dsCTPM = new ArrayList<>();
                for (int row = 0; row < ChuaCoThe_DSMuonMuon.getRowCount(); row++) {
                    String idSach = ChuaCoThe_DSMuonMuon.getValueAt(row, 0).toString();
                    String tenSach = ChuaCoThe_DSMuonMuon.getValueAt(row, 1).toString();
                    int soLuong = Integer.parseInt(ChuaCoThe_DSMuonMuon.getValueAt(row, 2).toString());
                    String tinhTrang = ChuaCoThe_DSMuonMuon.getValueAt(row, 3).toString();
                    dsCTPM.add(new CT_PhieuMuonDTO(maPM, idSach, tenSach, soLuong, tinhTrang));
                }
                if (ctpmBUS.addCT_PhieuMuon(dsCTPM)) {
                    JOptionPane.showMessageDialog(rootPane,
                            "Tạo phiếu mượn " + maPM + " cho người dùng có mã thẻ là " + maThe + " thành công!");
                    ChuaCoThe_DSMuonMuon.setRowCount(0);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "Tạo phiếu mượn " + maPM + " cho người dùng có mã thẻ là " + maThe + " thất bại!");
            }
        }
        txt_ChuaCoThe_idThe.setText("");
        txt_ChuaCoThe_HoTen.setText("");
        txt_ChuaCoThe_cccd.setText("");
        txt_ChuaCoThe_sdt.setText("");
        txt_ChuaCoThe_DiaChi.setText("");
        txt_ChuaCoThe_PhiDatCoc.setText("");
    }

    private void btn_ChuaCoThe_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_ChuaCoThe_TimKiemActionPerformed
        if (txt_ChuaCoThe_TimSach.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã sách muốn mượn!");
        } else {
            SachDTO sach = sachBUS.getSachById(txt_ChuaCoThe_TimSach.getText());
            int slcl = sachBUS.getSoSachConLai(sach.getId());
            if (slcl == 0 || sach == null) {
                JOptionPane.showMessageDialog(rootPane, "Không có dữ liệu.");
            }
            int rowCount = tbl_ChuaCoThe_DSMuonMuon.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                if (sach.getId().equals(tbl_ChuaCoThe_DSMuonMuon.getValueAt(i, 0).toString())) {
                    JOptionPane.showMessageDialog(rootPane, "Mã sách này đã có trong danh sách muốn mượn!");
                    break;
                }
            }
            txt_ChuaCoThe_idSach.setText(sach.getId());
            txt_ChuaCoThe_TenSach.setText(sach.getTenSach());
        }
    }

    public void AddRowToDSThe(ThanhVienDTO tv) {
        DefaultTableModel model_DSThe = (DefaultTableModel) tbl_DanhSachThe.getModel();
        model_DSThe.addRow(new Object[]{tv.getId(), tv.getTen(), tv.getCCCD(), tv.getSdt(), tv.getDiaChi(),
            tv.getNgayDK(), tv.getHanSD(), tv.getTrangThai()});
    }

    private void jButton_QLLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "LoaiSach");
        try {
            tlBUS = new TheLoaiBUS();
            ArrayList<TheLoaiDTO> listTheLoai = tlBUS.getAllTheLoai();
            DefaultTableModel model = (DefaultTableModel) jTable_TheLoai.getModel();
            model.setRowCount(0);
            for (TheLoaiDTO theLoai : listTheLoai) {
                Object[] row = {
                    theLoai.getId(),
                    theLoai.getTen()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton_QLTacGiaActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "QuanLyTacGia");
        try {
            tgBUS = new TacGiaBUS();
            ArrayList<TacGiaDTO> listTacGia = tgBUS.getAllTacGia();
            DefaultTableModel model = (DefaultTableModel) jTable_TacGia.getModel();
            model.setRowCount(0);
            for (TacGiaDTO tg : listTacGia) {
                Object[] row = {
                    tg.getId(),
                    tg.getTen()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void jButton_QLNXBActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout1.show(jPanel_Main, "QuanLyNXB");
        try {
            nxbBUS = new NXBBUS();
            ArrayList<NXBDTO> listNXB = nxbBUS.getAllNXB();
            DefaultTableModel model = (DefaultTableModel) jTable_NXB.getModel();
            model.setRowCount(0);
            for (NXBDTO nxb : listNXB) {
                Object[] row = {
                    nxb.getId(),
                    nxb.getTen()
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ButtonStatus(String trangThai) {
        if (trangThai.toLowerCase().equals("đang hoạt động") || trangThai.toLowerCase().equals("hết hạn")) {
            btn_QLThe_moKhoaThe.setEnabled(false);
            btn_QLThe_khoaThe.setEnabled(true);
            btn_QLThe_giaHan.setEnabled(true);
        } else {
            btn_QLThe_moKhoaThe.setEnabled(true);
            btn_QLThe_khoaThe.setEnabled(false);
            btn_QLThe_giaHan.setEnabled(false);
        }
    }

    private void addComponentTojPanel_Main() {
        cardLayout1 = new CardLayout();
        jPanel_Main.setLayout(cardLayout1);
        jPanel_Main.add(jPanel_TrangChu, "TrangChu");
        jPanel_Main.add(jPanel_QLloaisach, "LoaiSach");
        jPanel_Main.add(jPanel_QLsach, "QuanLySach");
        jPanel_Main.add(jPanel_QLNXB, "QuanLyNXB");
        jPanel_Main.add(jPanel_QLThe, "QuanLyThe");
        jPanel_Main.add(jPanel_QLTacGia, "QuanLyTacGia");
        jPanel_Main.add(jPanel_QLMuon, "QuanLyMuon");
        jPanel_Main.add(jPanel_QLTra, "QuanLyTra");
        jPanel_Main.add(jPanel_ThongKevaBaoCao, "ThongKevaBaoCao");

    }

    private void addComponentTojPanel_QLMuonTra() {
        cardLayout2 = new CardLayout();
        jPanel_QLMuonTraMain.setLayout(cardLayout2);
        jPanel_QLMuonTraMain.add(jPanel_TVMuon, "ThanhVienMuonSach");
        jPanel_QLMuonTraMain.add(jPanel_DanhSachMuon, "DanhSachMuon");
        jPanel_QLMuonTraMain.add(jPanel_ChuaCoThe, "ChuaCoThe");
    }

    private void loadDataTrangThaiPhieuMuon(ArrayList<PhieuMuonDTO> listPM) {
        LocalDate ngayHienTai = LocalDate.now();
        for (PhieuMuonDTO phieu : listPM) {
            LocalDate ngayHetHan = phieu.getHanTra().toLocalDate();
            if (ngayHetHan.isBefore(ngayHienTai)) {
                phieu.setTrangThai("Trễ hạn");
                pmBUS.updateTrangThaiPhieuMuon(phieu);
            }
            if (ngayHetHan.isEqual(ngayHienTai)) {
                phieu.setTrangThai("Tới hạn");
                pmBUS.updateTrangThaiPhieuMuon(phieu);
            }
        }
        dsPM.setRowCount(0);
        for (PhieuMuonDTO phieu : listPM) {
            Object[] row = {
                phieu.getId(),
                phieu.getMaThe(),
                phieu.getNgayMuon(),
                phieu.getHanTra(),
                phieu.getTienCoc(),
                phieu.getTrangThai()
            };
            dsPM.addRow(row);
        }
    }

    private void loadDataTrangThaiThe(ArrayList<ThanhVienDTO> listThe) {
        LocalDate ngayHienTai = LocalDate.now();
        for (ThanhVienDTO the : listThe) {
            LocalDate ngayHetHan = the.getHanSD().toLocalDate();
            if (ngayHetHan.isBefore(ngayHienTai)) {
                the.setTrangThai("Hết hạn");
                tvBUS.updateTrangThaiThe(the);
            }
        }
        dsThe.setRowCount(0);
        for (ThanhVienDTO the : listThe) {
            Object[] row = {
                the.getId(),
                the.getTen(),
                the.getCCCD(),
                the.getSdt(),
                the.getDiaChi(),
                the.getNgayDK(),
                the.getHanSD(),
                the.getTrangThai()
            };
            dsThe.addRow(row);
        }
    }

    private void loadDataToComboBoxes() {
        ArrayList<TheLoaiDTO> listTheLoai = sachBUS.getAllTheLoai();
        ArrayList<TacGiaDTO> listTacGia = sachBUS.getAllTacGia();
        ArrayList<NXBDTO> listNhaXuatBan = sachBUS.getAllNhaXuatBan();

        ArrayList<String> theLoaiNames = new ArrayList<>();
        for (TheLoaiDTO theLoai : listTheLoai) {
            theLoaiNames.add(theLoai.getTen());
        }

        ArrayList<String> tacGiaNames = new ArrayList<>();
        for (TacGiaDTO tacGia : listTacGia) {
            tacGiaNames.add(tacGia.getTen());
        }

        ArrayList<String> nhaXuatBanNames = new ArrayList<>();
        for (NXBDTO nhaXuatBan : listNhaXuatBan) {
            nhaXuatBanNames.add(nhaXuatBan.getTen()); // Lấy tên nhà xuất bản
        }

        jComboBoxTheLoai.setModel(new DefaultComboBoxModel<>(theLoaiNames.toArray(String[]::new)));
        jComboBoxTacGia.setModel(new DefaultComboBoxModel<>(tacGiaNames.toArray(String[]::new)));
        jComboBoxNhaXuatBan.setModel(new DefaultComboBoxModel<>(nhaXuatBanNames.toArray(String[]::new)));
    }

    public void loadDataBookToTable() throws SQLException {
        ArrayList<SachDTO> listSach = sachBUS.getAllSach();
        listSach.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));

        DefaultTableModel model = (DefaultTableModel) jTableSach.getModel();
        model.setRowCount(0);

        for (SachDTO sach : listSach) {
            TheLoaiDTO tl = tlBUS.getTheLoaiById(sach.getTheloai());
            TacGiaDTO tg = tgBUS.getTacGiaById(sach.getTacGia());
            NXBDTO nxb = nxbBUS.getNXBById(sach.getNXB());
            model.addRow(new Object[]{
                sach.getId(),
                sach.getTenSach(),
                tl.getTen(),
                tg.getTen(),
                nxb.getTen(),
                sach.getNamXB(),
                sach.getSoLuong(),
                sach.getGiaSach()
            });
        }
    }

    public void BieuDo1() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        ArrayList<Object[]> ds = sachBUS.getSachTheoLuotMuon();
        for (int i = 0; i < ds.size(); i++) {
            Object[] data = ds.get(i);
            String tenSach = (String) data[1];
            int soluotmuon = (int) data[2];
            int tong = 0;
            if (i < 5) {
                dataset.setValue(tenSach, soluotmuon);
            } else {
                tong += soluotmuon;
            }
            dataset.setValue("Khác", tong);
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "Sách được mượn nhiều nhất",
                dataset,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        pn_BieuDo1.add(chartPanel);
    }

    public void BieuDo2(int month, int year) {
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        ArrayList<Object[]> ds = ptBUS.CacLoaiPhi(month, year);
        for (Object[] row : ds) {
            dataset1.setValue((double) row[0], "Tiền cọc", "");
            dataset1.setValue((double) row[1], "Phí đền bù", "");
            dataset1.setValue((double) row[2], "Phí trễ hạn", "");
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Tổng các loại phí tháng " + month + "/" + year,
                "Loại phí",
                "Tổng tiền(VNĐ)",
                dataset1
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        pn_BieuDo2.add(chartPanel);
    }

    public void BieuDo3() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TrangChuGUI().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField SLCL_NXB;
    private javax.swing.JTextField SLCL_NXB1;
    private javax.swing.JTextField SLCL_TacGia;
    private javax.swing.JTextField SLCL_TacGia1;
    private javax.swing.JTextField SLCL_TheLoai;
    private javax.swing.JPanel SLSach_TheLoai;
    private javax.swing.JButton ViewAll_NXB;
    private javax.swing.JButton ViewAll_TacGia;
    private javax.swing.JButton ViewAll_TheLoai;
    private javax.swing.JButton btn_ChuaCoThe_ThemVaoPM;
    private javax.swing.JButton btn_ChuaCoThe_TimKiem;
    private javax.swing.JButton btn_ChuaCoThe_XacNhanMuon;
    private javax.swing.JButton btn_ChuaCoThe_XoaKhoiPM;
    private javax.swing.JButton btn_DSMuon_ChinhSuaPM;
    private javax.swing.JButton btn_DSMuon_GiaHan;
    private javax.swing.JButton btn_DSMuon_TimKiem;
    private javax.swing.JButton btn_DSMuon_TraSach;
    private javax.swing.JButton btn_DSMuon_XemTatCa;
    private javax.swing.JButton btn_DSMuon_XuatPhieu;
    private javax.swing.JButton btn_DSTra_TimKiem;
    private javax.swing.JButton btn_DSTra_XemTatCa;
    private javax.swing.JButton btn_QLThe_giaHan;
    private javax.swing.JButton btn_QLThe_khoaThe;
    private javax.swing.JButton btn_QLThe_moKhoaThe;
    private javax.swing.JButton btn_QLTra_ChinhSua;
    private javax.swing.JButton btn_QLthe_Update;
    private javax.swing.JButton btn_TVMuon_CapNhatPM;
    private javax.swing.JButton btn_TVMuon_Clear;
    private javax.swing.JButton btn_TVMuon_ThemVaoPM;
    private javax.swing.JButton btn_TVMuon_TimSach;
    private javax.swing.JButton btn_TVMuon_XacNhanMuon;
    private javax.swing.JButton btn_TVMuon_XoaKhoiPM;
    private javax.swing.JButton btn_ThongKe;
    private javax.swing.JButton btn_TimKiemThe;
    private javax.swing.JButton btn_XemTatCaThe;
    private javax.swing.JComboBox<String> cbb_CacLuaChon;
    private javax.swing.JComboBox<String> cbb_DSMuon_TimKiem;
    private javax.swing.JComboBox<String> cbb_DSTra_TimKiem;
    private javax.swing.JComboBox<String> cbb_Nam;
    private javax.swing.JComboBox<String> cbb_Thang;
    private javax.swing.JComboBox<String> cbb_ThongKe_LuaChon;
    private javax.swing.JComboBox<String> cbb_TimKiemThe;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonCapNhatSach;
    private javax.swing.JButton jButtonShowAllSach;
    private javax.swing.JButton jButtonThemSach;
    private javax.swing.JButton jButtonTimSach;
    private javax.swing.JButton jButton_CapNhatNXB;
    private javax.swing.JButton jButton_CapNhatTL;
    private javax.swing.JButton jButton_CapNhatTacGia;
    private javax.swing.JButton jButton_DangKyThe;
    private javax.swing.JButton jButton_DangXuat;
    private javax.swing.JButton jButton_QLLoaiSach;
    private javax.swing.JButton jButton_QLMuon;
    private javax.swing.JButton jButton_QLMuon_ChuaCoThe;
    private javax.swing.JButton jButton_QLMuon_DanhSachMuon;
    private javax.swing.JButton jButton_QLMuon_TVMuon;
    private javax.swing.JButton jButton_QLNXB;
    private javax.swing.JButton jButton_QLSach;
    private javax.swing.JButton jButton_QLTacGia;
    private javax.swing.JButton jButton_QLThe;
    private javax.swing.JButton jButton_QLTra;
    private javax.swing.JButton jButton_ThemNXB;
    private javax.swing.JButton jButton_ThemTacGia;
    private javax.swing.JButton jButton_ThemTheLoai;
    private javax.swing.JButton jButton_ThongKevaBaoCao;
    private javax.swing.JButton jButton_TimKiem_NXB;
    private javax.swing.JButton jButton_TimKiem_TacGia;
    private javax.swing.JButton jButton_TimKiem_TheLoai;
    private javax.swing.JButton jButton_TrangChu;
    private javax.swing.JButton jButton_XoaNXB;
    private javax.swing.JButton jButton_XoaTL;
    private javax.swing.JButton jButton_XoaTacGia;
    private javax.swing.JComboBox<String> jComboBoxNhaXuatBan;
    private javax.swing.JComboBox<String> jComboBoxSach;
    private javax.swing.JComboBox<String> jComboBoxTacGia;
    private javax.swing.JComboBox<String> jComboBoxTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Admin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_Admin;
    private javax.swing.JPanel jPanel_ChuaCoThe;
    private javax.swing.JPanel jPanel_ChucNang;
    private javax.swing.JPanel jPanel_DSThe;
    private javax.swing.JPanel jPanel_DanhSachMuon;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_QLMuon;
    private javax.swing.JPanel jPanel_QLMuonHeader;
    private javax.swing.JPanel jPanel_QLMuonTraMain;
    private javax.swing.JPanel jPanel_QLNXB;
    private javax.swing.JPanel jPanel_QLTacGia;
    private javax.swing.JPanel jPanel_QLThe;
    private javax.swing.JPanel jPanel_QLTheHeader;
    private javax.swing.JPanel jPanel_QLTra;
    private javax.swing.JPanel jPanel_QLloaisach;
    private javax.swing.JPanel jPanel_QLsach;
    private javax.swing.JPanel jPanel_SideBar;
    private javax.swing.JPanel jPanel_TVMuon;
    private javax.swing.JPanel jPanel_ThongKevaBaoCao;
    private javax.swing.JPanel jPanel_TrangChu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableSach;
    private javax.swing.JTable jTable_NXB;
    private javax.swing.JTable jTable_TacGia;
    private javax.swing.JTable jTable_TheLoai;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextFieldGiaSach;
    private javax.swing.JTextField jTextFieldMaSach;
    private javax.swing.JTextField jTextFieldNamXuatBan;
    private javax.swing.JTextField jTextFieldSach;
    private javax.swing.JTextField jTextFieldSoLuong;
    private javax.swing.JTextField jTextFieldTenSach;
    private javax.swing.JTextField jTextField_IDLoaiSach;
    private javax.swing.JTextField jTextField_IDNXB;
    private javax.swing.JTextField jTextField_IDTacGia;
    private javax.swing.JTextField jTextField_IDTacGia1;
    private javax.swing.JTextField jTextField_TenLoaiSach;
    private javax.swing.JTextField jTextField_TenNXB;
    private javax.swing.JTextField jTextField_TenTacGia;
    private javax.swing.JTextField jTextField_TenTacGia1;
    private javax.swing.JTextField jTextField_TimKiem_NXB;
    private javax.swing.JTextField jTextField_TimKiem_TacGia;
    private javax.swing.JTextField jTextField_TimKiem_TheLoai;
    private javax.swing.JLabel lb_HienThi;
    private javax.swing.JLabel lb_PhiDuyTri;
    private javax.swing.JLabel lb_QuyDinhVaChinhSach;
    private javax.swing.JPanel pn_BieuDo1;
    private javax.swing.JPanel pn_BieuDo2;
    private javax.swing.JPanel pn_BieuDo3;
    private javax.swing.JPanel pn_BieuDo4;
    private javax.swing.JPanel pn_BieuDoTron;
    private javax.swing.JPanel pn_ThongKe;
    private javax.swing.JSpinner spn_ChuaCoThe_SoLuong;
    private javax.swing.JSpinner spn_TVMuon_SoLuong;
    private javax.swing.JTable tbl_BangDuLieu;
    private javax.swing.JTable tbl_ChuaCoThe_DSMuonMuon;
    private javax.swing.JTable tbl_DSMuon_DSM;
    private javax.swing.JTable tbl_DSMuon_DSPM;
    private javax.swing.JTable tbl_DSTra_DSPT;
    private javax.swing.JTable tbl_DSTra_DST;
    private javax.swing.JTable tbl_DanhSachThe;
    private javax.swing.JTable tbl_TVMuon_DSMuonMuon;
    private javax.swing.JTextField txt_ChuaCoThe_DiaChi;
    private javax.swing.JTextField txt_ChuaCoThe_HanTra;
    private javax.swing.JTextField txt_ChuaCoThe_HoTen;
    private javax.swing.JTextField txt_ChuaCoThe_NgayMuon;
    private javax.swing.JTextField txt_ChuaCoThe_PhiDatCoc;
    private javax.swing.JTextField txt_ChuaCoThe_TenSach;
    private javax.swing.JTextField txt_ChuaCoThe_TimSach;
    private javax.swing.JTextField txt_ChuaCoThe_TrangThai;
    private javax.swing.JTextField txt_ChuaCoThe_cccd;
    private javax.swing.JTextField txt_ChuaCoThe_idSach;
    private javax.swing.JTextField txt_ChuaCoThe_idThe;
    private javax.swing.JTextField txt_ChuaCoThe_sdt;
    private javax.swing.JTextField txt_DSMuon_TimKiem;
    private javax.swing.JTextField txt_DSTra_TimKiem;
    private javax.swing.JTextField txt_GHT_idThe;
    private javax.swing.JTextField txt_GHT_ngayDK;
    private javax.swing.JTextField txt_GHT_ngayHH;
    private javax.swing.JTextField txt_GHT_trangThai;
    private javax.swing.JTextArea txt_QLThe_DiaChi;
    private javax.swing.JTextField txt_QLThe_cccd;
    private javax.swing.JTextField txt_QLThe_name;
    private javax.swing.JTextField txt_QLThe_sdt;
    private javax.swing.JTextField txt_SoDocGia;
    private javax.swing.JTextField txt_SoLuongSach;
    private javax.swing.JTextField txt_SoPhieuMuon;
    private javax.swing.JTextField txt_SoPhieuTra;
    private javax.swing.JTextField txt_TVMuon_HanTra;
    private javax.swing.JTextField txt_TVMuon_NgayMuon;
    private javax.swing.JTextField txt_TVMuon_PhiDatCoc;
    private javax.swing.JTextField txt_TVMuon_TenSach;
    private javax.swing.JTextField txt_TVMuon_TimKiemSach;
    private javax.swing.JTextField txt_TVMuon_TrangThai;
    private javax.swing.JTextField txt_TVMuon_idSach;
    private javax.swing.JTextField txt_TVMuon_idThe;
    private javax.swing.JTextField txt_TimKiemThe;
    // End of variables declaration//GEN-END:variables
}
