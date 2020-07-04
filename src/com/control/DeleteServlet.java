package com.control;

import com.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete.do")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String identity = request.getParameter("identity");
        AdminDao dao = new AdminDao();
        String message = null;
        PrintWriter out = response.getWriter();
        if(identity.equals("student")) {
            String Sid = request.getParameter("Sid");
            try{
                if (dao.DeleteStudent(Sid))
                {
                    out.print("删除成功!");
                }
            }
            catch (SQLException e1) {
                System.out.println(e1);
                e1.printStackTrace(out);
                return;
            }
        }
        else if (identity.equals("teacher")){
            String Tid = request.getParameter("Tid");
            String Trole = request.getParameter("Trole");
            if(Trole.equals("系统管理员")) {
                message = "没有权限!";
                out.print(message);
            }
            else {
                if(dao.DeleteAdmin(Tid) && dao.DeleteTeacher(Tid)) {
                    message = "删除成功!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                }
                else {
                    message = "删除失败!";
                    HttpSession session = request.getSession();
                    session.setAttribute("result", message);
                }
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
