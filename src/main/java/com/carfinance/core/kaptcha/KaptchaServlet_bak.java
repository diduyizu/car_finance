//package com.carfinance.core.kaptcha;
//
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.Properties;
//
//import javax.imageio.ImageIO;
//import javax.servlet.Servlet;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.code.kaptcha.Producer;
//import com.google.code.kaptcha.util.Config;
//
//public class KaptchaServlet_bak extends HttpServlet implements Servlet{
//
//	private static final long serialVersionUID = 2789898596375221351L;
//	private Properties props = new Properties();
//    private Producer kaptchaProducer = null;
//    private String sessionKeyValue = null;
//    private String sessionKeyDateValue = null;
//
//    @Override
//    public void init(ServletConfig conf) throws ServletException {
//        super.init(conf);
//        ImageIO.setUseCache(false);
//        Enumeration<?> initParams = conf.getInitParameterNames();
//        while (initParams.hasMoreElements()) {
//            String key = (String) initParams.nextElement();
//            String value = conf.getInitParameter(key);
//            this.props.put(key, value);
//        }
//        Config config = new Config(this.props);
//        this.kaptchaProducer = config.getProducerImpl();
//        this.sessionKeyValue = config.getSessionKey();
//        this.sessionKeyDateValue = config.getSessionDate();
//    }
//
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setHeader("Cache-Control", "no-store, no-cache");
//        resp.setContentType("image/jpeg");
//        String capText = this.kaptchaProducer.createText();
//        req.getSession().setAttribute(this.sessionKeyValue, capText);
//        req.getSession().setAttribute(this.sessionKeyDateValue, new Date());
//        BufferedImage bi = this.kaptchaProducer.createImage(capText);
//        ServletOutputStream out = resp.getOutputStream();
//        this.log("VALID>>sessionid="+ req.getSession().getId()+","+this.sessionKeyValue+"="+capText+","+this.sessionKeyDateValue+"="+ new Date());
//        ImageIO.write(bi, "jpg", out);
//    }
//}
