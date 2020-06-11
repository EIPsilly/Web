package com.control.Student;

import com.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/Admin/toStudent/DeleteStudent.do"})
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String message = null;
        String Sid=request.getParameter("Sid");
        AdminDao dao = new AdminDao();
        boolean success = dao.DeleteStudent(Sid);
        if (success) {
            message = "删除成功！";
        } else {
            message = "删除失败！";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
