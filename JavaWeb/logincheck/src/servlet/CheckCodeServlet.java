package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 创建图片对象
        int width = 100;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2. 美化图片
        //设置背景
        Graphics g = image.getGraphics(); //画笔对象
        g.setColor(Color.PINK); //设置画笔颜色
        g.fillRect(0, 0, width, height); //填充
        //设置边框
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width-1, height-1);
        //随机生成字符
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 1; i <= 4 ; i++) {
            int index = random.nextInt(characters.length());
            char c = characters.charAt(index);
            stringBuilder.append(c);
            g.drawString(c + "", width/5*i, height/2);
        }
        //因为验证码和登陆是两次请求，共享数据需要session
        String sessionCheckCode = stringBuilder.toString();
        req.getSession().setAttribute("sessionCheckCode", sessionCheckCode);

        //画干扰线
        g.setColor(Color.GREEN);
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);

            g.drawLine(x1, y1, x2, y2);
        }

        //3. 输出图片
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
