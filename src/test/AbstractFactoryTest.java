package test;

import main.AbstractFactory;
import org.junit.Test;

import java.nio.file.Paths;

public class AbstractFactoryTest {
    @Test
    public void test() throws Exception {
        AbstractFactory.MdAbstractFactory factory = AbstractFactory.MdAbstractFactory.createFactory("good");

        AbstractFactory.MdAbstractFactory.HtmlDocument html = factory.createHtml("</p>");
        html.save(Paths.get(".", "h.html"));

        AbstractFactory.MdAbstractFactory.WordDocument word = factory.createWord("abc");
        word.save(Paths.get(".", "w.doc"));
    }
}