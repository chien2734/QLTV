/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.NXBDTO;
import DTO.TheLoaiDTO;
import BUS.SachBUS;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ThemSachGUI extends javax.swing.JFrame {

    /**
     * Creates new form ThemSachGUI
     */
    SachBUS sachBUS;
    public ThemSachGUI() throws SQLException {
        initComponents();
        sachBUS = new SachBUS();
        loadDataToComboBoxes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonThemSach = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_TacGia = new javax.swing.JComboBox<>();
        jComboBox_TheLoai = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_NXB = new javax.swing.JComboBox<>();
        jTextField_TenSach = new javax.swing.JTextField();
        jTextField_NamXB = new javax.swing.JTextField();
        jSpinner_SoLuong = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txt_GiaSach = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 800));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 50));

        jLabel1.setBackground(new java.awt.Color(204, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN SÁCH MỚI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 50));

        jButtonThemSach.setBackground(new java.awt.Color(204, 255, 255));
        jButtonThemSach.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonThemSach.setForeground(new java.awt.Color(255, 51, 51));
        jButtonThemSach.setText("THÊM SÁCH");
        jButtonThemSach.setPreferredSize(new java.awt.Dimension(700, 50));
        jButtonThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonThemSach, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonThemSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tên sách");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Số lượng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Năm xuất bản");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tác giả");

        jComboBox_TacGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jComboBox_TheLoai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Thể loại");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Nhà xuất bản");

        jComboBox_NXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextField_TenSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextField_NamXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jSpinner_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jSpinner_SoLuong.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Giá sách");

        txt_GiaSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addContainerGap(528, Short.MAX_VALUE))
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel8)))
                .addGap(64, 64, 64)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox_TacGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_TheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField_NamXB, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_GiaSach, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner_SoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_NamXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_GiaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinner_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemSachActionPerformed
        String tenSach = jTextField_TenSach.getText().trim();
        String namXBStr = jTextField_NamXB.getText().trim();
        String theLoai = (String) jComboBox_TheLoai.getSelectedItem();
        String tacGia = (String) jComboBox_TacGia.getSelectedItem();
        String nhaXuatBan = (String) jComboBox_NXB.getSelectedItem();
        int soLuong = (int) jSpinner_SoLuong.getValue();
        String GiaSach =txt_GiaSach.getText();

        if (tenSach.isEmpty() || namXBStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sách!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int namXB;
        try {
            namXB = Integer.parseInt(namXBStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Năm xuất bản phải là một số nguyên!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double giaSach = 0;
        try{
            giaSach = Double.parseDouble(GiaSach);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Giá sách phải là 1 số thực");
        }
        String maSach = sachBUS.generateMaSach();
        System.out.println(maSach);
        
        String theLoaiId = null;
        String nhaXuatBanId = null;
        String tacGiaId = null;
        // Lấy id của thể loại
        try {
            theLoaiId = sachBUS.getIdByTenTheLoai(theLoai);
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        // Lấy id của nhà xuất bản
        try {
        nhaXuatBanId = sachBUS.getIdByTenNhaXuatBan(nhaXuatBan);
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Lấy id của tác giả
        try {
            tacGiaId = sachBUS.getIdByTenTacGia(tacGia);
        } catch (SQLException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SachDTO sachDTO = new SachDTO(maSach, tenSach, theLoaiId, tacGiaId, nhaXuatBanId, namXB, soLuong, giaSach);

        String message = sachBUS.addSach(sachDTO);
        JOptionPane.showMessageDialog(this, message, "Kết quả", JOptionPane.INFORMATION_MESSAGE);

        if (message.contains("thành công")) {
            resetInputFields();
        }
    }//GEN-LAST:event_jButtonThemSachActionPerformed

    /**
     * @param args the command line arguments
     */
    private void loadDataToComboBoxes() {
        // Lấy dữ liệu từ BUS
        ArrayList<TheLoaiDTO> listTheLoai = sachBUS.getAllTheLoai(); // Lấy danh sách thể loại từ BUS
        ArrayList<TacGiaDTO> listTacGia = sachBUS.getAllTacGia(); // Lấy danh sách tác giả từ BUS
        ArrayList<NXBDTO> listNhaXuatBan = sachBUS.getAllNhaXuatBan(); // Lấy danh sách nhà xuất bản từ BUS
        // Tạo danh sách kiểu String để gán vào JComboBox
        ArrayList<String> theLoaiNames = new ArrayList<>();
        for (TheLoaiDTO theLoai : listTheLoai) {
            theLoaiNames.add(theLoai.getTen()); // Lấy tên thể loại
        }

        ArrayList<String> tacGiaNames = new ArrayList<>();
        for (TacGiaDTO tacGia : listTacGia) {
            tacGiaNames.add(tacGia.getTen()); // Lấy tên tác giả
        }

        ArrayList<String> nhaXuatBanNames = new ArrayList<>();
        for (NXBDTO nhaXuatBan : listNhaXuatBan) {
            nhaXuatBanNames.add(nhaXuatBan.getTen()); // Lấy tên nhà xuất bản
        }
        // Gán danh sách vào các JComboBox
        jComboBox_TheLoai.setModel(new DefaultComboBoxModel<>(theLoaiNames.toArray(new String[0])));
        jComboBox_TacGia.setModel(new DefaultComboBoxModel<>(tacGiaNames.toArray(new String[0])));
        jComboBox_NXB.setModel(new DefaultComboBoxModel<>(nhaXuatBanNames.toArray(new String[0])));
    }
   
    private void resetInputFields() {
        jTextField_TenSach.setText("");
        jTextField_NamXB.setText("");
        txt_GiaSach.setText("");
        jComboBox_TheLoai.setSelectedIndex(-1);
        jComboBox_TacGia.setSelectedIndex(-1);
        jComboBox_NXB.setSelectedIndex(-1);
        jSpinner_SoLuong.setValue(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonThemSach;
    private javax.swing.JComboBox<String> jComboBox_NXB;
    private javax.swing.JComboBox<String> jComboBox_TacGia;
    private javax.swing.JComboBox<String> jComboBox_TheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSpinner jSpinner_SoLuong;
    private javax.swing.JTextField jTextField_NamXB;
    private javax.swing.JTextField jTextField_TenSach;
    private javax.swing.JTextField txt_GiaSach;
    // End of variables declaration//GEN-END:variables
}
