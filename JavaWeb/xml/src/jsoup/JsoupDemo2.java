package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JsoupDemo2 {
    public static void main(String[] args) throws IOException {
        //获取demo.xml的path
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("demo.xml")).getPath();

        //解析xml文档，加载文档进内存，获取dom树--->Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        //通过Document对象获取所有的name标签
        Elements elements = document.getElementsByTag("name");
        System.out.println(elements.size());
        System.out.println("----------------");

        //通过Element对象获取子标签对象
        Element element_user = document.getElementsByTag("user").get(0);
        Elements ele_name = element_user.getElementsByTag("name");
        System.out.println(ele_name.size());
        System.out.println("----------------");

        //获取user对象的属性值
        String number = element_user.attr("id");
        System.out.println(number);
        System.out.println("------------");

        //获取文本内容
        String text = ele_name.text();
        String html = ele_name.html();
        System.out.println(text);
        System.out.println("------------");
        System.out.println(html);
    }
}
