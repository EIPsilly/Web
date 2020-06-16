package com.control;

import com.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete.do")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String identity = request.getParameter("identity");
        AdminDao dao = new AdminDao();
        String message = null;
        if(identity.equals("student")) {
            String Sid = request.getParameter("Sid");
            if(dao.DeleteStudent(Sid)) {
                message = "删除成功!";
                HttpSession session = request.getSession();
                session.setAttribute("result", message);
                request.getRequestDispatcher("/test.jsp").forward(request, response);
            }
            else {
                message = "删除失败!";
                HttpSession session = request.getSession();
                session.setAttribute("result", message);
                request.getRequestDispatcher("/test.jsp").forward(request, response);
            }
        }
        else {
            String Tid = request.getParameter("Tid");
            String Trole = request.getParameter("Trole");
            if(Trole.equals("系统管理员")) {
                message = "没有权限!";
                HttpSession session = request.getSession();
                session.setAttribute("result", message);
                request.getRequestDispatcher("/test.jsp").forward(request, response);
            }
            else {
                if(dao.DeleteAdmin(Tid) && dao.DeleteTeacher(Tid)) {
                    message = "删除成功!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                    request.getRequestDispatcher("/test.jsp").forward(request, response);
                }
                else {
                    message = "删除失败!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                    request.getRequestDispatcher("/test.jsp").forward(request, response);
                }
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
