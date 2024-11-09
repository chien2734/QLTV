/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.CT_PhieuMuonBLL;
import BLL.CartUserBLL;
import BLL.PhieuMuonBLL;
import DTO.CT_PhieuMuonDTO;
import DTO.CartUserDTO;
import DTO.PhieuMuonDTO;
import BLL.BookBLL;
import BLL.BookCopyBLL;
import DTO.BookCopyDTO;
import DTO.BookDTO;
import BLL.CardBUS;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import BLL.LoaiSachBUS;
import DTO.NXBDTO;
import DTO.TheLoaiDTO;
import BLL.ReaderBUS;
import DAL.BookCopyDAO;
import DTO.CardDTO;
import DTO.ReaderDTO;
import java.awt.Dimension;

/**
 *
 * @author ADMIN
 */
public class SearchUserGUI extends JPanel {

    public JButton jButton_dangKi;
    public JButton jButton_timKiem;
    public JComboBox<String> jComboBox_theLoai;
    public JLabel jLabel_NXB;
    public JLabel jLabel_maSach;
    public JLabel jLabel_tenSach;
    public JLabel jLabel_tgia;
    public JLabel jLabel_theLoai;
    public JPanel jPanel_card;
    public JPanel jPanel_search;
    public JLabel tongTien;
    public JScrollPane jScrollPane_book;
    public JScrollPane jScrollPane_card;
    public JTable jTable_card;
    public JTable jTable_sach;
    public JComboBox<String> jComboBox_NXB;
    public JTextField jTextField_maSach;
    public JTextField jTextField_tacGia;
    public JTextField jTextField_tenSach;
    public JComboBox<String> jComboBox_NamXB;
    public DefaultTableModel tblModel;
    public JLabel jLabel_namXB;
    public DefaultTableModel tblModelCard;

    public JPopupMenu jPopupMenu_tableBook;
    public JPopupMenu jPopupMenu_card;
    public JMenuItem thongTinChiTiet;
    public JMenuItem xoaSach;
    public JMenuItem xoaTatCa;
    public String tenDN;

    public PhieuMuonBLL pmBLL = new PhieuMuonBLL();
    public CardBUS tmBLL = new CardBUS();
    public BookBLL bookBLL = new BookBLL();
    public BookCopyBLL bcBLL = new BookCopyBLL();
    public LoaiSachBUS lsBLL = new LoaiSachBUS();
    public ReaderBUS dgBLL = new ReaderBUS();
    public CartUserBLL cuBLL = new CartUserBLL();
    public CT_PhieuMuonBLL ctpmBLL = new CT_PhieuMuonBLL();

    public static int soLuongPhieu;
    public static int tongTienCanThanhToan = 0;

    public SearchUserGUI(String tenDN) {
        this.tenDN = tenDN;
        soLuongPhieu = pmBLL.getBorrowAll().size();
        this.init();
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
    }

    public void init() {
        tongTienCanThanhToan = 0;
        jPanel_search = new JPanel();
        jLabel_maSach = new JLabel();
        jLabel_tenSach = new JLabel();
        jLabel_tgia = new JLabel();
        jLabel_NXB = new JLabel();
        jLabel_theLoai = new JLabel();
        jButton_timKiem = new JButton();
        jTextField_maSach = new JTextField();
        jTextField_tenSach = new JTextField();
        jTextField_tacGia = new JTextField();
        jScrollPane_book = new JScrollPane();
        jTable_sach = new JTable();
        jPanel_card = new JPanel();
        jScrollPane_card = new JScrollPane();
        jTable_card = new JTable();
        jButton_dangKi = new JButton();
        jLabel_namXB = new JLabel();
        jPopupMenu_tableBook = new JPopupMenu();
        jPopupMenu_card = new JPopupMenu();
        thongTinChiTiet = new JMenuItem("Thông tin các bản sách");
        xoaSach = new JMenuItem("Xóa sách");
        xoaTatCa = new JMenuItem("Xóa tất cả sách");
        tongTien = new JLabel("Tổng tiền: ");

        setBackground(new Color(255, 255, 255));
        setLayout(null);

        jPanel_search.setBackground(new Color(236, 242, 243));
        jPanel_search.setBorder(BorderFactory.createTitledBorder(null, "Tìm kiếm", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18))); // NOI18N
        jPanel_search.setLayout(null);

        jLabel_maSach.setFont(new Font("Roboto", 0, 18)); // NOI18N
        jLabel_maSach.setText("Mã sách");
        jPanel_search.add(jLabel_maSach);
        jLabel_maSach.setBounds(40, 40, 90, 25);

        jLabel_tenSach.setFont(new Font("Roboto", 0, 18)); // NOI18N
        jLabel_tenSach.setText("Tên sách");
        jPanel_search.add(jLabel_tenSach);
        jLabel_tenSach.setBounds(40, 140, 150, 25);

        jLabel_tgia.setFont(new Font("Roboto", 0, 18)); // NOI18N
        jLabel_tgia.setText("Tác giả");
        jPanel_search.add(jLabel_tgia);
        jLabel_tgia.setBounds(40, 245, 90, 30);

        jLabel_NXB.setFont(new Font("Roboto", 0, 18)); // NOI18N
        jLabel_NXB.setText("NXB");
        jPanel_search.add(jLabel_NXB);
        jLabel_NXB.setBounds(420, 40, 74, 25);

        jLabel_namXB.setFont(new Font("Roboto", 0, 18)); // NOI18N
        jLabel_namXB.setText("NamXB");
        jPanel_search.add(jLabel_namXB);
        jLabel_namXB.setBounds(420, 140, 110, 25);

        jLabel_theLoai.setFont(new Font("Roboto", 0, 18)); // NOI18N
        jLabel_theLoai.setText("Thể loại");
        jPanel_search.add(jLabel_theLoai);
        jLabel_theLoai.setBounds(420, 240, 100, 30);

        jButton_timKiem.setBackground(new Color(102, 153, 255));
        jButton_timKiem.setFont(new Font("Roboto", 1, 20)); // NOI18N
        jButton_timKiem.setForeground(new Color(255, 255, 255));
        jButton_timKiem.setText("Tìm kiếm");
        jPanel_search.add(jButton_timKiem);
        jButton_timKiem.setBounds(300, 370, 130, 40);
        jButton_timKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timKiemSach();
            }
        });

        jComboBox_NXB = new JComboBox<>(getNXB());
        jComboBox_NXB.setFont(new Font("Roboto", 0, 18));
        jComboBox_NXB.setBackground(Color.white);
        jPanel_search.add(jComboBox_NXB);
        jComboBox_NXB.setBounds(420, 70, 300, 40);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ArrayList<String> listNamXB = new ArrayList<>();
        listNamXB.add("");
        for (int i = 1950; i <= year; i++) {
            listNamXB.add(i + "");
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listNamXB.toArray(new String[0]));
        jComboBox_NamXB = new JComboBox<>(model);
        jComboBox_NamXB.setFont(new Font("Roboto", 0, 18));
        jComboBox_NamXB.setBackground(Color.white);
        jPanel_search.add(jComboBox_NamXB);
        jComboBox_NamXB.setBounds(420, 170, 300, 40);

        jPanel_search.add(jTextField_maSach);
        jTextField_maSach.setBounds(30, 70, 300, 40);

        jPanel_search.add(jTextField_tenSach);
        jTextField_tenSach.setBounds(30, 170, 300, 40);

        jPanel_search.add(jTextField_tacGia);
        jTextField_tacGia.setBounds(30, 280, 300, 40);

        jComboBox_theLoai = new JComboBox<>(getTheLoai());
        jComboBox_theLoai.setFont(new Font("Roboto", 0, 18));
        jComboBox_theLoai.setBackground(Color.white);
        jPanel_search.add(jComboBox_theLoai);
        jComboBox_theLoai.setBounds(420, 280, 300, 40);

        add(jPanel_search);
        jPanel_search.setBounds(30, 30, 800, 450);

        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã đầu sách", "Tên sách", "Tác giả", "Thể loại", "NXB", "NămXB", "Số lượng copy"};
        tblModel.setColumnIdentifiers(header);
        jTable_sach.setModel(tblModel);
        jTable_sach.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_sach.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_sach.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable_sach.setDefaultEditor(Object.class, null);
        jScrollPane_book.setViewportView(jTable_sach);

        ArrayList<BookDTO> listDauSach = bookBLL.getBookAll();
        for (BookDTO s : listDauSach) {
            tblModel.addRow(new Object[]{s.getMaDS(), s.getTenSach(), s.getTacGia(), s.getTenLoai(), s.getnxb(), s.getNamXB(), s.getSoLuong()});
        }

        jTable_sach.setFont(new Font("Roboto", 0, 18));
        //setRowHeight: chỉnh độ rộng của từng dòng trong bảng
        jTable_sach.setRowHeight(50);
        jScrollPane_book.setBounds(30, 500, 1600, 440);
        this.add(jScrollPane_book);

        jPanel_card.setBorder(BorderFactory.createTitledBorder(null, "Giỏ đăng kí", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18))); // NOI18N
        jPanel_card.setLayout(null);

        tblModelCard = new DefaultTableModel();
        String[] headerCard = new String[]{"Mã sách", "Tên sách", "Giá mượn"};
        tblModelCard.setColumnIdentifiers(headerCard);
        jTable_card.setModel(tblModelCard);
        jTable_card.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_card.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_card.getColumnModel().getColumn(1).setPreferredWidth(300);
        jScrollPane_card.setViewportView(jTable_card);

        CardDTO tm = tmBLL.getCardsByIdMaDG(new CardDTO(tenDN));
        ReaderDTO dg = dgBLL.selectById(tenDN);
        if (dg.getTrangThai() != 0) {
            ArrayList<CartUserDTO> listcard = cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'");
            for (CartUserDTO s : listcard) {
                tblModelCard.addRow(new Object[]{s.getMaSach(), s.getTenSach(), s.getGiaMuon()});
                tongTienCanThanhToan += s.getGiaMuon();
            }
        }

        jTable_card.setFont(new Font("Roboto", 0, 18));
        //setRowHeight: chỉnh độ rộng của từng dòng trong bảng
        jTable_card.setRowHeight(50);
        jTable_card.setDefaultEditor(Object.class, null);
        jScrollPane_card.setBounds(20, 30, 650, 340);
        jPanel_card.add(jScrollPane_card);

        tongTien.setText("Tổng tiền: " + tongTienCanThanhToan + "đ");
        tongTien.setBounds(100, 380, 500, 50);
        tongTien.setFont(new Font("Roboto", 0, 20));
        jPanel_card.add(tongTien);

        jButton_dangKi.setBackground(new Color(102, 153, 255));
        jButton_dangKi.setFont(new Font("Roboto", 1, 20)); // NOI18N
        jButton_dangKi.setText("Đăng kí");
        jPanel_card.add(jButton_dangKi);
        jButton_dangKi.setBounds(400, 380, 150, 50);
        jButton_dangKi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dangKiMuon();
            }
        });

        thongTinChiTiet.setBackground(Color.white);
        thongTinChiTiet.setFont(new Font("Roboto", 0, 18));
        jPopupMenu_tableBook.add(thongTinChiTiet);
        jTable_sach.setComponentPopupMenu(jPopupMenu_tableBook);
        thongTinChiTiet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                detailBooks();
            }
        });

        xoaSach.setBackground(Color.white);
        xoaSach.setFont(new Font("Roboto", 0, 18));
        jPopupMenu_card.add(xoaSach);
        xoaSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                XoaMotSach();
            }
        });

        xoaTatCa.setBackground(Color.white);
        xoaTatCa.setFont(new Font("Roboto", 0, 18));
        jPopupMenu_card.add(xoaTatCa);
        xoaTatCa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                XoaTatCaSach();
            }
        });

        jTable_card.setComponentPopupMenu(jPopupMenu_card);

        add(jPanel_card);
        jPanel_card.setBounds(900, 30, 690, 450);
    }

    public DefaultComboBoxModel<String> getTheLoai() {
        ArrayList<TheLoaiDTO> theLoai = lsBLL.getTheLoaiAll();
        ArrayList<String> listTheLoai = new ArrayList<>();
        listTheLoai.add("");
        for (TheLoaiDTO tl : theLoai) {
            listTheLoai.add(tl.getTenLoai());
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listTheLoai.toArray(new String[0]));
        return model;
    }

    public DefaultComboBoxModel<String> getNXB() {
        ArrayList<NXBDTO> nxb = lsBLL.getNXBAll();
        ArrayList<String> listNXB = new ArrayList<>();
        listNXB.add("");
        for (NXBDTO tl : nxb) {
            listNXB.add(tl.getTenNXB());
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listNXB.toArray(new String[0]));
        return model;
    }

    public void detailBooks() {
        int selecRow = jTable_sach.getSelectedRow();
        if (selecRow != -1) {
            String maDS = (String) jTable_sach.getValueAt(selecRow, 0);
            System.out.println(maDS);
            new DetailBooks(maDS, tenDN, this);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách cần cập nhật", "warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void XoaMotSach() {
        int selecRow = jTable_card.getSelectedRow();
        if (selecRow != -1) {
            String maSach = (String) jTable_card.getValueAt(selecRow, 0);
            CardDTO tm = tmBLL.getCardsByIdMaDG(new CardDTO(tenDN));
            ArrayList<CartUserDTO> listcard = cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'");
            int dong = 0;
            for (CartUserDTO c : listcard) {
                if (c.getMaSach().equals(maSach)) {
                    dong = cuBLL.deleteCartUser1(c);
                    break;
                }
            }
            if (dong == 1) {
                loadDataforTableCard(cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'"));
                JOptionPane.showMessageDialog(null, "Xóa sách trong giỏ thành công", "del", JOptionPane.INFORMATION_MESSAGE);
            } else {
                loadDataforTableCard(cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'"));
                JOptionPane.showMessageDialog(null, "Xóa sách trong giỏ thất bại", "warning", JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách cần xóa", "warning", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public void XoaTatCaSach() {
        int check = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hết", "warning", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            CardDTO tm = tmBLL.getCardsByIdMaDG(new CardDTO(tenDN));
            ArrayList<CartUserDTO> listcard = cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'");
            int dong = cuBLL.deleteCartUser(new CartUserDTO(tm.getMaTheMuon(), "", "", 0));
            if (dong != 0) {
                loadDataforTableCard(cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'"));
                JOptionPane.showConfirmDialog(null, "Xóa giỏ hàng thành công", "info", JOptionPane.YES_NO_OPTION);
            }
        }

    }

    public void timKiemSach() {
        String maSach = jTextField_maSach.getText();
        String tenSach = jTextField_tenSach.getText();
        String tacGia = jTextField_tacGia.getText();
        String nhaxb = jComboBox_NXB.getSelectedItem().toString();
        String theLoai = jComboBox_theLoai.getSelectedItem().toString();
        String namXB = jComboBox_NamXB.getSelectedItem().toString();

        if (maSach.equals("") && tenSach.equals("") && tacGia.equals("") && nhaxb.equals("") && theLoai.equals("") && namXB.equals("")) {
            int ques = JOptionPane.showConfirmDialog(null, "Không có dữ liệu để hiển thị. Bạn muốn hiển thị toàn bộ?", "error", JOptionPane.YES_NO_OPTION);
            if (ques == JOptionPane.YES_OPTION) {
                ArrayList<BookDTO> listTimKiemSach = bookBLL.getBookAll();
                loadDataforTable(listTimKiemSach);
            } else {
                JOptionPane.showMessageDialog(null, "Yêu cầu nhập dữ liệu để tìm kiếm", "info", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            String sql = "";
            if (!maSach.equals("")) {
                sql += " AND DS.MaDS LIKE N'%" + maSach + "%'";
            }
            if (!tenSach.equals("")) {
                sql += " AND DS.TenSach LIKE N'%" + tenSach + "%'";
            }
            if (!tacGia.equals("")) {
                sql += " AND DS.TacGia LIKE N'%" + tacGia + "%'";
            }
            if (!nhaxb.equals("")) {
                sql += " AND DS.NXB LIKE N'%" + nhaxb + "%'";
            }
            if (!theLoai.equals("")) {
                sql += " AND TL.TenLoai LIKE N'%" + theLoai + "%'";
            }
            if (!namXB.equals("")) {
                sql += " AND DS.NamXB = " + Integer.valueOf(namXB);
            }
            ArrayList<BookDTO> listTimKiem = bookBLL.getBookByCondition(sql);
            loadDataforTable(listTimKiem);
        }

    }

    public void loadDataforTable(ArrayList<BookDTO> list) {
        try {
            tblModel.setRowCount(0);
            for (BookDTO s : list) {
                tblModel.addRow(new Object[]{s.getMaDS(), s.getTenSach(), s.getTacGia(), s.getTenLoai(), s.getnxb(), s.getNamXB(), s.getSoLuong()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadDataforTableCard(ArrayList<CartUserDTO> list) {
        try {
            tblModelCard.setRowCount(0);
            tongTienCanThanhToan = 0;
            for (CartUserDTO s : list) {
                tblModelCard.addRow(new Object[]{s.getMaSach(), s.getTenSach(), s.getGiaMuon()});
                tongTienCanThanhToan += s.getGiaMuon();
            }
            tongTien.setText("Tổng tiền: " + tongTienCanThanhToan + "đ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dangKiMuon() {
        CardDTO tm = tmBLL.getCardsByIdMaDG(new CardDTO(tenDN));
        ArrayList<CartUserDTO> listGio = cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'");
        ArrayList<PhieuMuonDTO> listPM = pmBLL.getBorrowByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'");
        int countSoLuongSachDaMuon = 0;
        for (PhieuMuonDTO p : listPM) {
            countSoLuongSachDaMuon += ctpmBLL.getDetailBorrowByCondition("MaPhieu = '" + p.getMaPhieu() + "'").size();
        }
        if (countSoLuongSachDaMuon >= 5) {
            JOptionPane.showMessageDialog(null, "Bạn đã mượn quá 5 cuốn. Vui lòng trả để mượn sách!", "info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            boolean ktra = true;
            for (CartUserDTO card : listGio) {
                BookCopyDTO book = bcBLL.getBookCopyById(new BookCopyDTO(card.getMaSach()));
                if (!book.getTinhTrang().equals("Hiện có")) {
                    ktra = false;
                    JOptionPane.showMessageDialog(null, "Sách" + book.getMaSach() + " đã được người khác mượn.", "error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (ktra == true) {
                int check = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đăng kí hết sách trong giỏ?", "update", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    soLuongPhieu++;
                    ReaderDTO dg = dgBLL.selectById(tenDN);
                    System.out.println(".." + dg.toString());
                    String maPhieu;
                    if (soLuongPhieu < 10) {
                        maPhieu = "PM000" + soLuongPhieu;
                    } else {
                        maPhieu = "PM00" + soLuongPhieu;
                    }
                    String maThe = tm.getMaTheMuon();
                    String tenDGM = dg.getTenDG();
                    LocalDate todayLocalDate = LocalDate.now();
                    Date ngayDK = Date.valueOf(todayLocalDate);
                    LocalDate fiveDaysAfterLocalDate = todayLocalDate.plusDays(5);
                    Date hanNhan = java.sql.Date.valueOf(fiveDaysAfterLocalDate);
                    int tienCoc = 0;
                    for (CartUserDTO a : listGio) {
                        tienCoc += a.getGiaMuon();
                    }
                    PhieuMuonDTO pm = new PhieuMuonDTO(maPhieu, maThe, tenDGM, ngayDK, hanNhan, null, null, tienCoc, "Có thể sử dụng");
                    System.out.println(pm.toString());
                    int kq = pmBLL.insertBorrow(pm);
                    if (kq != 0) {
                        ArrayList<CartUserDTO> card = cuBLL.getCartUserByCondition("MaTheMuon = '" + tm.getMaTheMuon() + "'");
                        for (CartUserDTO c : card) {
                            String tinhTrang = "Hiện có";
                            ctpmBLL.insertDetailBorrow(new CT_PhieuMuonDTO(maPhieu, c.getMaSach(), c.getTenSach(), (int) c.getGiaMuon(), tinhTrang));
                            BookCopyDTO bcp = bcBLL.getBookCopyById(new BookCopyDTO(c.getMaSach()));
                            bcp.setTinhTrang("Đã mượn");
                            bcBLL.updateBook(bcp);
                            System.out.println("bcp::::" + bcp.toString());
                        }
                        loadDataforTable(bookBLL.getBookAll());
                        int dong = cuBLL.deleteCartUser(new CartUserDTO(maThe, "", ""));
                        if (dong != 0) {
                            JOptionPane.showMessageDialog(null, "Đăng kí thành công. Vui lòng nhận sách trước ngày " + hanNhan.toString(), "info", JOptionPane.INFORMATION_MESSAGE);
                            loadDataforTableCard(cuBLL.getCartUserAll());
                        }
                    }

                }
            }
        }

    }

}
