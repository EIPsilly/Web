package com.control.Student;

import com.dao.AdminDao;
import com.model.Student;

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

@WebServlet({"/Admin/toStudent/AddmoreStudent.do"})
@MultipartConfig(location="C:\\Users\\29615\\IdeaProjects\\WEB\\Dao-nx\\web\\WEB-INF\\txt",fileSizeThreshold=1024*1024)
public class AddmoreStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Part p = request.getPart("file");
        String message = "<li>添加成功！</li>";
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        AdminDao dao = new AdminDao();
        while((line = br.readLine()) != null) {
            line.replaceAll(" +"," ");
            String []str = line.split(" ");
            Student student = new Student();
            student.setSid(str[0]);
            student.setSname(str[1]);
            student.setSidcard(str[2]);
            student.setScollege(str[3]);
            student.setSmajor(str[4]);
            student.setSclass(str[5]);
            if(!dao.AddStudent(student)) {
                message = "<li>添加失败</li>";
                break;
            }
        }
        request.setAttribute("result", message);
        getServletContext().getRequestDispatcher("/AddmoreStudent.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
