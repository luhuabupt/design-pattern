package main;

import java.io.IOException;
import java.nio.file.Path;

/**
 * 抽象工厂
 * <p>
 * 提供一个创建一系列相关或相互依赖的接口，而无需指定它们具体的类
 * 目的：让创建工厂和一组产品 与使用相分离，并可以随时切换到另一个工厂和另一组产品
 */
public class AbstractFactory {
    public interface MdAbstractFactory {
        public static MdAbstractFactory createFactory(String name) throws Exception {
            if (name.equals("fast")) {
                return new FastFactory();
            } else if (name.equals("good")) {
                return new GoodFactory();
            } else {
                throw new Exception("Invalid factory name");
            }
        }

        HtmlDocument createHtml(String md);

        WordDocument createWord(String md);

        public interface HtmlDocument {
            String toHtml();

            void save(Path path) throws IOException;
        }

        public interface WordDocument {
            void save(Path path) throws IOException;
        }
    }

    public static class FastFactory implements MdAbstractFactory {
        public HtmlDocument createHtml(String md) {
            return new FastHtmlDocument();
        }

        public WordDocument createWord(String md) {
            return new FastWordDocument();
        }

        public static class FastHtmlDocument implements HtmlDocument {
            public String toHtml() {
                return "";
            }

            public void save(Path path) throws IOException {
                // do smt
            }
        }

        public static class FastWordDocument implements WordDocument {
            public void save(Path path) throws IOException {
                // do smt
            }
        }
    }

    public static class GoodFactory implements MdAbstractFactory {
        public HtmlDocument createHtml(String md) {
            return new GoodHtmlDocument();
        }

        public WordDocument createWord(String md) {
            return new GoodWordDocument();
        }

        public static class GoodHtmlDocument implements HtmlDocument {
            public String toHtml() {
                return "";
            }

            public void save(Path path) throws IOException {
                // do smt
            }
        }

        public static class GoodWordDocument implements WordDocument {
            public void save(Path path) throws IOException {
                // do smt
            }
        }
    }

}


