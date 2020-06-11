package com.control.Teacher;

import com.dao.AdminDao;
import com.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/Admin/toTeacher/SearchTeacher.do"})
public class SearchTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String Tname = request.getParameter("Tname");
        AdminDao dao = new AdminDao();
        ArrayList<Teacher> teachers = dao.findTeacher(null,Tname,null);
        request.setAttribute("teacher", teachers);
        getServletContext().getRequestDispatcher("/showTeacher.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
