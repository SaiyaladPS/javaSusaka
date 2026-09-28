package includeClass;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mysql_connect.MysqlConnect;


public class BrandItem {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public BrandItem() {
        conn = MysqlConnect.connectDB();
    }

    // Method to get all brand name
    public List<String> getName() {

        List<String> brand_name = new ArrayList<>();
        String sql = "SELECT brand_name FROM brand ORDER BY brand_name";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                brand_name.add(rs.getString("brand_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brand_name;
    }

    // Method to get id by name
    public String getId(String brandName) {
        String brand_id = "";
        String sql = "SELECT brand_id FROM brand WHERE brand_name=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, brandName);
            rs = pst.executeQuery();
            if (rs.next()) {
                brand_id = rs.getString("brand_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brand_id;
    }

}
