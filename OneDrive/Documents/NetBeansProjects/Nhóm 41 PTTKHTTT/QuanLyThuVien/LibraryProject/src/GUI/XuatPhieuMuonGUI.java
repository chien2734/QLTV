/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import DTO.CT_PhieuMuonDTO;
import DTO.CardDTO;
import DTO.PhieuMuonDTO;
import DTO.ReaderDTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class XuatPhieuMuonGUI extends javax.swing.JFrame {

    private PhieuMuonBLL pmBLL = new PhieuMuonBLL();
    private CT_PhieuMuonBLL ctpmBLL = new CT_PhieuMuonBLL();
    private CartUserBLL cartUserBLL = new CartUserBLL();
    private CardBUS cardBLL = new CardBUS();
    private BookCopyBLL sachBLL = new BookCopyBLL();
    private BookBLL booksBLL = new BookBLL();
    private ReaderBUS readerBLL = new ReaderBUS();
    private CT_PhieuTraBLL ctptBLL = new CT_PhieuTraBLL();
    private PhieuTraBLL ptBLL = new PhieuTraBLL();
    private DefaultTableModel model;
    
    private static String maPhieu;
    public XuatPhieuMuonGUI(String maphieu) {
        this.maPhieu = maphieu;
        initComponents();        
        PhieuMuonDTO pm = pmBLL.getBorrowById(new PhieuMuonDTO(maphieu));
        tf_maphieu.setText(maphieu);
        tf_mathe.setText(pm.getMaTheMuon());
        tf_ngaymuon.setText(String.valueOf(pm.getNgayMuon()));
        tf_hantra.setText(String.valueOf(pm.getHanTra()));
        tf_tiencoc.setText(String.valueOf(pm.getTienCoc()));
        CardDTO themuon = cardBLL.getCardsByIdTM(pm.getMaTheMuon());
        ReaderDTO docgia = readerBLL.selectById(themuon.getTenDN());
        tf_ten.setText(docgia.getTenDG());
        model = (DefaultTableModel) tb_ctpm.getModel();
        loadDataIntoTable(maPhieu); 
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    public void loadDataIntoTable(String maPhieu) {
        ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(maPhieu);
        int count = 1;
        for (CT_PhieuMuonDTO ct : listctpm) {
            System.out.println(ct.toString());
            model.addRow(new Object[]{count, ct.getMaSach(), ct.getTenSach(), ct.getGiaMuon(), null});
            count ++;
            //model.fireTableDataChanged();  
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_ctpm = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tf_mathe = new javax.swing.JTextField();
        tf_ngaymuon = new javax.swing.JTextField();
        tf_hantra = new javax.swing.JTextField();
        tf_ten = new javax.swing.JTextField();
        tf_maphieu = new javax.swing.JTextField();
        tf_tiencoc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Phiếu mượn tài liệu");
        setPreferredSize(new java.awt.Dimension(1000, 740));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 735));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHIẾU MƯỢN TÀI LIỆU");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel2.setText("Số phiếu:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel3.setText("Mã thẻ mượn:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel4.setText("Ngày mượn:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel5.setText("Ký tên");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel6.setText("Danh sách tài liệu:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel7.setText("Họ và tên:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel8.setText("Ngày hẹn trả:");

        tb_ctpm.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        tb_ctpm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Tiền cọc", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_ctpm.setRowHeight(30);
        jScrollPane1.setViewportView(tb_ctpm);
        if (tb_ctpm.getColumnModel().getColumnCount() > 0) {
            tb_ctpm.getColumnModel().getColumn(0).setPreferredWidth(60);
            tb_ctpm.getColumnModel().getColumn(1).setPreferredWidth(150);
            tb_ctpm.getColumnModel().getColumn(2).setPreferredWidth(400);
            tb_ctpm.getColumnModel().getColumn(3).setPreferredWidth(150);
            tb_ctpm.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        jLabel9.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel9.setText("Tiền cọc:");

        tf_mathe.setEditable(false);
        tf_mathe.setBackground(new java.awt.Color(255, 255, 255));
        tf_mathe.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        tf_mathe.setBorder(null);

        tf_ngaymuon.setEditable(false);
        tf_ngaymuon.setBackground(new java.awt.Color(255, 255, 255));
        tf_ngaymuon.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        tf_ngaymuon.setBorder(null);

        tf_hantra.setEditable(false);
        tf_hantra.setBackground(new java.awt.Color(255, 255, 255));
        tf_hantra.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        tf_hantra.setBorder(null);

        tf_ten.setEditable(false);
        tf_ten.setBackground(new java.awt.Color(255, 255, 255));
        tf_ten.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        tf_ten.setBorder(null);

        tf_maphieu.setEditable(false);
        tf_maphieu.setBackground(new java.awt.Color(255, 255, 255));
        tf_maphieu.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        tf_maphieu.setBorder(null);

        tf_tiencoc.setEditable(false);
        tf_tiencoc.setBackground(new java.awt.Color(255, 255, 255));
        tf_tiencoc.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        tf_tiencoc.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(272, 272, 272))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tf_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel10)
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel11))
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tf_mathe, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(149, 149, 149)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tf_tiencoc, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tf_maphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tf_ngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(75, 75, 75)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_hantra, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_maphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(tf_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(tf_mathe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tiencoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_ngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(tf_hantra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_ctpm;
    private javax.swing.JTextField tf_hantra;
    private javax.swing.JTextField tf_maphieu;
    private javax.swing.JTextField tf_mathe;
    private javax.swing.JTextField tf_ngaymuon;
    private javax.swing.JTextField tf_ten;
    private javax.swing.JTextField tf_tiencoc;
    // End of variables declaration//GEN-END:variables
    

}
