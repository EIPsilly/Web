package com.control;

import com.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetClassServlet",urlPatterns = "/getClass.do")
public class GetClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String major = request.getParameter("Smajor");
        System.out.println(major);
        AdminDao dao = new AdminDao();
        ArrayList<String> allClass = dao.GetClass(major);
        String tmp = "[";
        for(String Class : allClass) {
            if(tmp.equals("[")) {
                tmp += Class;
            }
            else {
                tmp = tmp + "," + Class;
            }
        }
        tmp += "]";
        System.out.println(tmp);
        PrintWriter out = response.getWriter();
        out.print(tmp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
