package applicationForm;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import includeClass.TableColumnAligner;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mysql_connect.MysqlConnect;

public class PanelCategory extends javax.swing.JPanel {

    Connection conn = null;         //ເກັບການເຊື່ອມຕໍ່ຖານຂໍ້ມູນ
    PreparedStatement pst = null;   //ກຽມຄໍາສັ່ງ sql
    ResultSet rs = null;            //ເກັບຜົນໄດ້ຮັບຈາກການປະມວນຜົນຄໍາສັ່ງ sql

    public PanelCategory() {
        initComponents();

        //PlaceHolder
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ຄົ້ນຫາຂໍ້ມູນປະເພດສິນຄ້າ");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("images_svg/search_text.svg"));

        //ກໍານົດໃຫ້ສະແດງຜົນກາງຖັນຕາຕະລາງ
        TableColumnAligner.alignCenter(jTable1, 0, 1);

        conn = MysqlConnect.connectDB(); //ເຊື່ອມຕໍ່ຖານຂໍ້ມູນ
        tableUpdate();
        autoID();
    }

    //ສ້າງເມັດທອດສະແດງຄ່າຂໍ້ມູນໃນຕາຕະລາງ
    private void tableUpdate() {
        try {
            String sql = " SELECT *FROM category ORDER BY category_id DESC ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
            jTable1.setRowHeight(30); //ຄວາມສູງຂອງແຖວ
            d.setRowCount(0); //ລືບຂໍ້ມູນອອກຈາກຕາຕະລາງ jTable1 
            int number = 0;
            while (rs.next()) {
                d.addRow(new Object[]{
                    ++number,
                    rs.getString(1),
                    rs.getString(2)
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    //ສ້າງເມັດທອດສະແດງລະຫັດແບບໂອໂຕຂຶ້ນຕົ້ນດ້ວຍ B ຕາມດ້ວຍຕົວເລກ 3 ຫຼັກ
    private void autoID() {
        try {
            String sql = " SELECT SUBSTR(MAX(category_id), 2, 4) FROM category ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                txtCategory_id.setText("C" + String.format("%03d", ++id));
            } else {
                txtCategory_id.setText("C001");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    //ສ້າງເມັດທອດຍົກເລີກຄ່າ
    private void clearForm() {
        autoID();
        txtCategory_name.setText("");
        jTable1.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCategory_name = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtCategory_id = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ລໍາດັບ", "ລະຫັດປະເພດ", "ຊື່ປະເພດສິນຄ້າ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ຈັດການຂໍ້ມູນປະເພດສິນຄ້າ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lao_SomVang", 0, 16))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel1.setText("ລະຫັດປະເພດ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 45, 70, -1));

        jLabel2.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel2.setText("ຊື່ປະເພດສິນຄ້າ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 90, -1));

        txtCategory_name.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jPanel1.add(txtCategory_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 350, -1));

        btnEdit.setBackground(new java.awt.Color(255, 153, 0));
        btnEdit.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(51, 51, 51));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.addActionListener(this::btnEditActionPerformed);
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 80, -1));

        btnAdd.setBackground(new java.awt.Color(51, 102, 255));
        btnAdd.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ເພີ້ມ");
        btnAdd.addActionListener(this::btnAddActionPerformed);
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 80, -1));

        btnCancel.setBackground(new java.awt.Color(0, 255, 255));
        btnCancel.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(51, 51, 51));
        btnCancel.setText("ຍົກເລີກ");
        btnCancel.addActionListener(this::btnCancelActionPerformed);
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 80, -1));

        btnDelete.setBackground(new java.awt.Color(204, 0, 51));
        btnDelete.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລືບ");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 80, -1));

        txtCategory_id.setEditable(false);
        txtCategory_id.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jPanel1.add(txtCategory_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 76, 350, -1));

        txtSearch.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearForm();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //ຖ້າເລືອກຂໍ້ມູນໃນຕາຕະລາງແລ້ວກົດປຸ່ມ ເພີ່ມ ໃຫ້ແຈ້ງເຕືອນ
        if (jTable1.getSelectedRow() >= 0) {
            JOptionPane.showMessageDialog(this, "ເມື່ອທ່ານເລືອກຂໍ້ມູນໃນຕາຕະລາງແລ້ວບໍ່ສາມາດກົດປຸ່ມເພີ່ມໄດ້ ", "ຜິດພາດ", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //ຖ້າຫ້ອງຊື່ຍີ່ຫໍ້ບໍ່ມີຄ່າໃຫ້ແຈ້ງເຕືອນ
        if (txtCategory_name.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຊື່ຍີ່ຫໍ້ດ້ວຍ", "ຫວ່າງເປົ່າ", JOptionPane.WARNING_MESSAGE);
            txtCategory_name.requestFocus();
            return;
        }

        try {
            String sql = " INSERT INTO category  VALUES( ?,  ? ) ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtCategory_id.getText());
            pst.setString(2, txtCategory_name.getText());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "ຂໍ້ມູນຖືກບັນທຶກລົງໃນຖານຂໍ້ມູນ ສໍາເລັດ",
                        "ສໍາເລັດ",
                        JOptionPane.WIDTH,
                        new FlatSVGIcon("images_svg/done.svg")
                );

                clearForm();
                tableUpdate();
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(
                        this,
                        "ຊື່ຍີ່ຫໍ້ນີ້ມີຢູ່ໃນລະບົບແລ້ວ! ກະລຸນາປ່ຽນໃໝ່",
                        "ຂໍ້ມູນຊໍ້າ",
                        JOptionPane.WARNING_MESSAGE
                );
                txtCategory_name.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, e);
            }

        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        int rowIndex = jTable1.getSelectedRow();  //ເອົາຄ່າລໍາດັບແຖວກັບໄວ້າໃນ rowIndex

        if (rowIndex < 0) {
            return;
        }

        txtCategory_id.setText(d.getValueAt(rowIndex, 1).toString());
        txtCategory_name.setText(d.getValueAt(rowIndex, 2).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //ຖ້າເລືອກຂໍ້ມູນໃນຕາຕະລາງແລ້ວກົດປຸ່ມ ແກ້ໄຂ ໃຫ້ແຈ້ງເຕືອນ
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "ກະລຸນາເລືອກຂໍ້ມູນໃນຕາຕະລາງກ່ອນແກ້ໄຂດ້ວຍ", "ຜິດພາດ", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //ຖ້າຫ້ອງຊື່ຍີ່ຫໍ້ບໍ່ມີຄ່າໃຫ້ແຈ້ງເຕືອນ
        if (txtCategory_name.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຊື່ຍີ່ຫໍ້ດ້ວຍ", "ຫວ່າງເປົ່າ", JOptionPane.WARNING_MESSAGE);
            txtCategory_name.requestFocus();
            return;
        }

        try {
            String sql = " UPDATE  category  SET  category_name=?  WHERE  category_id=?  ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtCategory_name.getText());
            pst.setString(2, txtCategory_id.getText());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "ປັບປຸງຂໍ້ມູນໃນຖານຂໍ້ມູນ ສໍາເລັດ",
                        "ສໍາເລັດ",
                        JOptionPane.WIDTH,
                        new FlatSVGIcon("images_svg/done.svg")
                );

                clearForm();
                tableUpdate();
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(
                        this,
                        "ຊື່ຍີ່ຫໍ້ນີ້ມີຢູ່ໃນລະບົບແລ້ວ! ກະລຸນາປ່ຽນໃໝ່",
                        "ຂໍ້ມູນຊໍ້າ",
                        JOptionPane.WARNING_MESSAGE
                );
                txtCategory_name.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, e);
            }

        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //ຖ້າເລືອກຂໍ້ມູນໃນຕາຕະລາງແລ້ວກົດປຸ່ມ ແກ້ໄຂ ໃຫ້ແຈ້ງເຕືອນ
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "ກະລຸນາເລືອກຂໍ້ມູນໃນຕາຕະລາງກ່ອນລືບດ້ວຍ", "ຜິດພາດ", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //ຢືນຢັນກ່ອນລືບຂໍ້ມູນ
        int data = JOptionPane.showConfirmDialog(
                this,
                "ທ່ານຕ້ອງການລືບຂຂໍ້ມູນລາຍການນີ້ແທ້ ຫຼື ບໍ່?",
                "ຢືນຢັນ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (data != 0) {
            clearForm();
            return;
        }

        try {
            String sql = " DELETE FROM category  WHERE  category_id=?  ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtCategory_id.getText());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "ຂໍ້ມູນຖືກລືບອອກຈາກຖານຂໍ້ມູນ ສໍາເລັດ",
                        "ສໍາເລັດ",
                        JOptionPane.WIDTH,
                        new FlatSVGIcon("images_svg/done.svg")
                );

                clearForm();
                tableUpdate();
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(
                        this,
                        "ບໍ່ສາມາດລືບຍີ່ຫໍ້ນີ້ໄດ້ ເນື່ອງຈາກຖືກນໍາໃຊ້ຢູ່",
                        "ຜິດພາດ",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {
                JOptionPane.showMessageDialog(this, e);
            }

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            String sql = " SELECT *FROM category  WHERE  CONCAT(category_id,  category_name) LIKE  ? ORDER BY category_id DESC ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+ txtSearch.getText() +"%");
            rs = pst.executeQuery();

            DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
            jTable1.setRowHeight(30); //ຄວາມສູງຂອງແຖວ
            d.setRowCount(0); //ລືບຂໍ້ມູນອອກຈາກຕາຕະລາງ jTable1 
            int number = 0;
            while (rs.next()) {
                d.addRow(new Object[]{
                    ++number,
                    rs.getString(1),
                    rs.getString(2)
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCategory_id;
    private javax.swing.JTextField txtCategory_name;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
