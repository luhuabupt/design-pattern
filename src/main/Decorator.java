package main;

/**
 * 装饰器模式
 * <p>
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，相比生成子类更为灵活
 * 独立增加核心功能，独立增加附加功能，二者互不影响
 */
public class Decorator {

    public interface TextNode {
        void setText(String text);

        String getText();
    }

    public class SpanNode implements TextNode {
        private String text;

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return "<span>" + text + "</span>";
        }
    }

    public abstract class NodeDecorator implements TextNode {
        protected final TextNode target;

        protected NodeDecorator(TextNode target) {
            this.target = target;
        }

        public void setText(String text) {
            this.target.setText(text);
        }
    }

    public class BoldDecorator extends NodeDecorator {
        public BoldDecorator(TextNode target) {
            super(target);
        }

        public String getText() {
            return "<b>" + target.getText() + "</b>";
        }
    }

    public class ItalicDecorator extends NodeDecorator {
        public ItalicDecorator(TextNode target) {
            super(target);
        }

        public String getText() {
            return "<u>" + target.getText() + "</u>";
        }
    }
}
