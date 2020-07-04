package com.control;
import com.dao.AdminDao;
import com.model.Student;
import com.model.Teacher;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.model.QRCode.QREncode;

@WebServlet(name = "QRCodeServlet", urlPatterns = "/QRCode.do")
public class QRCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String idcard = request.getParameter("idcard");
        String identity = request.getParameter("identity");
        AdminDao dao = new AdminDao();
        String health = null;
        int today = 0;
        if(identity.equals("student")) {
            Student stu = dao.findStudent(id, name, idcard).get(0);
            health = stu.getShealth();
            today = stu.getStoday();
        }
        else if(identity.equals("teacher")){
            Teacher tea = dao.findTeacher(id, name, idcard).get(0);
            health = tea.getThealth();
            today = tea.getTtoday();
        }
        HttpSession session = request.getSession();
        if(health.equals("null")) {
            session.setAttribute("message","未申报！");
            return;
        }
        else if(today == 0) {
            session.setAttribute("message", "今日未打卡！");
            return;
        }
        int color = 0xFF008000;
        if(health.equals("red")) {
            color = 0xFFFF0000;
        }
        else if(health.equals("yellow")) {
            color = 0xFFFFFF00;
        }
        Date now = Calendar.getInstance().getTime();
        BufferedImage code = QREncode(new SimpleDateFormat("MM-dd HH:mm:ss").format(now) + "\n" + name + "\n" + idcard,color);
        String path = this.getServletContext().getRealPath("/images/QRcode") + "\\";
        String filename = idcard +"-"+ new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss-SSS").format(now) + ".jpeg";
        File outputfile = new File(path+filename);
        ImageIO.write(code,"jpeg",outputfile);
        System.out.println(filename);

        session.setAttribute("codename",filename);

        response.sendRedirect("/ShowQRCode.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
