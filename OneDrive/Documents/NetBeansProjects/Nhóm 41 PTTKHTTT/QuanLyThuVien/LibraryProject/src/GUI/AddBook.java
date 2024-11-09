/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.BookBLL;
import javax.swing.JPanel;
import DTO.BookDTO;
import DTO.TheLoaiDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import BLL.LoaiSachBUS;
import DTO.NXBDTO;

/**
 *
 * @author ADMIN
 */
public class AddBook extends JPanel {

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

    public BookBLL bBLL;
    public LoaiSachBUS lsBLL;

    public static int soLuong;

    public JPopupMenu jPopupMenu;
    public JButton jButton_addBook;

    public JPanel jPanel_info;

    public AddBook() {
        bBLL = new BookBLL();
        lsBLL = new LoaiSachBUS();
        this.init();
    }

    public void init() {
        soLuong = bBLL.getBookAll().size();
        jLabel_maSach = new JLabel("Mã sách");
        jLabel_tenSach = new JLabel("Tên sách");
        jLabel_tacGia = new JLabel("Tác giả");
        jLabel_tenLoai = new JLabel("Thể loại");
        jLabel_NXB = new JLabel("NXB");
        jLabel_namXB = new JLabel("NămXB");
        jLabel_soLuong = new JLabel("Số lượng");

//        jTextField_maSach = new JTextField(200);
        jTextField_tenSach = new JTextField(200);
        jTextField_tacGia = new JTextField(200);
        jTextField_soLuong = new JTextField(200);

        jButton_addBook = new JButton("Thêm sách");

        jTable_infoBook = new JTable();
        jScrollPane_table = new JScrollPane();

        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        setLayout(null);

        jPanel_info = new JPanel();
        jPanel_info.setBackground(new Color(213, 226, 234));
        jPanel_info.setBorder(BorderFactory.createTitledBorder(null, "Thêm sách", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18), new Color(255, 255, 255)));
        jPanel_info.setLayout(null);

        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã đầu sách", "Tên sách", "Tác giả", "Thể loại", "NXB", "NămXB", "Số lượng copy"};
        tblModel.setColumnIdentifiers(header);
        jTable_infoBook.setModel(tblModel);
        jTable_infoBook.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_infoBook.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_infoBook.getColumnModel().getColumn(1).setPreferredWidth(300);
//        jTable_infoBook.getColumnModel().getColumn(5).setMaxWidth(1500);
        jScrollPane_table.setViewportView(jTable_infoBook);

        ArrayList<BookDTO> listDauSach = bBLL.getBookAll();
        for (BookDTO s : listDauSach) {
            tblModel.addRow(new Object[]{s.getMaDS(), s.getTenSach(), s.getTacGia(), s.getTenLoai(), s.getnxb(), s.getNamXB(), s.getSoLuong()});
        }

        jTable_infoBook.setFont(new Font("Roboto", 0, 18));
        jTable_infoBook.setDefaultEditor(Object.class, null);
        //setRowHeight: chỉnh độ rộng của từng dòng trong bảng
        jTable_infoBook.setRowHeight(50);
        jScrollPane_table.setBounds(30, 55, 1600, 600);
        this.add(jScrollPane_table);

        jLabel_tenSach.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tenSach);
        jLabel_tenSach.setBounds(50, 25, 100, 40);

        jLabel_tacGia.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tacGia);
        jLabel_tacGia.setBounds(50, 100, 100, 40);

        jLabel_tenLoai.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tenLoai);
        jLabel_tenLoai.setBounds(50, 175, 100, 40);

        jLabel_NXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_NXB);
        jLabel_NXB.setBounds(650, 25, 100, 40);

        jLabel_namXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_namXB);
        jLabel_namXB.setBounds(650, 100, 100, 40);

//        jLabel_soLuong.setFont(new Font("Roboto", 0, 18));
//        jPanel_info.add(jLabel_soLuong);
//        jLabel_soLuong.setBounds(650, 175, 100, 40);
        jTextField_tenSach.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jTextField_tenSach);
        jTextField_tenSach.setBounds(200, 25, 300, 40);

        jTextField_tacGia.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jTextField_tacGia);
        jTextField_tacGia.setBounds(200, 100, 300, 40);

        jComboBox_theLoai = new JComboBox<>(getTheLoai());
        jComboBox_theLoai.setBackground(Color.white);
        jComboBox_theLoai.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jComboBox_theLoai);
        jComboBox_theLoai.setBounds(200, 175, 300, 40);

        jComboBox_NXB = new JComboBox<>(getNXB());
        jComboBox_NXB.setBackground(Color.white);
        jComboBox_NXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jComboBox_NXB);
        jComboBox_NXB.setBounds(800, 25, 300, 40);

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
        jComboBox_namXB.setBounds(800, 100, 300, 40);

//        jTextField_soLuong.setFont(new Font("Roboto", 0, 18));
//        jPanel_info.add(jTextField_soLuong);
//        jTextField_soLuong.setBounds(800, 175, 300, 40);
//        jTextField_soLuong.setEnabled(false);
        jButton_addBook.setBackground(new Color(187, 222, 251));
        jButton_addBook.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_addBook);
        jButton_addBook.setBounds(1300, 100, 140, 60);
        jButton_addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                themDauSach();
            }
        });

        this.add(jPanel_info);
        jPanel_info.setBounds(0, 700, this.getMaximumSize().width, this.getMaximumSize().height);
    }

    public void resetFrm() {
        jTextField_tenSach.setEnabled(true);
        jTextField_tenSach.setText("");
        jTextField_tacGia.setText("");
        jComboBox_theLoai.setSelectedItem("");
        jComboBox_NXB.setSelectedItem("");
        jComboBox_namXB.setSelectedItem("");
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
                if (dong != 1) {
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
}
