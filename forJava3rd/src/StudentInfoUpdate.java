import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentInfoUpdate {
    public  static  void  main(String[] args) {
        Connection conn=null;
        PreparedStatement st=null;

        try{

            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);

            String sql="UPDATE `student` SET `sex` = ? WHERE studentName=?" ;

            st=conn.prepareStatement(sql);
            st.setString(1,"女");
            st.setString(2,"什么是早八");
            st.executeUpdate();
            conn.commit();
            System.out.println("更新成功!");

        } catch (SQLException e) {
            try {
                System.out.println("更新失败!");
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
