package com.control;

import com.dao.AdminDao;
import com.dao.DataDao;
import com.model.DateCheck;
import com.model.Student;
import com.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete.do")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String identity = request.getParameter("identity");
        AdminDao dao = new AdminDao();
        DataDao dao2 = new DataDao();
        String message = null;
        PrintWriter out = response.getWriter();
        if(identity.equals("student")) {
            String Sid = request.getParameter("Sid");
            if(dao.DeleteStudent(Sid)) {
                Student stu = dao.findStudent(Sid, "", "").get(0);
                Date date = new Date();
                DateCheck d = dao2.GetDataByDate(date);
                if(stu.getStoday() == 1) {
                    d.setSfinish(d.getSfinish() - 1);
                    String health = stu.getShealth();
                    if(health.equals("red")) {
                        d.setSred(d.getSred() - 1);
                    }
                    else if(health.equals("yellow")) {
                        d.setSyellow(d.getSyellow() - 1);
                    }
                    else if(health.equals("green")) {
                        d.setSgreen(d.getSgreen() - 1);
                    }
                }
                d.setSsum(d.getSsum() - 1);
                if(dao2.UpdateData(d)) {
                    message = "删除成功!";
                    out.print(message);
                }
                else {
                    message = "汇总数据删除失败!";
                    out.print(message);
                }
            }
            else {
                message = "删除失败!";
                out.print(message);
            }
        }
        else {
            String Tid = request.getParameter("Tid");
            String Trole = request.getParameter("Trole");
            if(Trole.equals("系统管理员")) {
                message = "没有权限!";
                out.print(message);
            }
            else if(Trole.equals("校级管理员") || Trole.equals("院级管理员")){
                if(dao.DeleteAdmin(Tid) && dao.DeleteTeacher(Tid)) {
                    Teacher tea = dao.findTeacher(Tid, "", "").get(0);
                    Date date = new Date();
                    DateCheck d = dao2.GetDataByDate(date);
                    if(tea.getTtoday() == 1) {
                        d.setTfinish(d.getTfinish() - 1);
                        String health = tea.getThealth();
                        if(health.equals("red")) {
                            d.setTred(d.getTred() - 1);
                        }
                        else if(health.equals("yellow")) {
                            d.setTyellow(d.getTyellow() - 1);
                        }
                        else if(health.equals("green")) {
                            d.setTgreen(d.getTgreen() - 1);
                        }
                    }
                    d.setTsum(d.getTsum() - 1);
                    if(dao2.UpdateData(d)) {
                        message = "删除成功!";
                        out.print(message);
                    }
                    else {
                        message = "汇总数据删除失败!";
                        out.print(message);
                    }
                }
                else {
                    message = "删除失败!";
                    out.print(message);
                }
            }
            else if(Trole.equals("普通教师")){
                if(dao.DeleteTeacher(Tid)) {
                    Teacher tea = dao.findTeacher(Tid, "", "").get(0);
                    Date date = new Date();
                    DateCheck d = dao2.GetDataByDate(date);
                    if(tea.getTtoday() == 1) {
                        d.setTfinish(d.getTfinish() - 1);
                        String health = tea.getThealth();
                        if(health.equals("red")) {
                            d.setTred(d.getTred() - 1);
                        }
                        else if(health.equals("yellow")) {
                            d.setTyellow(d.getTyellow() - 1);
                        }
                        else if(health.equals("green")) {
                            d.setTgreen(d.getTgreen() - 1);
                        }
                    }
                    d.setTsum(d.getTsum() - 1);
                    if(dao2.UpdateData(d)) {
                        message = "删除成功!";
                        out.print(message);
                    }
                    else {
                        message = "汇总数据删除失败!";
                        out.print(message);
                    }
                }
                else {
                    message = "删除失败!";
                    out.print(message);
                }
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
