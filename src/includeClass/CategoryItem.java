package includeClass;

//ສ້າງ class Category

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mysql_connect.MysqlConnect;

public class CategoryItem {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public CategoryItem() {
        conn = MysqlConnect.connectDB();
    }

    // Method to get all category name
    public List<String> getName() {

        List<String> category_name = new ArrayList<>();
        String sql = "SELECT category_name FROM category ORDER BY category_name";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                category_name.add(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category_name;
    }

    // Method to get id by name
    public String getId(String categoryName) {

        String category_id = "";
        String sql = "SELECT category_id FROM category WHERE category_name=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, categoryName);
            rs = pst.executeQuery();
            if (rs.next()) {
                category_id = rs.getString("category_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category_id;
    }

}
