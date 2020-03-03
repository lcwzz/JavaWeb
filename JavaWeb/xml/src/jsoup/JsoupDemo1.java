package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JsoupDemo1 {
    public static void main(String[] args) throws IOException {
        //获取demo.xml的path
//        String path = "xml/src/demo.xml";
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("demo.xml")).getPath();

        //解析xml文档，加载文档进内存，获取dom树--->Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        //获取所有name元素的Element对象
        Elements elements = document.getElementsByTag("name");
        System.out.println(elements.size());

        //获取第一个name的Element对象
        Element element = elements.get(0);

        //获取数据
        String name = element.text();
        System.out.println(name);
    }
}
