package servlet;

import utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取文件服务器路径
        String filename = req.getParameter("filename");
        ServletContext servletContext = this.getServletContext();
        String path = servletContext.getRealPath("/resource/" + filename);

        //2.指定响应头，保证点击超链接出现下载弹框
        //设置MimeType
        String mimeType = servletContext.getMimeType(filename);
        resp.setContentType(mimeType);
        //解决中文文件名问题
        filename = DownLoadUtils.getFileName(req.getHeader("user-agent"), filename);
        //设置附件形式打开
        resp.setHeader("content-disposition", "attachment;filename="+filename);

        //3.字节输入流加载文件
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path));
        //4.将数据写出到response输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
