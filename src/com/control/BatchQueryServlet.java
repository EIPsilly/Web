package com.control;

import com.alibaba.fastjson.JSON;
import com.dao.AdminDao;
import com.dao.DataDao;
import com.model.DateCheck;
import com.model.Student;
import com.model.Teacher;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "BatchQueryServlet",urlPatterns = "/batchquery.do")
public class BatchQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        DataDao dao = new DataDao();
        String identity = request.getParameter("identity");
        HttpSession sesson = request.getSession();
        if (identity.equals("student")) {
            String Scollege = null;
            if(((String)sesson.getAttribute("identity")).equals("院级管理员")) {
                Scollege = (String)sesson.getAttribute("college");
            }
            else {
                Scollege = request.getParameter("Scollege");
            }
            String Smajor = request.getParameter("Smajor");
            String Sclass = request.getParameter("Sclass");
            Pair<DateCheck, ArrayList<Student>> res = dao.GetDataOnStudent(Scollege,Smajor,Sclass);
            DateCheck tmp1 = res.getKey();
            ArrayList<Student> tmp2 = res.getValue();
            String tmp3 = JSON.toJSONString(tmp1);
            String tmp4 = JSON.toJSONString(tmp2);
            PrintWriter out = response.getWriter();
            System.out.println(tmp3);
            System.out.println(tmp4);
            out.print("[" + tmp3 + ",");
            out.print(tmp4 + "]");
        }
        else if (identity.equals("teacher")) {
            String Tcollege = null;
            if(((String)sesson.getAttribute("identity")).equals("院级管理员")) {
                Tcollege = (String)sesson.getAttribute("college");
            }
            else {
                Tcollege = request.getParameter("Tcollege");
            }
            Pair<DateCheck, ArrayList<Teacher>> res = dao.GetDataOnTeacher(Tcollege);
            DateCheck tmp1 = res.getKey();
            ArrayList<Teacher> tmp2 = res.getValue();
            String tmp3 = JSON.toJSONString(tmp1);
            String tmp4 = JSON.toJSONString(tmp2);
            PrintWriter out = response.getWriter();
            System.out.println(tmp3);
            System.out.println(tmp4);
            out.print("[" + tmp3 + ",");
            out.print(tmp4 + "]");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
