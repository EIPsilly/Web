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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "RecordStudentServlet", urlPatterns = "/record.do")
public class RecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String identity = request.getParameter("identity");
        String name = request.getParameter("name");
        String IDcard = request.getParameter("IDcard");
        String Sno = request.getParameter("Sno");
        String Phone = request.getParameter("phone");
        String Leave = request.getParameter("Leave");//是否去过疫区
        String Abroad = request.getParameter("abroad");//是否去过国外
        String Contact = request.getParameter("contact");//是否接触
        String Diagnosis = request.getParameter("diagnosis");//确诊
        String[] HealthyStatus = request.getParameterValues("HealthStatus");//状况
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String color = "green";
        if (Contact.equals("Yes") || (HealthyStatus != null && HealthyStatus.length >= 2) || Diagnosis.equals("Yes")) color = "red";
        else if (Leave.equals("Yes") || Abroad.equals("Yes") || (HealthyStatus != null && HealthyStatus.length == 1)) color = "yellow";
        if(identity.equals("student")) {
            String id = request.getParameter("IDcard");
            AdminDao dao = new AdminDao();
            DataDao dao2 = new DataDao();
            ArrayList<Student> stulist = dao.findStudent(Sno,name,IDcard);
            Student stu = stulist.get(0);
            if(stu.getStoday() == 1) {//已打卡
                String message = "已打卡！";
                out.print(message);
                return;
            }
            else {
                stu.setStoday(1);
            }
            if(stu.getShealth().equals("null")) {
                stu.setShealth(color);
                stu.setSdate((color.equals("green") ? 1 : 0));
            }
            else if(color.equals("green")) {
                if(stu.getShealth().equals("green")) {
                    stu.setSdate(stu.getSdate() + 1);
                }
                else if(stu.getShealth().equals("yellow")){
                    if(stu.getSdate() == 6) {
                        stu.setShealth("green");
                        stu.setSdate(0);//重新计数
                    }
                    else {
                        stu.setSdate(stu.getSdate() + 1);
                    }
                }
                else if(stu.getShealth().equals("red")){
                    if(stu.getSdate() == 6) {
                        stu.setShealth("yellow");
                        stu.setSdate(0);//重新计数
                    }
                    else {
                        stu.setSdate(stu.getSdate() + 1);
                    }
                }
            }
            else if(color.equals("red")) {
                stu.setSdate(0);
                stu.setShealth("red");
            }
            else if(color.equals("yellow")) {
                stu.setSdate(0);
                if(stu.getShealth().equals("green")){
                    stu.setShealth("yellow");
                }
            }
            if(dao.ModifyStudent(stu)) {
                color = stu.getShealth();
                Date d = new Date();
                DateCheck today = dao2.GetDataByDate(d);
                today.setSfinish(today.getTfinish() + 1);
                if(color.equals("green")) {
                    today.setSgreen(today.getSgreen() + 1);
                }
                else if(color.equals("yellow")) {
                    today.setSyellow(today.getSyellow() + 1);
                }
                else if(color.equals("red")) {
                    today.setSred(today.getSred() + 1);
                }
                if(dao2.UpdateData(today)) {
                    out.print("修改成功");
                }
                else {
                    out.print("总数据修改失败");
                }
            }
            else {
                out.print("个人数据修改失败");
            }
        }
        else if(identity.equals("teacher")) {
            String id = request.getParameter("IDcard");
            AdminDao dao = new AdminDao();
            DataDao dao2 = new DataDao();
            ArrayList<Teacher> tealist = dao.findTeacher(Sno,name,IDcard);
            Teacher tea= tealist.get(0);
            if(tea.getTtoday() == 1) {//已打卡
                String message = "已打卡！";
                out.print(message);
            }
            else {
                tea.setTtoday(1);
            }
            if(tea.getThealth().equals("null")) {
                tea.setThealth(color);
                tea.setTdate((color.equals("green") ? 1 : 0));
            }
            else if(color.equals("green")) {
                if(tea.getThealth().equals("green")) {
                    tea.setTdate(tea.getTdate() + 1);
                }
                else if(tea.getThealth().equals("yellow")){
                    if(tea.getTdate() == 6) {
                        tea.setThealth("green");
                        tea.setTdate(0);//重新计数
                    }
                    else {
                        tea.setTdate(tea.getTdate() + 1);
                    }
                }
                else if(tea.getThealth().equals("red")){
                    if(tea.getTdate() == 6) {
                        tea.setThealth("yellow");
                        tea.setTdate(0);//重新计数
                    }
                    else {
                        tea.setTdate(tea.getTdate() + 1);
                    }
                }
            }
            else if(color.equals("red")) {
                tea.setTdate(0);
                tea.setThealth("red");
            }
            else if(color.equals("yellow")) {
                tea.setTdate(0);
                if(tea.getThealth().equals("green")){
                    tea.setThealth("yellow");
                }
            }
            if(dao.ModifyTeacher(tea)) {
                Date d = new Date();
                DateCheck today = dao2.GetDataByDate(d);
                today.setTfinish(today.getTfinish() + 1);
                if(color.equals("green")) {
                    today.setTgreen(today.getTgreen() + 1);
                }
                else if(color.equals("yellow")) {
                    today.setTyellow(today.getTyellow() + 1);
                }
                else if(color.equals("red")) {
                    today.setTred(today.getTred() + 1);
                }
                if(dao2.UpdateData(today)) {
                    out.print("修改成功");
                }
                else {
                    out.print("总数据修改失败");
                }
            }
            else {
                out.print("个人数据修改失败");
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
