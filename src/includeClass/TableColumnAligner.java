package includeClass;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableColumnAligner {

    // ຈັດໃຫ້ຖັນທີ່ລະບຸຢູ່ກາງ
    public static void alignCenter(JTable table, int... columns) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int col : columns) {
            table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }
    }

    // ຈັດໃຫ້ຖັນທີ່ລະບຸຢູ່ຂວາ
    public static void alignRight(JTable table, int... columns) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        for (int col : columns) {
            table.getColumnModel().getColumn(col).setCellRenderer(rightRenderer);
        }
    }
}
