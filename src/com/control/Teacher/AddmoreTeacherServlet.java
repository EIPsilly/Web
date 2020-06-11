package com.control.Teacher;

import com.dao.AdminDao;
import com.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet({"/Admin/toTeacher/AddmoreTeacher.do"})
@MultipartConfig(location="C:\\Users\\29615\\IdeaProjects\\WEB\\Dao-nx\\web\\WEB-INF\\txt",fileSizeThreshold=1024*1024)
public class AddmoreTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Part p = request.getPart("file");
        String message = "<li>添加成功！</li>";
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
        String line = "";
        AdminDao dao = new AdminDao();
        while((line = br.readLine()) != null) {
            line.replaceAll(" +"," ");
            String []str = line.split(" ");
            Teacher teacher = new Teacher();
            teacher.setTid(str[0]);
            teacher.setTname(str[1]);
            teacher.setTcollege(str[2]);
            teacher.setTrole(str[3]);
            System.out.println(teacher.getTname());
            if(!dao.AddTeacher(teacher)) {
                message = "<li>添加失败</li>";
                break;
            }
        }
        System.out.println(message);
        request.setAttribute("result", message);
        getServletContext().getRequestDispatcher("/AddmoreTeacher.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
