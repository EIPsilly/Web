package com.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dao.AdminDao;
import com.model.Student;
import com.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
@MultipartConfig(location = "C:\\temp",fileSizeThreshold = 1024)
@WebServlet(name = "AddServlet", urlPatterns = "/add.do")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Part p = request.getPart("JsonFile");
        System.out.println(p.getName());
        String str = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
        String line = "";
        while((line = br.readLine()) != null) {
            str += line;
        }
        System.out.println(str);
        br.close();
        JSONArray jArray = null;
        try {
            jArray = JSONArray.parseArray(str);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            e.printStackTrace(out);
            return;
        }
        String identify = request.getParameter("identity");
        AdminDao dao = new AdminDao();
        for (int i = 0; i < jArray.size(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            if ("student".equals(identify)) {
                Student stu = new Student();
                stu.setSid(obj.getString("sid"));
                stu.setSname(obj.getString("sname"));
                stu.setSidcard(obj.getString("sidcard"));
                stu.setScollege(obj.getString("scollege"));
                stu.setSmajor(obj.getString("smajor"));
                stu.setSclass(obj.getString("sclass"));
                stu.setShealth(obj.getString("shealth"));
                stu.setSdate(obj.getInteger("sdate"));
                stu.setStoday(obj.getInteger("stoday"));
                try{
                    dao.AddStudent(stu);
                }
                catch (SQLException se) {
                    se.printStackTrace();
                    se.printStackTrace(out);
                    return;
                }
            }
            else if ("teacher".equals(identify))
            {
                Teacher teacher = new Teacher();
                teacher.setTid(obj.getString("tid"));
                teacher.setTname(obj.getString("tname"));
                teacher.setTidcard(obj.getString("tidcard"));
                teacher.setTcollege(obj.getString("tcollege"));
                teacher.setTrole(obj.getString("trole"));
                teacher.setThealth(obj.getString("thealth"));
                teacher.setTdate(obj.getInteger("tdate"));
                teacher.setTtoday(obj.getInteger("ttoday"));
                try{
                    dao.AddTeacher(teacher);
                }
                catch (SQLException se) {
                    se.printStackTrace();
                    se.printStackTrace(out);
                    return;
                }
            }
        }
        out.print("ok");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
