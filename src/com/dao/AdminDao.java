package com.dao;

import com.model.DateCheck;
import com.model.Student;
import com.model.Teacher;
import com.model.admin;
import com.mysql.cj.conf.ConnectionUrlParser;
import javafx.util.Pair;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminDao extends BaseDao{
    //管理员登录
    public int LoginAdmin(String Aid, String Apassword) {
    //0:登录失败,0:普通教师，1：院级管理员,2:校级管理员,3:系统管理员
        String sqlStr = "SELECT Apassword, Arole FROM admin WHERE Aid = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.setString(1, Aid);
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            if(rst.next()) {
                System.out.println(rst.getString("Apassword"));
                if(rst.getString("Apassword").equals(Apassword)) {//判定是否登录成功
                    String Arole = rst.getNString("Arole");
                    System.out.println(Arole);
                    if(Arole.equals("院级管理员")) {
                        return 1;
                    }
                    else if(Arole.equals("校级管理员")) {
                        return 2;
                    }
                    else return 3;//系统管理员
                }
                else return 0;
            }
            else {
                return 0;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;
        }
    }
   //删除管理员
    public boolean DeleteAdmin(String Aid) {
        try(Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement()){
            st.setMaxRows(20);
            String sqlStr = "DELETE FROM admin WHERE Aid = '" + Aid +"'";
            System.out.println(sqlStr);
            st.executeUpdate(sqlStr);
            return true;
        } catch (SQLException e1) {
            System.out.println(e1);
            return false;
        }
    }
    //管理员密码更改
    public boolean ChangeAdminPassword(admin Admin) {
        String sqlStr = "UPDATE admin SET Apassword = ? WHERE Aid = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)){
            pstmt.setString(1, Admin.getAid());
            pstmt.setString(2, Admin.getApassword());
            System.out.println(sqlStr);
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }
    //学生登录
    public Boolean LoginStudent(String Sid, String password) {
        String sqlStr = "SELECT Sidcard FROM student WHERE Sid = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.setString(1, Sid);
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            if(rst.next()) { //Sid为唯一标识
                String Sidcard =  rst.getString("Sidcard");
                if(Sidcard.substring(Sidcard.length() - 8).equals(password)) {
                    return true;
                }
                else return false;
            }
            else {
                return false;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }
    //教师登录
    public boolean LoginTeacher(String Tid, String password) {
        String sqlStr = "SELECT Tidcard FROM teacher WHERE Tid = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.setString(1, Tid);
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            if(rst.next()) { //Tid为唯一标识
                String Tidcard =  rst.getString("Tidcard");
                if(Tidcard.substring(Tidcard.length() - 8).equals(password)) {
                    return true;
                }
                else return false;
            }
            else {
                return false;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }
    //学生信息模糊查询
    public ArrayList<Student> findStudent(String Sid, String Sname, String Sidcard) {
        ArrayList<Student> StuList = new ArrayList<Student>();
        if(Sid.equals("")) Sid = null;
        if(Sname.equals("")) Sname = null;
        if(Sidcard.equals("")) Sidcard = null;
        try(Connection conn = dataSource.getConnection())  {
            String sqlStr = "SELECT * FROM student";
            if(Sid != null || Sname != null || Sidcard != null) {
                sqlStr += " WHERE ";
                if(Sid != null) {
                    sqlStr = sqlStr + "Sid like '%" + Sid + "%'";
                    if(Sname != null || Sidcard != null) {
                        sqlStr += " AND ";
                    }
                }
                if(Sname != null) {
                    sqlStr = sqlStr + "Sname like '%" + Sname + "%'";
                    if(Sidcard != null) {
                        sqlStr += " AND ";
                    }
                }
                if(Sidcard != null) {
                    sqlStr = sqlStr + "Sidcard like '%" + Sidcard + "%'";
                }
            }
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                Student student = new Student();
                student.setSid(rst.getString("Sid"));
                student.setSname(rst.getString("Sname"));
                student.setSidcard(rst.getString("Sidcard"));
                student.setScollege(rst.getString("Scollege"));
                student.setSmajor(rst.getString("Smajor"));
                student.setSclass(rst.getString("Sclass"));
                student.setSdate(rst.getInt("Sdate"));
                student.setShealth(rst.getString("Shealth"));
                student.setStoday(rst.getInt("Stoday"));
                StuList.add(student);
            }
        } catch (SQLException se) {
            System.out.println(se);
            return null;
        }
        return StuList;
    }
    //教师信息模糊查询
    public ArrayList<Teacher> findTeacher(String Tid, String Tname, String Tidcard) {
        ArrayList<Teacher> TeaList = new ArrayList<Teacher>();
        if(Tid.equals("")) Tid = null;
        if(Tname.equals("")) Tname = null;
        if(Tidcard.equals("")) Tidcard = null;
        try(Connection conn = dataSource.getConnection()) {
            String sqlStr = "SELECT * FROM teacher";
            if (Tid != null || Tname != null || Tidcard != null) {
                sqlStr += " WHERE ";
                if (Tid != null) {
                    sqlStr = sqlStr + "Tid like '%" + Tid + "%'";
                    if (Tname != null || Tidcard != null) {
                        sqlStr += " AND ";
                    }
                }
                if (Tname != null) {
                    sqlStr = sqlStr + "Tname like '%" + Tname + "%'";
                    if (Tidcard != null) {
                        sqlStr += " AND ";
                    }
                }
                if (Tidcard != null) {
                    sqlStr = sqlStr + "Tidcard like '%" + Tidcard + "%'";
                }
            }
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                Teacher teacher = new Teacher();
                teacher.setTid(rst.getString("Tid"));
                teacher.setTname(rst.getString("Tname"));
                teacher.setTidcard(rst.getString("Tidcard"));
                teacher.setTcollege(rst.getString("Tcollege"));
                teacher.setTrole(rst.getString("Trole"));
                teacher.setTdate(rst.getInt("Tdate"));
                teacher.setThealth(rst.getString("Thealth"));
                teacher.setTtoday(rst.getInt("Ttoday"));
                TeaList.add(teacher);
            }
        }catch (SQLException se) {
            System.out.println(se);
            return null;
        }
        return TeaList;
    }
    //学生删除
    public Boolean DeleteStudent(String Sid) {
        String sqlStr = "DELETE FROM student WHERE Sid = '" + Sid + "'";
        try(Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement()){
            st.setMaxRows(20);
            System.out.println(sqlStr);
            st.executeUpdate(sqlStr);
            return true;
        } catch (SQLException e1) {
            System.out.println(e1);
            return false;
        }
    }
    //教师删除
    public Boolean DeleteTeacher(String Tid) {
        try(Connection conn = dataSource.getConnection();
                Statement st = conn.createStatement()){
            st.setMaxRows(20);
            String sqlStr = "DELETE FROM teacher WHERE Tid = '" + Tid + "'";
            System.out.println(sqlStr);
            st.executeUpdate(sqlStr);
            return true;
        } catch (SQLException e1) {
            System.out.println(e1);
            return false;
        }
    }
    //学生信息修改
    public Boolean ModifyStudent(Student student) {
        String sqlStr = "UPDATE student SET Sname = ?, Sgid = ?, Scollege = ?, Smajor = ?, Sclass = ?" +
                ",Shealth = ?, Sdate = ?, Stoday = ? WHERE Sid = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.setString(1, student.getSname());
            pstmt.setString(2, student.getSidcard());
            pstmt.setString(3, student.getScollege());
            pstmt.setString(4, student.getSmajor());
            pstmt.setString(5, student.getSclass());
            pstmt.setString(6, student.getShealth());
            pstmt.setInt(7, student.getSdate());
            pstmt.setInt(8, student.getStoday());
            pstmt.setString(9, student.getSid());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
    //教师信息修改
    public Boolean ModifyTeacher(Teacher teacher) {
        String sqlStr = "UPDATE teacher SET Tname = ?, Tcollege = ?, Trole = ?," +
                " Thealth = ?, Tdate = ?, Ttoday = ? WHERE Tid = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.setString(1, teacher.getTname());
            pstmt.setString(2, teacher.getTcollege());
            pstmt.setString(3, teacher.getTrole());
            pstmt.setString(4, teacher.getThealth());
            pstmt.setInt(5, teacher.getTdate());
            pstmt.setInt(6, teacher.getTtoday());
            pstmt.setString(7, teacher.getTid());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
    //学生单体添加
    public Boolean AddStudent(Student student) throws SQLException {
        String sqlStr = "INSERT INTO student values('"+student.getSid()+"','"+student.getSname()+"','"+student.getSidcard()+
                "','"+student.getScollege()+"','"+student.getSmajor()+"','"+student.getSclass()+
                "','"+student.getShealth()+"','"+student.getSdate()+"','"+student.getStoday()+"')";
        Connection conn = dataSource.getConnection();
        Statement st = conn.createStatement();
        st.setMaxRows(20);
        System.out.println(sqlStr);
        st.executeUpdate(sqlStr);
        conn.close();
        st.close();
        return true;
    }
    //教师单体添加
    public Boolean AddTeacher(Teacher teacher) throws SQLException{
        String sqlStr = "INSERT INTO teacher values('"+teacher.getTid()+"','"+teacher.getTname()+"','"+teacher.getTidcard()+"','"+
                teacher.getTcollege()+"','"+teacher.getTrole()+"','"+teacher.getThealth()+"','"+teacher.getTdate()+"','"+teacher.getTtoday()+"')";
        Connection conn = dataSource.getConnection();
        Statement st = conn.createStatement();
        st.setMaxRows(20);
        st.executeUpdate(sqlStr);
        conn.close();
        st.close();
        return true;
    }
}