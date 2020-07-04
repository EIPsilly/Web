package com.Listener;

import com.dao.DataDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Start implements ServletContextListener {
    public void contextInitialized(ServletContextEvent e) {
        long daySpan = 24 * 60 * 60 * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        // 首次运行时间
        try {
            Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
            // 如果今天的已经过了 首次运行时间就改为明天
            if (System.currentTimeMillis() > startTime.getTime()){
                startTime = new Date(startTime.getTime() + daySpan);
            }
            Timer t1 = new Timer();
            // 以每24小时执行一次
            t1.schedule(new reset(), startTime, daySpan);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Timer t2 = new Timer();
        t2.schedule(new set(),new Date(), 1000 * 60 * 30L);
    }
    public void contextDestroyed(ServletContextEvent e) {

    }
    class set extends TimerTask {
        @Override
        public void run() {
            System.out.println("t2");
            DataDao dao = new DataDao();
            Date date = new Date();
            dao.CreateData(date);
        }
    }

    class reset extends TimerTask {
        @Override
        public void run() {
            System.out.println("t1");
            DataDao dao = new DataDao();
            dao.Reset();
        }
    }

}