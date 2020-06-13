package com.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.AdminDao;
import com.model.Student;
import com.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
@MultipartConfig(location = "C:\\Users\\29615\\IdeaProjects\\WEB\\HealthyCode\\web\\test",fileSizeThreshold = 1024)
@WebServlet(name = "AddServlet", urlPatterns = "/add.do")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Part p = request.getPart("JsonFile");
        if(p.getSize() > 1024*1024){    // 上传的文件不能超过1MB大小
            p.delete();
            HttpSession session = request.getSession();
            session.setAttribute("result", "文件太大");
            request.getRequestDispatcher("/#").forward(request, response);
        }
        else {
            String str = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
            String line = "";
            while((line = br.readLine()) != null) {
                str += line;
            }
            System.out.println(str);
            br.close();
            JSONArray jArray = JSONArray.parseArray(str);
            String identify = request.getParameter("idetity");
//            String identify = "student";
            String res = "[";
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
                    if(dao.AddStudent(stu)) {
                        continue;
                    } else {
                        if("[".equals(res)) {
                            res = res + i;
                        }
                        else res = res + "," + i;
                    }
                }
                else {
                    Teacher teacher = new Teacher();
                    teacher.setTid(obj.getString("tid"));
                    teacher.setTname(obj.getString("tname"));
                    teacher.setTidcard(obj.getString("tidcard"));
                    teacher.setTcollege(obj.getString("tcollege"));
                    teacher.setTrole(obj.getString("trole"));
                    teacher.setThealth(obj.getString("thealth"));
                    teacher.setTdate(obj.getInteger("tdate"));
                    teacher.setTtoday(obj.getInteger("ttoday"));
                    if(dao.AddTeacher(teacher)) {
                        continue;
                    } else {
                        if("[".equals(res)) {
                            res = res + i;
                        }
                        else res = res + "," + i;
                    }
                }
            }
            res = res + "]";
            System.out.println(res);
            out.print(res);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
