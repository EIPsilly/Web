package com.control.Admin;

import com.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/Admin/loginAdmin.do"})
public class LoginAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Aid = request.getParameter("Aid");
        String Apassword = request.getParameter("Apassword");
        AdminDao dao = new AdminDao();
        if(dao.LoginAdmin(Aid,Apassword) != 0) {
            HttpSession session = request.getSession(true);
            session.setAttribute("infor","success");
            System.out.println("success");
            response.sendRedirect("Admin/Manage.jsp");
        }
        else {
            request.setAttribute("infor","id或密码错误！");
            getServletContext().getRequestDispatcher("/AdminLogin.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
