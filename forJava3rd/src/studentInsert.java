import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class studentInsert {
    public  static  void  main(String[] args) {
        // this is where we insert info into table student and class
        Connection conn=null;
        PreparedStatement st=null;

        try{

            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);

            String sql="INSERT INTO student(`studentID`,`studentName`,`sex`,`idCard`)" +
                    "VALUES(?,?,?,?)" ;
            //idCard是身份证号,studentID是学号
            st=conn.prepareStatement(sql);
            //设置参数，参数位置从1开始
            st.setInt(1,743303190);
            st.setString(2,"寒假为什么要早起");
            st.setString(3,"女");
            st.setString(4,"35029457294837501");
            st.executeUpdate();
            String sql2="INSERT INTO `class`(`classOrder`,`studentName`,`enterTime`)"+
                    "VALUES(?,?,?)" ;
            st=conn.prepareStatement(sql2);
            //classOrder是班级名字
            st.setString(1,"20级1班");
            st.setString(2,"寒假为什么要早起");
            st.setDate(3,new java.sql.Date(System.currentTimeMillis()));
            st.executeUpdate();
            conn.commit();
            System.out.println("插入成功!");
        } catch (SQLException e) {
            try {
                System.out.println("失败");
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);
        }
        finally{
            JdbcUtils.release(conn,st,null);
        }

    }
}
