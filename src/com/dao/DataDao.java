package com.dao;

import com.model.DateCheck;
import com.model.Student;
import com.model.Teacher;
import javafx.util.Pair;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataDao extends BaseDao{
    //  创造新日期
    public void CreateData(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String day = df.format(date);
        String sqlStr = "SELECT * FROM data WHERE Date = '" + day + "'";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlStr);
             ResultSet rst = pstmt.executeQuery()) {
            System.out.println(sqlStr);
            if (rst.next()) {
                return;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        sqlStr = "SELECT * FROM teacher";
        DateCheck dateCheck = new DateCheck();
        dateCheck.setDate(date);
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            ResultSet rst = pstmt.executeQuery()) {
            System.out.println(sqlStr);
            int Tsum = 0;
            int Tfinish = 0;
            int Tred = 0;
            int Tgreen = 0;
            int Tyellow = 0;
            while (rst.next()) {
                Tsum++;
                if (rst.getInt("Ttoday") == 1) {
                    Tfinish++;
                }
                String judge = rst.getString("Thealth");
                if ("green".equals(judge)) {
                    Tgreen++;
                } else if ("red".equals(judge)) {
                    Tred++;
                } else if ("yellow".equals(judge)){
                    Tyellow++;
                }
            }
            dateCheck.setTsum(Tsum);
            dateCheck.setTfinish(Tfinish);
            dateCheck.setTred(Tred);
            dateCheck.setTyellow(Tyellow);
            dateCheck.setTgreen(Tgreen);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlStr = "SELECT * FROM student";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            ResultSet rst = pstmt.executeQuery()) {
                System.out.println(sqlStr);
                int Ssum = 0;
                int Sfinish = 0;
                int Sred = 0;
                int Sgreen = 0;
                int Syellow = 0;
                while(rst.next()) {
                    Ssum++;
                    if(rst.getInt("Stoday") == 1) {
                        Sfinish++;
                    }
                    String judge = rst.getString("Shealth");
                    if("green".equals(judge)) {
                        Sgreen++;
                    }
                    else if("red".equals(judge)) {
                        Sred++;
                    }
                    else if ("yellow".equals(judge)){
                        Syellow++;
                    }
                }
                dateCheck.setSsum(Ssum);
                dateCheck.setSfinish(Sfinish);
                dateCheck.setSred(Sred);
                dateCheck.setSyellow(Syellow);
                dateCheck.setSgreen(Sgreen);
                AddData(dateCheck);
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }
    // 人物打卡情况更新
    public void Reset() {
        String sqlStr = "UPDATE teacher SET Ttoday = '0'";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("0点重置状态失败");
        }
        sqlStr = "UPDATE student SET Stoday = '0'";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("0点重置状态失败");
        }
    }

    //数据增加
    public Boolean AddData(DateCheck data) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sqlStr = "INSERT INTO data values('"+df.format(data.getDate())+"','"+data.getTsum()+"','"+data.getTfinish()+
                "','"+data.getSsum()+"','"+data.getSfinish()+"','"+data.getTgreen()+
                "','"+data.getTyellow()+"','"+data.getTred()+"','"+data.getSgreen()+"','"+data.getSyellow()
                +"','"+data.getSred()+"')";
        try(Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement()) {
            st.setMaxRows(20);
            System.out.println(sqlStr);
            st.executeUpdate(sqlStr);
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
    //数据更新
    public Boolean UpdateData(DateCheck data) {
        String sqlStr = "UPDATE data SET Tsum = ?, Tfinish = ?, Ssum = ?, Sfinish = ?" +
                ",Tred = ?, Tyellow = ?, Tgreen = ?, Sred = ?, Syellow = ?, Sgreen = ? WHERE Date = ?";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            pstmt.setInt(1, data.getTsum());
            pstmt.setInt(2, data.getTfinish());
            pstmt.setInt(3, data.getSsum());
            pstmt.setInt(4, data.getSfinish());
            pstmt.setInt(5, data.getTred());
            pstmt.setInt(6, data.getTyellow());
            pstmt.setInt(7, data.getTgreen());
            pstmt.setInt(8, data.getSred());
            pstmt.setInt(9, data.getSyellow());
            pstmt.setInt(10, data.getSgreen());
            pstmt.setString(11, df.format(data.getDate()));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
    //数据获取-打卡情况by日期
    public DateCheck GetDataByDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        String sqlStr = "SELECT * FROM data WHERE Date = '" +dateStr+"'";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            ResultSet rst = pstmt.executeQuery()) {
            System.out.println(sqlStr);
            if(rst.next()) {
                DateCheck data = new DateCheck();
                data.setDate(rst.getDate("Date"));
                data.setTsum(rst.getInt("Tsum"));
                data.setTfinish(rst.getInt("Tfinish"));
                data.setTgreen(rst.getInt("Tgreen"));
                data.setTyellow(rst.getInt("Tyellow"));
                data.setTred(rst.getInt("Tred"));
                data.setSsum(rst.getInt("Ssum"));
                data.setSfinish(rst.getInt("Sfinish"));
                data.setSgreen(rst.getInt("Sgreen"));
                data.setSyellow(rst.getInt("Syellow"));
                data.setSred(rst.getInt("Sred"));
                return data;
            }
            else {
                return null;
            }
        } catch (SQLException se) {
            System.out.println(se);
            return null;
        }
    }
    //数据获取-打卡情况by信息学生
    public Pair<DateCheck, ArrayList<Student>> GetDataOnStudent(String college, String major, String Class) {
        String sqlStr = "SELECT * FROM student";
        ArrayList<Student> StuList = new ArrayList<Student>();
        if(college.equals("")) college = null;
        if(major.equals("")) major = null;
        if(Class.equals("")) Class = null;
        DateCheck dateCheck = new DateCheck();
        if(college != null || major != null || Class != null) {
            sqlStr += " WHERE ";
            if(college != null) {
                sqlStr = sqlStr + "Scollege = '" + college+ "'";
                if(major != null || Class != null) {
                    sqlStr += " AND ";
                }
            }
            if(major != null) {
                sqlStr = sqlStr + "Smajor = '" + major + "'";
                if(Class != null) {
                    sqlStr += " AND ";
                }
            }
            if(Class != null) {
                sqlStr = sqlStr + "Sclass = '" + Class + "'";
            }
        }
        System.out.println(sqlStr);
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);){
            ResultSet rst = pstmt.executeQuery();
            int Ssum = 0;
            int Sfinish = 0;
            int Sred = 0;
            int Sgreen = 0;
            int Syellow = 0;
            while(rst.next()) {
                Ssum++;
                if(rst.getInt("Stoday") == 1) {
                    Sfinish++;
                }
                String judge = rst.getString("Shealth");
                if("green".equals(judge)) {
                    Sgreen++;
                }
                else if("red".equals(judge)) {
                    Sred++;
                }
                else if ("yellow".equals(judge)){
                    Syellow++;
                }
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
            dateCheck.setSsum(Ssum);
            dateCheck.setSfinish(Sfinish);
            dateCheck.setSred(Sred);
            dateCheck.setSyellow(Syellow);
            dateCheck.setSgreen(Sgreen);
            Pair<DateCheck, ArrayList<Student>> data = new Pair<DateCheck, ArrayList<Student>>(dateCheck,StuList);
            System.out.println(1);
            return data;
        }catch (SQLException se) {
            System.out.println(se);
            return null;
        }
    }
    //数据获取-打卡情况by信息教师
    public Pair<DateCheck, ArrayList<Teacher>> GetDataOnTeacher(String college) {
        String sqlStr = "SELECT * FROM teacher";
        ArrayList<Teacher> TeaList = new ArrayList<Teacher>();
        DateCheck dateCheck = new DateCheck();
        if(college.equals("")) college = null;
        if(college != null) {
            sqlStr = sqlStr + " WHERE Tcollege = '" + college + "'";
        }
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            ResultSet rst = pstmt.executeQuery()) {
            System.out.println(sqlStr);
            int Tsum = 0;
            int Tfinish = 0;
            int Tred = 0;
            int Tgreen = 0;
            int Tyellow = 0;
            while(rst.next()) {
                Tsum++;
                if(rst.getInt("Ttoday") == 1) {
                    Tfinish++;
                }
                String judge = rst.getString("Thealth");
                if("green".equals(judge)) {
                    Tgreen++;
                }
                else if("red".equals(judge)) {
                    Tred++;
                }
                else if ("yellow".equals(judge)){
                    Tyellow++;
                }
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
            dateCheck.setTsum(Tsum);
            dateCheck.setTfinish(Tfinish);
            dateCheck.setTred(Tred);
            dateCheck.setTyellow(Tyellow);
            dateCheck.setTgreen(Tgreen);
            Pair<DateCheck, ArrayList<Teacher>> data = new Pair<DateCheck, ArrayList<Teacher>>(dateCheck,TeaList);
            return data;
        }catch (SQLException se) {
            System.out.println(se);
            return null;
        }
    }
    //获取学院 - 学生
    public ArrayList<String> GetCollegeForStudent() {
        ArrayList<String> allCollege = new ArrayList<String>();
        String sqlStr = "SELECT Scollege FROM student";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        ) {
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                String college = rst.getString("Scollege");
                if(!allCollege.contains(college)) {
                    allCollege.add(college);
                }
            }
            return allCollege;
        }catch (SQLException se) {
            System.out.println(se);
            return null;
        }
    }
    //获取学院 - 老师
    public ArrayList<String> GetCollegeForTeacher() {
        ArrayList<String> allCollege = new ArrayList<String>();
        try(Connection conn = dataSource.getConnection();) {
            String sqlStr = "SELECT Tcollege FROM teacher";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                String college = rst.getString("Tcollege");
                if(!allCollege.contains(college)) {
                    allCollege.add(college);
                }
            }
            return allCollege;
        }catch (SQLException se) {
            System.out.println(se);
            return null;
        }
    }
    //学院获取专业
    public ArrayList<String> GetMajor(String college) {
        ArrayList<String> allMajor = new ArrayList<String>();
        String sqlStr = "SELECT Smajor FROM student WHERE Scollege = '"+college+"'";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        ) {
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                String major = rst.getString("Smajor");
                if(!allMajor.contains(major)) {
                    allMajor.add(major);
                }
            }
            return allMajor;
        }catch (SQLException se) {
            System.out.println(se);
            return null;
        }
    }
    //专业获取班级
    public ArrayList<String> GetClass(String major) {
        ArrayList<String> allClass = new ArrayList<String>();
        String sqlStr = "SELECT Sclass FROM student WHERE Smajor = '"+major+"'";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);) {
            System.out.println(sqlStr);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                String Class = rst.getString("Sclass");
                if(!allClass.contains(Class)) {
                    allClass.add(Class);
                }
            }
            return allClass;
        }catch (SQLException se) {
            System.out.println(se);
            return null;
        }
    }
}