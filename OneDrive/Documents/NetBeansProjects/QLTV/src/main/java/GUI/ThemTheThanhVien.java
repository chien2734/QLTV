package GUI;

import BUS.ThanhVienBUS;
import DTO.ThanhVienDTO;
import static GUI.RegexUtils.isValidDate;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ThemTheThanhVien extends javax.swing.JFrame {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private ThanhVienDTO tv, tvtemp;
    private TrangChuGUI trangChuGUI;
    ThanhVienBUS tvBUS;

    /**
     * Creates new form ThemTheThanhVien
     *
     * @throws java.sql.SQLException
     */
    public ThemTheThanhVien() throws SQLException {
        initComponents();
        tvBUS = new ThanhVienBUS();
        Calendar calendar = Calendar.getInstance();
        txt_ngayDK.setText(formatter.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, 6);
        txt_ngayHH.setText(formatter.format(calendar.getTime()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_hoTen = new javax.swing.JTextField();
        txt_cccd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_ngayDK = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_ngayHH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_diaChi = new javax.swing.JTextArea();
        btn_kichHoatThe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(712, 50));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN ĐỌC GIẢ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Họ tên");

        txt_hoTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_cccd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Căn cước công dân");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Số điện thoại");

        txt_sdt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Địa chỉ");

        txt_ngayDK.setEditable(false);
        txt_ngayDK.setBackground(new java.awt.Color(255, 255, 255));
        txt_ngayDK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Ngày đăng ký");

        txt_ngayHH.setEditable(false);
        txt_ngayHH.setBackground(new java.awt.Color(255, 255, 255));
        txt_ngayHH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_ngayHH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ngayHHFocusGained(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Ngày hết hạn");

        txt_diaChi.setColumns(20);
        txt_diaChi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_diaChi.setLineWrap(true);
        txt_diaChi.setRows(5);
        jScrollPane1.setViewportView(txt_diaChi);

        btn_kichHoatThe.setBackground(new java.awt.Color(204, 255, 255));
        btn_kichHoatThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_kichHoatThe.setForeground(new java.awt.Color(255, 0, 51));
        btn_kichHoatThe.setText("KÍCH HOẠT THẺ");
        btn_kichHoatThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kichHoatTheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cccd)
                            .addComponent(txt_hoTen)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_sdt)
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ngayDK)
                            .addComponent(txt_ngayHH)))
                    .addComponent(btn_kichHoatThe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_ngayDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_ngayHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(btn_kichHoatThe, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kichHoatTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kichHoatTheActionPerformed
        String ma, ten, cccd, sdt, diaChi;
        Date ngayDK = null, ngayHH = null;
        ten = txt_hoTen.getText();
        cccd = txt_cccd.getText();
        sdt = txt_sdt.getText();
        RegexUtils.CheckRegex(cccd, sdt, jPanel2);
        diaChi = txt_diaChi.getText();
        try {
            ngayDK = formatter.parse(txt_ngayDK.getText());
            ngayHH = formatter.parse(txt_ngayHH.getText());
        } catch (ParseException ex) {
            Logger.getLogger(TrangChuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date ngayDk = new java.sql.Date(ngayDK.getTime());
        java.sql.Date ngayHh = new java.sql.Date(ngayHH.getTime());
        ma = tvBUS.generateNewTVCode();
        tv = new ThanhVienDTO(ma, ten, cccd, sdt, diaChi, ngayDk, ngayHh, 100000, "Đang hoạt động", 0);
        tvtemp = new ThanhVienDTO(ma, ten, cccd, sdt, diaChi, ngayDk, ngayHh, "Đang hoạt động");
        JOptionPane.showMessageDialog(rootPane, tvBUS.addThanhVien(tv), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        trangChuGUI.AddRowToDSThe(tvtemp);
        dispose();
        txt_hoTen.setText("");
        txt_cccd.setText("");
        txt_sdt.setText("");
        txt_diaChi.setText("");
        txt_ngayDK.setText("");
        txt_ngayHH.setText("");
        txt_hoTen.requestFocus();

    }//GEN-LAST:event_btn_kichHoatTheActionPerformed

    private void txt_ngayHHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ngayHHFocusGained
        String currentDateText = txt_ngayDK.getText().trim();

        try {
            Date date = formatter.parse(currentDateText);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 6);

            txt_ngayHH.setText(formatter.format(calendar.getTime()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi xử lý ngày tháng! Vui lòng thử lại.");
            Logger.getLogger(ThemTheThanhVien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txt_ngayHHFocusGained

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kichHoatThe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_cccd;
    private javax.swing.JTextArea txt_diaChi;
    private javax.swing.JTextField txt_hoTen;
    private javax.swing.JTextField txt_ngayDK;
    private javax.swing.JTextField txt_ngayHH;
    private javax.swing.JTextField txt_sdt;
    // End of variables declaration//GEN-END:variables
}
