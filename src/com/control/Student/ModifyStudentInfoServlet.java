package com.control.Student;

import com.dao.AdminDao;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/Admin/toStudent/modifyStudentInfo.do"})
public class ModifyStudentInfoServlet extends HttpServlet {
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
        String message = "";
        if(dao.ModifyStudent(student)) {
            message="信息修改成功！";
        }
        else {
            message="信息修改失败！";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/ModifyStudent.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
