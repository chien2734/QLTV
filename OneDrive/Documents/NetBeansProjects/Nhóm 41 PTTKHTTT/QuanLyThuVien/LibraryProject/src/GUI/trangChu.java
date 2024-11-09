/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class trangChu extends JPanel{
    public trangChu() {
        initComponents();
    }
                           
    public void initComponents() {

        jLabel_img = new JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        setLayout(null);

        jLabel_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/main.jpg")));
        add(jLabel_img);
        jLabel_img.setBounds(250, 120, 1240, 750);
    }                                       
    private javax.swing.JLabel jLabel_img;                

}
