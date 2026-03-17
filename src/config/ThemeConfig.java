package config;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.UIManager;


public class ThemeConfig {
    public static void config() {
        // Use FlatLaf Light theme
        FlatLightLaf.setup();
//         FlatDarkLaf.setup();

        // ====== Default Font ======
        UIManager.put("defaultFont", new Font("Lao_Somvang", Font.PLAIN, 14));
        UIManager.put("TitlePane.font", new Font("Lao_Somvang", Font.PLAIN, 16));

        // ====== OptionPane Buttons ======
        UIManager.put("OptionPane.okButtonText", "ຕົກລົງ");
        UIManager.put("OptionPane.cancelButtonText", "ຍົກເລີກ");
        UIManager.put("OptionPane.yesButtonText", "ແມ່ນ");
        UIManager.put("OptionPane.noButtonText", "ບໍ່");

        // ====== Component Shape ======
        UIManager.put("Button.arc", 10);
        UIManager.put("Component.arc", 10); //ComboBox
        UIManager.put("ProgressBar.arc", 10);
        UIManager.put("TextComponent.arc", 10); //Text Field
        UIManager.put("Component.arrowType", "triangle");

        // ======Scroll Bar======
        UIManager.put("ScrollBar.showButtons", true);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));

        // ====== JTable Header Customization ======
        UIManager.put("TableHeader.font", new Font("Lao_Somvang", Font.PLAIN, 15));
        UIManager.put("TableHeader.background", new Color(108, 117, 125));
        UIManager.put("TableHeader.foreground", new Color(243, 243, 243));
        //UIManager.put("TableHeader.height", 30); // optional: change row height

        // ====== Table Border Line ======
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.gridColor", new Color(139, 138, 137));
    }
}
