package com.control;

import com.dao.AdminDao;
import com.dao.DataDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetMajorServlet", urlPatterns = "/getmajor.do")
public class GetMajorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String college = request.getParameter("Scollege");
        System.out.println(college);
        DataDao dao = new DataDao();
        ArrayList<String> allMajor = dao.GetMajor(college);
        String tmp = "[";
        for(String major : allMajor) {
            if(tmp.equals("[")) {
                tmp += "\"" + major + "\"";
            }
            else {
                tmp = tmp + "," + "\"" + major + "\"";
            }
        }
        tmp += "]";
        System.out.println(tmp);
        response.getWriter().print(tmp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
