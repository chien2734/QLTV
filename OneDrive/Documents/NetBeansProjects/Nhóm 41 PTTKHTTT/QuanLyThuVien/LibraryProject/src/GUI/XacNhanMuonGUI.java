/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.CT_PhieuMuonDTO;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class XacNhanMuonGUI extends JFrame{
    public JLabel lb_title, lb_nguoimuon, lb_mathemuon, lb_ngaymuon, lb_hantra, lb_tiencoc;
    public JButton jb_huybo, jb_xacnhan;
    public JTextField tf_nguoimuon, tf_mathemuon, tf_ngaymuon, tf_hantra, tf_tiencoc;
    private String maPhieu; 
    
    public XacNhanMuonGUI() {
        this.init();
        jb_huybo.setBackground(new Color(187, 222, 251));
        jb_xacnhan.setBackground(new Color(187, 222, 251));
    }
    
    public XacNhanMuonGUI(String maPhieu) {
        this.maPhieu = maPhieu;
        this.init();
    }
    
    private void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(650, 900));
        getContentPane().setBackground(Color.white);
        setTitle("Phiếu mượn");
        setLocationRelativeTo(null);
        this.setLayout(null);
        
        lb_title = new JLabel("Phiếu mượn", JLabel.CENTER);
        lb_title.setBounds(0, 10, 600, 60);
        lb_title.setFont(new Font("Roboto", 1, 20));
        lb_title.setForeground(new Color(24, 116, 205));
        this.add(lb_title);
        
        Font font = new Font("Roboto", 0, 18);
        lb_nguoimuon = new JLabel("Người mượn");
        lb_nguoimuon.setFont(font);
        lb_nguoimuon.setBounds(25, 120, 250, 40);
        
        lb_mathemuon = new JLabel("Mã thẻ mượn");
        lb_mathemuon.setFont(font);
        lb_mathemuon.setBounds(25, 220, 250, 40);
        
        lb_ngaymuon = new JLabel("Ngày mượn");
        lb_ngaymuon.setFont(font);
        lb_ngaymuon.setBounds(25, 320, 250, 40);
        
        lb_hantra = new JLabel("Hạn trả");
        lb_hantra.setFont(font);
        lb_hantra.setBounds(25, 420, 250, 40);
        
        lb_tiencoc = new JLabel("Tiền cọc");
        lb_tiencoc.setFont(font);
        lb_tiencoc.setBounds(25, 520, 250, 40);
        
        jb_huybo = new JButton("Huỷ bỏ");
        jb_huybo.setFont(new Font("Roboto", 0, 18));
        jb_huybo.setBounds(150, 680, 150, 40);
        
        jb_xacnhan = new JButton("Xác nhận");
        jb_xacnhan.setBounds(350, 680, 180, 40);
        jb_xacnhan.setFont(new Font("Roboto", 0, 18));
        
        Font font_text = new Font("Roboto", 0, 18);
        tf_nguoimuon = new JTextField();
        tf_nguoimuon.setFont(font_text);
        tf_nguoimuon.setBounds(270, 120, 320, 40);
        tf_nguoimuon.setEditable(false);
        tf_nguoimuon.setBackground(new Color(245, 245, 245));
        tf_nguoimuon.setOpaque(true);
        tf_nguoimuon.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        tf_mathemuon = new JTextField();
        tf_mathemuon.setFont(font_text);
        tf_mathemuon.setBounds(270, 220, 320, 40);
        tf_mathemuon.setEditable(false);
        tf_mathemuon.setBackground(new Color(245, 245, 245));
        tf_mathemuon.setOpaque(true);
        tf_mathemuon.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        
        tf_ngaymuon = new JTextField();
        tf_ngaymuon.setFont(font_text);
        tf_ngaymuon.setBounds(270, 320, 320, 40);
        tf_ngaymuon.setEditable(false);
        tf_ngaymuon.setBackground(new Color(245, 245, 245));
        tf_ngaymuon.setOpaque(true);
        tf_ngaymuon.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        tf_hantra = new JTextField();
        tf_hantra.setFont(font_text);
        tf_hantra.setBounds(270, 420, 320, 40);
        tf_hantra.setEditable(false);
        tf_hantra.setBackground(new Color(245, 245, 245));
        tf_hantra.setOpaque(true);
        tf_hantra.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        tf_tiencoc = new JTextField();
        tf_tiencoc.setFont(font_text);
        tf_tiencoc.setBounds(270, 520, 320, 40);
        tf_tiencoc.setEditable(false);
        tf_tiencoc.setBackground(new Color(245, 245, 245));
        tf_tiencoc.setOpaque(true);
        tf_tiencoc.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        this.add(lb_nguoimuon); this.add(lb_mathemuon); this.add(lb_ngaymuon); 
        this.add(lb_hantra); this.add(lb_tiencoc); 
        this.add(jb_huybo); this.add(jb_xacnhan);
        this.add(tf_nguoimuon); this.add(tf_mathemuon); this.add(tf_ngaymuon); 
        this.add(tf_hantra); this.add(tf_tiencoc);
        
        this.setVisible(true);
        }
       public static void main(String[] args) {
           new XacNhanMuonGUI();
       }
    }
    
