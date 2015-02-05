package com.carfinance.core.kaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class KaptchaServlet extends HttpServlet
{

    private static final long serialVersionUID = 4745835569705949574L;

    private static final char[] CHARS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'}; //设置验证码字符
    private static Random random = new Random();
    /**
     * 获取四位随机验证码
     * @return
     */
    private static String getRandomString()
    {
        StringBuffer buffer = new StringBuffer();
        for(int i=1;i<=4;i++)
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        return buffer.toString();
    }
    //随机获取颜色
    private static Color getRandomColor()
    {
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }
    private static Color getReverseColor(Color c)
    {
        return new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("image/jpeg");
        String randomString = getRandomString();
        req.getSession(true).setAttribute("random", randomString);

        int width = 100;
        int height = 30;

        Color color = getRandomColor();
        Color reverse = getReverseColor(color);

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(reverse);
        g.drawString(randomString, 18, 20);
        for(int i=0,n=random.nextInt(100);i<n;i++)
            g.drawRect(random.nextInt(width), random.nextInt(height),1,1);

        ServletOutputStream out = resp.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bi);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
