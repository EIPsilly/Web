package com.control.Student;

import com.dao.AdminDao;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/Admin/toStudent/SearchStudent.do"})
public class SearchStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String Sname = request.getParameter("Sname");
        AdminDao dao = new AdminDao();
        ArrayList<Student> students = dao.findStudent(null,Sname,null);
        request.setAttribute("student", students);
        getServletContext().getRequestDispatcher("/showStudent.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
