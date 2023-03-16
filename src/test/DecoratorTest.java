package test;

import main.Decorator;

import static main.Decorator.TextNode;

public class DecoratorTest {
    public void test() {
        Decorator decorator = new Decorator();
        TextNode a = decorator.new SpanNode();
        TextNode b = decorator.new BoldDecorator(decorator.new SpanNode());
        TextNode c = decorator.new ItalicDecorator(decorator.new BoldDecorator(decorator.new SpanNode()));

        a.setText("Hello");
        b.setText("Hello");
        c.setText("Hello");

        System.out.println(a.getText());
        System.out.println(b.getText());
        System.out.println(c.getText());
    }
}