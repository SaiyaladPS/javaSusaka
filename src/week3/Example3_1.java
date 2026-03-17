package week3;

import com.formdev.flatlaf.FlatClientProperties;
import config.ThemeConfig;
import includeClass.NumberComma;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Example3_1 extends javax.swing.JFrame {

    double total; //ລາຄາທັງໝົດ

    public Example3_1() {
        initComponents();
        this.setTitle("ໂປຣແກຣມຄໍານວນລາຄາສິນຄ້າ");
        this.setLocationRelativeTo(this);  //ສະແດງຜົນກາງຈໍ     
        this.setResizable(false); //ບໍ່ໃຫ້ຂະຫຍາຍໃຫຍ່

        //ໃສ່ຮູບ icon program
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));

        //ປຽນສີແຖບ Title bar ດ້ານເທິງ
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(211, 211, 211));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_FOREGROUND, new Color(30, 30, 30));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        btnCalculate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPay = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtChange = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lao_SomVang", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("ໂປຣແກຣມຄໍານວນລາຄາລວມຂອງສິນຄ້າ");

        jLabel2.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel2.setText("ລາຄາສິນຄ້າຕໍ່ໜ່ວຍ");

        txtPrice.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtPrice.addActionListener(this::txtPriceActionPerformed);
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel3.setText("ຈໍານວນ");

        txtQty.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtQty.addActionListener(this::txtQtyActionPerformed);
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
        });

        btnCalculate.setBackground(new java.awt.Color(102, 102, 102));
        btnCalculate.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnCalculate.setForeground(new java.awt.Color(255, 255, 255));
        btnCalculate.setText("ຄໍານວນ");
        btnCalculate.addActionListener(this::btnCalculateActionPerformed);

        btnCancel.setBackground(new java.awt.Color(0, 204, 204));
        btnCancel.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnCancel.setText("ຍົກເລີກ");

        jLabel4.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel4.setText("ລາຄາລວມ");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel5.setText("ເງິນຊໍາລະ");

        txtPay.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtPay.addActionListener(this::txtPayActionPerformed);
        txtPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel6.setText("ເງິນທອນ");

        txtChange.setEditable(false);
        txtChange.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btnCalculate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalculate)
                    .addComponent(btnCancel))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        // ປະກາດຕົວປ່ຽນ
        double price;   //ລາຄາສິນຄ້າ
        int qty;        //ລາຄາຕໍ່ໜ່ວຍ

        if (txtPrice.getText().isBlank() || txtQty.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບຖ້ວນດວ້ຍ", "ເກີດຂໍ້ຜຶດພາດ", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            price = Double.parseDouble(txtPrice.getText().replace(",", ""));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນລາຄາສິນຄ້າໃຫ້ຖືກຕ້ອງ", "ຜິດພາດ", JOptionPane.WARNING_MESSAGE);
            txtPrice.requestFocus();
            return;
        }

        try {
            qty = Integer.parseInt(txtQty.getText().replace(",", ""));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຈຳນວນສິນຄ້າໃຫ້ຖືກຕ້ອງ", "ຜິດພາດ", JOptionPane.WARNING_MESSAGE);
            txtPrice.requestFocus();
            return;
        }
       
        total = price * qty;

        DecimalFormat df = new DecimalFormat("#,###.##");

        //ສະແດງຄ່າທີ່ຫ້ອງ ລາຄາລວມ
        txtTotal.setText(df.format(total));
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void txtPayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayKeyReleased
        // ຖ້າຫ້ອງປ້ອນເງິນຊໍາລະບໍ່ມີຄ່າຈະໃຫ້ຢຸດການປະມວນຜົນ
        if (txtPay.getText().isBlank()) {
            txtChange.setText("");
            return;
        }

        double pay = Double.parseDouble(txtPay.getText());
        double change = pay - total;

        if (change < 0) {
            txtChange.setText("ເງິນຊໍາລະບໍ່ພຽງພໍ");
        } else {
            txtChange.setText(Double.toString(change));
        }
    }//GEN-LAST:event_txtPayKeyReleased

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
        txtQty.requestFocus();
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
        btnCalculate.doClick();
    }//GEN-LAST:event_txtQtyActionPerformed

    private void txtPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyReleased
        // TODO add your handling code here:
        NumberComma.numberFormat(txtPrice, true);
    }//GEN-LAST:event_txtPriceKeyReleased

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
        // TODO add your handling code here:
        NumberComma.numberFormat(txtQty, true);
    }//GEN-LAST:event_txtQtyKeyReleased

    private void txtPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPayActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPayActionPerformed

    public static void main(String args[]) {
        /*ໃຊ້  FlatLaft Libary*/
        ThemeConfig.config();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Example3_1().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtChange;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
