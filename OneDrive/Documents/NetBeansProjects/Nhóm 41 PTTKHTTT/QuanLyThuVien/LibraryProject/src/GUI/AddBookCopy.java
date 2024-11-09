/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.BookBLL;
import BLL.BookCopyBLL;
import DTO.BookCopyDTO;
import DTO.BookDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import BLL.LoaiSachBUS;
import DTO.NXBDTO;
import DTO.TheLoaiDTO;

/**
 *
 * @author ADMIN
 */
public class AddBookCopy extends JPanel {

    public JButton jButton_themCopy;
    public JLabel jLabel_NXB;
    public JLabel jLabel_NXB2;
    public JLabel jLabel_giaCopy;
    public JLabel jLabel_loaiSach;
    public JLabel jLabel_namXB;
    public JLabel jLabel_namXB2;
    public JLabel jLabel_soLuong;
    public JLabel jLabel_tacGia;
    public JLabel jLabel_tacGia2;
    public JLabel jLabel_tenSach;
    public JTextField jTextField_tenSach;
    public JLabel jLabel_theLoai;
    public JLabel jLabel_theLoai2;
    public JPanel jPanel_info;
    public JScrollPane jScrollPane_sach;
    public JSpinner jSpinner_soLuong;
    public JTable jTable_sach;
    public JSpinner jSpinner_giaCopy;
    public JComboBox<String> jComboBoxloaiSach;
    public DefaultTableModel model;

    public BookCopyBLL bcBLL = new BookCopyBLL();
    public BookBLL bBLL = new BookBLL();
    public LoaiSachBUS lsBLL = new LoaiSachBUS();

    public static int soLuongBanSach;

    public AddBookCopy() {
        this.init();
    }

    public void init() {
        soLuongBanSach = bcBLL.getBookCopyAll().size();
        jPanel_info = new JPanel();
        jLabel_tenSach = new JLabel();
        jLabel_soLuong = new JLabel();
        jLabel_loaiSach = new JLabel();
        jLabel_giaCopy = new JLabel();
        jLabel_tacGia = new JLabel();
        jLabel_NXB = new JLabel();
        jLabel_namXB = new JLabel();
        jLabel_theLoai = new JLabel();
        jSpinner_giaCopy = new JSpinner();
        jSpinner_soLuong = new JSpinner();
        jLabel_tacGia2 = new JLabel();
        jLabel_NXB2 = new JLabel();
        jLabel_namXB2 = new JLabel();
        jLabel_theLoai2 = new JLabel();
        jTextField_tenSach = new JTextField();
        jButton_themCopy = new JButton();

        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height - 100));
        setLayout(null);

        jPanel_info.setBackground(new Color(213, 226, 234));
        jPanel_info.setBorder(BorderFactory.createTitledBorder(null, "Thông tin", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18), new Color(255, 255, 255)));
        jPanel_info.setLayout(null);

        jLabel_tenSach.setFont(new Font("Roboto", 0, 18));
        jLabel_tenSach.setText("Tên sách");
        jPanel_info.add(jLabel_tenSach);
        jLabel_tenSach.setBounds(650, 25, 100, 40);

        jLabel_soLuong.setFont(new Font("Roboto", 0, 18));
        jLabel_soLuong.setText("Số lượng:");
        jPanel_info.add(jLabel_soLuong);
        jLabel_soLuong.setBounds(650, 100, 90, 40);

        jLabel_loaiSach.setFont(new Font("Roboto", 0, 18));
        jLabel_loaiSach.setText("Loại sách:");
        jPanel_info.add(jLabel_loaiSach);
        jLabel_loaiSach.setBounds(650, 175, 100, 40);

        jLabel_giaCopy.setFont(new Font("Roboto", 0, 18));
        jLabel_giaCopy.setText("Giá bản sách:");
        jPanel_info.add(jLabel_giaCopy);
        jLabel_giaCopy.setBounds(650, 260, 150, 25);

        jLabel_tacGia.setFont(new Font("Roboto", 0, 18));
        jLabel_tacGia.setText("Tác giả");
        jPanel_info.add(jLabel_tacGia);
        jLabel_tacGia.setBounds(50, 25, 100, 40);

        jLabel_NXB.setFont(new Font("Roboto", 0, 18));
        jLabel_NXB.setText("NXB");
        jPanel_info.add(jLabel_NXB);
        jLabel_NXB.setBounds(50, 100, 100, 40);

        jLabel_namXB.setFont(new Font("Roboto", 0, 18));
        jLabel_namXB.setText("NămXB");
        jPanel_info.add(jLabel_namXB);
        jLabel_namXB.setBounds(50, 175, 100, 40);

        jLabel_theLoai.setFont(new Font("Roboto", 0, 18));
        jLabel_theLoai.setText("Thể loại");
        jPanel_info.add(jLabel_theLoai);
        jLabel_theLoai.setBounds(50, 250, 100, 40);

        jPanel_info.add(jSpinner_giaCopy);
        jSpinner_giaCopy.setFont(new Font("Roboto", 0, 18));
        jSpinner_giaCopy.setBounds(800, 250, 300, 40);
        jPanel_info.add(jSpinner_soLuong);
        jSpinner_soLuong.setBounds(800, 100, 300, 40);
        jSpinner_soLuong.setFont(new java.awt.Font("Roboto", 0, 18));

        String[] list = {"Có thể mượn", "Đọc tại chỗ"};
        jComboBoxloaiSach = new JComboBox<>(list);
        jComboBoxloaiSach.setBackground(Color.white);
        jComboBoxloaiSach.setFont(new java.awt.Font("Roboto", 0, 18));
        jComboBoxloaiSach.setBounds(800, 175, 300, 40);
        jPanel_info.add(jComboBoxloaiSach);

        jLabel_tacGia2.setBackground(new Color(255, 255, 255));
        jLabel_tacGia2.setOpaque(true);
        jPanel_info.add(jLabel_tacGia2);
        jLabel_tacGia2.setFont(new Font("Roboto", 0, 18));
        jLabel_tacGia2.setBounds(200, 25, 300, 40);

        jLabel_NXB2.setBackground(new Color(255, 255, 255));
        jLabel_NXB2.setOpaque(true);
        jLabel_NXB2.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_NXB2);
        jLabel_NXB2.setBounds(200, 100, 300, 40);

        jLabel_namXB2.setBackground(new Color(255, 255, 255));
        jLabel_namXB2.setOpaque(true);
        jLabel_namXB2.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_namXB2);
        jLabel_namXB2.setBounds(200, 175, 300, 40);

        jLabel_theLoai2.setBackground(new Color(255, 255, 255));
        jLabel_theLoai2.setOpaque(true);
        jLabel_theLoai2.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_theLoai2);
        jLabel_theLoai2.setBounds(200, 250, 300, 40);

        jTextField_tenSach.setBackground(new Color(255, 255, 255));
        jTextField_tenSach.setOpaque(true);
        jTextField_tenSach.setFont(new Font("Roboto", 0, 18));
        JScrollPane jScrollPane_tenSach = new JScrollPane();
        jScrollPane_tenSach.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane_tenSach.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane_tenSach.setViewportView(jTextField_tenSach);
        jScrollPane_tenSach.setBounds(800, 25, 320, 40);
        jPanel_info.add(jScrollPane_tenSach);

        jButton_themCopy.setBackground(new Color(59, 138, 195));
        jButton_themCopy.setFont(new Font("Roboto", 1, 18));
        jButton_themCopy.setForeground(new Color(255, 255, 255));
        jButton_themCopy.setText("Thêm bản sách");
        jPanel_info.add(jButton_themCopy);
        jButton_themCopy.setBounds(1300, 150, 190, 60);
        jButton_themCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonThemSachCopyActionPerformed(evt);
            }
        });

        add(jPanel_info);
        jPanel_info.setBounds(0, 620, this.getMaximumSize().width, this.getMaximumSize().height);

        model = new DefaultTableModel(new String[]{
            "Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Năm XB", "Thể loại", "Số lượng"
        }, 0);

        ArrayList<BookDTO> listDauSach = bBLL.getBookAll();
        for (BookDTO s : listDauSach) {
            model.addRow(new Object[]{s.getMaDS(), s.getTenSach(), s.getTacGia(), s.getnxb(), s.getNamXB(), s.getTenLoai(), s.getSoLuong()});
        }

        jTable_sach = new JTable(model);
        jTable_sach.setFont(new java.awt.Font("Roboto", 0, 18));
        jTable_sach.setRowHeight(50);
        jTable_sach.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_sach.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_sach.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable_sach.getTableHeader().setFont(new Font("Roboto", 1, 18));
        jTable_sach.setDefaultEditor(Object.class, null);
        jScrollPane_sach = new JScrollPane(jTable_sach);
        jTable_sach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        add(jScrollPane_sach);
        jScrollPane_sach.setBounds(30, 30, 1600, 550);
    }

    public void jTable1MouseClicked(MouseEvent evt) {
        int selecRow = jTable_sach.getSelectedRow();
        if (selecRow != -1) {
            String maDS = (String) jTable_sach.getValueAt(selecRow, 0);
            String tenSach = (String) jTable_sach.getValueAt(selecRow, 1);
            String tacGia = (String) jTable_sach.getValueAt(selecRow, 2);
            String NXB = (String) jTable_sach.getValueAt(selecRow, 3);
            int namXB = (int) jTable_sach.getValueAt(selecRow, 4);
            String tenLoai = (String) jTable_sach.getValueAt(selecRow, 5);
            int soLuong = (int) jTable_sach.getValueAt(selecRow, 6);
            System.out.println("lấy giá trị ra: " + maDS + ", " + tenSach + ", " + tacGia + ", " + NXB + ", " + namXB + ", " + tenLoai + "," + soLuong);

            jLabel_tacGia2.setText(tacGia);
            jLabel_NXB2.setText(NXB);
            jLabel_namXB2.setText(namXB + "");
            jLabel_theLoai2.setText(tenLoai);
            jTextField_tenSach.setText(tenSach);
            jTextField_tenSach.setEnabled(false);
        }
    }

    public void resetFrm() {
        jLabel_tacGia2.setText("");
        jTextField_tenSach.setEnabled(true);
        jTextField_tenSach.setText("");
        jLabel_NXB2.setText("");
        jLabel_namXB2.setText("");
        jSpinner_soLuong.setValue((int) 0);
        jSpinner_giaCopy.setValue((int) 0);
        jComboBoxloaiSach.setSelectedItem("Có thể mượn");
    }

    public void loadDataforTable(ArrayList<BookDTO> list) {
        try {
            model.setRowCount(0);
            for (BookDTO s : list) {
                model.addRow(new Object[]{s.getMaDS(), s.getTenSach(), s.getTacGia(), s.getTenLoai(), s.getnxb(), s.getNamXB(), s.getSoLuong()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void jButtonThemSachCopyActionPerformed(ActionEvent evt) {
        System.out.println("soLuong bản copy: " + soLuongBanSach);
        int selecRow = jTable_sach.getSelectedRow();
        String maDS = "";
        if (selecRow == -1) {
            JOptionPane.showMessageDialog(null, "Chưa chọn sách cần thêm", "error", JOptionPane.INFORMATION_MESSAGE);
        }
        maDS = (String) jTable_sach.getValueAt(selecRow, 0);
        int soLuongCopy = (int) jSpinner_soLuong.getValue();
        String loaiSach = jComboBoxloaiSach.getSelectedItem().toString();
        int giaBan = (int) jSpinner_giaCopy.getValue();
        String checkNumber = "^[0-9]*$";
        Pattern pattern = Pattern.compile(checkNumber);
        Matcher matcher = pattern.matcher(soLuongCopy + "");
        if (soLuongCopy <= 0 && matcher.matches() != true) {
            //cần có thêm điều kiện ko phải là số v..v..
            JOptionPane.showMessageDialog(null, "Số lượng được nhập không chính xác. Yêu cầu nhập lại", "error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            matcher = pattern.matcher(giaBan + "");
            if (giaBan > 0 && matcher.matches() == true) {
                for (int i = 0; i < soLuongCopy; i++) {
                    soLuongBanSach++;
                    String maS = "S00" + soLuongBanSach;

                    BookCopyDTO bookCopy = new BookCopyDTO(maS, maDS, loaiSach, giaBan, "Nguyên vẹn", "Hiện có");
                    bookCopy.setGiaMuon(giaBan);
                    bcBLL.insertBookCopy(bookCopy);
                }
                BookDTO book = bBLL.getBookById(new BookDTO(maDS));
                int soLuongDS = book.getSoLuong() + soLuongCopy;
                book.setSoLuong(soLuongDS);

                System.out.println("sach sau khi dc cap nhat so luong: " + book);

                int dong = bBLL.updateBook(book);
                if (dong == 1) {
                    JOptionPane.showMessageDialog(null, "Thêm bản sách thành công", "addCopy", JOptionPane.INFORMATION_MESSAGE);
                    loadDataforTable(bBLL.getBookAll());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Thêm bản không thành công", "addCopy", JOptionPane.INFORMATION_MESSAGE);
                    loadDataforTable(bBLL.getBookAll());
                }
                resetFrm();
            } else {
                JOptionPane.showMessageDialog(null, "Giá được nhập không chính xác. Yêu cầu nhập lại", "error", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }
}
