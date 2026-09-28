/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package applicationForm;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import includeClass.BrandItem;
import includeClass.CategoryItem;
import includeClass.TableColumnAligner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mysql_connect.MysqlConnect;

/**
 *
 * @author jayj2
 */
public class PanelProduct extends javax.swing.JPanel {

    Connection conn = null;         //ເກັບການເຊື່ອມຕໍ່ຖານຂໍ້ມູນ
    PreparedStatement pst = null;   //ກຽມຄໍາສັ່ງ sql
    ResultSet rs = null;            //ເກັບຜົນໄດ້ຮັບຈາກການປະມວນຜົນຄໍາສັ່ງ sql

    private boolean updatingCell;
    BrandItem brand = new BrandItem();
    CategoryItem category = new CategoryItem();

    /**
     * Creates new form PanelProduct
     */
    public PanelProduct() {
        initComponents();
        conn = MysqlConnect.connectDB();
        BrandList();
        CategoryList();
        configureProductTable();
        addMoneyFormatListener(txtCost_price);
        addMoneyFormatListener(txtRetail_price);

        //PlaceHolder
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ຄົ້ນຫາຂໍ້ມູນປະເພດສິນຄ້າ");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("images_svg/search_text.svg"));

        //ກໍານົດໃຫ້ສະແດງຜົນກາງຖັນຕາຕະລາງ
        TableColumnAligner.alignCenter(jTable1, 0, 1);

        conn = MysqlConnect.connectDB(); //ເຊື່ອມຕໍ່ຖານຂໍ້ມູນ
        tableUpdate();
        autoID();
        BrandList();
        CategoryList();
    }

    //ສ້າງເມັດທອດສະແດງຄ່າຂໍ້ມູນໃນຕາຕະລາງ
    private void tableUpdate() {
        try {
            String sql = """
                SELECT 
                    pd.barcode,
                    pd.product_name,
                    pd.unit,
                    pd.quantity,
                    pd.quantity_min,
                    pd.cost_price,
                    pd.retail_price,
                    cg.category_name,
                    ba.brand_name,
                    pd.status
                FROM product pd
                LEFT JOIN category cg 
                    ON pd.category_id = cg.category_id
                LEFT JOIN brand ba 
                    ON pd.brand_id = ba.brand_id
                ORDER BY pd.barcode DESC
                """;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel d
                    = (DefaultTableModel) jTable1.getModel();

            jTable1.setRowHeight(30);
            d.setRowCount(0);

            while (rs.next()) {
                d.addRow(new Object[]{
                    rs.getString("barcode"),
                    rs.getString("product_name"),
                    rs.getString("unit"),
                    rs.getString("quantity"),
                    rs.getString("quantity_min"),
                    formatMoney(rs.getString("cost_price")),
                    formatMoney(rs.getString("retail_price")),
                    rs.getString("brand_name"),
                    rs.getString("category_name"),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    //ສ້າງເມັດທອດສະແດງລະຫັດແບບໂອໂຕຂຶ້ນຕົ້ນດ້ວຍ B ຕາມດ້ວຍຕົວເລກ 3 ຫຼັກ
    private void autoID() {
        try {
            String sql = " SELECT SUBSTR(MAX(barcode), 2, 4) FROM product ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                txtBarcode.setText("C" + String.format("%03d", ++id));
            } else {
                txtBarcode.setText("C001");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void clearForm() {
        txtProduct_name.setText("");
        txtUnit.setText("");
        txtQuantity.setText("");
        txtQuantity_min.setText("");
        txtCost_price.setText("");
        txtRetail_price.setText("");
        txtBrand.setSelectedIndex(0);
        txtCategory.setSelectedIndex(0);
        txtStatus.setSelectedIndex(0);
        jTable1.clearSelection();
        autoID();
        txtBarcode.requestFocus();
    }

    private void addMoneyFormatListener(javax.swing.JTextField field) {
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent event) {
                formatMoneyField(field);
            }
        });
    }

    private void formatMoneyField(javax.swing.JTextField field) {
        String value = field.getText().replace(",", "").trim();
        if (value.isEmpty()) return;
        try {
            field.setText(String.format("%,.0f", Double.parseDouble(value)));
        } catch (NumberFormatException ignored) {
            // Validation will show the input error when saving.
        }
    }

    private java.math.BigDecimal moneyValue(javax.swing.JTextField field) {
        return new java.math.BigDecimal(field.getText().replace(",", "").trim());
    }

    private String formatMoney(String value) {
        if (value == null || value.isBlank()) return "";
        try {
            return String.format("%,.0f", Double.parseDouble(value.replace(",", "").trim()));
        } catch (NumberFormatException e) {
            return value;
        }
    }

    private void BrandList() {
        txtBrand.removeAllItems();
        txtBrand.addItem("---ເລືອກຍີຫໍ້---");
        for (String name : brand.getName()) {
            txtBrand.addItem(name);
        }
    }

    private void CategoryList() {
        txtCategory.removeAllItems();
        txtCategory.addItem("---ເລືອກປະເພດ---");
        for (String name : category.getName()) {
            txtCategory.addItem(name);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProduct_name = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtBarcode = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtUnit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQuantity_min = new javax.swing.JTextField();
        txtCost_price = new javax.swing.JTextField();
        txtRetail_price = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JComboBox<>();
        txtBrand = new javax.swing.JComboBox<>();
        txtCategory = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ຈັດການຂໍ້ມູນປະເພດສິນຄ້າ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lao_SomVang", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel1.setText("ລະຫັດບາໂຄດ");

        jLabel2.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel2.setText("ລາຍການສິນຄ້າ");

        txtProduct_name.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtProduct_name.addActionListener(this::txtProduct_nameActionPerformed);
        txtProduct_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProduct_nameKeyPressed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(255, 153, 0));
        btnEdit.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(51, 51, 51));
        btnEdit.setText("ແກ້ໄຂ");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnAdd.setBackground(new java.awt.Color(51, 102, 255));
        btnAdd.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ເພີ້ມ");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnCancel.setBackground(new java.awt.Color(0, 255, 255));
        btnCancel.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(51, 51, 51));
        btnCancel.setText("ຍົກເລີກ");
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        btnDelete.setBackground(new java.awt.Color(204, 0, 51));
        btnDelete.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ລືບ");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        txtBarcode.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyPressed(evt);
            }
        });

        txtQuantity.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
        });

        txtUnit.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnitKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel3.setText("ຈຳນວນ");

        jLabel4.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel4.setText("ຫົວຫນ່ວຍ");

        jLabel6.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel6.setText("ລາຄາຂາຍ");

        jLabel7.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel7.setText("ລາຄາຕົ້ນທືນ");

        jLabel8.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel8.setText("ຈຳນວນຕຳສຸດ");

        txtQuantity_min.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtQuantity_min.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantity_minKeyPressed(evt);
            }
        });

        txtCost_price.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtCost_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCost_priceKeyPressed(evt);
            }
        });

        txtRetail_price.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtRetail_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRetail_priceKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel10.setText("ສະຖານະ");

        jLabel11.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel11.setText("ປະເພດ");

        jLabel12.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel12.setText("ຍີຫໍ້");

        txtStatus.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ມີ", "ບໍ່ມີ" }));
        txtStatus.addActionListener(this::txtStatusActionPerformed);

        txtBrand.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N

        txtCategory.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCategoryKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        jLabel5.setText("ຄົ້ນວຫາ");

        txtSearch.setFont(new java.awt.Font("Lao_SomVang", 0, 14)); // NOI18N
        txtSearch.setToolTipText("ຄົ້ນວຫາສິນຄ້າ...");

        jTable1.setFont(new java.awt.Font("Lao_SomVang", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ລະຫັດສິນຄ້າ", "ລາຍການສິນຄ້າ", "ຊື່ສິນ້າ", "ຫົວໜ່ວຍ", "ຈຳນວນ", "ຈຳນວນຕຳສຸດ", "ລາຄາຕົ້ນທືນ", "ຍີຫໍ້", "ປະເພດ", "ສະຖານະ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, 0, 731, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(txtQuantity_min, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(10, 10, 10)
                                    .addComponent(txtProduct_name, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(txtCost_price, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(txtRetail_price, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8))
                    .addComponent(txtQuantity_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12))
                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProduct_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCost_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRetail_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd)
                            .addComponent(btnEdit)
                            .addComponent(btnDelete)
                            .addComponent(btnCancel)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, 0, 206, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void configureProductTable() {
        jTable1.setRowHeight(34);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowVerticalLines(false);
        jTable1.setShowHorizontalLines(true);
        jTable1.setGridColor(new java.awt.Color(226, 232, 240));
        jTable1.setSelectionBackground(new java.awt.Color(219, 234, 254));
        jTable1.setSelectionForeground(new java.awt.Color(30, 64, 175));
        jTable1.setRowSorter(new javax.swing.table.TableRowSorter<>(jTable1.getModel()));
        jTable1.setFillsViewportHeight(true);
        javax.swing.JComboBox<String> brandEditor = new javax.swing.JComboBox<>();
        for (int i = 0; i < txtBrand.getItemCount(); i++) brandEditor.addItem(txtBrand.getItemAt(i));
        javax.swing.JComboBox<String> categoryEditor = new javax.swing.JComboBox<>();
        for (int i = 0; i < txtCategory.getItemCount(); i++) categoryEditor.addItem(txtCategory.getItemAt(i));
        jTable1.getColumnModel().getColumn(7).setCellEditor(new javax.swing.DefaultCellEditor(brandEditor));
        jTable1.getColumnModel().getColumn(8).setCellEditor(new javax.swing.DefaultCellEditor(categoryEditor));
        jTable1.getModel().addTableModelListener(event -> {
            if (!updatingCell && event.getType() == javax.swing.event.TableModelEvent.UPDATE
                    && event.getFirstRow() >= 0
                    && event.getColumn() >= 0) {
                updateProductCell(event.getFirstRow(), event.getColumn());
            }
        });

        jTable1.getTableHeader().setFont(new java.awt.Font("Lao_SomVang", java.awt.Font.BOLD, 14));
        jTable1.getTableHeader().setBackground(new java.awt.Color(30, 64, 175));
        jTable1.getTableHeader().setForeground(java.awt.Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 38));
        jTable1.getTableHeader().setReorderingAllowed(false);

        javax.swing.table.DefaultTableCellRenderer centeredCellRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centeredCellRenderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        int[] centeredColumns = {0, 3, 4, 5, 6, 7, 8, 9};
        for (int column : centeredColumns) {
            jTable1.getColumnModel().getColumn(column).setCellRenderer(centeredCellRenderer);
        }

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);

        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent event) {
                filterProductTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent event) {
                filterProductTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent event) {
                filterProductTable();
            }
        });
    }

    private void updateProductCell(int row, int column) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String barcode = String.valueOf(model.getValueAt(row, 0));
        Object value = model.getValueAt(row, column);
        String sql;
        try (PreparedStatement statement = conn.prepareStatement("UPDATE product SET " + switch (column) {
            case 1 -> "product_name=?";
            case 2 -> "unit=?";
            case 3 -> "quantity=?";
            case 4 -> "quantity_min=?";
            case 5 -> "cost_price=?";
            case 6 -> "retail_price=?";
            case 7 -> "brand_id=?";
            case 8 -> "category_id=?";
            case 9 -> "status=?";
            default -> throw new IllegalArgumentException("Unsupported column");
        } + " WHERE barcode=?")) {
            String text = String.valueOf(value).trim();
            if (column == 3 || column == 4) {
                statement.setInt(1, Integer.parseInt(text));
            } else if (column == 5 || column == 6) {
                statement.setBigDecimal(1, new java.math.BigDecimal(text.replace(",", "")));
            } else if (column == 7) {
                statement.setString(1, brand.getId(text));
            } else if (column == 8) {
                statement.setString(1, category.getId(text));
            } else {
                statement.setString(1, text);
            }
            statement.setString(2, barcode);
            statement.executeUpdate();
            if (column == 5 || column == 6) {
                updatingCell = true;
                model.setValueAt(formatMoney(text), row, column);
                updatingCell = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ไม่สามารถบันทึกข้อมูล", JOptionPane.ERROR_MESSAGE);
            tableUpdate();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void filterProductTable() {
        javax.swing.table.TableRowSorter sorter = (javax.swing.table.TableRowSorter) jTable1.getRowSorter();
        String keyword = txtSearch.getText().trim();
        sorter.setRowFilter(keyword.isEmpty()
                ? null
                : javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(keyword)));
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        int rowIndex = jTable1.getSelectedRow();  //ເອົາຄ່າລໍາດັບແຖວກັບໄວ້າໃນ rowIndex

        if (rowIndex < 0) {
            return;
        }

        txtBarcode.setText(d.getValueAt(rowIndex, 0).toString());
        txtProduct_name.setText(d.getValueAt(rowIndex, 1).toString());
        txtUnit.setText(d.getValueAt(rowIndex, 2).toString());
        txtQuantity.setText(d.getValueAt(rowIndex, 3).toString());
        txtQuantity_min.setText(d.getValueAt(rowIndex, 4).toString());
        txtCost_price.setText(d.getValueAt(rowIndex, 5).toString());
        txtRetail_price.setText(d.getValueAt(rowIndex, 6).toString());
        selectComboValue(txtBrand, d.getValueAt(rowIndex, 7));
        selectComboValue(txtCategory, d.getValueAt(rowIndex, 8));
        txtStatus.setSelectedItem(d.getValueAt(rowIndex, 9));
    }//GEN-LAST:event_jTable1MouseClicked

    private void selectComboValue(javax.swing.JComboBox<String> combo, Object value) {
        String wanted = value == null ? " : value.toString().trim();
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item != null && item.trim().equalsIgnoreCase(wanted)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        combo.setSelectedIndex(0);
    }

    private void txtStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select a product first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Delete this product?", "Confirm", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM product WHERE barcode=?")) {
            statement.setString(1, txtBarcode.getText().trim());
            statement.executeUpdate();
            clearForm();
            tableUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearForm();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!validateProductForm()) return;
        String sql = "INSERT INTO product (barcode, product_name, unit, quantity, quantity_min, cost_price, retail_price, brand_id, category_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            bindProduct(statement);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully.");
            clearForm();
            tableUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select a product first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validateProductForm()) return;
        String sql = "UPDATE product SET product_name=?, unit=?, quantity=?, quantity_min=?, cost_price=?, retail_price=?, brand_id=?, category_id=?, status=? WHERE barcode=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, txtProduct_name.getText().trim());
            statement.setString(2, txtUnit.getText().trim());
            statement.setInt(3, Integer.parseInt(txtQuantity.getText().trim()));
            statement.setInt(4, Integer.parseInt(txtQuantity_min.getText().trim()));
            statement.setBigDecimal(5, moneyValue(txtCost_price));
            statement.setBigDecimal(6, moneyValue(txtRetail_price));
            statement.setString(7, brand.getId(String.valueOf(txtBrand.getSelectedItem())));
            statement.setString(8, category.getId(String.valueOf(txtCategory.getSelectedItem())));
            statement.setString(9, String.valueOf(txtStatus.getSelectedItem()));
            statement.setString(10, txtBarcode.getText().trim());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product updated successfully.");
            clearForm();
            tableUpdate();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private boolean validateProductForm() {
        if (txtBarcode.getText().isBlank() || txtProduct_name.getText().isBlank() || txtUnit.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Please fill barcode, product name and unit.", "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            if (Integer.parseInt(txtQuantity.getText().trim()) < 0 || Integer.parseInt(txtQuantity_min.getText().trim()) < 0) throw new NumberFormatException();
            if (moneyValue(txtCost_price).signum() < 0 || moneyValue(txtRetail_price).signum() < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity and prices must be valid non-negative numbers.", "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtBrand.getSelectedIndex() <= 0 || txtCategory.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Please select brand and category.", "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void bindProduct(PreparedStatement statement) throws SQLException {
        statement.setString(1, txtBarcode.getText().trim());
        statement.setString(2, txtProduct_name.getText().trim());
        statement.setString(3, txtUnit.getText().trim());
        statement.setInt(4, Integer.parseInt(txtQuantity.getText().trim()));
        statement.setInt(5, Integer.parseInt(txtQuantity_min.getText().trim()));
        statement.setBigDecimal(6, moneyValue(txtCost_price));
        statement.setBigDecimal(7, moneyValue(txtRetail_price));
        statement.setString(8, brand.getId(String.valueOf(txtBrand.getSelectedItem())));
        statement.setString(9, category.getId(String.valueOf(txtCategory.getSelectedItem())));
        statement.setString(10, String.valueOf(txtStatus.getSelectedItem()));
    }

    private void txtBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String barcode = txtBarcode.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນ");
                return;
            }

            // เรียกเมธอดที่ต้องการเมื่อกด Enter
            txtProduct_name.requestFocus();
        }
    }//GEN-LAST:event_txtBarcodeKeyPressed

    private void txtProduct_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProduct_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProduct_nameActionPerformed

    private void txtProduct_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProduct_nameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String barcode = txtProduct_name.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນ");
                return;
            }

            // เรียกเมธอดที่ต้องการเมื่อกด Enter
            txtQuantity.requestFocus();
        }
    }//GEN-LAST:event_txtProduct_nameKeyPressed

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String barcode = txtQuantity.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນ");
                return;
            }

            // เรียกเมธอดที่ต้องการเมื่อกด Enter
            txtUnit.requestFocus();
        }
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void txtUnitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitKeyReleased

    private void txtUnitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String barcode = txtUnit.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນ");
                return;
            }

            // เรียกเมธอดที่ต้องการเมื่อกด Enter
            txtQuantity_min.requestFocus();
        }
    }//GEN-LAST:event_txtUnitKeyPressed

    private void txtQuantity_minKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantity_minKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String barcode = txtQuantity_min.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນ");
                return;
            }

            // เรียกเมธอดที่ต้องการเมื่อกด Enter
            txtCost_price.requestFocus();
        }
    }//GEN-LAST:event_txtQuantity_minKeyPressed

    private void txtCost_priceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCost_priceKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String barcode = txtCost_price.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນ");
                return;
            }

            // เรียกเมธอดที่ต้องการเมื่อกด Enter
            txtRetail_price.requestFocus();
        }
    }//GEN-LAST:event_txtCost_priceKeyPressed

    private void txtRetail_priceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRetail_priceKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String barcode = txtRetail_price.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ກະລຸນາປ້ອນຂໍ້ມູນ");
                return;
            }

            // เรียกเมธอดที่ต้องการเมื่อกด Enter
            txtBrand.requestFocus();
        }
    }//GEN-LAST:event_txtRetail_priceKeyPressed

    private void txtCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoryKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JComboBox<String> txtBrand;
    private javax.swing.JComboBox<String> txtCategory;
    private javax.swing.JTextField txtCost_price;
    private javax.swing.JTextField txtProduct_name;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtQuantity_min;
    private javax.swing.JTextField txtRetail_price;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JComboBox<String> txtStatus;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
