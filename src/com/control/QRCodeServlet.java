package com.control;
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

//import static model.QRCode.QREncode;

@WebServlet(name = "QRCodeServlet", urlPatterns = "/qrCode.do")
public class QRCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String IDcard = request.getParameter("IDcard");
        String Sno = request.getParameter("Sno");
        String Phone = request.getParameter("phone");
        String Leave = request.getParameter("Leave");
        String Abroad = request.getParameter("abroad");
        String Contact = request.getParameter("contact");
        String Diagnosis = request.getParameter("diagnosis");
        String[] HealthyStatus = request.getParameterValues("HealthStatus");

        int color = 0xFF008000;
        if (Contact.equals("Yes") || (HealthyStatus != null && HealthyStatus.length >= 2) || Diagnosis.equals("Yes")) color = 0xFFFF0000;
        else if (Leave.equals("Yes") || Abroad.equals("Yes") || (HealthyStatus != null && HealthyStatus.length == 1)) color = 0xFFFFFF00;
        Date now = Calendar.getInstance().getTime();
     //   BufferedImage code = QREncode(new SimpleDateFormat("MM-dd HH:mm:ss").format(now) + "\n" + name + "\n" + Sno,color);
        String path = this.getServletContext().getRealPath("/images") + "\\";
        String filename = IDcard +"-"+ new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss-SSS").format(now) + ".jpeg";
        File outputfile = new File(path+filename);
//        ImageIO.write(code,"jpeg",outputfile);
        System.out.println(filename);

        HttpSession session = request.getSession();
        session.setAttribute("code",filename);
        session.setAttribute("name",name);
        response.sendRedirect("/show.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
