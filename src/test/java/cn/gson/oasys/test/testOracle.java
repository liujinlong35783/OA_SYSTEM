package cn.gson.oasys.test;

import java.io.*;
import java.sql.*;
import org.junit.Test;
public class testOracle {

    public static void main(String[] args) throws Exception {
        Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
//        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        Connection conn = DriverManager.getConnection( "jdbc:db2://127.0.0.1:50000/sample", "db2admin", "123456");
//        Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:male", "feelview", "feelview");
        String sql = "insert into feelview.TEST(id,file) VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        File f = new File("F:\\广电运通\\grgbanking报销\\2021\\11\\2_0051610104_50060121.pdf");
        InputStream fileContent = new FileInputStream(f);
        ps.setAsciiStream(1, fileContent, fileContent.available());

        ps.executeUpdate();
    }
}
