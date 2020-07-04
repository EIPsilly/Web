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

@WebServlet(name = "GetCollegeServlet", urlPatterns = "/getCollege.do")
public class GetCollegeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        AdminDao dao = new AdminDao();
        String identity = request.getParameter("identity");
        ArrayList<String> AllCollege = new  ArrayList<String>();
        if(identity.equals("teacher")) {
            AllCollege = dao.GetCollegeForTeacher();
        }
        else if(identity.equals("student")) {
            AllCollege = dao.GetCollegeForStudent();
        }
        String tmp = "[";
        for(String college : AllCollege) {
            if(tmp.equals("[")) {
                tmp += "\"" + college + "\"";
            }
            else {
                tmp = tmp + "," + "\"" + college + "\"";
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
