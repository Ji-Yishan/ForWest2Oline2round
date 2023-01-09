import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectStudentInfo {
    public static void main(String[]args){
        // we can get detail information about a certain student
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            conn=JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String sql="SELECT c.`classOrder`, s.`studentName`,c.`enterTime`, s.`studentID`,s.`sex`,s.`idCard` \n" +
                    "FROM `student` AS s\n" +
                    "INNER JOIN `class` AS c\n" +
                    "ON s.studentName = c.studentName&&s.studentName=?";
            st=conn.prepareStatement(sql);
            st.setString(1,"什么是早八");
            rs =st.executeQuery();
            if(rs.next()){
                System.out.println("班级名称： "+rs.getString("classOrder"));
                System.out.println("学生姓名： "+rs.getString("studentName"));
                System.out.println("学号： "+rs.getInt("studentID"));
                System.out.println("性别："+rs.getString("sex"));
                System.out.println("身份证号："+rs.getString("idCard"));
                System.out.println("进入班级的时间："+rs.getDate("enterTime"));
                System.out.println("==================================================");

            }

            conn.commit();

        } catch (SQLException e) {
            try {
                System.out.println("查询失败");
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);
        }

    }
}
