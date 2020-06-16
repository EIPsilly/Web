package com.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.dao.AdminDao;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String identity = request.getParameter("identity");
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            System.out.println(identity);
            System.out.println(id);
            System.out.println(password);
            AdminDao dao = new AdminDao();
            String message = null;
            if("student".equals(identity)) {
                if(dao.LoginStudent(id,password)) {
                    message = "登录成功!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                    request.getRequestDispatcher("/#").forward(request, response);
                }
                else {
                    message = "学号或密码错误!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                    request.getRequestDispatcher("/#").forward(request, response);
                }
            }
            else if("admin".equals(identity)) {
                int res = dao.LoginAdmin(id, password);
                System.out.println("result=" + res);
                if(res == 0) {
                    message = "工号或密码错误!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                    request.getRequestDispatcher("/Login.html").forward(request, response);
                }
                else if(res == 1) { // 院级管理员
                    message = "院级管理员";
                    HttpSession session = request.getSession();
                    session.setAttribute("identity", message);
                    request.getRequestDispatcher("/Administrator.jsp").forward(request, response);
                }
                else if(res == 2) { // 校级管理员
                    message = "校级管理员";
                    HttpSession session = request.getSession();
                    session.setAttribute("identity", message);
                    request.getRequestDispatcher("/Administrator.jsp").forward(request, response);
                }
                else if(res == 3) { // 系统管理员
                    message = "系统管理员";
                    HttpSession session = request.getSession();
                    session.setAttribute("identity", message);
                    request.getRequestDispatcher("Administrator.jsp").forward(request, response);
                }
            }
            else if("teacher".equals(identity)) {
                if(dao.LoginTeacher(id,password)) {
                    message = "登录成功!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                    request.getRequestDispatcher("/#").forward(request, response);
                }
                else {
                    message = "学号或密码错误!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                    request.getRequestDispatcher("/Login.html").forward(request, response);
                }
            }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
