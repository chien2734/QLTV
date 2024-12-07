
package GUI;
import BUS.CT_PhieuMuonBUS;
import BUS.CT_PhieuTraBUS;
import BUS.PhieuTraBUS;
import BUS.SachBUS;
import DTO.CT_PhieuMuonDTO;
import DTO.CT_PhieuTraDTO;
import DTO.PhieuTraDTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class TraSachGUI extends javax.swing.JFrame {

    /**
     * Creates new form TraSachGUI
     * 
     */
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final String maPM;
    PhieuTraBUS ptBUS;
    SachBUS sachBUS;
    CT_PhieuTraBUS ctptBUS ;
    CT_PhieuMuonBUS ctpmBUS;
    ArrayList<String> tinhTrangSach =new ArrayList<>(List.of("Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất"));
    public TraSachGUI(String maPM, String mathe, String tienCoc, String ngayMuon, String hanTra) throws SQLException {
        initComponents();
        this.setTitle("Giao diện trả sách");
        ptBUS = new PhieuTraBUS();
        ctptBUS = new CT_PhieuTraBUS();
        ctpmBUS = new CT_PhieuMuonBUS();
        sachBUS = new SachBUS();
        this.maPM = maPM;
        txt_MaPM.setText(maPM);
        txt_MaThe.setText(mathe);
        txt_TienCoc.setText(tienCoc);
        txt_NgayMuon.setText(ngayMuon);
        txt_HanTra.setText(hanTra);
        Calendar calendar = Calendar.getInstance();
        String ngaytra = formatter.format(calendar.getTime());
        txt_NgayTra.setText(ngaytra);       
        display();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_TinhTrangSach = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_NgayMuon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_HanTra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_NgayTra = new javax.swing.JTextField();
        txt_PhiTreHan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_TinhPhiTreHan = new javax.swing.JButton();
        btn_TraSach = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_TongTien = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_PhiDenBu = new javax.swing.JTextField();
        btn_TinhPhiDenBu = new javax.swing.JButton();
        txt_TienCoc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_MaThe = new javax.swing.JTextField();
        txt_MaPM = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2147483647, 1010));
        setPreferredSize(new java.awt.Dimension(1130, 1010));

        jPanel1.setPreferredSize(new java.awt.Dimension(1241, 64));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN TRẢ SÁCH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Tình trạng sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        tbl_TinhTrangSach.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_TinhTrangSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><center style='font-size:16px'>Mã sách</html>", "<html><center style='font-size:16px'>Tên sách</html>", "<html><center style='font-size:16px'>Số lượng</html>", "<html><center style='font-size:16px'>Tình trạng mượn</html>", "<html><center style='font-size:16px'>Tình trạng trả</html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_TinhTrangSach.setRowHeight(25);
        jScrollPane1.setViewportView(tbl_TinhTrangSach);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Tính phí trễ hạn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Ngày mượn");

        txt_NgayMuon.setEditable(false);
        txt_NgayMuon.setBackground(new java.awt.Color(255, 255, 255));
        txt_NgayMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Hạn trả");

        txt_HanTra.setEditable(false);
        txt_HanTra.setBackground(new java.awt.Color(255, 255, 255));
        txt_HanTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Ngày trả");

        txt_NgayTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_PhiTreHan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Phí trễ hạn");

        btn_TinhPhiTreHan.setBackground(new java.awt.Color(204, 255, 255));
        btn_TinhPhiTreHan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_TinhPhiTreHan.setText("Tính phí trễ hạn");
        btn_TinhPhiTreHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TinhPhiTreHanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_TinhPhiTreHan, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txt_NgayMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txt_HanTra)
                    .addComponent(txt_NgayTra)
                    .addComponent(txt_PhiTreHan))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_NgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_HanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_NgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txt_PhiTreHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_TinhPhiTreHan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        btn_TraSach.setBackground(new java.awt.Color(204, 255, 255));
        btn_TraSach.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_TraSach.setForeground(new java.awt.Color(255, 51, 51));
        btn_TraSach.setText("XÁC NHẬN TRẢ SÁCH");
        btn_TraSach.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        btn_TraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TraSachActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("Tổng tiền phải trả:");

        txt_TongTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt_TongTien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TongTienFocusGained(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setText("VND");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "Thông tin phiếu trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Phí đền bù");

        txt_PhiDenBu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btn_TinhPhiDenBu.setBackground(new java.awt.Color(204, 255, 255));
        btn_TinhPhiDenBu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_TinhPhiDenBu.setText("Tính phí đền bù");
        btn_TinhPhiDenBu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TinhPhiDenBuActionPerformed(evt);
            }
        });

        txt_TienCoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Tiền cọc");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Mã thẻ");

        txt_MaThe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_MaPM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Mã phiếu mượn");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_MaPM, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_PhiDenBu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(175, 175, 175)
                    .addComponent(btn_TinhPhiDenBu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(80, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MaPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_MaThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_TienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_PhiDenBu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(274, 274, 274)
                    .addComponent(btn_TinhPhiDenBu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(32, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(121, 121, 121)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(179, 179, 179)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel11))))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TraSachActionPerformed
        String id = maPM.replace("PM", "PT");
        String maThe = txt_MaThe.getText();
        String ngayMuon = txt_NgayMuon.getText();
        String ngayTra = txt_NgayTra.getText();
        double phiDenBu = Double.parseDouble(txt_PhiDenBu.getText());
        double phiTreHan = Double.parseDouble(txt_PhiTreHan.getText());
        Date nt = null, nm = null;
        try {
            nm = formatter.parse(ngayMuon);
            nt = formatter.parse(ngayTra);
        } catch (ParseException ex) {
            Logger.getLogger(TraSachGUI.class.getName()).log(Level.SEVERE, null, ex);
        }      
        java.sql.Date ngaymuon = new java.sql.Date(nm.getTime());
        java.sql.Date ngaytra = new java.sql.Date(nt.getTime());
        PhieuTraDTO ptDTO = new PhieuTraDTO(id, maPM, maThe, ngaymuon, ngaytra, phiDenBu, phiTreHan);
        if(ptBUS.addPhieuTra(ptDTO)){
            ArrayList<CT_PhieuTraDTO> dsCTPT = new ArrayList<>();
            for (int row = 0; row < tbl_TinhTrangSach.getRowCount(); row++) {
                String idSach = tbl_TinhTrangSach.getValueAt(row, 0).toString();
                String tenSach = tbl_TinhTrangSach.getValueAt(row, 1).toString();
                int soLuong = Integer.parseInt(tbl_TinhTrangSach.getValueAt(row, 2).toString());
                String tinhTrang = tbl_TinhTrangSach.getValueAt(row, 4).toString();
                dsCTPT.add(new CT_PhieuTraDTO(id, idSach, tenSach, soLuong, tinhTrang));
            }
            if (ctptBUS.addCT_PhieuTra(dsCTPT)) {
                JOptionPane.showMessageDialog(rootPane,
                        "Tạo phiếu trả " + maPM + " cho người dùng có mã thẻ là " + maThe + " thành công!");
            }        
        }else{
            JOptionPane.showMessageDialog(rootPane, "Tạo phiếu trả thất bại!", id, HEIGHT);
        }
        
    }//GEN-LAST:event_btn_TraSachActionPerformed

    private void txt_TongTienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TongTienFocusGained
        double tongTien = 0.0;
        double phiTreHan = Double.parseDouble(txt_PhiTreHan.getText());
        double phiDenBu = Double.parseDouble(txt_PhiDenBu.getText());
        tongTien = phiTreHan + phiDenBu;
        txt_TongTien.setText(tongTien+"");
    }//GEN-LAST:event_txt_TongTienFocusGained

    private void btn_TinhPhiDenBuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TinhPhiDenBuActionPerformed
        double tongPhi = 0;
        int quantityRow = tbl_TinhTrangSach.getRowCount();
        for(int i = 0;i < quantityRow; i++){
            String truoc = tbl_TinhTrangSach.getValueAt(i, 3).toString();
            String sau = tbl_TinhTrangSach.getValueAt(i, 4).toString();
            double gia = sachBUS.getSachById(tbl_TinhTrangSach.getValueAt(i, 0).toString()).getGiaSach();
            int muon = 0, tra = 0;
            for(int x = 0; x < tinhTrangSach.size(); x++){
                String temp = tinhTrangSach.get(x);
                if(temp.equals(truoc)){
                    muon = x;
                }
                if(temp.equals(sau)){
                    tra = x;
                }
            }
            double tong;
            if(tra - muon == 0){
                tong = 0;
                tongPhi += tong;
            }
            if(tra - muon == 1){
                tong = gia*0.5;
                tongPhi+=tong;
            }
            if(tra - muon == 2){
                tong = gia;
                tongPhi+=tong;
            }else{
                tong = gia*1.5;
                tongPhi+=tong;
            }
        }
        txt_PhiDenBu.setText(tongPhi+"");
    }//GEN-LAST:event_btn_TinhPhiDenBuActionPerformed

    private void btn_TinhPhiTreHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TinhPhiTreHanActionPerformed
        String ngayTra = txt_NgayTra.getText();
        String hanTra = txt_HanTra.getText();
        int soLuong = tbl_TinhTrangSach.getRowCount();
        Date nt = null, ht = null;
        try {
            ht = formatter.parse(hanTra);
            nt = formatter.parse(ngayTra);
        } catch (ParseException ex) {
            Logger.getLogger(TraSachGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.sql.Date hantra = new java.sql.Date(ht.getTime());
        java.sql.Date ngaytra = new java.sql.Date(nt.getTime());
        int soNgay = ptBUS.TinhSoNgay(ngaytra, hantra);
        double tienTreHan = soNgay * 1000 * soLuong;
        txt_PhiTreHan.setText(tienTreHan+"");
    }//GEN-LAST:event_btn_TinhPhiTreHanActionPerformed
    
    private void display(){
        DefaultTableModel tinhtrangNhanSach = (DefaultTableModel) tbl_TinhTrangSach.getModel();
        tinhtrangNhanSach.setRowCount(0);
        CT_PhieuMuonDTO ctpm;
        ArrayList<CT_PhieuMuonDTO> ds = ctpmBUS.getCT_PhieuMuonByPhieuMuonId(maPM);
        for(int i =0 ; i<ds.size();i++){
            ctpm = ds.get(i);
            Object[] row = {ctpm.getMaSach(), ctpm.getTenSach(), ctpm.getSoLuong(), ctpm.getTrangThai()};
            tinhtrangNhanSach.addRow(row);
        }
        String[] options = {"Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất"};
        JComboBox<String> cbb = new JComboBox<>(options);
        cbb.setFont(new java.awt.Font("Segoe", java.awt.Font.PLAIN, 16));
        TableColumn column = tbl_TinhTrangSach.getColumnModel().getColumn(4);
        column.setCellEditor(new javax.swing.DefaultCellEditor(cbb));  
    }
    
    public void updateButtonName(String name){
        btn_TraSach.setText(name);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_TinhPhiDenBu;
    private javax.swing.JButton btn_TinhPhiTreHan;
    private javax.swing.JButton btn_TraSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_TinhTrangSach;
    private javax.swing.JTextField txt_HanTra;
    private javax.swing.JTextField txt_MaPM;
    private javax.swing.JTextField txt_MaThe;
    private javax.swing.JTextField txt_NgayMuon;
    private javax.swing.JTextField txt_NgayTra;
    private javax.swing.JTextField txt_PhiDenBu;
    private javax.swing.JTextField txt_PhiTreHan;
    private javax.swing.JTextField txt_TienCoc;
    private javax.swing.JTextField txt_TongTien;
    // End of variables declaration//GEN-END:variables
}
