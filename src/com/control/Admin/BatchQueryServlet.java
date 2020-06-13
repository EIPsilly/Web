package com.control.Admin;

import com.alibaba.fastjson.JSON;
import com.dao.AdminDao;
import com.model.DateCheck;
import com.model.Student;
import com.model.Teacher;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "BatchQueryServlet",urlPatterns = "/batchquery.do")
public class BatchQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        AdminDao dao = new AdminDao();
        String identity = request.getParameter("identity");
        if (identity.equals("student")) {
            String Scollege = request.getParameter("Scollege");
            String Smajor = request.getParameter("Smajor");
            String Sclass = request.getParameter("Sclass");
            Pair<DateCheck, ArrayList<Student>> res = dao.GetDataOnStudent(Scollege,Smajor,Sclass);
            DateCheck tmp1 = res.getKey();
            ArrayList<Student> tmp2 = res.getValue();
            String tmp3 = JSON.toJSONString(tmp1);
            String tmp4 = JSON.toJSONString(tmp2);
            PrintWriter out = response.getWriter();
            System.out.println(tmp3);
            System.out.println(tmp4);
        }
        else {
            String Tcollege = request.getParameter("Tcollege");
            Pair<DateCheck, ArrayList<Teacher>> res = dao.GetDataOnTeacher(Tcollege);
            DateCheck tmp1 = res.getKey();
            ArrayList<Teacher> tmp2 = res.getValue();
            String tmp3 = JSON.toJSONString(tmp1);
            String tmp4 = JSON.toJSONString(tmp2);
            PrintWriter out = response.getWriter();
            System.out.println(tmp3);
            System.out.println(tmp4);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
