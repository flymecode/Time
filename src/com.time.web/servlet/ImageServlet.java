package com.time.web.servlet;

import com.time.web.base.BaseServlet;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
@WebServlet("/imageServlet")
public class ImageServlet extends BaseServlet {

    public String ProImage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BufferedImage bufferedImage = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        Color color = new Color(255,255,255);
        graphics.setColor(color);
        graphics.fillRect(0,0,75,250);
        char[] ch = "ABCEDFIOSJEMKLP".toCharArray();
        Random random = new Random();
        int length = ch.length;
        int index ;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(length);
            graphics.setColor(new Color(random.nextInt(88),random.nextInt(55),random.nextInt(123)));
            graphics.drawString(ch[index]+"",(i*15)+3,22);
            sb.append(ch[index]);
        }
        req.getSession().setAttribute("piccode",sb.toString());
        ImageIO.write(bufferedImage,"JPG",resp.getOutputStream());
        return null;

    }
}
