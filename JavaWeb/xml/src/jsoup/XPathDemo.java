package jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class XPathDemo {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        //获取demo.xml的path
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("demo.xml")).getPath();

        //获取Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        //根据document对象，创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);

        //结合xpath语法查询
        //例1：查询所有user标签
        List<JXNode> jxNodes = jxDocument.selN("//user");
        for (JXNode jxNode : jxNodes) {
            System.out.println(jxNode);
        }
        System.out.println("--------------------");

        //例2：查询所有user标签下的name标签
        List<JXNode> jxNodes2 = jxDocument.selN("//user/name");
        for (JXNode jxNode : jxNodes2) {
            System.out.println(jxNode);
        }
        System.out.println("--------------------");

        //例3：查询user标签下带有id属性的name标签
        List<JXNode> jxNodes3 = jxDocument.selN("//user/name[@id]");
        for (JXNode jxNode : jxNodes3) {
            System.out.println(jxNode);
        }
        System.out.println("--------------------");

        //例4：查询user标签下带有id属性的name标签, 并且id属性值为10
        List<JXNode> jxNodes4 = jxDocument.selN("//user/name[@id='10']");
        for (JXNode jxNode : jxNodes4) {
            System.out.println(jxNode);
        }
    }
}
