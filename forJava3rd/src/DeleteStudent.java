import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStudent {
    public  static  void  main(String[] args) {
        //delete the student's information from table class and student
        // class的studentName是外键
        Connection conn=null;
        PreparedStatement st=null;
        try{
            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String sql="DELETE FROM class WHERE studentName=?" ;
            st=conn.prepareStatement(sql);
            st.setString(1, "晚上吃什么");
            st.executeUpdate();
            String sql2="DELETE FROM student WHERE studentName=?";
            st=conn.prepareStatement(sql2);
            st.setString(1, "寒假为什么要早起");
            st.executeUpdate();
            conn.commit();
            System.out.println ("删除成功!");

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
