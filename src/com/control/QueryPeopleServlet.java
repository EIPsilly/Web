package com.control;

import com.alibaba.fastjson.JSON;
import com.dao.AdminDao;
import com.model.Student;
import com.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "QueryPeopleServlet", urlPatterns = "/queryPeople.do")
public class QueryPeopleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String identity = request.getParameter("identity");
        AdminDao dao = new AdminDao();
        if("student".equals(identity)) {
            String Sid = request.getParameter("Sid");
            String Sname = request.getParameter("Sname");
            String Sidcard = request.getParameter("Sidcard");
            ArrayList<Student> stulist = dao.findStudent(Sid,Sname,Sidcard);
            String tmp = JSON.toJSONString(stulist);
            System.out.println(tmp);
            PrintWriter out = response.getWriter();
            out.print(tmp);
        }
        else {
            String Tid = request.getParameter("Tid");
            String Tname = request.getParameter("Tname");
            String Tidcard = request.getParameter("Tidcard");
            ArrayList<Teacher> tealist = dao.findTeacher(Tid,Tname,Tidcard);
            String tmp = JSON.toJSONString(tealist);
            System.out.println(tmp);
            PrintWriter out = response.getWriter();
            out.print(tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
