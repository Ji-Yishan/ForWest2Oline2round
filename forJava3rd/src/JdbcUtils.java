import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class JdbcUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static{
        try{
            InputStream in =JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(in);
            driver= properties.getProperty("driver");
            url= properties.getProperty("url");
            username= properties.getProperty("username");
            password= properties.getProperty("password");
            //驱动只用加载一次
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
    public static void release(Connection conn, Statement st, ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(st!=null){
            try{
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try{
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
