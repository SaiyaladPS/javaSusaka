package applicationForm;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import config.ThemeConfig;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.sql.*;
import mysql_connect.MysqlConnect;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends javax.swing.JFrame {

    Connection conn = null;         //ເກັບການເຊື່ອມຕໍ່ຖານຂໍ້ມູນ
    PreparedStatement pst = null;   //ກຽມຄໍາສັ່ງ sql
    ResultSet rs = null;            //ເກັບຜົນໄດ້ຮັບຈາກການປະມວນຜົນຄໍາສັ່ງ sql

    public Login() {
        initComponents();
        this.setTitle("ໂປຣແກຣມຂາຍເຄື່ອງໜ້າຮ້ານ");
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));

        //ໃສ່ຮູບໂລໂກລ໋ອກອິນ
        sVGImage1.setSvgImage("images_svg/login-form.svg", 100, 100);

        //ປຽນສີແຖບ Title bar ດ້ານເທິງ
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(211, 211, 211));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_FOREGROUND, new Color(30, 30, 30));

        //PlaceHolder
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ບັນຊີເຂົ້າໃຊ້");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ລະຫັດຜ່ານ");

        //ລືບ
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

        //ໃສ່ຮູບໃນ txtUsername ແລະ txtPassword
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("images_svg/username.svg"));
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("images_svg/password.svg"));

        //ໃສຮູບທີ່ປຸ່ມ ເຂົ້າໃຊ້ງານ
        btnLogin.setIcon(new FlatSVGIcon("images_svg/login.svg"));

        //ສະແດງລະຫັດຜ່ານ ແລະ ສະຖານະປຸ່ມ capslock
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;" + "showCapsLock:true");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sVGImage1 = new includeClass.SVGImage();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sVGImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sVGImage1.setOpaque(true);

        txtUsername.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtUsername.addActionListener(this::txtUsernameActionPerformed);

        txtPassword.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        btnLogin.setBackground(new java.awt.Color(51, 51, 255));
        btnLogin.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("ເຂົ້າໃຊ້ງານ");
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sVGImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(sVGImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnLogin)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        txtPassword.requestFocus();
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        btnLogin.doClick();
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // ຖ້າບໍ່ປ້ອນບັນຊີເຂົ້າໃຊ້ ຫຼື ລະຫັດຜ່ານ ໃຫ້ແຈ້ງເຕືອນ
        if (txtUsername.getText().isBlank() || txtPassword.getText().isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບຖ້ວນດ້ວຍ",
                    "ຫວ່າງເປົ່າ",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {
            conn = MysqlConnect.connectDB(); //ເຊື່ອມຕໍ່ຖານຂໍ້ມູນ
            String sql = "SELECT emp_id, CONCAT(emp_name, ' ', emp_lname) AS name, status , password FROM employee WHERE username = ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtUsername.getText());
            rs = pst.executeQuery();
            if (rs.next()) {

                if (BCrypt.checkpw(txtPassword.getText(), rs.getString(4))) {
                    Main m = new Main(rs.getString(1), rs.getString(2), rs.getString(3));
                    m.setVisible(true);
                    dispose(); //ປິດໜ້າ Login
                } else {
                    JOptionPane.showMessageDialog(this, "ບັນຊີເຂົ້າໃຊ້ ແລະ ລະຫັດຜ່ານບໍ່ຖືກຕ້ອງ", "ຜິດພາດ", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "ບັນຊີເຂົ້າໃຊ້ ແລະ ລະຫັດຜ່ານບໍ່ຖືກຕ້ອງ", "ຜິດພາດ", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    public static void main(String args[]) {
        /* ເອີ້ນໃຊ້ງານ FlatLaf*/
        ThemeConfig.config();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private includeClass.SVGImage sVGImage1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
