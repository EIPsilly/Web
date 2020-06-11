package com.control.Teacher;

import com.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/Admin/toTeacher/modifyTeacher.do"})
public class ModifyTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Teacher teacher = new Teacher();
        teacher.setTid(request.getParameter("Tid"));
        teacher.setTname(request.getParameter("Tname"));
        teacher.setTcollege(request.getParameter("Tcollege"));
        teacher.setTrole(request.getParameter("Trole"));
        request.setAttribute("Teacher", teacher);
        getServletContext().getRequestDispatcher("/ModifyTeacher.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
