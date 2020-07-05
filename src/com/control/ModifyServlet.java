package com.control;

import com.dao.AdminDao;
import com.model.Student;
import com.model.Teacher;
import com.model.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ModifyServlet", urlPatterns = "/modify.do")
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String identity = request.getParameter("identity");
        AdminDao dao = new AdminDao();
        PrintWriter out = response.getWriter();
        String message = null;
        //管理员更改密码
        if("admin".equals(identity)) {
            String initiative = request.getParameter("initiative");
            String passive = request.getParameter("passive");
            System.out.println("密码修改");
            if(((initiative.equals("院级管理员") || initiative.equals("校级管理员")) && passive.equals("系统管理员")) ||
                    (initiative.equals("校级管理员") && passive.equals("院级管理员")) || initiative.equals(passive)) {
                message = "没有权限!";
                out.print(message);
            }
            else {
                admin New = new admin();
                New.setAid(request.getParameter("Aid"));
                New.setApassword(request.getParameter("Apassword"));
                if(dao.ChangeAdminPassword(New)) {
                    message = "修改成功!";
                    out.print(message);
                }
                else {
                    message = "修改失败!";
                    out.print(message);
                }
            }
        }
        //更改学生信息
        else if("student".equals(identity)) {
            Student New = new Student();
            New.setSid(request.getParameter("Sid"));
            Student last = dao.findStudent(New.getSid(), "", "").get(0);
            New.setSname(request.getParameter("Sname"));
            New.setSidcard(request.getParameter("Sidcard"));
            New.setScollege(request.getParameter("Scollege"));
            New.setSmajor(request.getParameter("Smajor"));
            New.setSclass(request.getParameter("Sclass"));
            New.setStoday(last.getStoday());
            New.setShealth(last.getShealth());
            New.setSdate(last.getSdate());
            if(dao.ModifyStudent(New)) {
                message = "修改成功!";
                out.print(message);
            }
            else {
                message = "修改失败!";
                out.print(message);
            }
        }
        //更改教师信息
        else if("teacher".equals(identity)) {
            Teacher New = new Teacher();
            New.setTid(request.getParameter("Tid"));
            Teacher last = dao.findTeacher(New.getTid(),"","").get(0);
            New.setTname(request.getParameter("Tname"));
            New.setTidcard(request.getParameter("Tidcard"));
            New.setTcollege(request.getParameter("Tcollege"));
            New.setTrole(last.getTrole());
            New.setTtoday(last.getTtoday());
            New.setThealth(last.getThealth());
            New.setTdate(last.getTdate());
            if(dao.ModifyTeacher(New)) {
                System.out.println("1");
                message = "修改成功!";
                out.print(message);
            }
            else {
                System.out.println("wu l");
                message = "修改失败!";
                out.print(message);
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
