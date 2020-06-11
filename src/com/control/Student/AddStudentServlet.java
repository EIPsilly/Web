package com.control.Student;

import com.dao.AdminDao;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/Admin/toStudent/AddStudent.do"})
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Student student = new Student();
        student.setSid(request.getParameter("Sid"));
        student.setSidcard(request.getParameter("Sgid"));
        student.setSname(request.getParameter("Sname"));
        student.setScollege(request.getParameter("Scollege"));
        student.setSmajor(request.getParameter("Smajor"));
        student.setSclass(request.getParameter("Sclass"));
        AdminDao dao = new AdminDao();
        String message = null;
        if(dao.AddStudent(student)) {
            message = "<li>添加成功！</li>";
        }
        else {
            message = "<li>添加失败</li>";
        }
        request.setAttribute("result", message);
        getServletContext().getRequestDispatcher("/AddStudent.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
