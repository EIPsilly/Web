package com.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.dao.AdminDao;
import com.dao.DataDao;
import com.model.Student;
import com.model.Teacher;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String identity = request.getParameter("identity");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        System.out.println(identity);
        System.out.println(id);
        System.out.println(password);
        AdminDao dao = new AdminDao();
//            DataDao dao2 = new DataDao();
//            Date date = new Date();
//            dao2.CreateData(date);
        String message = null;
        if("student".equals(identity)) {
            if(dao.LoginStudent(id,password)) {
                HttpSession session = request.getSession();
                session.setAttribute("identity", "student");
                Student stu = dao.findStudent(id,"","").get(0);
//                    System.out.println(stu.getSid() + " " + stu.getSname() + " " + stu.getSidcard());
                session.setAttribute("id", stu.getSid());
                session.setAttribute("name", stu.getSname());
                session.setAttribute("idcard", stu.getSidcard());
                out.print("control.jsp");
            }
            else {
                out.print("用户名或密码错误");
            }
        }
        else if("admin".equals(identity)) {
            int res = dao.LoginAdmin(id, password);
            if(res == 0) {
                out.print("用户名或密码错误");
            }
            else if(res == 1) { // 院级管理员
                message = "院级管理员";
                HttpSession session = request.getSession();
                session.setAttribute("identity", message);
                Teacher t = dao.findTeacher(id,"","").get(0);
                session.setAttribute("college",t.getTcollege());
                out.print("Administrator.jsp");
            }
            else if(res == 2) { // 校级管理员
                message = "校级管理员";
                HttpSession session = request.getSession();
                session.setAttribute("identity", message);
                out.print("Administrator.jsp");
            }
            else if(res == 3) { // 系统管理员
                message = "系统管理员";
                HttpSession session = request.getSession();
                session.setAttribute("identity", message);
                out.print("Administrator.jsp");
            }
        }
        else if("teacher".equals(identity)) {
            if(dao.LoginTeacher(id,password)) {
                message = "登录成功!";
                HttpSession session = request.getSession();
                session.setAttribute("identity", "teacher");
                Teacher tea = dao.findTeacher(id,"","").get(0);
                session.setAttribute("id", tea.getTid());
                session.setAttribute("name", tea.getTname());
                session.setAttribute("idcard", tea.getTidcard());
                out.print("control.jsp");
            }
            else {
                out.print("用户名或密码错误!");
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
