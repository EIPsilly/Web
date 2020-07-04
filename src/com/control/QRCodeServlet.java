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

import static com.model.QRCode.QREncode;

@WebServlet(name = "QRCodeServlet", urlPatterns = "/QRCode.do")
public class QRCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = null;
        String Sno = null;

        int color = 0xFF0080;

        Date now = Calendar.getInstance().getTime();
        BufferedImage code = QREncode(new SimpleDateFormat("MM-dd HH:mm:ss").format(now) + "\n" + name + "\n" + Sno,color);

        String path = this.getServletContext().getRealPath("/images/QRcode") + "\\";
        String filename = Sno +"-"+ new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss-SSS").format(now) + ".jpeg";
        File outputfile = new File(path+filename);
        ImageIO.write(code,"jpeg",outputfile);

        System.out.println(filename);
        HttpSession session = request.getSession();
        session.setAttribute("codename",filename);
        response.sendRedirect("/ShowQRCode.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
