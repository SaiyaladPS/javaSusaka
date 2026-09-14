package applicationForm;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main extends javax.swing.JFrame {

    private String emp_id;

    public Main(String id, String name, String status) {
        initComponents();
        this.emp_id = id;
        //        System.out.println("id: "+ id +"  name: "+ name +" status: "+ status);

        this.setTitle("ໂປຣແກຣມຂາຍເຄື່ອງໜ້າຮ້ານ");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //ສະແດງຜົນເຕັມຈໍ
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_SHOW_ICON, false); //ບໍ່ສະແດງຮູບ icon ເທິງແຖບເມນູ

        //ປຽນສີແຖບ Title bar ດ້ານເທິງ
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(211, 211, 211));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_FOREGROUND, new Color(30, 30, 30));

        //ຖ້າສະຖານະພະນັກງານບໍ່ເທົ່າກັບ Admin ບໍ່ໃຫ້ສະແດງເມນູ: ຈັດການຂໍ້ມູນ, ສັ່ງຊື້-ນໍາເຂົ້າ, ລາຍງານ
        if (!status.equals("Admin")) {
            jMenuData.setVisible(false);
            jMenuOrder_Import.setVisible(false);
            jMenuReport.setVisible(false);
        }

        //ສະແດງໜ້າ PanelHome ກ່ອນ
        showPanel(new PanelHome());
        
        //ກໍານົດຄ່າທີ່ແຖບດ້ານລຸ່ມໂປຣແກຣມ
        SimpleDateFormat fm = new SimpleDateFormat("ວັນທີ: dd/MM/yyyy  ເວລາ hh:mm:ss a");
        txtStatusBar.setText("ຜູ້ໃຊ້ງານ: " + id + " : " + name + " " + fm.format(new Date()));
        txtStatusBar.setForeground(new Color(40, 42, 180));
    }
     
    //ສ້າງເມັດທອດສະແດງແຕ່ລະໜ້າໃນ jPanelMain
    private void showPanel(JPanel panel) {
        jPanelMain.removeAll();
        jPanelMain.add(panel);
        jPanelMain.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        txtStatusBar = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuHome = new javax.swing.JMenu();
        jMenuData = new javax.swing.JMenu();
        jMenuItemBrand = new javax.swing.JMenuItem();
        jMenuItemCategory = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuOrder_Import = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuReport = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItemLight = new javax.swing.JMenuItem();
        jMenuItemDark = new javax.swing.JMenuItem();
        jMenuItemMacDark = new javax.swing.JMenuItem();
        jMenuExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMain.setBackground(new java.awt.Color(255, 204, 204));
        jPanelMain.setLayout(new java.awt.BorderLayout());

        txtStatusBar.setFont(new java.awt.Font("Lao_SomVang", 0, 16)); // NOI18N
        txtStatusBar.setText("jLabel1");

        jMenuHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_Page.png"))); // NOI18N
        jMenuHome.setText("ໜ້າຫຼັກ");
        jMenuHome.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuHomeMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuHome);

        jMenuData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_Database.png"))); // NOI18N
        jMenuData.setText("ຈັດການຂໍ້ມູນ");
        jMenuData.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMenuItemBrand.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItemBrand.setText("ຈັດການຂໍ້ມູນຍີ່ຫໍ້");
        jMenuItemBrand.addActionListener(this::jMenuItemBrandActionPerformed);
        jMenuData.add(jMenuItemBrand);

        jMenuItemCategory.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItemCategory.setText("ຈັດການຂໍ້ມູນປະເພດສິນຄ້າ");
        jMenuItemCategory.addActionListener(this::jMenuItemCategoryActionPerformed);
        jMenuData.add(jMenuItemCategory);

        jMenuItem3.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem3.setText("ຈັດການຂໍ້ມູນສິນຄ້າ");
        jMenuData.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem4.setText("ຈັດການຂໍ້ມູນພະນັກງານ");
        jMenuData.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem5.setText("ຈັດການຂໍ້ມູນອັດຕາແລກປ່ຽນ");
        jMenuData.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem6.setText("ຈັດການຂໍ້ມູນຜູ້ສະໜອງ");
        jMenuData.add(jMenuItem6);

        jMenuBar1.add(jMenuData);

        jMenuOrder_Import.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order_Import.png"))); // NOI18N
        jMenuOrder_Import.setText("ສັ່ງຊື້-ນໍາເຂົ້າ");
        jMenuOrder_Import.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuOrder_Import.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuOrder_Import.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuOrder_Import.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMenuItem7.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem7.setText("ຈັດການຂໍ້ມູນສັ່ງຊື້ສິນຄ້າ");
        jMenuOrder_Import.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem8.setText("ຈັດການຂໍ້ມູນນໍາເຂົ້າສິນຄ້າ");
        jMenuOrder_Import.add(jMenuItem8);

        jMenuBar1.add(jMenuOrder_Import);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
        jMenu4.setText("ລູກຄ້າ");
        jMenu4.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sale_Product.png"))); // NOI18N
        jMenu5.setText("ຂາຍສິນຄ້າ");
        jMenu5.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_Property.png"))); // NOI18N
        jMenu6.setText("ຄົ້ນຫາສິນຄ້າ");
        jMenu6.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenu6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuBar1.add(jMenu6);

        jMenuReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report_Card.png"))); // NOI18N
        jMenuReport.setText("ລາຍງານ");
        jMenuReport.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuReport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuReport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuReport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMenuItem9.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem9.setText("ລາຍງານຂໍ້ມູນການຂາຍສິນຄ້າ");
        jMenuReport.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem10.setText("ລາຍງານຂໍ້ມູນສິນຄ້າໃນຮ້ານ");
        jMenuReport.add(jMenuItem10);

        jMenuItem11.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem11.setText("ລາຍງານຂໍ້ມູນສິນຄ້າໄກ້ໝົດ");
        jMenuReport.add(jMenuItem11);

        jMenuItem12.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem12.setText("ໃບບິນຮັບເງິນ");
        jMenuReport.add(jMenuItem12);

        jMenuItem13.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem13.setText("ລາຍງານຂໍ້ມູນສັ່ງຊື້ສິນຄ້າ");
        jMenuReport.add(jMenuItem13);

        jMenuItem14.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem14.setText("ລາຍງານຂໍ້ມູນນໍາເຂົ້າສິນຄ້າ");
        jMenuReport.add(jMenuItem14);

        jMenuItem15.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem15.setText("ລາຍງານຂໍ້ມູນລູກຄ້າ");
        jMenuReport.add(jMenuItem15);

        jMenuItem16.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItem16.setText("ລາຍງານຂໍ້ມູນພະນັກງານ");
        jMenuReport.add(jMenuItem16);

        jMenuBar1.add(jMenuReport);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N
        jMenu8.setText("ໂປຣໄຟລ໌");
        jMenu8.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenu8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuBar1.add(jMenu8);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change_Theme.png"))); // NOI18N
        jMenu9.setText("ສີພື້ນຫຼັງ");
        jMenu9.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenu9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMenuItemLight.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItemLight.setText("FlatLaf Light");
        jMenuItemLight.addActionListener(this::jMenuItemLightActionPerformed);
        jMenu9.add(jMenuItemLight);

        jMenuItemDark.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItemDark.setText("FlatLaf Dark");
        jMenuItemDark.addActionListener(this::jMenuItemDarkActionPerformed);
        jMenu9.add(jMenuItemDark);

        jMenuItemMacDark.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuItemMacDark.setText("FlatLaf MacDark");
        jMenuItemMacDark.addActionListener(this::jMenuItemMacDarkActionPerformed);
        jMenu9.add(jMenuItemMacDark);

        jMenuBar1.add(jMenu9);

        jMenuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shutdown.png"))); // NOI18N
        jMenuExit.setText("ອອກລະບົບ");
        jMenuExit.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jMenuExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExitMouseClicked
        int data = JOptionPane.showConfirmDialog(
                this,
                "ທ່ານຕ້ອງການອອກຈາກລະບົບແທ້ ຫຼື ບໍ່?",
                "ຢື້ນຢັນ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (data == 0) {
            FlatLightLaf.setup();
            Login login = new Login();
            login.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jMenuExitMouseClicked

    private void jMenuItemLightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLightActionPerformed
        txtStatusBar.setForeground(new Color(40, 42, 180));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(204, 209, 209));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_FOREGROUND, new Color(23, 32, 42));
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        });
    }//GEN-LAST:event_jMenuItemLightActionPerformed

    private void jMenuItemDarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDarkActionPerformed
        txtStatusBar.setForeground(new Color(250, 250, 249));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(33, 47, 61));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_FOREGROUND, new Color(253, 254, 254));
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        });
    }//GEN-LAST:event_jMenuItemDarkActionPerformed

    private void jMenuItemMacDarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMacDarkActionPerformed
        txtStatusBar.setForeground(new Color(250, 250, 249));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(28, 40, 51));
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_FOREGROUND, new Color(253, 254, 254));
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        });
    }//GEN-LAST:event_jMenuItemMacDarkActionPerformed

    private void jMenuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuHomeMouseClicked
        showPanel(new PanelHome());
    }//GEN-LAST:event_jMenuHomeMouseClicked

    private void jMenuItemBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBrandActionPerformed
       showPanel(new PanelBrand());
    }//GEN-LAST:event_jMenuItemBrandActionPerformed

    private void jMenuItemCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoryActionPerformed
        showPanel(new PanelCategory());
    }//GEN-LAST:event_jMenuItemCategoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuData;
    private javax.swing.JMenu jMenuExit;
    private javax.swing.JMenu jMenuHome;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemBrand;
    private javax.swing.JMenuItem jMenuItemCategory;
    private javax.swing.JMenuItem jMenuItemDark;
    private javax.swing.JMenuItem jMenuItemLight;
    private javax.swing.JMenuItem jMenuItemMacDark;
    private javax.swing.JMenu jMenuOrder_Import;
    private javax.swing.JMenu jMenuReport;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JLabel txtStatusBar;
    // End of variables declaration//GEN-END:variables
}
