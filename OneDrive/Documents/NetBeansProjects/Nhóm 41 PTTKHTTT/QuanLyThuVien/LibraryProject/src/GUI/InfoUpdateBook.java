/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.CT_PhieuMuonBLL;
import DTO.CT_PhieuMuonDTO;
import BLL.BookBLL;
import BLL.BookCopyBLL;
import DTO.BookCopyDTO;
import DTO.BookDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import BLL.LoaiSachBUS;
import DTO.NXBDTO;
import DTO.TheLoaiDTO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class InfoUpdateBook extends JPanel {

    public JLabel jLabel_maSach;
    public JLabel jLabel_tenSach;
    public JLabel jLabel_tacGia;
    public JLabel jLabel_tenLoai;
    public JLabel jLabel_NXB;
    public JLabel jLabel_namXB;
    public JLabel jLabel_soLuong;

    public JTextField jTextField_maSach;
    public JTextField jTextField_tenSach;
    public JTextField jTextField_tacGia;
    public JComboBox<String> jComboBox_theLoai;
    public JComboBox<String> jComboBox_NXB;
    public JComboBox<String> jComboBox_namXB;
    public JTextField jTextField_soLuong;

    public DefaultTableModel tblModel;
    public JTable jTable_infoBook;
    public JScrollPane jScrollPane_table;

    public JButton jButton_search;
    public JButton jButton_capNhat;
    public JButton jButton_xemChiTiet;
    public JButton jButton_them;

    public JPanel jPanel_info;

    public static int soLuong;

    public JPopupMenu jPopupMenu;
    public JMenuItem jMenuXoa;
    public JMenuItem jMenuCapNhat;
    public JMenuItem jMenuThongTinCopy;
    public BookBLL bBLL = new BookBLL();
    public LoaiSachBUS lsBLL = new LoaiSachBUS();
    public CT_PhieuMuonBLL ctpmBLL = new CT_PhieuMuonBLL();
    public BookCopyBLL bcBLL = new BookCopyBLL();
    private JScrollPane jScrollPane_tenSach;

    public InfoUpdateBook() {
        soLuong = bBLL.getBookAll1().size();
        this.init();
    }

    public void init() {

        jLabel_maSach = new JLabel("Mã sách");
        jLabel_tenSach = new JLabel("Tên sách");
        jLabel_tacGia = new JLabel("Tác giả");
        jLabel_tenLoai = new JLabel("Thể loại");
        jLabel_NXB = new JLabel("NXB");
        jLabel_namXB = new JLabel("NămXB");
        jLabel_soLuong = new JLabel("Số lượng");

        jTextField_maSach = new JTextField(200);
        jTextField_tenSach = new JTextField();
        jTextField_tacGia = new JTextField(200);

        jButton_search = new JButton("Tìm kiếm");
        jButton_capNhat = new JButton("Update");
        jButton_xemChiTiet = new JButton("Chi tiết bản sách");
        jButton_them = new JButton("Thêm sách");

        jTable_infoBook = new JTable();
        jScrollPane_table = new JScrollPane();

        jPopupMenu = new JPopupMenu();
        jMenuXoa = new JMenuItem("Xóa");
        jMenuCapNhat = new JMenuItem("Cập nhật");
        jMenuThongTinCopy = new JMenuItem("Thông tin các bản sách");

        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        setLayout(null);

        jPanel_info = new JPanel();
        jPanel_info.setBackground(new Color(213, 226, 234));
        jPanel_info.setBorder(BorderFactory.createTitledBorder(null, "Thông tin sách", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18), new Color(255, 255, 255)));
        jPanel_info.setLayout(null);

        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã đầu sách", "Tên sách", "Tác giả", "Thể loại", "NXB", "NămXB"};
        tblModel.setColumnIdentifiers(header);
        jTable_infoBook.setModel(tblModel);
        jTable_infoBook.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_infoBook.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_infoBook.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable_infoBook.getColumnModel().getColumn(5).setMaxWidth(1000);
        jScrollPane_table.setViewportView(jTable_infoBook);

        ArrayList<BookDTO> listDauSach = bBLL.getBookAll();
        for (BookDTO s : listDauSach) {
            tblModel.addRow(new Object[]{s.getMaDS(), s.getTenSach(), s.getTacGia(), s.getTenLoai(), s.getnxb(), s.getNamXB(), s.getSoLuong()});
        }

        jTable_infoBook.setFont(new Font("Roboto", 0, 18));
        //setRowHeight: chỉnh độ rộng của từng dòng trong bảng
        jTable_infoBook.setRowHeight(50);
        jTable_infoBook.setDefaultEditor(Object.class, null);
        jScrollPane_table.setBounds(30, 40, 1600, 600);
        this.add(jScrollPane_table);

        jLabel_maSach.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_maSach);
        jLabel_maSach.setBounds(50, 25, 100, 40);

        jLabel_tenSach.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tenSach);
        jLabel_tenSach.setBounds(50, 100, 100, 40);

        jLabel_tacGia.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tacGia);
        jLabel_tacGia.setBounds(50, 175, 100, 40);

        jLabel_tenLoai.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tenLoai);
        jLabel_tenLoai.setBounds(650, 25, 100, 40);

        jLabel_NXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_NXB);
        jLabel_NXB.setBounds(650, 100, 100, 40);

        jLabel_namXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_namXB);
        jLabel_namXB.setBounds(650, 175, 100, 40);

        jTextField_maSach.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jTextField_maSach);
        jTextField_maSach.setBounds(200, 25, 300, 40);

        jTextField_tenSach.setFont(new Font("Roboto", 0, 18));
        jScrollPane_tenSach = new JScrollPane();
        jScrollPane_tenSach.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane_tenSach.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane_tenSach.setBounds(200, 100, 300, 45);
        jScrollPane_tenSach.setViewportView(jTextField_tenSach);
        jPanel_info.add(jScrollPane_tenSach);
        jScrollPane_tenSach.setEnabled(true);

        jTextField_tacGia.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jTextField_tacGia);
        jTextField_tacGia.setBounds(200, 175, 300, 40);

        jComboBox_theLoai = new JComboBox<>(getTheLoai());
        jComboBox_theLoai.setBackground(Color.white);
        jComboBox_theLoai.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jComboBox_theLoai);
        jComboBox_theLoai.setBounds(800, 25, 300, 40);

        jComboBox_NXB = new JComboBox<>(getNXB());
        jComboBox_NXB.setBackground(Color.white);
        jComboBox_NXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jComboBox_NXB);
        jComboBox_NXB.setBounds(800, 100, 300, 40);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ArrayList<String> listNamXB = new ArrayList<>();
        listNamXB.add("");
        for (int i = 1950; i <= year; i++) {
            listNamXB.add(i + "");
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listNamXB.toArray(new String[0]));
        jComboBox_namXB = new JComboBox<>(model);
        jComboBox_namXB.setBackground(Color.white);
        jComboBox_namXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jComboBox_namXB);
        jComboBox_namXB.setBounds(800, 175, 300, 40);

        jButton_search.setBackground(new Color(187, 222, 251));
        jButton_search.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_search);
        jButton_search.setBounds(1200, 80, 140, 40);
        jButton_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timKiemSach();
            }
        });

        jButton_them.setBackground(new Color(187, 222, 251));
        jButton_them.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_them);
        jButton_them.setBounds(1400, 80, 140, 40);
        jButton_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                themDauSach();
            }
        });

        jMenuXoa.setBackground(Color.white);
        jMenuXoa.setFont(new Font("Roboto", 0, 18));
        jPopupMenu.add(jMenuXoa);
        jMenuXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setDeleteDB();
            }
        });

        jPopupMenu.addSeparator();

        jMenuCapNhat.setBackground(Color.white);
        jMenuCapNhat.setFont(new Font("Roboto", 0, 18));
        jPopupMenu.add(jMenuCapNhat);
        jMenuCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setTextForUpdate();
            }
        });

        jPopupMenu.addSeparator();

        jMenuThongTinCopy.setBackground(Color.white);
        jMenuThongTinCopy.setFont(new Font("Roboto", 0, 18));
        jMenuThongTinCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addDetailCopyBook();
            }
        });
        jPopupMenu.add(jMenuThongTinCopy);

        jTable_infoBook.setComponentPopupMenu(jPopupMenu);

        this.add(jPanel_info);
        jPanel_info.setBounds(0, 690, this.getMaximumSize().width, this.getMaximumSize().height);

        jButton_capNhat.setBackground(new Color(187, 222, 251));
        jButton_capNhat.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_capNhat);
        jButton_capNhat.setBounds(1200, 160, 140, 40);
        jButton_capNhat.setEnabled(false);
        jButton_capNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addJButtonCapNhat();
            }
        });
    }

    public void timKiemSach() {
        String maSach = jTextField_maSach.getText();
        String tenSach = jTextField_tenSach.getText();
        String tacGia = jTextField_tacGia.getText();
        String nhaxb = jComboBox_NXB.getSelectedItem().toString();
        String theLoai = jComboBox_theLoai.getSelectedItem().toString();
        String namXB = jComboBox_namXB.getSelectedItem().toString();

        if (maSach.equals("") && tenSach.equals("") && tacGia.equals("") && nhaxb.equals("") && theLoai.equals("") && namXB.equals("")) {
            int ques = JOptionPane.showConfirmDialog(null, "Không có dữ liệu để hiển thị. Bạn muốn hiển thị toàn bộ?", "error", JOptionPane.YES_NO_OPTION);
            if (ques == JOptionPane.YES_OPTION) {
                ArrayList<BookDTO> listTimKiemSach = bBLL.getBookAll();
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
                sql += " AND N.TenNXB LIKE N'%" + nhaxb + "%'";
            }
            if (!theLoai.equals("")) {
                sql += " AND TL.TenLoai LIKE N'%" + theLoai + "%'";
            }
            if (!namXB.equals("")) {
                sql += " AND DS.NamXB = " + Integer.valueOf(namXB);
            }
            ArrayList<BookDTO> listTimKiem = bBLL.getBookByCondition(sql);
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

    public void addJButtonCapNhat() {
        BookDTO bookNew = updateBook();
        if (bookNew != null) {
            System.out.println("bookNew: " + bookNew.toString());
            String sql = "AND DS.TrangThai = 1 AND TL.TenLoai = N'" + bookNew.getTenLoai() + "' AND DS.TenSach = N'"
                    + bookNew.getTenSach() + "' AND DS.TacGia = N'" + bookNew.getTacGia() + "' AND N.TenNXB = N'"
                    + bookNew.getnxb() + "' AND DS.NamXB = " + bookNew.getNamXB() + " AND DS.MaDS != '" + bookNew.getMaDS() + "'";
            ArrayList<BookDTO> listBook = bBLL.getBookByCondition(sql);
            if (listBook.isEmpty()) {
                String tenLoai = bookNew.getTenLoai();
                TheLoaiDTO theLoai = lsBLL.getTheLoaiById(new TheLoaiDTO(tenLoai));
                System.out.println("Đây là thể loại nè: " + theLoai);
                bookNew.setTenLoai(theLoai.getMaLoai());

                String tenNXB = bookNew.getnxb();
                NXBDTO nxb = lsBLL.getNXBById(new NXBDTO(tenNXB));
                bookNew.setnxb(nxb.getMaNXB());

                System.out.println("Dữ liệu để up " + bookNew);
                bBLL.updateBook(bookNew);
                ArrayList<BookDTO> list = bBLL.getBookAll();
                loadDataforTable(list);
                JOptionPane.showMessageDialog(null, "Cập nhật sách thành công", "update", JOptionPane.INFORMATION_MESSAGE);
                jButton_search.setEnabled(true);
                jButton_them.setEnabled(true);
                resetFrm();
            } else {
                JOptionPane.showMessageDialog(null, "Sách đã tồn tại, không thể cập nhật", "warning", JOptionPane.INFORMATION_MESSAGE);
                jButton_search.setEnabled(true);
                jButton_them.setEnabled(true);
                resetFrm();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dữ liệu sai.", "warning", JOptionPane.INFORMATION_MESSAGE);
        }

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

    public BookDTO bookOriginal() {
        BookDTO bookGetSL = null;
        int selecRow = jTable_infoBook.getSelectedRow();
        if (selecRow != -1) {
            String maDS = (String) jTable_infoBook.getValueAt(selecRow, 0);
            bookGetSL = bBLL.getBookById(new BookDTO(maDS));

        }
        return bookGetSL;
    }

    public void setTextForUpdate() {
        int selecRow = jTable_infoBook.getSelectedRow();
        if (selecRow != -1) {
            String maDS = (String) jTable_infoBook.getValueAt(selecRow, 0);
            String tenSach = (String) jTable_infoBook.getValueAt(selecRow, 1);
            String tenLoai = (String) jTable_infoBook.getValueAt(selecRow, 3);
            String tacGia = (String) jTable_infoBook.getValueAt(selecRow, 2);
            String NXB = (String) jTable_infoBook.getValueAt(selecRow, 4);
            int namXB = (int) jTable_infoBook.getValueAt(selecRow, 5);

            jTextField_maSach.setText(maDS);
            jTextField_maSach.setEnabled(false);
            jButton_search.setEnabled(false);
            jButton_them.setEnabled(false);
            jTextField_tenSach.setText(tenSach);
            jTextField_tacGia.setText(tacGia);
            jComboBox_theLoai.setSelectedItem(tenLoai);
            jComboBox_NXB.setSelectedItem(NXB);
            jComboBox_namXB.setSelectedItem(namXB + "");
            jButton_capNhat.setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách cần cập nhật", "warning", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public BookDTO updateBook() {
        String maSV = jTextField_maSach.getText();
        String tenSach = jTextField_tenSach.getText();
        String tacGia = jTextField_tacGia.getText();
        String theLoai = jComboBox_theLoai.getSelectedItem().toString();
        String NXB = jComboBox_NXB.getSelectedItem().toString();
        String NamXB = jComboBox_namXB.getSelectedItem().toString();
        String checkTacGia = "[^0-9]*";
        Pattern pattern = Pattern.compile(checkTacGia);
        Matcher matcherTacGia = pattern.matcher(jTextField_tacGia.getText());
        if (tenSach.equals("") || (tacGia.equals("") || !matcherTacGia.matches())) {
            return null;
        } else {
            BookDTO bookNew = new BookDTO(maSV, tenSach, tacGia, theLoai, NXB, NamXB);
            BookDTO bookGetSL = bBLL.getBookById(bookNew);
            bookNew.setSoLuong(bookGetSL.getSoLuong());
            bookNew.setTrangThai(bookGetSL.getTrangThai());
            return bookNew;
        }

    }

    public void resetFrm() {
        jTextField_maSach.setText("");
        jTextField_maSach.setEnabled(true);
        jButton_search.setEnabled(true);
        jScrollPane_tenSach.setEnabled(true);
        jTextField_tenSach.setText("");
        jTextField_tacGia.setText("");
        jComboBox_theLoai.setSelectedItem("");
        jComboBox_NXB.setSelectedItem("");
        jComboBox_namXB.setSelectedItem("");
        jButton_capNhat.setEnabled(false);
    }

    public void setDeleteDB() {
        int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc đang muốn xóa sách này?", "warning", JOptionPane.YES_NO_OPTION);
        if (yes == JOptionPane.YES_OPTION) {
            int selecRow = jTable_infoBook.getSelectedRow();
            if (selecRow != -1) {
                String maDS = (String) jTable_infoBook.getValueAt(selecRow, 0);
                String tenSach = (String) jTable_infoBook.getValueAt(selecRow, 1);
                String tenLoai = (String) jTable_infoBook.getValueAt(selecRow, 3);
                String tacGia = (String) jTable_infoBook.getValueAt(selecRow, 2);
                String NXB = (String) jTable_infoBook.getValueAt(selecRow, 4);
                int namXB = (int) jTable_infoBook.getValueAt(selecRow, 5);

                NXBDTO nxb = lsBLL.getNXBById(new NXBDTO(NXB));
                TheLoaiDTO tl = lsBLL.getTheLoaiById(new TheLoaiDTO(tenLoai));
                BookDTO book = new BookDTO(maDS, tenSach, tacGia, tl.getMaLoai(), nxb.getMaNXB(), namXB + "");
                System.out.println(book.toString());
                ArrayList<BookCopyDTO> listBookCopy = bcBLL.getBookCopyByCondition(" AND MaDS = '" + maDS + "'");
                boolean check = true;
                for (BookCopyDTO b : listBookCopy) {
                    if (!b.getTinhTrang().equals("Hiện có")) {
                        check = false;
                        JOptionPane.showMessageDialog(null, "Sách đang được mượn. Vui lòng xóa sau", "warning", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                if (check) {
                    book.setTrangThai(0);
                    int kqua = bBLL.updateBook(book);
                    if (kqua != 0) {
                        JOptionPane.showMessageDialog(null, "Xóa sách thành công.", "warning", JOptionPane.INFORMATION_MESSAGE);
                        loadDataforTable(bBLL.getBookAll());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách cần xóa", "warning", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void addDetailCopyBook() {
        int selecRow = jTable_infoBook.getSelectedRow();
        String maDS = (String) jTable_infoBook.getValueAt(selecRow, 0);
        new DetailBookCopy(maDS);
    }

    public boolean checkDkNhap() {
        boolean dieuKien = true;
        String checkTacGia = "[^0-9]*";
        Pattern pattern = Pattern.compile(checkTacGia);
        Matcher matcherTacGia = pattern.matcher(jTextField_tacGia.getText());
        Matcher matcherNXB = pattern.matcher(jComboBox_NXB.getSelectedItem().toString());
        if (!matcherTacGia.matches()) {
            JOptionPane.showMessageDialog(null, "Yêu cầu nhập đúng dữ liệu", "warning", JOptionPane.INFORMATION_MESSAGE);
            dieuKien = false;
        } else if (!matcherNXB.matches()) {
            JOptionPane.showMessageDialog(null, "Yêu cầu nhập đúng dữ liệu", "warning", JOptionPane.INFORMATION_MESSAGE);
            dieuKien = false;
        }
        return dieuKien;
    }

    public void themDauSach() {
        JOptionPane.showMessageDialog(null, "Với việc thêm đầu sách thì mã sách sẽ được tự động.", "warning", JOptionPane.INFORMATION_MESSAGE);
        if (checkDkNhap()) {
            soLuong++;
            System.out.println("soluong: " + soLuong);
            String maSach = "DS00" + this.soLuong;
            String tenSach = this.jTextField_tenSach.getText();
            String tacGia = this.jTextField_tacGia.getText();
            String tenLoai = this.jComboBox_theLoai.getSelectedItem().toString();
            String NXB = this.jComboBox_NXB.getSelectedItem().toString();
            int namXB = Integer.valueOf(this.jComboBox_namXB.getSelectedItem().toString());

            BookDTO book = new BookDTO(maSach, tenSach, tacGia, tenLoai, NXB, namXB, 0, 1);

            String tenLoaiSach = book.getTenLoai();
            TheLoaiDTO theLoai = lsBLL.getTheLoaiById(new TheLoaiDTO(tenLoaiSach));
            book.setTenLoai(theLoai.getMaLoai());

            String tennxb = book.getnxb();
            NXBDTO nxb = lsBLL.getNXBById(new NXBDTO(tennxb));
            book.setnxb(nxb.getMaNXB());

            String sql = "AND DS.TenSach = N'" + tenSach + "' AND DS.TacGia = N'" + tacGia + "' AND DS.MaLoai = N'"
                    + theLoai.getMaLoai() + "' AND DS.MaNXB = N'" + nxb.getMaNXB() + "' AND NamXB = " + namXB;
            ArrayList<BookDTO> listBookExist = bBLL.getBookByCondition(sql);
            if (listBookExist.isEmpty()) {
                int dong = bBLL.insertBook(book);
                System.out.println("Dòng thay đổi bao nhiêu: " + dong);
                if (dong == 1) {
                    JOptionPane.showMessageDialog(null, "Thêm sách thành công", "add", JOptionPane.INFORMATION_MESSAGE);
                    resetFrm();
                    loadDataforTable(bBLL.getBookAll());
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm sách không thành công", "error", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Sách đã tồn tại.", "error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
    
    
    
    
    
    
    
    public void timKiemSach1() {
        String maSach = jTextField_maSach.getText();
        String tenSach = jTextField_tenSach.getText();
        String tacGia = jTextField_tacGia.getText();
        String NXB = jComboBox_NXB.getSelectedItem().toString();
        String namXB = jComboBox_namXB.getSelectedItem().toString();
        String theLoai = jComboBox_theLoai.getSelectedItem().toString();
        if(maSach.equals("") && tenSach.equals("") && tacGia.equals("") && NXB.equals("") && namXB.equals("") && theLoai.equals("")) {
            int yes = JOptionPane.showConfirmDialog(null, "Vui lòng nhập dữ liệu. Nhấn yes để hiển thị toàn bộ sách.", "info", JOptionPane.YES_NO_OPTION);
            if(yes == JOptionPane.YES_OPTION) {
                ArrayList<BookDTO> listBoook = bBLL.getBookAll();
                loadDataforTable(listBoook);
            }
        } else {
            String sql = "";
            if(!maSach.equals("")) {
                sql = " AND DS.MaDS LIKE N'%N " + maSach + "' ";
            } if(!tenSach.equals("")) {
                sql = "AND DS.TenSach LIKE N'%N " + tenSach + "' ";
            } if(!tacGia.equals("")) {
                sql = "AND DS.TacGia LIKE N'%N " + tacGia + "' ";
            } if(!NXB.equals("")) {
                sql = "AND N.TenNXB LIKE N'%N " + NXB + "' ";
            } if(!namXB.equals("")) {
                sql = "AND DS.NamXB LIKE N'%N " + namXB + "' ";
            } if(!theLoai.equals("")) {
                sql = "AND TL.TenLoai LIKE N'%N " + theLoai + "' ";
            }
            ArrayList<BookDTO> list = bBLL.getBookByCondition(sql);
            loadDataforTable(list);
        }
    }
}
